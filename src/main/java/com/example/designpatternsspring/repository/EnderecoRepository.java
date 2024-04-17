package com.example.designpatternsspring.repository;

import com.example.designpatternsspring.model.Endereco;
import org.springframework.data.repository.CrudRepository;

public interface EnderecoRepository extends CrudRepository<Endereco, String> {
}
