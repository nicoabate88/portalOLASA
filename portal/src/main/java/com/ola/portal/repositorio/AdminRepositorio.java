
package com.ola.portal.repositorio;

import com.ola.portal.entidad.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepositorio extends JpaRepository<Admin, Long> {
    
    @Query("SELECT u FROM Usuario u WHERE u.usuario = :usuario")
    public Admin buscarPorUsuario(@Param("usuario")String usuario);
    
    @Query("SELECT u FROM Usuario u WHERE u.nombre = :nombre")
    public Admin buscarPorNombre(@Param("nombre")String nombre);
    
}
