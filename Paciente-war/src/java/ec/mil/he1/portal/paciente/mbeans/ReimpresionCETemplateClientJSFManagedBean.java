/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mil.he1.portal.paciente.mbeans;

import he1.sis.entities.Personal;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author christian_ruiz
 */
@ManagedBean
@SessionScoped
public class ReimpresionCETemplateClientJSFManagedBean {

    Personal personal;

    /**
     * Creates a new instance of ReimpresionCETemplateClientJSFManagedBean
     */
    public ReimpresionCETemplateClientJSFManagedBean() {
        personal = new Personal();
    }

}
