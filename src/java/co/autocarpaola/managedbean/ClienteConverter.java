package co.autocarpaola.managedbean;

import co.autocarpaola.entity.Cliente;
import co.autocarpaola.sessionbeans.ClienteFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;


@Named(value = "clienteConverter")
@ViewScoped
public class ClienteConverter implements Serializable {

    public ClienteConverter() {
    }
    
    private Cliente cliente;
    
    @EJB
    private ClienteFacadeLocal clienteEJB;
    
    public List<Cliente> getAll(){
        List<Cliente> li=clienteEJB.findAll();
        return li;
    }
    
    @PostConstruct
    public void init (){
        cliente = new Cliente();
    }
    
    public Cliente getSelected() {
       if (cliente == null) {
           cliente = new Cliente();
           int selectedItemIndex = -1;
       }
       return cliente;
    }
    
    public Cliente getCliente(java.lang.Integer id) {
       return clienteEJB.find(id);
   }
    
    @FacesConverter(forClass = Cliente.class)
        public static class RolConverter implements Converter { 

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
           if (value == null || value.length() == 0) {
               return null;
           }
           ClienteConverter controlador = (ClienteConverter) facesContext.getApplication().getELResolver().
                   getValue(facesContext.getELContext(), null, "ClienteConverter");
           return controlador.getCliente(getKey(value));
       }

       java.lang.Integer getKey(String value) {
           java.lang.Integer key;
           key = Integer.valueOf(value);
           return key;
       }

       String getStringKey(java.lang.Integer value) {
           StringBuilder sb = new StringBuilder();
           sb.append(value);
           return sb.toString();
       }

        @Override
       public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
           if (object == null) {
               return null;
           }
           if (object instanceof Cliente) {
              Cliente  o = (Cliente) object;
               return getStringKey(o.getIdCliente());
           } else {
               throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName()+ "; expected type: " + Cliente.class.getName());
           }
       }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
   
       

}

