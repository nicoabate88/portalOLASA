
package com.ola.portal.servicio;

import com.ola.portal.entidad.Admin;
import com.ola.portal.excepcion.MiException;
import com.ola.portal.repositorio.AdminRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class AdminServicio implements UserDetailsService {
    
    @Autowired
    private AdminRepositorio adminRepositorio;
    
    @Transactional
    public void crearUsuario(String nombre, String dni, String telefono, String email, String usuario, String password, String password2, String rol) throws MiException{
        
        validar(nombre, dni, telefono, email, usuario, password, password2);
        
        Admin admin = new Admin();
        
        admin.setNombre(nombre);
        admin.setDni(dni);
        admin.setTelefono(telefono);
        admin.setEmail(email);
        admin.setUsuario(usuario);
        admin.setPassword(new BCryptPasswordEncoder().encode(password));
        admin.setRol(rol);
        admin.setFechaAlta(new Date());
        
        adminRepositorio.save(admin);
        
    }
    
    private void validar(String nombre, String dni, String telefono, String email, String usuario, String password, String password2) throws MiException{
        
        if(nombre.isEmpty()){
            throw new MiException("El Nombre de Usuario no puede estar vacío");
        }
        if(dni.isEmpty()){
            throw new MiException("El DNI de Usuario no puede estar vacío");
        }
        if(telefono.isEmpty()){
            throw new MiException("El Número de Teléfono no puede estar vacío");
        }
        if(email.isEmpty()){
            throw new MiException("El Email no puede estar vacío");
        }
        if(usuario.isEmpty()){
            throw new MiException("El nombre de Usuario no puede estar vacío");
        }
        if(password.isEmpty() || password.length() < 6){
            throw new MiException("La contraseña no puede estar vacía y/o debe tener 6 o más dígitos");
        }
        if(!password.equals(password2)){
            throw new MiException("Las contraseñas ingresadas deben ser iguales");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        
       Admin admin = adminRepositorio.buscarPorUsuario(usuario);
       
       if(admin != null){
           
           List<GrantedAuthority> permisos = new ArrayList();
           
           GrantedAuthority p = new SimpleGrantedAuthority("ROLE_"+admin.getRol());
           
           permisos.add(p);
           
           ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
           
           HttpSession session = attr.getRequest().getSession(true);
           
           session.setAttribute("usuariosession", admin);
           
           return new User(admin.getUsuario(), admin.getPassword(), permisos);
           
       } else{
           
        return null;
    }
    }
    
}
