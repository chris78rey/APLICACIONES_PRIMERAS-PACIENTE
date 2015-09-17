/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mil.he1.portal.paciente.mbeans.HistorialTurno;

import ec.mil.he1.portal.paciente.mbeans.PacienteJSFManagedBean;
import he1.utilities.SesionPersona;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author christian_ruiz
 */
@ManagedBean(name = "hturno")
@RequestScoped
public class JSFManagedBeanHistorialTurno {

    @EJB
    private SesionPersona sesionPersona;

    String hc = "";

    /**
     * Creates a new instance of JSFManagedBeanHistorialTurno
     */
    public JSFManagedBeanHistorialTurno() {
    }

//esto se coloca en el Managed Bean  
    List<Map> listaf = new ArrayList<>();

    public List<Map> getListaf() {
        FacesContext facesContext;
        facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        PacienteJSFManagedBean pacienteJSFManagedBean;
        pacienteJSFManagedBean = (PacienteJSFManagedBean) session.getAttribute("pacienteJSFManagedBean");
        List data = new ArrayList<HashMap>();
        data = sesionPersona.buscaHistorial(pacienteJSFManagedBean.getPacientes().getNumeroHc().toString());
        return data;
    }

}
