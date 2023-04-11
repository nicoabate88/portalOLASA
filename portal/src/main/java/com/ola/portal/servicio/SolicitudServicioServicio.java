
package com.ola.portal.servicio;

import com.ola.portal.entidad.Admin;
import com.ola.portal.entidad.Cliente;
import com.ola.portal.entidad.SolicitudServicio;
import com.ola.portal.excepcion.MiException;
import com.ola.portal.repositorio.AdminRepositorio;

import com.ola.portal.repositorio.ClienteRepositorio;
import com.ola.portal.repositorio.SolicitudServicioRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolicitudServicioServicio {
    
    @Autowired
    private SolicitudServicioRepositorio solicitudRepositorio;
    @Autowired
    private ClienteRepositorio clienteRepositorio;
    @Autowired
    private AdminRepositorio adminRepositorio;
    
    @Transactional
    public void crearSolicitud(String tipoServicio, String observacion, Long idCliente, Long idUsuario) throws MiException{
        
        validar(tipoServicio, observacion, idCliente);
        
        Cliente cliente = new Cliente();
        Optional<Cliente> cte = clienteRepositorio.findById(idCliente);
        if(cte.isPresent()){
            cliente = cte.get();
        }
        Admin usuario = new Admin();
        Optional<Admin> user = adminRepositorio.findById(idUsuario);
        if(user.isPresent()){
            usuario = user.get();
        }
       
        SolicitudServicio solicitud = new SolicitudServicio();
        
        solicitud.setTipoServicio(tipoServicio);
        solicitud.setObservacion(observacion);
        solicitud.setCliente(cliente);
        solicitud.setUsuario(usuario);
        
        solicitud.setFecha(new Date());
        solicitud.setEstado("pendiente");
        
        solicitudRepositorio.save(solicitud);    
    }
    
    public ArrayList<SolicitudServicio> buscarSolicitudes(){
        
        ArrayList<SolicitudServicio> lista = new ArrayList();
        
        lista = (ArrayList<SolicitudServicio>) solicitudRepositorio.findAll();
        
        return lista;
    }
    
    public SolicitudServicio buscarSolicitud(Long id){
        
        return solicitudRepositorio.getById(id);
    }
    
    @Transactional
    public void modificarSolicitud(Long id, String tipoServicio, String observacion, String estado) throws MiException{
        
        validar(tipoServicio, observacion, id);
            
        Optional<SolicitudServicio> respuesta = solicitudRepositorio.findById(id);
        
        if(respuesta.isPresent()){
            
            SolicitudServicio solicitud = respuesta.get();
            
            solicitud.setTipoServicio(tipoServicio);
            solicitud.setObservacion(observacion);
            solicitud.setEstado(estado);
            
            solicitudRepositorio.save(solicitud);
        
        }
    }
    
    @Transactional
    public void eliminarSolicitud(Long id) throws MiException{
        
        Optional<SolicitudServicio> respuesta = solicitudRepositorio.findById(id);
        
        if(respuesta.isPresent()){
            
            solicitudRepositorio.deleteById(id);
        }
        
    }
    
    public boolean validarModificarSolicitud(Long id){
        
         SolicitudServicio solicitudS = solicitudRepositorio.findById(id).get();

         boolean flag = false;
         
         if(solicitudS.getEstado().equals("ejecutado")){
            flag = true;
        }
         
         return flag;
    }
    
    private void validar(String tipoServicio, String observacion, Long idCliente) throws MiException{
        
        if(tipoServicio.isEmpty()){
            throw new MiException("Tipo de Servicio no puede estar vacío");
        }
        if(observacion.isEmpty()){
            throw new MiException("Observación no puede estar vacío");
        }
        if(idCliente == null){
            throw new MiException("Debe seleccionar un Cliente");
        }
    }
    
    
    
}
