package managedbean;

import entity.Cliente;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import sessionbeans.ClienteFacadeLocal;

@Named(value = "clienteManagedBean")
@RequestScoped
public class ClienteManagedBean implements Serializable {
    @EJB
    private ClienteFacadeLocal ClienteEJB;
    
    private Cliente cliente;
    
    private String accion;
    
    public ClienteManagedBean() {
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    @PostConstruct
    public void init() {
       cliente = new Cliente();
    }
    
    public void  registroCliente (){
        try{
            ClienteEJB.create(cliente);
            manejarExito ("Registrar");
        }catch (Exception e){
            manejarError (e);
        }
    }
    
    public String registrar(){
        return "listar";
    }
    
    public List<Cliente> getClie(){
        try{
            return this.ClienteEJB.findAll();
        }catch (Exception e) {
            manejarError(e);
        }
        return null;
    }
     public void elimarCliente (Cliente cli){
        try {
            ClienteEJB.remove(cli);
            manejarExito("Eliminado");
        }catch (Exception e){
            manejarError (e);
        }
    }
    public void leer(Cliente leerCliente){
        cliente = leerCliente;
        this.setAccion("M");
    }
    public void modificar (){
        
          try {
             ClienteEJB.edit(cliente);
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
