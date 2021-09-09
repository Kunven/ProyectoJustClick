package com.example.demo.repository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import com.example.demo.Datos;

public interface Repositorio extends ElasticsearchRepository<Datos, String> {
    
}
