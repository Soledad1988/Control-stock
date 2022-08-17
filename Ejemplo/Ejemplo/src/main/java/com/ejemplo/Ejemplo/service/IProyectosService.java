
package com.ejemplo.Ejemplo.service;

import com.ejemplo.Ejemplo.model.Proyectos;
import java.util.List;


public interface IProyectosService {
    public List<Proyectos> verProyectos();
    public void crearProyectos (Proyectos proye);
    public void borrarProyectos(Long id);
    public Proyectos buscarProyectos(Long id);
    public void actualizar(Proyectos proye);
    
}
