package io.fiap.erp.util;

import com.pgvector.PGvector;
import io.fiap.erp.model.Embedding;
import io.fiap.erp.model.ItemEstoque;
import io.fiap.erp.repository.EmbeddingRepository;
import io.fiap.erp.repository.ItemEstoqueRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DataLoader {

    @Autowired
    private VectorStore vectorStore;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ItemEstoqueRepository itemEstoqueRepository;
    @Autowired
    private EmbeddingRepository embeddingRepository;

    @PostConstruct
    public void init() {
        int count = 0;
        System.out.println("🗃️  Contagem de registros no Vector store = " + count);

        if (count == 0) {
            System.out.println("📥 Carregando dados a partir da tabela item_estoque do banco de dados MySQL...");

            List<Document> documentos = carregarItensEstoqueComoDocumentos();

            vectorStore.add(documentos);

            System.out.println("✅ Dados de estoque carregados em vector store.");
        } else {
            System.out.println("✅ O vector store já contém dados.");
        }
    }

    private List<Document> carregarItensEstoqueComoDocumentos() {
        List<Document> documents = new ArrayList<>();
        List<ItemEstoque> itens = itemEstoqueRepository.findAll();
        itens.forEach(item -> {
            String descricao = item.getDescricao();
            Long quantidadeAtual = item.getQuantidadeAtual();
            Long quantidadeIdeal = item.getQuantidadeIdeal();
            Long id = item.getId();

            String text = "Item: " + descricao + "\nQuantidade Atual: " + quantidadeAtual + "\nQuantidade Ideal: " + quantidadeIdeal;
            Map<String, Object> metadata = new HashMap<>();
            metadata.put("id", id);
            metadata.put("descricao", descricao);
            metadata.put("quantidadeAtual", quantidadeAtual);
            metadata.put("quantidadeIdeal", quantidadeIdeal);
            documents.add(new Document(text, metadata));
        });
        return documents;
    }
}