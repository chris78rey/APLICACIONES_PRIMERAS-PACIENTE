/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mil.he1.portal.paciente.mbeans;

import he1.seguridades.entities.SegUrls;
import he1.seguridades.sessions.SegUrlsFacade;
import he1.utilities.SesionSeguridades;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author christian_ruiz
 */
@ManagedBean(name = "certmedico")
@RequestScoped
public class JSFManagedBeanCertificados {

    @EJB
    private SegUrlsFacade segUrlsFacade;
    @EJB
    private SesionSeguridades sesionSeguridades1;

    private String hc = "";
    private String nombre = "";
    private String apellido = "";
    private String servicio = "";
    private String fecha = "";
    private String diagnostico = "";
    private String diagnosticocie10 = "";
    private String tratamiento = "";
    private String reposo = "";
    private String desde = "";
    private String hasta = "";
    private String observaciones = "";
    private String firmaNombre = "";
    private String firmaApellido = "";

    @EJB
    private SesionSeguridades sesionSeguridades;

    /**
     * Creates a new instance of JSFManagedBeanCertificados
     */
    public JSFManagedBeanCertificados() {
    }
    List<Map> listaf = new ArrayList<>();

    public List<Map> getListaf() {
        List data = new ArrayList<>();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        PacienteJSFManagedBean pacienteJSFManagedBean = new PacienteJSFManagedBean();
        pacienteJSFManagedBean = (PacienteJSFManagedBean) session.getAttribute("pacienteJSFManagedBean");
        data = sesionSeguridades.listaBuscaFechasCertificados(pacienteJSFManagedBean.getPacientes().getNumeroHc().toString());
        return data;
    }

    public String accion(String id) throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

        PacienteJSFManagedBean pacienteJSFManagedBean = new PacienteJSFManagedBean();
        pacienteJSFManagedBean = (PacienteJSFManagedBean) session.getAttribute("pacienteJSFManagedBean");
        //id es el número del certificado
        session.setAttribute("idcertif", id);

        SegUrls segUrl = segUrlsFacade.findURL("reporte Certificado");
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(segUrl.getUrl() + pacienteJSFManagedBean.getPacientes().getNumeroHc() + "&sddvaxd=" + id);
        return null;
    }

    /**
     * @return the hc
     */
    public String getHc() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        PacienteJSFManagedBean pacienteJSFManagedBean = new PacienteJSFManagedBean();
        pacienteJSFManagedBean = (PacienteJSFManagedBean) session.getAttribute("pacienteJSFManagedBean");

        Iterator<Map> iterator = sesionSeguridades.despliegaCertificado(pacienteJSFManagedBean.getPacientes().getNumeroHc().toString(), (String) session.getAttribute("idcertif")).iterator();
        while (iterator.hasNext()) {
            Map next = iterator.next();
            hc = (String) next.get("HC").toString();
        }
        return hc;
    }

    /**
     * @param hc the hc to set
     */
    public void setHc(String hc) {
        this.hc = hc;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        PacienteJSFManagedBean pacienteJSFManagedBean = new PacienteJSFManagedBean();
        pacienteJSFManagedBean = (PacienteJSFManagedBean) session.getAttribute("pacienteJSFManagedBean");

        Iterator<Map> iterator = sesionSeguridades.despliegaCertificado(pacienteJSFManagedBean.getPacientes().getNumeroHc().toString(), (String) session.getAttribute("idcertif")).iterator();
        while (iterator.hasNext()) {
            Map next = iterator.next();
            nombre = (String) next.get("PRIMER_NOMBRE").toString();
        }
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        PacienteJSFManagedBean pacienteJSFManagedBean = new PacienteJSFManagedBean();
        pacienteJSFManagedBean = (PacienteJSFManagedBean) session.getAttribute("pacienteJSFManagedBean");

        Iterator<Map> iterator = sesionSeguridades.despliegaCertificado(pacienteJSFManagedBean.getPacientes().getNumeroHc().toString(), (String) session.getAttribute("idcertif")).iterator();
        while (iterator.hasNext()) {
            Map next = iterator.next();
            apellido = (String) next.get("APELLIDO_PATERNO").toString();
        }
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the servicio
     */
    public String getServicio() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        PacienteJSFManagedBean pacienteJSFManagedBean = new PacienteJSFManagedBean();
        pacienteJSFManagedBean = (PacienteJSFManagedBean) session.getAttribute("pacienteJSFManagedBean");

        Iterator<Map> iterator = sesionSeguridades.despliegaCertificado(pacienteJSFManagedBean.getPacientes().getNumeroHc().toString(), (String) session.getAttribute("idcertif")).iterator();
        while (iterator.hasNext()) {
            Map next = iterator.next();
            servicio = (String) next.get("SERVICIO").toString();
        }
        return servicio;
    }

    /**
     * @param servicio the servicio to set
     */
    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        PacienteJSFManagedBean pacienteJSFManagedBean = new PacienteJSFManagedBean();
        pacienteJSFManagedBean = (PacienteJSFManagedBean) session.getAttribute("pacienteJSFManagedBean");

        Iterator<Map> iterator = sesionSeguridades.despliegaCertificado(pacienteJSFManagedBean.getPacientes().getNumeroHc().toString(), (String) session.getAttribute("idcertif")).iterator();
        while (iterator.hasNext()) {
            Map next = iterator.next();
            fecha = (String) next.get("FECHA").toString();
        }
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the diagnostico
     */
    public String getDiagnostico() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        PacienteJSFManagedBean pacienteJSFManagedBean = new PacienteJSFManagedBean();
        pacienteJSFManagedBean = (PacienteJSFManagedBean) session.getAttribute("pacienteJSFManagedBean");

        Iterator<Map> iterator = sesionSeguridades.despliegaCertificado(pacienteJSFManagedBean.getPacientes().getNumeroHc().toString(), (String) session.getAttribute("idcertif")).iterator();
        while (iterator.hasNext()) {
            Map next = iterator.next();
            diagnostico = (String) next.get("ENFERMEDAD").toString();
        }
        return diagnostico;
    }

    /**
     * @param diagnostico the diagnostico to set
     */
    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    /**
     * @return the diagnosticocie10
     */
    public String getDiagnosticocie10() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        PacienteJSFManagedBean pacienteJSFManagedBean = new PacienteJSFManagedBean();
        pacienteJSFManagedBean = (PacienteJSFManagedBean) session.getAttribute("pacienteJSFManagedBean");

        Iterator<Map> iterator = sesionSeguridades.despliegaCertificado(pacienteJSFManagedBean.getPacientes().getNumeroHc().toString(), (String) session.getAttribute("idcertif")).iterator();
        while (iterator.hasNext()) {
            Map next = iterator.next();
            diagnosticocie10 = (String) next.get("ENFERMEDADES_CODIGO").toString();
        }

        return diagnosticocie10;
    }

    /**
     * @param diagnosticocie10 the diagnosticocie10 to set
     */
    public void setDiagnosticocie10(String diagnosticocie10) {
        this.diagnosticocie10 = diagnosticocie10;
    }

    /**
     * @return the tratamiento
     */
    public String getTratamiento() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        PacienteJSFManagedBean pacienteJSFManagedBean = new PacienteJSFManagedBean();
        pacienteJSFManagedBean = (PacienteJSFManagedBean) session.getAttribute("pacienteJSFManagedBean");

        Iterator<Map> iterator = sesionSeguridades.despliegaCertificado(pacienteJSFManagedBean.getPacientes().getNumeroHc().toString(), (String) session.getAttribute("idcertif")).iterator();
        while (iterator.hasNext()) {
            Map next = iterator.next();
            tratamiento = (String) next.get("TIPO_TRATAMIENTO").toString();
        }
        return tratamiento;
    }

    /**
     * @param tratamiento the tratamiento to set
     */
    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    /**
     * @return the reposo
     */
    public String getReposo() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        PacienteJSFManagedBean pacienteJSFManagedBean = new PacienteJSFManagedBean();
        pacienteJSFManagedBean = (PacienteJSFManagedBean) session.getAttribute("pacienteJSFManagedBean");

        Iterator<Map> iterator = sesionSeguridades.despliegaCertificado(pacienteJSFManagedBean.getPacientes().getNumeroHc().toString(), (String) session.getAttribute("idcertif")).iterator();
        while (iterator.hasNext()) {
            Map next = iterator.next();
            reposo = (String) next.get("REPOSO_FISICO").toString();
        }
        return reposo;
    }

    /**
     * @param reposo the reposo to set
     */
    public void setReposo(String reposo) {
        this.reposo = reposo;
    }

    /**
     * @return the desde
     */
    public String getDesde() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        PacienteJSFManagedBean pacienteJSFManagedBean = new PacienteJSFManagedBean();
        pacienteJSFManagedBean = (PacienteJSFManagedBean) session.getAttribute("pacienteJSFManagedBean");
        try {
            Iterator<Map> iterator = sesionSeguridades.despliegaCertificado(pacienteJSFManagedBean.getPacientes().getNumeroHc().toString(), (String) session.getAttribute("idcertif")).iterator();
            while (iterator.hasNext()) {
                Map next = iterator.next();
                desde = (String) next.get("FECHA_INICIO_REPOSO").toString();
            }
        } catch (Exception e) {
        }

        return desde;
    }

    /**
     * @param desde the desde to set
     */
    public void setDesde(String desde) {
        this.desde = desde;
    }

    /**
     * @return the hasta
     */
    public String getHasta() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        PacienteJSFManagedBean pacienteJSFManagedBean = new PacienteJSFManagedBean();
        pacienteJSFManagedBean = (PacienteJSFManagedBean) session.getAttribute("pacienteJSFManagedBean");

        try {
            Iterator<Map> iterator = sesionSeguridades.despliegaCertificado(pacienteJSFManagedBean.getPacientes().getNumeroHc().toString(), (String) session.getAttribute("idcertif")).iterator();
            while (iterator.hasNext()) {
                Map next = iterator.next();
                hasta = (String) next.get("FECHA_FIN_REPOSO").toString();
            }
        } catch (Exception e) {
        }

        return hasta;
    }

    /**
     * @param hasta the hasta to set
     */
    public void setHasta(String hasta) {
        this.hasta = hasta;
    }

    /**
     * @return the observaciones
     */
    public String getObservaciones() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        PacienteJSFManagedBean pacienteJSFManagedBean = new PacienteJSFManagedBean();
        pacienteJSFManagedBean = (PacienteJSFManagedBean) session.getAttribute("pacienteJSFManagedBean");

        Iterator<Map> iterator = sesionSeguridades.despliegaCertificado(pacienteJSFManagedBean.getPacientes().getNumeroHc().toString(), (String) session.getAttribute("idcertif")).iterator();
        while (iterator.hasNext()) {
            Map next = iterator.next();
            observaciones = (String) next.get("OBSERVACIONES").toString();
        }
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * @return the firmaNombre
     */
    public String getFirmaNombre() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        PacienteJSFManagedBean pacienteJSFManagedBean = new PacienteJSFManagedBean();
        pacienteJSFManagedBean = (PacienteJSFManagedBean) session.getAttribute("pacienteJSFManagedBean");

        Iterator<Map> iterator = sesionSeguridades.despliegaCertificado(pacienteJSFManagedBean.getPacientes().getNumeroHc().toString(), (String) session.getAttribute("idcertif")).iterator();
        while (iterator.hasNext()) {
            Map next = iterator.next();
            firmaNombre = (String) next.get("PERSONAL_1_NOMBRES").toString();
        }
        return firmaNombre;
    }

    /**
     * @param firmaNombre the firmaNombre to set
     */
    public void setFirmaNombre(String firmaNombre) {
        this.firmaNombre = firmaNombre;
    }

    /**
     * @return the firmaApellido
     */
    public String getFirmaApellido() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        PacienteJSFManagedBean pacienteJSFManagedBean = new PacienteJSFManagedBean();
        pacienteJSFManagedBean = (PacienteJSFManagedBean) session.getAttribute("pacienteJSFManagedBean");

        Iterator<Map> iterator = sesionSeguridades.despliegaCertificado(pacienteJSFManagedBean.getPacientes().getNumeroHc().toString(), (String) session.getAttribute("idcertif")).iterator();
        while (iterator.hasNext()) {
            Map next = iterator.next();
            firmaNombre = (String) next.get("PERSONAL_1_APELLIDOS").toString();
        }
        return firmaApellido;
    }

    /**
     * @param firmaApellido the firmaApellido to set
     */
    public void setFirmaApellido(String firmaApellido) {
        this.firmaApellido = firmaApellido;
    }

}
