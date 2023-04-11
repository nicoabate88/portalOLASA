
package com.ola.portal.repositorio;

import com.ola.portal.entidad.SolicitudServicio;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitudServicioRepositorio extends JpaRepository<SolicitudServicio, Long> {
    
    @Query("SELECT s FROM SolicitudServicio s WHERE cliente_id = :id")
    public ArrayList<SolicitudServicio> buscarSolicitudIdCte(@Param("id") Long id);
    
}
