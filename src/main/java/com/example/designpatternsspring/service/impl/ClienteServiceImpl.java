package com.example.designpatternsspring.service.impl;

import com.example.designpatternsspring.model.Cliente;
import com.example.designpatternsspring.model.Endereco;
import com.example.designpatternsspring.repository.ClienteRepository;
import com.example.designpatternsspring.repository.EnderecoRepository;
import com.example.designpatternsspring.service.ClienteService;
import com.example.designpatternsspring.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ClienteServiceImpl implements ClienteService {
    // TODO: SINGLETON injetar os componentes do Spring com @Autowired
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;
    // TODO: STRATEGY implementar os métodos definidos na interface
    // TODO: FACADE abstrair integrações com subsistemas, proveno uma inteface simples

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void inserir(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente clienteParameter) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            String cep = cliente.get().getEndereco().getCep();
            Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
                Endereco novoEndereco = viaCepService.consultarCep(cep);
                enderecoRepository.save(novoEndereco);
                return novoEndereco;
            });
            cliente.get().setEndereco(endereco);
            clienteRepository.save(cliente.get());
        }
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }
}
