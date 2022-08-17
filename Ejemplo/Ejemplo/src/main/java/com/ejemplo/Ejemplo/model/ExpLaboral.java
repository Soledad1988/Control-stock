
package com.ejemplo.Ejemplo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class ExpLaboral {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String empresa;
    private String descripcion;
    private String fecha_desde;
    private String fecha_hasta;
    private String imagen;

    public ExpLaboral() {
    }

    public ExpLaboral(Long id, String empresa, String descripcion, String fecha_desde, String fecha_hasta, String imagen) {
        this.id = id;
        this.empresa = empresa;
        this.descripcion = descripcion;
        this.fecha_desde = fecha_desde;
        this.fecha_hasta = fecha_hasta;
        this.imagen = imagen;
    }
    
}
