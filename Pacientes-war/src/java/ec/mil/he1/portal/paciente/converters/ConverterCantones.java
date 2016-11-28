/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mil.he1.portal.paciente.converters;

import he1.sis.entities.Cantones;
import he1.sis.entities.CantonesPK;
import he1.sis.entities.Provincias;
import he1.sis.sessions.CantonesFacade;
import java.util.ArrayList;
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
@FacesConverter("converterCantones")
public class ConverterCantones implements Converter {

    @EJB
    private CantonesFacade cantonesFacade;
    private List<Cantones> lcantones = new ArrayList<>();
    private Cantones cantones = new Cantones();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String valueString) {
        System.out.println("valueString = " + valueString);
        lcantones = cantonesFacade.findAll();
        Iterator<Cantones> iterator = lcantones.iterator();
        while (iterator.hasNext()) {
            Cantones next = iterator.next();
            if (next.getCantonesPK().toString().equalsIgnoreCase(valueString)) {
                return next;
            }

        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object valueObject) {
        System.out.println("(valueObject instanceof Cantones) = " + (valueObject instanceof Cantones));
        CantonesPK k = (valueObject instanceof Cantones) ? ((Cantones) valueObject).getCantonesPK() : null;
        System.out.println("k = " + k);
        return (k != null) ? k.toString() : null;

    }

}
