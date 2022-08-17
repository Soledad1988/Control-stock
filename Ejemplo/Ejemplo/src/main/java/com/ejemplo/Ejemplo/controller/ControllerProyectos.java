
package com.ejemplo.Ejemplo.controller;

import com.ejemplo.Ejemplo.model.Proyectos;
import com.ejemplo.Ejemplo.service.IProyectosService;
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
@RequestMapping("/proyectos")
public class ControllerProyectos {
    
    @Autowired 
    private IProyectosService proyeServ;
    
    @PostMapping("")
    public void agregaProyectos(@RequestBody Proyectos proyecto){
        //listaPersonas.add(pers);
        proyeServ.crearProyectos(proyecto);
    }
    
    @GetMapping("")//trabajo/ver
    @ResponseBody
    public List<Proyectos> verExpLaboral (){
       // return listaPersonas;
       return proyeServ.verProyectos();
    }
    
    @DeleteMapping("/{id}")
    public void borrarExpLaboral(@PathVariable Long id){
        proyeServ.borrarProyectos(id);
    }
    
    //obtiene un estudiante
   @GetMapping("/{id}")
    public Proyectos get(@PathVariable("id") Long id){
       // return listaPersonas;
       return proyeServ.buscarProyectos(id);
    }
    
    //actualizar
    @PutMapping("")
    public void actualizaProyectos(@RequestBody Proyectos proyecto){
       proyeServ.actualizar(proyecto);
    }
    
    
}
