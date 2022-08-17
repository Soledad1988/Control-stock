
package com.ejemplo.Ejemplo.service;

import com.ejemplo.Ejemplo.model.Persona;
import com.ejemplo.Ejemplo.repository.PersonaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PersonaService implements IPersonaService{
    
    @Autowired
    public PersonaRepository persRepo;

    @Override
    public List<Persona> verPersona() {
        return persRepo.findAll();
    }

    @Override
    public void crearPersona(Persona per) {
        persRepo.save(per);
    }

    @Override
    public void borrarPersona(Long id) {
        persRepo.deleteById(id);
    }

    @Override
    public Persona buscarPersona(Long id) {
        return persRepo.findById(id).orElse(null);
    }

    @Override
    public void actualizar(Persona per) {
        persRepo.save(per);
    }
    
    
}
