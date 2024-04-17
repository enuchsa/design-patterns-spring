package com.example.designpatternsspring.repository;

import com.example.designpatternsspring.model.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
}
