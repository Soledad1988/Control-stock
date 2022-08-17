
package com.ejemplo.Ejemplo.service;

import com.ejemplo.Ejemplo.model.Usuarios;
import com.ejemplo.Ejemplo.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuariosService{
    
    @Autowired
    public UsuariosRepository usuRepo;

    @Override
    public void crearUsuarios(Usuarios usu) {
        usuRepo.save(usu);
    }

    @Override
    public void borrarUsuarios(Long id) {
       usuRepo.deleteById(id);
    }

    @Override
    public Usuarios buscarUsuario(Long id) {
        return usuRepo.findById(id).orElse(null);
    }

    @Override
    public void actualizar(Usuarios usu) {
        usuRepo.save(usu);
    }
    
}
