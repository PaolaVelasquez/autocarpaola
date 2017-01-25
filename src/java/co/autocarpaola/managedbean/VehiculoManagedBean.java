package co.autocarpaola.managedbean;

import co.autocarpaola.entity.Concesionario;
import co.autocarpaola.entity.Vehiculo;
import co.autocarpaola.sessionbeans.ConcesionarioFacadeLocal;
import co.autocarpaola.sessionbeans.VehiculoFacadeLocal;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@Named(value = "vehiculoManagedBean")
@RequestScoped
public class VehiculoManagedBean implements Serializable {
    
    @EJB
    VehiculoFacadeLocal vehiculoEJB;
    
    @EJB
    ConcesionarioFacadeLocal concesionarioEJB;
    
    private Vehiculo vehiculo;
    private String accion;
    private List<Concesionario> listaconcesionario;
    private List<Vehiculo>listaVehiculo;   
    
    @PostConstruct
    public void init() {
       vehiculo = new Vehiculo();
    }
    
    public VehiculoManagedBean() {
    }

    public List<Concesionario> getListaconcesionario() {
        return listaconcesionario;
    }

    public void setListaconcesionario(List<Concesionario> listaconcesionario) {
        this.listaconcesionario = listaconcesionario;
    }

    public List<Vehiculo> getListaVehiculo() {
        return listaVehiculo;
    }

    public void setListaVehiculo(List<Vehiculo> listaVehiculo) {
        this.listaVehiculo = listaVehiculo;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
    public void  registrarVehiculo (){
        try{
            vehiculoEJB.create(vehiculo);
            manejarExito ("Registrar");
        }catch (Exception e){
            manejarError (e);
        }
    }
    
    public String registrar(){
        return "listarVehiculo";
    }
    
    public List<Vehiculo> getVehiculos(){
        try{
            return this.vehiculoEJB.findAll();
        }catch (Exception e) {
            manejarError(e);
        }
        return null;
    }
     public void elimarVehiculo(Vehiculo V){
        try {
            vehiculoEJB.remove(V);
            manejarExito("Eliminado");
        }catch (Exception e){
            manejarError (e);
        }
    }
    public void leer(Vehiculo leerVehiculo){
        vehiculo = leerVehiculo;
    }
    
    public void modificarVehiculo (){
        
          try {
            vehiculoEJB.edit(vehiculo);
            manejarExito("Editado");
        }catch (Exception e){
            manejarError (e);
        }
    }
    
    
    
    private void manejarExito(String operacion) {
        String msg = "Se ha realizado exitosamente la operacion de " + operacion;
        FacesContext context =FacesContext.getCurrentInstance();
        context.addMessage(null,new FacesMessage(msg));
        FacesMessage sal = new FacesMessage(FacesMessage.SEVERITY_INFO,"Operación exitosa: ", msg);
        RequestContext.getCurrentInstance().showMessageInDialog(sal);
    }
    private void manejarError(Exception e) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Se produjo el sigiente error: ", e.getMessage()));
        FacesMessage msg =new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al insertar: ", e.getMessage());
        RequestContext.getCurrentInstance().showMessageInDialog(msg);
    }
}
