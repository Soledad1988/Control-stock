
package com.ejemplo.Ejemplo.controller;

import com.ejemplo.Ejemplo.model.Educacion;
import com.ejemplo.Ejemplo.service.IEducacionService;
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
@RequestMapping("/educacion")
public class ControlerEducacion {
    
    @Autowired 
    private IEducacionService eduServ;
    
    @PostMapping("")
    public void agregaEducacion(@RequestBody Educacion educacion){
        eduServ.crearEducacion(educacion);
    }
    
    @GetMapping("")
    @ResponseBody
    public List<Educacion> verEducacion (){
       // return listaPersonas;
       return eduServ.verEducacion();
    }
    
    @DeleteMapping("/{id}")
    public void borrarEducacion(@PathVariable Long id){
        eduServ.borrarEducacion(id);
    }
    
    //obtiene un estudiante
   @GetMapping("/{id}")
    public Educacion get(@PathVariable("id") Long id){
       // return listaPersonas;
       return eduServ.buscarEducacion(id);
    }
    
    //actualizar
    @PutMapping("")
    public void actualizarEducacion(@RequestBody Educacion educacion){
       eduServ.actualizar(educacion);
    }
    
}
