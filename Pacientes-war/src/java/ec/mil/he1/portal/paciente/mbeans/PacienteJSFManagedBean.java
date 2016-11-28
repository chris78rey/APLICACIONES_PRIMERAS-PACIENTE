/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mil.he1.portal.paciente.mbeans;

import ec.mil.he1.portal.paciente.servlet.DatosDinamicos;
import he1.seguridades.entities.SegUrls;
import he1.seguridades.entities.nuevos.VUsuariosClasif;
import he1.seguridades.sessions.SegUrlsFacade;
import he1.sis.entities.Pacientes;
import he1.utilities.SesionSeguridades;
import java.io.IOException;
import java.io.Serializable;
import java.util.Enumeration;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpSession;

/**
 *
 * @author christian_ruiz
 */
@ManagedBean
@SessionScoped
public class PacienteJSFManagedBean implements Serializable {

    @EJB
    private SegUrlsFacade segUrlsFacade;

    @EJB(name = "SegUrlsFacadePruebaRedireccion")
    private SegUrlsFacade segUrlsFacadePruebaRedireccion;

    private Pacientes pacientes = new Pacientes();
    @EJB
    private SesionSeguridades encryptFacade;

    private VUsuariosClasif findByCedulaLogin = new VUsuariosClasif();

    DatosDinamicos datosDinamicos = new DatosDinamicos();

    /**
     * Creates a new instance of PacienteJSFManagedBean
     */
    public PacienteJSFManagedBean() {
    }

    /**
     * @return the pacientes
     */
    public Pacientes getPacientes() {

        return pacientes;
    }

    /**
     * @param pacientes the pacientes to set
     */
    public void setPacientes(Pacientes pacientes) {
        this.pacientes = pacientes;
    }

    public String logout() throws IOException {
        try {
            datosDinamicos = new DatosDinamicos();
            FacesContext context = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
            Enumeration<String> attributeNames = session.getAttributeNames();
            String nextElement = "";
            while (attributeNames.hasMoreElements()) {
                try {
                    nextElement = attributeNames.nextElement();
                    session.removeAttribute(nextElement);
                } catch (Exception e) {
                }
            }
            session = null;
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(datosDinamicos.getPresionarSalir());
        } catch (Exception e) {
        }
        return "";
    }

    public VUsuariosClasif getFindByCedulaLogin() {
        return findByCedulaLogin;
    }

    /**
     * @param findByCedulaLogin the findByCedulaLogin to set
     */
    public void setFindByCedulaLogin(VUsuariosClasif findByCedulaLogin) {
        this.findByCedulaLogin = findByCedulaLogin;
    }

    public void buttonAction(ActionEvent actionEvent) {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            String cedulausuario = (String) session.getAttribute("cedulausuario");
            String usuId = datosDinamicos.encripta(cedulausuario);
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(datosDinamicos.findURL() + usuId);
        } catch (Exception e) {
            System.out.println("1111111111111111111111111111111111111111111111111 = " + e.getLocalizedMessage());
        }
    }

    @PostConstruct
    private void init() {

        System.out.println("@PostConstruct");
    }
}
