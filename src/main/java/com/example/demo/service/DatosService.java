package com.example.demo.service;

import com.example.demo.Datos;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatosService {
    private final Repositorio repository;
    @Autowired
    public DatosService(Repositorio repository){
        this.repository = repository;
    }
    public void save(final Datos datos){
        repository.save(datos);
    }
    public Datos findById(final String id){
        return repository.findById(id).orElse(null);
    }
}
