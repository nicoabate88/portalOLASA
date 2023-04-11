package com.ola.portal.controlador;

import com.ola.portal.entidad.Admin;
import com.ola.portal.entidad.SolicitudServicio;
import com.ola.portal.excepcion.MiException;
import com.ola.portal.servicio.ClienteServicio;
import com.ola.portal.servicio.SolicitudServicioServicio;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
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
@RequestMapping("/solicitud")
@PreAuthorize("hasAnyRole('ROLE_administrativo', 'ROLE_directivo')")
public class SolicitudServicioControlador {
    
    @Autowired
    private SolicitudServicioServicio solicitudServicio;
    
    @Autowired
    private ClienteServicio clienteServicio;
    
    @GetMapping("/registrar")
    public String registrar(HttpSession session, ModelMap modelo){
        
        Admin logueado = (Admin) session.getAttribute("usuariosession");
        
        modelo.addAttribute("usuario", logueado);
        modelo.put("clientes", clienteServicio.buscarClientes());
        
        return "solicitudRegistrar.html";
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam String tipoServicio, @RequestParam String observacion, @RequestParam Long idCliente, @RequestParam Long idUsuario, ModelMap modelo){
        
        try {
            
            solicitudServicio.crearSolicitud(tipoServicio, observacion, idCliente, idUsuario);
            
            modelo.put("exito", "Solicitud de Servicio registrada exitosamente");
            return "index.html";
            
        } catch (MiException ex) {
        
            modelo.put("error", ex.getMessage());
            
            return "solicitudRegistrar.html";
        }
    }
    
    @GetMapping("/listar")
    public String listar(ModelMap modelo){
        
        ArrayList<SolicitudServicio> listaSolicitud = solicitudServicio.buscarSolicitudes();
        
        modelo.addAttribute("solicitudes", listaSolicitud);
        
        return "solicitudLista.html";
    }
    
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable Long id, ModelMap modelo) throws MiException{
        
    boolean flag = solicitudServicio.validarModificarSolicitud(id);
        
    if(flag==true){
        modelo.put("error", "Solicitud de Servicio no puede ser MODIFICADA porque ha sido ejecutada");
        
        return "solicitudNoModificada.html";
        
    } else {
    
        modelo.put("solicitud", solicitudServicio.buscarSolicitud(id));
        modelo.put("clientes", clienteServicio.buscarClientes());
        
        return "solicitudModificar.html";
    
}
    }
    
    @PostMapping("/modifica/{id}")
    public String modifica(@RequestParam Long id, @RequestParam String tipoServicio, @RequestParam String observacion, 
            @RequestParam String estado, ModelMap modelo){
        
        try {
            solicitudServicio.modificarSolicitud(id, tipoServicio, observacion, estado);
            modelo.put("exito", "Solicitud de Servicio modificado exitosamente");
            modelo.put("solicitud", solicitudServicio.buscarSolicitud(id));
            
            return "solicitudModificada.html";
            
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("solicitud", solicitudServicio.buscarSolicitud(id));
            
            return "solicitudNoModificadaPost.html";
        }   
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, ModelMap modelo) throws MiException{
     
        boolean flag = solicitudServicio.validarModificarSolicitud(id);
        
        if(flag == true){
            
            modelo.put("error", "Solicitud de Servicio no puede ser eliminada porque ha sido ejecutada");
            
              return "solicitudNoEliminada.html";
        }else {
            
           modelo.put("solicitud", solicitudServicio.buscarSolicitud(id));
        
            return "solicitudEliminar.html";
        }
    }
    
    @GetMapping("/elimina/{id}")
    public String elimina(@PathVariable Long id, ModelMap modelo) throws MiException{
        
        solicitudServicio.eliminarSolicitud(id);
        modelo.put("exito", "Solicitud de Servicio eliminada exitosamente");
        return "solicitudEliminada.html";
    }

}