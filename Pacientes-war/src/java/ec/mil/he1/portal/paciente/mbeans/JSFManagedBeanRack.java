/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mil.he1.portal.paciente.mbeans;

import he1.utilities.SesionSeguridades;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.mail.internet.MailDateFormat;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author christian_ruiz
 */
@ManagedBean(name = "hospitalizado")
@SessionScoped
public class JSFManagedBeanRack implements Serializable {

    @EJB
    private SesionSeguridades sesionSeguridades;

    private Date date1;
    private Date date2;

    String strDate1;
    String strDate2;

    /**
     * Creates a new instance of JSFManagedBeanRack
     */
    public JSFManagedBeanRack() {

    }

    /**
     * @return the date1
     */
    public Date getDate1() {
        return date1;
    }

    /**
     * @param date1 the date1 to set
     */
    public void setDate1(Date date1) {

        this.date1 = date1;
    }

    /**
     * @return the date2
     */
    public Date getDate2() {
        return date2;
    }

    /**
     * @param date2 the date2 to set
     */
    public void setDate2(Date date2) {

        this.date2 = date2;
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    //esto se coloca en el Managed Bean  
    List<Map> litaPermanencias = new ArrayList<>();

    public List<Map> getLitaPermanencias() {
        List data = new ArrayList<HashMap>();
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            PacienteJSFManagedBean pacienteJSFManagedBean = (PacienteJSFManagedBean) session.getAttribute("pacienteJSFManagedBean");

            data = sesionSeguridades.litaPermanencias(pacienteJSFManagedBean.getPacientes().getNumeroHc().toString(), strDate1, strDate2);

        } catch (Exception e) {
        }
        return data;
    }

    public void listen1(AjaxBehaviorEvent event) {

    }

    public void listen2(AjaxBehaviorEvent event) {

    }

    public void click() {

        SimpleDateFormat sm = new SimpleDateFormat("yyyyMMdd");

        strDate1 = sm.format(date1);
        strDate2 = sm.format(date2);
        litaPermanencias = getLitaPermanencias();

    }
}
