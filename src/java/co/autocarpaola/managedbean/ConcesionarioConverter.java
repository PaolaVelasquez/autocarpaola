package co.autocarpaola.managedbean;

import co.autocarpaola.entity.Concesionario;
import co.autocarpaola.sessionbeans.ConcesionarioFacadeLocal;
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

@Named(value = "concesionarioConverter")
@ViewScoped
public class ConcesionarioConverter implements Serializable {

    public ConcesionarioConverter() {
    }
    
    private Concesionario concesionario;
    
    @EJB
    private ConcesionarioFacadeLocal concesionarioEJB;
    
    public List<Concesionario> getAll(){
        List<Concesionario> li=concesionarioEJB.findAll();
        return li;
    }
    
    @PostConstruct
    public void init (){
        concesionario = new Concesionario();
    }
    
    public Concesionario getSelected() {
       if (concesionario == null) {
           concesionario = new Concesionario();
           int selectedItemIndex = -1;
       }
       return concesionario;
    }
    
    public Concesionario getConcesionario(java.lang.Integer id) {
       return concesionarioEJB.find(id);
   }
    
    @FacesConverter(forClass = Concesionario.class)
        public static class RolConverter implements Converter { 

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
           if (value == null || value.length() == 0) {
               return null;
           }
           ConcesionarioConverter controlador = (ConcesionarioConverter) facesContext.getApplication().getELResolver().
                   getValue(facesContext.getELContext(), null, "ConcesionarioConverter");
           return controlador.getConcesionario(getKey(value));
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
           if (object instanceof Concesionario) {
              Concesionario  o = (Concesionario) object;
               return getStringKey(o.getNit());
           } else {
               throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Concesionario.class.getName());
           }
       }
    }

    public Concesionario getConcesionario() {
        return concesionario;
    }

    public void setConcesionario(Concesionario concesionario) {
        this.concesionario = concesionario;
    }
    
   
       

}

