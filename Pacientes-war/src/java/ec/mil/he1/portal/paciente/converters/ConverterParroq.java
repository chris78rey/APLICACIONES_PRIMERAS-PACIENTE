/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mil.he1.portal.paciente.converters;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import he1.sis.entities.Parroquias;
import he1.sis.entities.ParroquiasPK;
import he1.sis.sessions.ParroquiasFacade;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author christian_ruiz
 */
@FacesConverter("prqconverter")
public class ConverterParroq implements Converter {

    @EJB
    private ParroquiasFacade parroquiasFacade;
    private List<Parroquias> lparroquias = new VirtualFlow.ArrayLinkedList<>();
    private Parroquias parroquias = new Parroquias();
    private BigInteger bigInteger = new BigInteger("1");

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String valueString) {
        System.out.println("valueString = " + valueString);
        lparroquias = parroquiasFacade.findAll();
        Iterator<Parroquias> iterator = lparroquias.iterator();
        Parroquias next0 = new Parroquias();
        while (iterator.hasNext()) {
            Parroquias next = iterator.next();
            if (next.getParroquiasPK().toString().equalsIgnoreCase(valueString)) {
                next0 = next;
            }

        }
//        ParroquiasPK k = (ParroquiasPK) ((Object) valueString);
//        System.out.println("k  antes de retornar como obj= " + k);
        return next0;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object valueObj) {
       
        System.out.println("(valueObj instanceof Parroquias) = " + (valueObj instanceof Parroquias));
        ParroquiasPK k = (valueObj instanceof Parroquias) ? ((Parroquias) valueObj).getParroquiasPK() : null;
//        return (id != null) ? String.valueOf(id) : null;
        System.out.println("k = " + k);
        return (k != null) ? k.toString() : null;
    }

    /**
     * @return the bigInteger
     */
    public BigInteger getBigInteger() {
        return bigInteger;
    }

    /**
     * @param bigInteger the bigInteger to set
     */
    public void setBigInteger(BigInteger bigInteger) {
        this.bigInteger = bigInteger;
    }

}
