
package com.ola.portal.servicio;

import com.ola.portal.entidad.Cliente;
import com.ola.portal.entidad.SolicitudServicio;
import com.ola.portal.excepcion.MiException;
import com.ola.portal.repositorio.ClienteRepositorio;
import com.ola.portal.repositorio.SolicitudServicioRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServicio {
    
    @Autowired
    private ClienteRepositorio clienteRepositorio;
    @Autowired
    private SolicitudServicioRepositorio solicitudRepositorio;
    
    @Transactional
    public void crearCliente(String nombre, String dni, String telefono, String email, String observacion) throws MiException{
        
        validar(nombre, dni, telefono, email);
        
        Cliente cliente = new Cliente();
        
        cliente.setNombre(nombre);
        cliente.setDni(dni);
        cliente.setTelefono(telefono);
        cliente.setEmail(email);
        cliente.setObservacion(observacion);
        cliente.setFechaAlta(new Date());
        
        clienteRepositorio.save(cliente);
        
    }
    
    public ArrayList<Cliente> buscarClientes(){
        
        ArrayList<Cliente> listaClientes = new ArrayList();
        
        listaClientes = (ArrayList<Cliente>) clienteRepositorio.findAll();
        
        return listaClientes;
        
    }
    
    public Cliente buscarCliente(Long id){
        
        return clienteRepositorio.getById(id);
    }
    
    @Transactional
    public void modificarCliente(Long id, String nombre, String dni, String telefono, String email, String observacion) throws MiException{
        
        validar(nombre, dni, telefono, email);
        
        Optional<Cliente> cte = clienteRepositorio.findById(id);
        
        if(cte.isPresent()){
            Cliente cliente = cte.get();
            
            cliente.setNombre(nombre);
            cliente.setDni(dni);
            cliente.setTelefono(telefono);
            cliente.setEmail(email);
            cliente.setObservacion(observacion);
            
            clienteRepositorio.save(cliente);
        } 
    }
    
    @Transactional
    public void eliminarCliente(Long id) throws MiException{
        
        ArrayList<SolicitudServicio> solicitud = solicitudRepositorio.buscarSolicitudIdCte(id);
        
        if(solicitud == null || solicitud.isEmpty()){
            
            clienteRepositorio.deleteById(id);
            
        } else{
            
            throw new MiException("El Cliente no puede ser eliminado porque tiene contratado Solicitud/es de Servicios");
        }
        
    }
    
    
    
    private void validar(String nombre, String dni, String telefono, String email) throws MiException{
        
        if(nombre.isEmpty()){
            throw new MiException("El Nombre del Cliente no puede estar vacío");
        }
         if(dni.isEmpty()){
            throw new MiException("El DNI del Cliente no puede estar vacío");
        } 
         if(telefono.isEmpty()){
            throw new MiException("El Teléfono del Cliente no puede estar vacío");
        }
         if(email.isEmpty()){
            throw new MiException("El Email del Cliente no puede estar vacío");
        } 
         
    }
    
    
    
}
