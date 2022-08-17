
package com.ejemplo.Ejemplo.controller;

import com.ejemplo.Ejemplo.model.Persona;
import com.ejemplo.Ejemplo.service.IPersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/persona")
public class ControllerPersona {

    @Autowired 
    private IPersonaService persoServ;
    
    @PostMapping("")
    public void agregarPersona(@RequestBody Persona persona){
        persoServ.crearPersona(persona);
    }
    
    @GetMapping("")
    @ResponseBody
    public List<Persona> verPersona (){
       return persoServ.verPersona();
    }
    
     //obtiene
   @GetMapping("/{id}")
    public Persona get(@PathVariable("id") Long id){
       // return listaPersonas;
       return persoServ.buscarPersona(id);
    }
    
    @DeleteMapping("/{id}")
     void borrarPersona(@PathVariable Long id){
        persoServ.borrarPersona(id);
    }
    
    
   //actualizar no anda
   @PutMapping("")
    public void actualizarPersona(@RequestBody Persona persona){
        persoServ.actualizar(persona);
    }
    
}
