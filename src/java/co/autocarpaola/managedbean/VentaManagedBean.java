package co.autocarpaola.managedbean;


import co.autocarpaola.entity.Cliente;
import co.autocarpaola.entity.Concesionario;
import co.autocarpaola.entity.Vehiculo;
import co.autocarpaola.entity.Venta;
import co.autocarpaola.sessionbeans.ClienteFacadeLocal;
import co.autocarpaola.sessionbeans.ConcesionarioFacadeLocal;
import co.autocarpaola.sessionbeans.VehiculoFacadeLocal;
import co.autocarpaola.sessionbeans.VentaFacadeLocal;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;


@Named(value = "ventaManagedBean")
@RequestScoped
public class VentaManagedBean implements Serializable {
    @EJB
    VehiculoFacadeLocal vehiculoEJB;
    
    @EJB
    ConcesionarioFacadeLocal concesionarioEJB;
    
    @EJB
    ClienteFacadeLocal clienteEJB;
    
    @EJB
    VentaFacadeLocal ventaEJB;
    
    private Venta venta;
    private String accion;
    private List<Concesionario> listaconcesionario;
    private List<Vehiculo>listaVehiculo;  
    private List<Cliente>listaVenta;
        
    @PostConstruct
    public void init() {
       venta = new Venta();
    }
    
    public VentaManagedBean() {
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
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

    public List<Cliente> getListaVenta() {
        return listaVenta;
    }

    public void setListaVenta(List<Cliente> listaVenta) {
        this.listaVenta = listaVenta;
    }
    
    
    public void  registroVenta (){
        try{
            ventaEJB.create(venta);
            manejarExito ("Registrar");
        }catch (Exception e){
            manejarError (e);
        }
    }
    
    public String registrar(){
        return "listarVenta";
    }
    
    public List<Venta> getVen(){
        try{
            return this.ventaEJB.findAll();
        }catch (Exception e) {
            manejarError(e);
        }
        return null;
    }
     public void elimarVenta (Venta vent){
        try {
            ventaEJB.remove(vent);
            manejarExito("Eliminado");
        }catch (Exception e){
            manejarError (e);
        }
    }
    public void leer(Venta leerVenta){
        venta = leerVenta;
        this.setAccion("M");
    }
    public void modificarVenta (){
        
          try {
            ventaEJB.edit(venta);
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
