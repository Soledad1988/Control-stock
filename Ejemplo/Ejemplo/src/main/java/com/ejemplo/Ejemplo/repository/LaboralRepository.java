
package com.ejemplo.Ejemplo.repository;

import com.ejemplo.Ejemplo.model.ExpLaboral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaboralRepository extends JpaRepository <ExpLaboral, Long>{
    
}
