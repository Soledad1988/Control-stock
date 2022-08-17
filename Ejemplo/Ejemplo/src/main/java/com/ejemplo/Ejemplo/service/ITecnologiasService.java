
package com.ejemplo.Ejemplo.service;

import com.ejemplo.Ejemplo.model.Tecnologias;
import java.util.List;


public interface ITecnologiasService {
    public List<Tecnologias> verTecnologias();
    public void crearTecnologias (Tecnologias tecno);
    public void borraTecnologias(Long id);
    public Tecnologias buscarTecnologias(Long id);
    public void actualizar(Tecnologias tecno);
    
}
