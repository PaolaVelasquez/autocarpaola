package co.autocarpaola.managedbean;



import co.autocarpaola.entity.Concesionario;
import co.autocarpaola.sessionbeans.ConcesionarioFacadeLocal;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;



@Named(value = "concesionarioManagedBean")
@RequestScoped
public class ConcesionarioManagedBean implements Serializable {
    @EJB
    private ConcesionarioFacadeLocal ConcesionarioEJB;
    
    private Concesionario concesionario;
    
    private String accion;
    
    public ConcesionarioManagedBean() {
    }

    public Concesionario getConcesionario() {
        return concesionario;
    }

    public void setConcesionario(Concesionario concesionario) {
        this.concesionario = concesionario;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    
    @PostConstruct
    public void init() {
       concesionario = new Concesionario();
    }
    
    public void  registroConcesionario (){
        try{
            ConcesionarioEJB.create(concesionario);
            manejarExito ("Registrar");
        }catch (Exception e){
            manejarError (e);
        }
    }
    
    public String registrar(){
        return "listar";
    }
    
    public List<Concesionario> getCon(){
        try{
            return this.ConcesionarioEJB.findAll();
        }catch (Exception e) {
            manejarError(e);
        }
        return null;
    }
     public void elimarConcesionario (Concesionario conc){
        try {
            ConcesionarioEJB.remove(conc);
            manejarExito("Eliminado");
        }catch (Exception e){
            manejarError (e);
        }
    }
    public void leer(Concesionario leerConcesionario){
        concesionario = leerConcesionario;
        this.setAccion("M");
    }
    public void modificar (){
        
          try {
            ConcesionarioEJB.edit(concesionario);
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
