
package com.ola.portal.entidad;

import java.util.Date;
import javax.persistence.Entity;

@Entity
public class Cliente extends Usuario {
    
    private String observacion;
   
    public Cliente() {
    }

    public Cliente(String observacion, Long id, String nombre, String dni, String telefono, String email, Date fechaAlta) {
        super(id, nombre, dni, telefono, email, fechaAlta);
        this.observacion = observacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

   

   

    
    
    
    
    
}
