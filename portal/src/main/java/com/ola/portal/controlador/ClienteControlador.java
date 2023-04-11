
package com.ola.portal.controlador;

import com.ola.portal.entidad.Cliente;
import com.ola.portal.excepcion.MiException;
import com.ola.portal.servicio.ClienteServicio;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cliente")
@PreAuthorize("hasAnyRole('ROLE_administrativo', 'ROLE_directivo')")
public class ClienteControlador {
    
    @Autowired
    private ClienteServicio clienteServicio;
    
    @GetMapping("/registrar")
    public String registrar(){
        
        return "clienteRegistrar.html";
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, @RequestParam String dni, 
            @RequestParam String telefono, @RequestParam String email, @RequestParam String observacion, ModelMap modelo){
        
        try {
            
            clienteServicio.crearCliente(nombre, dni, telefono, email, observacion);
            modelo.put("exito", "Cliente registrado exitosamente");
            return "clienteRegistrar.html";
            
        } catch (MiException ex) {
           modelo.put("error", ex.getMessage());
           return "clienteRegistrar.html";
        }
    }
    
    @GetMapping("/listar")
    public String listar(ModelMap modelo){
        
        ArrayList<Cliente> listaClientes = clienteServicio.buscarClientes();
        
        modelo.addAttribute("clientes", listaClientes);
        
        return "clienteLista.html";
    }
    
    @GetMapping("/mostrar/{id}")
    public String mostrar(@PathVariable Long id, ModelMap modelo){
        
        modelo.put("cliente", clienteServicio.buscarCliente(id));
        
        return "clienteEliminar.html";
    }
    
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable Long id, ModelMap modelo){
        
        modelo.put("cliente", clienteServicio.buscarCliente(id));
        
        return "clienteModificar.html";
    }
    
    @PostMapping("/modifica/{id}")
    public String modifica(@RequestParam Long id, @RequestParam String nombre, @RequestParam String dni, 
            @RequestParam String telefono, @RequestParam String email, String observacion, ModelMap modelo){
        
        try {
            clienteServicio.modificarCliente(id, nombre, dni, telefono, email, observacion);
            modelo.put("exito", "Cliente modificado exitosamente");
            modelo.put("cliente", clienteServicio.buscarCliente(id));
            return "clienteModificado.html";
            
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("cliente", clienteServicio.buscarCliente(id));
            return "clienteNoModificado.html"; 
        }
    }
    
    @GetMapping("/elimina/{id}")
    public String elimina(@PathVariable Long id, ModelMap modelo){
        
        try {
            clienteServicio.eliminarCliente(id);
            modelo.put("exito", "Cliente eliminado exitosamente");
            return "clienteEliminado.html";
            
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "clienteNoEliminado";
        }             
        
            }
}
