
package com.ejemplo.Ejemplo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter

@Entity
public class Tecnologias {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre_herramienta;
    private String nivel;

    public Tecnologias() {
    }

    public Tecnologias(Long id, String nombre_herramienta, String nivel) {
        this.id = id;
        this.nombre_herramienta = nombre_herramienta;
        this.nivel = nivel;
    }
    
}
