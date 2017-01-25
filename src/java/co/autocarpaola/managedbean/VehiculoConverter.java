package co.autocarpaola.managedbean;

import co.autocarpaola.entity.Vehiculo;
import co.autocarpaola.sessionbeans.VehiculoFacadeLocal;
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

@Named(value = "vehiculoConverter")
@ViewScoped
public class VehiculoConverter implements Serializable {

    public VehiculoConverter() {
    }
    
    private Vehiculo vehiculo;
    
    @EJB
    private VehiculoFacadeLocal vehiculoEJB;
    
    public List<Vehiculo> getAll(){
        List<Vehiculo> li=vehiculoEJB.findAll();
        return li;
    }
    
    @PostConstruct
    public void init (){
        vehiculo = new Vehiculo();
    }
    
    public Vehiculo getSelected() {
       if (vehiculo == null) {
           vehiculo = new Vehiculo();
           int selectedItemIndex = -1;
       }
       return vehiculo;
    }
    
    public Vehiculo getVehiculo(java.lang.Integer id) {
       return vehiculoEJB.find(id);
   }
    
    @FacesConverter(forClass = Vehiculo.class)
        public static class RolConverter implements Converter { 

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
           if (value == null || value.length() == 0) {
               return null;
           }
           VehiculoConverter controlador = (VehiculoConverter) facesContext.getApplication().getELResolver().
                   getValue(facesContext.getELContext(), null, "vehiculoConverter");
           return controlador.getVehiculo(getKey(value));
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
           if (object instanceof Vehiculo) {
              Vehiculo o = (Vehiculo) object;
               return getStringKey(o.getCodigoVehiculo());
           } else {
               throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Vehiculo.class.getName());
           }
       }
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
    
   
       

}

