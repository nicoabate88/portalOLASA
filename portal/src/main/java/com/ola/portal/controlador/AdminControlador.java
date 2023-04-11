
package com.ola.portal.controlador;

import com.ola.portal.excepcion.MiException;
import com.ola.portal.servicio.AdminServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyRole('ROLE_administrativo', 'ROLE_directivo')")
public class AdminControlador {
    
    @Autowired
    private AdminServicio adminServicio;
    
    @GetMapping("/registrar")
    public String registrar(){
        
        return "adminRegistrar.html";
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, @RequestParam String dni, @RequestParam String telefono, @RequestParam String email, 
            @RequestParam String usuario, @RequestParam String password, @RequestParam String password2, @RequestParam String rol, ModelMap modelo){
        
        try {
            adminServicio.crearUsuario(nombre, dni, telefono, email, usuario, password, password2, rol);
            
            modelo.put("exito", "Usuario registrado exitosamente");
            
            return "index.html";
            
        } catch (MiException ex) {
          
            modelo.put("error", ex.getMessage());
            
            return "adminRegistrar.html";
        }
        
    }
}
