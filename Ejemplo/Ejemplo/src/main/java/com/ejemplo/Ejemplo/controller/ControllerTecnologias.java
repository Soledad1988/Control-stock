
/*package com.ejemplo.Ejemplo.controller;

import com.ejemplo.Ejemplo.model.Tecnologias;
import com.ejemplo.Ejemplo.service.ITecnologiasService;
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
@RequestMapping("/tecnologias")
public class ControllerTecnologias {
    
    @Autowired 
    private ITecnologiasService tecnoServ;
    
    @PostMapping("")
    public void agregarTecnologias(@RequestBody Tecnologias tecnologia){
        tecnoServ.crearTecnologias(tecnologia);
    }
    
    @GetMapping("")
    @ResponseBody
    public List<Tecnologias> verTecnologias (){
       return tecnoServ.verTecnologias();
    }
    
    @DeleteMapping("/{id}")
    public void borrarTecnologias(@PathVariable Long id){
        tecnoServ.borraTecnologias(id);
    }
    
    //obtiene un estudiante
   @GetMapping("/{id}")
    public Tecnologias get(@PathVariable("id") Long id){
       return tecnoServ.buscarTecnologias(id);
    }
    
    //actualizar
    @PutMapping("")
    public void actualizarTecnologias(@RequestBody Tecnologias tecnologia){
       tecnoServ.actualizar(tecnologia);
    }
    
}*/
