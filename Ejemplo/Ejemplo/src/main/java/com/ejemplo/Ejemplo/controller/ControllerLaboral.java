
package com.ejemplo.Ejemplo.controller;

import com.ejemplo.Ejemplo.model.ExpLaboral;
import com.ejemplo.Ejemplo.service.ILaboralService;
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
@RequestMapping("/trabajo")
public class ControllerLaboral {
    
    @Autowired 
    private ILaboralService workServ;
    
    @PostMapping("") //trabajo/nuevo
    public void agregarPersona(@RequestBody ExpLaboral trabajo){
        //listaPersonas.add(pers);
        workServ.crearExpLaboral(trabajo);
    }
    
    @GetMapping("")//trabajo/ver
    @ResponseBody
    public List<ExpLaboral> verExpLaboral (){
       // return listaPersonas;
       return workServ.verExpLaboral();
    }
    
    @DeleteMapping("/{id}")
    public void borrarExpLaboral(@PathVariable Long id){
        workServ.borrarExpLaboral(id);
    }
    
    //obtiene un estudiante
   @GetMapping("/{id}")
    public ExpLaboral get(@PathVariable("id") Long id){
       // return listaPersonas;
       return workServ.buscarExpLaboral(id);
    }
    
    //actualizar
    @PutMapping("")
    public void actualizarExpLaboral(@RequestBody ExpLaboral trabajo){
       workServ.actualizar(trabajo);
    }
    
}

