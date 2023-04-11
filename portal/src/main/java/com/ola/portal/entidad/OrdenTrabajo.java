
package com.ola.portal.entidad;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class OrdenTrabajo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipoServicio;
    private String observacion;
    private String cliente;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @OneToOne
    private SolicitudServicio solicitud;
    @OneToOne
    private Admin usuario;

    public OrdenTrabajo() {
    }

    public OrdenTrabajo(Long id, String tipoServicio, String observacion, String cliente, Date fecha, SolicitudServicio solicitud, Admin usuario) {
        this.id = id;
        this.tipoServicio = tipoServicio;
        this.observacion = observacion;
        this.cliente = cliente;
        this.fecha = fecha;
        this.solicitud = solicitud;
        this.usuario = usuario;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Admin getUsuario() {
        return usuario;
    }

    public void setUsuario(Admin usuario) {
        this.usuario = usuario;
    }

    public SolicitudServicio getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(SolicitudServicio solicitud) {
        this.solicitud = solicitud;
    }

   
    
    
    
    
    
}
