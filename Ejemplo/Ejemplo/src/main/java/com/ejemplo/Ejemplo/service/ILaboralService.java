
package com.ejemplo.Ejemplo.service;

import com.ejemplo.Ejemplo.model.ExpLaboral;
import java.util.List;


public interface ILaboralService {

    public List<ExpLaboral> verExpLaboral();
    public void crearExpLaboral (ExpLaboral trab);
    public void borrarExpLaboral(Long id);
    public ExpLaboral buscarExpLaboral(Long id);
    public void actualizar(ExpLaboral trab);
}
    
