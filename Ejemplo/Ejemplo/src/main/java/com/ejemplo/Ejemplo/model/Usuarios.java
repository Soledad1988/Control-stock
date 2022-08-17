
package com.ejemplo.Ejemplo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String correo_electronico;
    private String contraseña;

    public Usuarios() {
    }

    public Usuarios(Long id, String correo_electronico, String contraseña) {
        this.id = id;
        this.correo_electronico = correo_electronico;
        this.contraseña = contraseña;
    }
    
    
    
}
