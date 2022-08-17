
package com.ejemplo.Ejemplo.repository;

import com.ejemplo.Ejemplo.model.Proyectos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyectosRepository extends JpaRepository <Proyectos, Long>{
    
}
