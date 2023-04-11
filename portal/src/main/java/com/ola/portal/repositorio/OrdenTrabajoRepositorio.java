
package com.ola.portal.repositorio;

import com.ola.portal.entidad.OrdenTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenTrabajoRepositorio extends JpaRepository<OrdenTrabajo, Long> {
    
}
