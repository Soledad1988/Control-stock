
package com.ejemplo.Ejemplo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Proyectos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titulo;
    private String descripcion;
    private String institucion;
    private String año;
    private String imagen;

    public Proyectos() {
    }

    public Proyectos(Long id, String titulo, String descripcion, String institucion, String año, String imagen) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.institucion = institucion;
        this.año = año;
        this.imagen = imagen;
    }

    
    
}
