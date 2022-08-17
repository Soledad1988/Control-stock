
package com.ejemplo.Ejemplo.service;

import com.ejemplo.Ejemplo.model.Persona;
import java.util.List;


public interface IPersonaService {
    public List<Persona> verPersona();
    public void crearPersona (Persona per);
    public void borrarPersona(Long id);
    public Persona buscarPersona(Long id);
    public void actualizar(Persona per);
    
}
