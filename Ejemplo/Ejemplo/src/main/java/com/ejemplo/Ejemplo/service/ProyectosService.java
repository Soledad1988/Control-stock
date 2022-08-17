
package com.ejemplo.Ejemplo.service;

import com.ejemplo.Ejemplo.model.Proyectos;
import com.ejemplo.Ejemplo.repository.ProyectosRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProyectosService implements IProyectosService{
    
    @Autowired
    public ProyectosRepository proyeRepo;

    @Override
    public List<Proyectos> verProyectos() {
        return proyeRepo.findAll();
    }

    @Override
    public void crearProyectos(Proyectos proye) {
        proyeRepo.save(proye);
    }

    @Override
    public void borrarProyectos(Long id) {
        proyeRepo.deleteById(id);
    }

    @Override
    public Proyectos buscarProyectos(Long id) {
        return proyeRepo.findById(id).orElse(null);
    }

    @Override
    public void actualizar(Proyectos proye) {
         proyeRepo.save(proye);
    }
    
}
