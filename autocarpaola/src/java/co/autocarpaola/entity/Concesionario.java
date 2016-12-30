/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.autocarpaola.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author yo
 */
@Entity
@Table(name = "concesionario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Concesionario.findAll", query = "SELECT c FROM Concesionario c")
    , @NamedQuery(name = "Concesionario.findByNit", query = "SELECT c FROM Concesionario c WHERE c.nit = :nit")
    , @NamedQuery(name = "Concesionario.findByNombre", query = "SELECT c FROM Concesionario c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Concesionario.findByTelefono", query = "SELECT c FROM Concesionario c WHERE c.telefono = :telefono")
    , @NamedQuery(name = "Concesionario.findByDireccion", query = "SELECT c FROM Concesionario c WHERE c.direccion = :direccion")})
public class Concesionario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "nit")
    private Integer nit;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "direccion")
    private String direccion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoConcesionario")
    private List<Vehiculo> vehiculoList;

    public Concesionario() {
    }

    public Concesionario(Integer nit) {
        this.nit = nit;
    }

    public Concesionario(Integer nit, String nombre, String telefono, String direccion) {
        this.nit = nit;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public Integer getNit() {
        return nit;
    }

    public void setNit(Integer nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @XmlTransient
    public List<Vehiculo> getVehiculoList() {
        return vehiculoList;
    }

    public void setVehiculoList(List<Vehiculo> vehiculoList) {
        this.vehiculoList = vehiculoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nit != null ? nit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Concesionario)) {
            return false;
        }
        Concesionario other = (Concesionario) object;
        if ((this.nit == null && other.nit != null) || (this.nit != null && !this.nit.equals(other.nit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.autocarpaola.entity.Concesionario[ nit=" + nit + " ]";
    }
    
}
