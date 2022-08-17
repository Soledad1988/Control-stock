
package com.ejemplo.Ejemplo.repository;

import com.ejemplo.Ejemplo.model.Tecnologias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TecnologiasRepository extends JpaRepository <Tecnologias, Long>{
    
}
