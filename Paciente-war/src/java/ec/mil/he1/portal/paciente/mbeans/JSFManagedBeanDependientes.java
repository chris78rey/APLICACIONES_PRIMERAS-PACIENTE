/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mil.he1.portal.paciente.mbeans;

import he1.utilities.SesionSeguridades;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author christian_ruiz
 */
@ManagedBean(name = "dependientes")
@SessionScoped
public class JSFManagedBeanDependientes {

    private String fuerza = "";
    private String grado = "";
    private String tipoDependencia = "";
    private String CC = "";
    private String nombre = "";
    private String apell = "";
    private String nombre2 = "";
    private String apell2 = "";
    private String HC = "";
    private String genero = "";
    private String bloqueado = "";
    private String email = "";
    private String IDISSFATitular = "";
    private String telefono = "";
    private String celular = "";

    @EJB
    private SesionSeguridades sesionSeguridades;

    /**
     * Creates a new instance of JSFManagedBeanDependientes
     */
    public JSFManagedBeanDependientes() {
    }
    private List<Map> listaBuscaLogueadoDependientes = new ArrayList<>();

    public List<Map> getListaBuscaLogueadoDependientes() {
        List data = new ArrayList<>();

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        PacienteJSFManagedBean pacienteJSFManagedBean = new PacienteJSFManagedBean();
        pacienteJSFManagedBean = (PacienteJSFManagedBean) session.getAttribute("pacienteJSFManagedBean");

        listaBuscaLogueadoDependientes = sesionSeguridades.listaLogueadoDependiente(pacienteJSFManagedBean.getPacientes().getNumeroHc().toString());
        Iterator<Map> iterator = listaBuscaLogueadoDependientes.iterator();
        String FUERZA = "";
        while (iterator.hasNext()) {
            Map next = iterator.next();
            FUERZA = (String) next.get("FUERZA");

        }
        return listaBuscaLogueadoDependientes;
    }

    /**
     * @return the fuerza
     */
    public String getFuerza() {
        List data = new ArrayList<>();

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        PacienteJSFManagedBean pacienteJSFManagedBean = new PacienteJSFManagedBean();
        pacienteJSFManagedBean = (PacienteJSFManagedBean) session.getAttribute("pacienteJSFManagedBean");

        listaBuscaLogueadoDependientes = sesionSeguridades.listaLogueadoDependiente(pacienteJSFManagedBean.getPacientes().getNumeroHc().toString());
        Iterator<Map> iterator = listaBuscaLogueadoDependientes.iterator();
        String FUERZA = "";
        while (iterator.hasNext()) {
            Map next = iterator.next();
            fuerza = (String) next.get("FUERZA");

        }
        return fuerza;
    }

    /**
     * @param fuerza the fuerza to set
     */
    public void setFuerza(String fuerza) {
        this.fuerza = fuerza;
    }

    /**
     * @return the grado
     */
    public String getGrado() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        PacienteJSFManagedBean pacienteJSFManagedBean = new PacienteJSFManagedBean();
        pacienteJSFManagedBean = (PacienteJSFManagedBean) session.getAttribute("pacienteJSFManagedBean");

        listaBuscaLogueadoDependientes = sesionSeguridades.listaLogueadoDependiente(pacienteJSFManagedBean.getPacientes().getNumeroHc().toString());
        Iterator<Map> iterator = listaBuscaLogueadoDependientes.iterator();
        String FUERZA = "";
        while (iterator.hasNext()) {
            Map next = iterator.next();
            grado = (String) next.get("GRADOM");

        }
        return grado;
    }

    /**
     * @param grado the grado to set
     */
    public void setGrado(String grado) {
        this.grado = grado;
    }

    /**
     * @return the CC
     */
    public String getCC() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        PacienteJSFManagedBean pacienteJSFManagedBean = new PacienteJSFManagedBean();
        pacienteJSFManagedBean = (PacienteJSFManagedBean) session.getAttribute("pacienteJSFManagedBean");

        listaBuscaLogueadoDependientes = sesionSeguridades.listaLogueadoDependiente(pacienteJSFManagedBean.getPacientes().getNumeroHc().toString());
        Iterator<Map> iterator = listaBuscaLogueadoDependientes.iterator();
        String FUERZA = "";
        while (iterator.hasNext()) {
            Map next = iterator.next();
            CC = (String) next.get("CEDULA");

        }
        return CC;
    }

    /**
     * @param CC the CC to set
     */
    public void setCC(String CC) {
        this.CC = CC;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        PacienteJSFManagedBean pacienteJSFManagedBean = new PacienteJSFManagedBean();
        pacienteJSFManagedBean = (PacienteJSFManagedBean) session.getAttribute("pacienteJSFManagedBean");

        listaBuscaLogueadoDependientes = sesionSeguridades.listaLogueadoDependiente(pacienteJSFManagedBean.getPacientes().getNumeroHc().toString());
        Iterator<Map> iterator = listaBuscaLogueadoDependientes.iterator();
        String FUERZA = "";
        while (iterator.hasNext()) {
            Map next = iterator.next();
            nombre = (String) next.get("PRIMER_NOMBRE");

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
     * @return the apell
     */
    public String getApell() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        PacienteJSFManagedBean pacienteJSFManagedBean = new PacienteJSFManagedBean();
        pacienteJSFManagedBean = (PacienteJSFManagedBean) session.getAttribute("pacienteJSFManagedBean");

        listaBuscaLogueadoDependientes = sesionSeguridades.listaLogueadoDependiente(pacienteJSFManagedBean.getPacientes().getNumeroHc().toString());
        Iterator<Map> iterator = listaBuscaLogueadoDependientes.iterator();
        String FUERZA = "";
        while (iterator.hasNext()) {
            Map next = iterator.next();
            apell = (String) next.get("APELLIDO_PATERNO");

        }
        return apell;
    }

    /**
     * @param apell the apell to set
     */
    public void setApell(String apell) {
        this.apell = apell;
    }

    /**
     * @return the HC
     */
    public String getHC() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        PacienteJSFManagedBean pacienteJSFManagedBean = new PacienteJSFManagedBean();
        pacienteJSFManagedBean = (PacienteJSFManagedBean) session.getAttribute("pacienteJSFManagedBean");

        listaBuscaLogueadoDependientes = sesionSeguridades.listaLogueadoDependiente(pacienteJSFManagedBean.getPacientes().getNumeroHc().toString());
        Iterator<Map> iterator = listaBuscaLogueadoDependientes.iterator();
        String FUERZA = "";
        while (iterator.hasNext()) {
            Map next = iterator.next();
            HC = (String) next.get("NUMERO_HC").toString();

        }
        return HC;
    }

    /**
     * @param HC the HC to set
     */
    public void setHC(String HC) {
        this.HC = HC;
    }

    /**
     * @return the genero
     */
    public String getGenero() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        PacienteJSFManagedBean pacienteJSFManagedBean = new PacienteJSFManagedBean();
        pacienteJSFManagedBean = (PacienteJSFManagedBean) session.getAttribute("pacienteJSFManagedBean");

        listaBuscaLogueadoDependientes = sesionSeguridades.listaLogueadoDependiente(pacienteJSFManagedBean.getPacientes().getNumeroHc().toString());
        Iterator<Map> iterator = listaBuscaLogueadoDependientes.iterator();
        String FUERZA = "";
        while (iterator.hasNext()) {
            Map next = iterator.next();
            genero = (String) next.get("SEXO");

        }
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * @return the bloqueado
     */
    public String getBloqueado() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        PacienteJSFManagedBean pacienteJSFManagedBean = new PacienteJSFManagedBean();
        pacienteJSFManagedBean = (PacienteJSFManagedBean) session.getAttribute("pacienteJSFManagedBean");

        listaBuscaLogueadoDependientes = sesionSeguridades.listaLogueadoDependiente(pacienteJSFManagedBean.getPacientes().getNumeroHc().toString());
        Iterator<Map> iterator = listaBuscaLogueadoDependientes.iterator();
        String FUERZA = "";
        while (iterator.hasNext()) {
            Map next = iterator.next();
            bloqueado = (String) next.get("BLOQUEADO");

        }
        return bloqueado;
    }

    /**
     * @param bloqueado the bloqueado to set
     */
    public void setBloqueado(String bloqueado) {
        this.bloqueado = bloqueado;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        PacienteJSFManagedBean pacienteJSFManagedBean = new PacienteJSFManagedBean();
        pacienteJSFManagedBean = (PacienteJSFManagedBean) session.getAttribute("pacienteJSFManagedBean");

        listaBuscaLogueadoDependientes = sesionSeguridades.listaLogueadoDependiente(pacienteJSFManagedBean.getPacientes().getNumeroHc().toString());
        Iterator<Map> iterator = listaBuscaLogueadoDependientes.iterator();
        String FUERZA = "";
        while (iterator.hasNext()) {
            Map next = iterator.next();
            email = (String) next.get("EMAIL");

        }
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the IDISSFATitular
     */
    public String getIDISSFATitular() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        PacienteJSFManagedBean pacienteJSFManagedBean = new PacienteJSFManagedBean();
        pacienteJSFManagedBean = (PacienteJSFManagedBean) session.getAttribute("pacienteJSFManagedBean");

        listaBuscaLogueadoDependientes = sesionSeguridades.listaLogueadoDependiente(pacienteJSFManagedBean.getPacientes().getNumeroHc().toString());
        Iterator<Map> iterator = listaBuscaLogueadoDependientes.iterator();
        String FUERZA = "";
        while (iterator.hasNext()) {
            Map next = iterator.next();
            IDISSFATitular = (String) next.get("ES_TITULAR");

        }
        return IDISSFATitular;
    }

    /**
     * @param IDISSFATitular the IDISSFATitular to set
     */
    public void setIDISSFATitular(String IDISSFATitular) {
        this.IDISSFATitular = IDISSFATitular;
    }

    /**
     * @return the tipoDependencia
     */
    public String getTipoDependencia() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        PacienteJSFManagedBean pacienteJSFManagedBean = new PacienteJSFManagedBean();
        pacienteJSFManagedBean = (PacienteJSFManagedBean) session.getAttribute("pacienteJSFManagedBean");

        listaBuscaLogueadoDependientes = sesionSeguridades.listaLogueadoDependiente(pacienteJSFManagedBean.getPacientes().getNumeroHc().toString());
        Iterator<Map> iterator = listaBuscaLogueadoDependientes.iterator();
        String FUERZA = "";
        while (iterator.hasNext()) {
            Map next = iterator.next();
            tipoDependencia = (String) next.get("TIPO_DEPENDENCIA");

        }
        return tipoDependencia;
    }

    /**
     * @param tipoDependencia the tipoDependencia to set
     */
    public void setTipoDependencia(String tipoDependencia) {
        this.tipoDependencia = tipoDependencia;
    }

    List<Map> listaBuscaDependientes = new ArrayList<>();

    public List<Map> getListaBuscaDependientes() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        PacienteJSFManagedBean pacienteJSFManagedBean = new PacienteJSFManagedBean();
        pacienteJSFManagedBean = (PacienteJSFManagedBean) session.getAttribute("pacienteJSFManagedBean");
        List data = new ArrayList<>();
        data = sesionSeguridades.listaBuscaDependientes(pacienteJSFManagedBean.getPacientes().getNumeroHc().toString());
        return data;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        PacienteJSFManagedBean pacienteJSFManagedBean = new PacienteJSFManagedBean();
        pacienteJSFManagedBean = (PacienteJSFManagedBean) session.getAttribute("pacienteJSFManagedBean");

        listaBuscaLogueadoDependientes = sesionSeguridades.listaLogueadoDependiente(pacienteJSFManagedBean.getPacientes().getNumeroHc().toString());
        Iterator<Map> iterator = listaBuscaLogueadoDependientes.iterator();
        String FUERZA = "";
        while (iterator.hasNext()) {
            Map next = iterator.next();
            telefono = (String) next.get("TELEFONO");

        }
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the celular
     */
    public String getCelular() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        PacienteJSFManagedBean pacienteJSFManagedBean = new PacienteJSFManagedBean();
        pacienteJSFManagedBean = (PacienteJSFManagedBean) session.getAttribute("pacienteJSFManagedBean");

        listaBuscaLogueadoDependientes = sesionSeguridades.listaLogueadoDependiente(pacienteJSFManagedBean.getPacientes().getNumeroHc().toString());
        Iterator<Map> iterator = listaBuscaLogueadoDependientes.iterator();
        String FUERZA = "";
        while (iterator.hasNext()) {
            Map next = iterator.next();
            celular = (String) next.get("CELULAR");

        }
        return celular;
    }

    /**
     * @param celular the celular to set
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }

    /**
     * @return the nombre2
     */
    public String getNombre2() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        PacienteJSFManagedBean pacienteJSFManagedBean = new PacienteJSFManagedBean();
        pacienteJSFManagedBean = (PacienteJSFManagedBean) session.getAttribute("pacienteJSFManagedBean");

        listaBuscaLogueadoDependientes = sesionSeguridades.listaLogueadoDependiente(pacienteJSFManagedBean.getPacientes().getNumeroHc().toString());
        Iterator<Map> iterator = listaBuscaLogueadoDependientes.iterator();
        String FUERZA = "";
        while (iterator.hasNext()) {
            Map next = iterator.next();
            tipoDependencia = (String) next.get("SEGUNDO_NOMBRE");

        }
        return nombre2;
    }

    /**
     * @return the apell2
     */
    public String getApell2() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        PacienteJSFManagedBean pacienteJSFManagedBean = new PacienteJSFManagedBean();
        pacienteJSFManagedBean = (PacienteJSFManagedBean) session.getAttribute("pacienteJSFManagedBean");

        listaBuscaLogueadoDependientes = sesionSeguridades.listaLogueadoDependiente(pacienteJSFManagedBean.getPacientes().getNumeroHc().toString());
        Iterator<Map> iterator = listaBuscaLogueadoDependientes.iterator();
        String FUERZA = "";
        while (iterator.hasNext()) {
            Map next = iterator.next();
            tipoDependencia = (String) next.get("APELLIDO_MATERNO");

        }
        return apell2;
    }
}
