
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
public class SolicitudServicio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipoServicio;
    private String observacion;
    private String estado;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @OneToOne
    private Admin usuario;
    @OneToOne
    private Cliente cliente;

    public SolicitudServicio() {
    }

    public SolicitudServicio(Long id, String tipoServicio, String observacion, String estado, Date fecha, Admin usuario, Cliente cliente) {
        this.id = id;
        this.tipoServicio = tipoServicio;
        this.observacion = observacion;
        this.estado = estado;
        this.fecha = fecha;
        this.usuario = usuario;
        this.cliente = cliente;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
    
    
    
    
    
    
}
