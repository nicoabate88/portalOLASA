
package com.ola.portal.repositorio;

import com.ola.portal.entidad.ServicioEjecutado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioEjecutadoRepositorio extends JpaRepository<ServicioEjecutado, Long> {
    
}
