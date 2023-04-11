
package com.ola.portal.entidad;

import java.util.Date;
import javax.persistence.Entity;

@Entity
public class Admin extends Usuario {
    
    private String usuario;
    private String password;
    private String rol;

    public Admin() {
    }

    public Admin(String usuario, String password, String rol, Long id, String nombre, String dni, String telefono, String email, Date fechaAlta) {
        super(id, nombre, dni, telefono, email, fechaAlta);
        this.usuario = usuario;
        this.password = password;
        this.rol = rol;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    
    
    
    
    
}
