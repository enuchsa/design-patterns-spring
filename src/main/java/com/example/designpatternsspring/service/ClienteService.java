package com.example.designpatternsspring.service;

import com.example.designpatternsspring.model.Cliente;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Interface que define o padrão STRATEGY no domínio cliete. Com isso se necessário
 * podemos ter multilplas implementações dessa mesma interface.
 */

public interface ClienteService {
    Iterable<Cliente> buscarTodos();
    Cliente buscarPorId(Long id);
    void inserir(Cliente cliente);
    void atualizar(Long id, Cliente cliente);
    void deletar(Long id);
}
