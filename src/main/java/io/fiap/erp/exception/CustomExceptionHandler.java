package io.fiap.erp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = { UsuarioJaExistenteException.class })
    public ResponseEntity<ErrorDTO> handleUsuarioJaExistenteException(UsuarioJaExistenteException exception) {
        return ResponseEntity.badRequest().body(new ErrorDTO(400, "Usuário já existe."));
    }

    @ExceptionHandler(value = { UsuarioNaoExisteException.class })
    public ResponseEntity<ErrorDTO> handleUsuarioNaoExisteException(UsuarioNaoExisteException exception) {
        return ResponseEntity.badRequest().body(new ErrorDTO(400, "Usuário não existe."));
    }

    @ExceptionHandler(value = { PedidoNaoExisteException.class })
    public ResponseEntity<ErrorDTO> handlePedidoNaoExisteException(PedidoNaoExisteException exception) {
        return ResponseEntity.badRequest().body(new ErrorDTO(400, "Pedido não existe."));
    }

    @ExceptionHandler(value = { ProdutoNaoExisteException.class })
    public ResponseEntity<ErrorDTO> handleProdutoNaoExisteException() {
        return ResponseEntity.badRequest().body(new ErrorDTO(400, "Produto não existe."));
    }

    @ExceptionHandler(value = { ProdutosNãoExistentesComTagsException.class })
    public ResponseEntity<ErrorDTO> handleProdutosNãoExistentesComTagsException() {
        return ResponseEntity.badRequest().body(new ErrorDTO(400, "Produtos não existentes com as tags informadas."));
    }

    @ExceptionHandler(value = { ItemEstoqueNaoEncontradoException.class })
    public ResponseEntity<ErrorDTO> handleItemEstoqueNaoEncontradoException() {
        return ResponseEntity.badRequest().body(new ErrorDTO(400, "Item de estoque não encontrado."));
    }

    @ExceptionHandler(value = { UsuarioBloqueadoException.class })
    public ResponseEntity<ErrorDTO> handleUsuarioBloqueadoException() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorDTO(403, "Usuário com conta bloqueada."));
    }

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<ErrorDTO> handleException() {
        return ResponseEntity.internalServerError().body(new ErrorDTO(500, "Erro interno do servidor."));
    }

    @ExceptionHandler(value = { IOException.class })
    public ResponseEntity<ErrorDTO> handleIOException() {
        return ResponseEntity.internalServerError().body(new ErrorDTO(500, "Erro ao exportar relatório."));
    }
}
