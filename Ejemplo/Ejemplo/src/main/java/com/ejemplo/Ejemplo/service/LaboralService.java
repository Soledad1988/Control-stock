
package com.ejemplo.Ejemplo.service;

import com.ejemplo.Ejemplo.model.ExpLaboral;
import com.ejemplo.Ejemplo.repository.LaboralRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LaboralService implements ILaboralService{

 
    @Autowired
    public LaboralRepository workRepo;
    
    @Override
    public List<ExpLaboral> verExpLaboral() {
        return workRepo.findAll();
    }

    @Override
    public void crearExpLaboral(ExpLaboral trab) {
        workRepo.save(trab);
    }

    @Override
    public void borrarExpLaboral(Long id) {
        workRepo.deleteById(id);
    }

    @Override
    public ExpLaboral buscarExpLaboral(Long id) {
        return workRepo.findById(id).orElse(null);
    }


    @Override
    public void actualizar(ExpLaboral trab) {
        workRepo.save(trab);
    }
 
}
