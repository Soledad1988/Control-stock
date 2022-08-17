
package com.ejemplo.Ejemplo.service;

import com.ejemplo.Ejemplo.model.Usuarios;


public interface IUsuariosService {
    public void crearUsuarios (Usuarios usu);
    public void borrarUsuarios(Long id);
    public Usuarios buscarUsuario(Long id);
    public void actualizar(Usuarios usu);
   
}
