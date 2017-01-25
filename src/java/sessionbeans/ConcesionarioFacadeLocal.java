/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entity.Concesionario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author yo
 */
@Local
public interface ConcesionarioFacadeLocal {

    void create(Concesionario concesionario);

    void edit(Concesionario concesionario);

    void remove(Concesionario concesionario);

    Concesionario find(Object id);

    List<Concesionario> findAll();

    List<Concesionario> findRange(int[] range);

    int count();
    
}
