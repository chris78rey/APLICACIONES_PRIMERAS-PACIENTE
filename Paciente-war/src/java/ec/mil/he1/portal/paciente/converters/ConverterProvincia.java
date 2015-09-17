/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mil.he1.portal.paciente.converters;

import he1.sis.entities.Provincias;
import he1.sis.sessions.ProvinciasFacade;
import java.util.ArrayList;
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
@FacesConverter("provinciaconverter")
public class ConverterProvincia implements Converter {

    @EJB
    private ProvinciasFacade provinciasFacade;
    private List<Provincias> lprovincias = new ArrayList<Provincias>();
    private Provincias provincias = new Provincias();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String valueString) {
//        System.out.println("valueString = " + valueString);
        provincias = provinciasFacade.find(valueString);
        return provincias;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object valueObject) {
//        System.out.println("valueObject = " + valueObject);
        String id = (valueObject instanceof Provincias) ? ((Provincias) valueObject).getCodigo() : "";
        return id;
    }

}
