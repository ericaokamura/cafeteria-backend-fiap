package io.fiap.erp.util;

import io.fiap.erp.model.ItemEstoque;
import io.fiap.erp.model.Produto;
import io.fiap.erp.repository.ItemEstoqueRepository;
import io.fiap.erp.repository.ProdutoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataLoader {

    @Autowired
    private VectorStore vectorStore;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ItemEstoqueRepository itemEstoqueRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    @PostConstruct
    public void init() {
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM vector_store", Integer.class);
        System.out.println("🗃️  Contagem de registros no Vector store = " + count);

        if (count == 0) {
            System.out.println("📥 Carregando dados a partir da tabela item_estoque do banco de dados PostgreSQL...");

            List<Document> documentos = carregarItensEstoqueComoDocumentos();

            documentos.addAll(carregarProdutosPorTags());

            vectorStore.add(documentos);

            System.out.println("✅ Dados de estoque carregados em vector store.");
        } else {
            System.out.println("✅ O vector store já contém dados.");
        }
    }

    private List<Document> carregarProdutosPorTags() {
        List<Document> documents = new ArrayList<>();
        List<Produto> produtos = produtoRepository.findAll();
        produtos.forEach(produto -> {
            String tags = produto.getTags();
            String descricao = produto.getDescricao();
            Long id = produto.getId();

            String text = "Produto: " + descricao + "\nTags: " + tags;
            Map<String, Object> metadata = new HashMap<>();
            metadata.put("id", id);
            metadata.put("descricao", descricao);
            metadata.put("tags", tags);
            documents.add(new Document(text, metadata));
        });
        return documents;
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