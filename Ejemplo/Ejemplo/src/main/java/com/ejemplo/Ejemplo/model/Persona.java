
package com.ejemplo.Ejemplo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String apellido;
    private String profesion;
    private String sobreMi;
    private String imagen;
    private String pais;
    private String provincia;
    private String correo;

    public Persona() {
    }

    public Persona(Long id, String nombre, String apellido, String profesion, String sobreMi, String imagen, String pais, String provincia, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.profesion = profesion;
        this.sobreMi = sobreMi;
        this.imagen = imagen;
        this.pais = pais;
        this.provincia = provincia;
        this.correo = correo;
    }

    

  

   
    
}
