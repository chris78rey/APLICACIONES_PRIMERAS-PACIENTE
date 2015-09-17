/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mil.he1.portal.paciente.mbeans;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import he1.seguridades.entities.nuevos.PersonalNomina;
import he1.seguridades.entities.nuevos.VUsuariosClasif;
import he1.seguridades.sessions.PersonalNominaFacade;
import he1.sis.entities.Cantones;
import he1.sis.entities.Pacientes;
import he1.sis.entities.Parroquias;
import he1.sis.entities.ParroquiasPK;
import he1.sis.entities.Provincias;
import he1.sis.sessions.CantonesFacade;
import he1.sis.sessions.PacientesFacade;
import he1.sis.sessions.ParroquiasFacade;
import he1.sis.sessions.ProvinciasFacade;
import he1.utilities.SesionSeguridades;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

/**
 *
 * @author christian_ruiz
 */
@ManagedBean(name = "datosPaciente")
@SessionScoped
public class JSFManagedBeanActDatos implements Serializable {

    @EJB
    private ProvinciasFacade provinciasFacade;
    private List<Provincias> lprovincias = new ArrayList<Provincias>();
    private Provincias provincias = new Provincias();

    @EJB
    private CantonesFacade cantonesFacade;
    private List<Cantones> lcantones = new ArrayList<>();
    private Cantones cantones = new Cantones();

    @EJB
    private ParroquiasFacade parroquiasFacade;
    private List<Parroquias> lparroquias = new VirtualFlow.ArrayLinkedList<>();
    private Parroquias parroquias = new Parroquias();
    @EJB
    private PersonalNominaFacade personalNominaFacade;
    private String parr = "";
    @EJB
    private PacientesFacade pacientesFacade;
    @EJB
    private SesionSeguridades sesionSeguridades;
    private String clave = "";
    private String clavenueva = "";
    private String clavenueva2 = "";
    private Pacientes paciente = new Pacientes();
    private Object facesContext;
    private BigInteger bigInteger = new BigInteger("1");
    private Boolean boolean1 = new Boolean(false);
    private List<SelectItem> listaCantones = new ArrayList<>();

    private String cant = "";

    @PostConstruct
    private void init() {
        System.out.println("@PostConstruct");
        lprovincias = provinciasFacade.findProvinciasOrdenadas();

        lcantones = cantonesFacade.findByProvincia(provincias);

    }

    /**
     * Creates a new instance of JSFManagedBeanActDatos
     */
    public JSFManagedBeanActDatos() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        PacienteJSFManagedBean pacienteJSFManagedBean = new PacienteJSFManagedBean();
        pacienteJSFManagedBean = (PacienteJSFManagedBean) session.getAttribute("pacienteJSFManagedBean");
        paciente = pacienteJSFManagedBean.getPacientes();

    }

    public void actualizarDatos() {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        PacienteJSFManagedBean pacienteJSFManagedBean = new PacienteJSFManagedBean();
        pacienteJSFManagedBean = (PacienteJSFManagedBean) session.getAttribute("pacienteJSFManagedBean");

        Pacientes find = pacientesFacade.find(pacienteJSFManagedBean.getPacientes().getNumeroHc());
        find.setTelefono(getPaciente().getTelefono());
        find.setTelefonoTrabajo(getPaciente().getTelefonoTrabajo());
        find.setEmail(getPaciente().getEmail());
        find.setParroquias(paciente.getParroquias());
        find.setDireccionDomicilio(paciente.getDireccionDomicilio());
        pacientesFacade.edit(find);

        //
        try {
            PersonalNomina findByPrnNumdocumento = personalNominaFacade.findByPrnNumdocumento(find.getCedula());
            findByPrnNumdocumento.setPrnCorreoelectronico(getPaciente().getEmail());
            findByPrnNumdocumento.setPrnTelefonocasa(getPaciente().getTelefono());
            findByPrnNumdocumento.setPrnTelefonomovil(getPaciente().getTelefonoTrabajo());
            personalNominaFacade.edit(findByPrnNumdocumento);
            System.out.println("personalNominaFacade = " + personalNominaFacade);

        } catch (Exception e) {
        }

        addMessage("Cambio satisfactorio", "Cambio satisfactorio.");

    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     * @return the paciente
     */
    public Pacientes getPaciente() {
        return paciente;
    }

    /**
     * @param paciente the paciente to set
     */
    public void setPaciente(Pacientes paciente) {
        this.paciente = paciente;
    }

    /**
     * @return the clave
     */
    public String getClave() {
        return clave;
    }

    /**
     * @param clave the clave to set
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * @return the clavenueva
     */
    public String getClavenueva() {
        return clavenueva;
    }

    /**
     * @param clavenueva the clavenueva to set
     */
    public void setClavenueva(String clavenueva) {
        this.clavenueva = clavenueva;
    }

    /**
     * @return the clavenueva2
     */
    public String getClavenueva2() {
        return clavenueva2;
    }

    /**
     * @param clavenueva2 the clavenueva2 to set
     */
    public void setClavenueva2(String clavenueva2) {
        this.clavenueva2 = clavenueva2;
    }

    public void cambiarClave() {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        PacienteJSFManagedBean pacienteJSFManagedBean = new PacienteJSFManagedBean();
        pacienteJSFManagedBean = (PacienteJSFManagedBean) session.getAttribute("pacienteJSFManagedBean");
        String pEstado;
        if (clavenueva2.equalsIgnoreCase(clavenueva)) {
            VUsuariosClasif findByCedulaLogin = (VUsuariosClasif) session.getAttribute("findByCedulaLogin");
            pEstado = sesionSeguridades.pCambiarClavePaciente(findByCedulaLogin.getCedulaLogin(), clave, clavenueva);
            if (pEstado.equalsIgnoreCase("1")) {
                addMessage("Contraseña cambiada satisfactoriamente", "");
            } else {
                addMessage("La clave proporcionada no coincide con la almacenada", "");
            }
        } else {
            //en el caso de que no coincidan las claves
            pEstado = "-1";
            addMessage("Las nuevas claves no coinciden", "");
        }

    }

    /**
     * @return the parroquias
     */
    public Parroquias getParroquias() {
        return parroquias;
    }

    /**
     * @param parroquias the parroquias to set
     */
    public void setParroquias(Parroquias parroquias) {
        this.parroquias = parroquias;
    }

    /**
     * @return the lparroquias
     */
    public List<Parroquias> getLparroquias() {
        return lparroquias;
    }

    /**
     * @param lparroquias the lparroquias to set
     */
    public void setLparroquias(List<Parroquias> lparroquias) {
        this.lparroquias = lparroquias;
    }

    /**
     * @return the parr
     */
    public String getParr() {
        return parr;
    }

    /**
     * @param parr the parr to set
     */
    public void setParr(String parr) {
        this.parr = parr;
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

    /**
     * @return the boolean1
     */
    public Boolean getBoolean1() {
        return boolean1;
    }

    /**
     * @param boolean1 the boolean1 to set
     */
    public void setBoolean1(Boolean boolean1) {
        this.boolean1 = boolean1;
    }

    /**
     * @return the provincias
     */
    public Provincias getProvincias() {
        return provincias;
    }

    /**
     * @param provincias the provincias to set
     */
    public void setProvincias(Provincias provincias) {
        this.provincias = provincias;
    }

    /**
     * @return the lCantones
     */
    public List<Cantones> getlCantones() {
        return lcantones;
    }

    /**
     * @return the cantones
     */
    public Cantones getCantones() {
        return cantones;
    }

    /**
     * @param cantones the cantones to set
     */
    public void setCantones(Cantones cantones) {
        this.cantones = cantones;
    }

    /**
     * @return the lprovincias
     */
    public List<Provincias> getLprovincias() {
        return lprovincias;
    }

    /**
     * @return the lcantones
     */
    public List<Cantones> getLcantones() {
        return lcantones;
    }

    /**
     * @param lcantones the lcantones to set
     */
    public void setLcantones(List<Cantones> lcantones) {
        this.lcantones = lcantones;
    }

    public void listen1(AjaxBehaviorEvent event) {
        System.out.println("provincias seteadas actualmente= " + provincias);
        recuperaCAnton();

    }

    public void listen2(AjaxBehaviorEvent event) {
        System.out.println("  public void listen2(AjaxBehaviorEvent event)");
        System.out.println("provincias = " + provincias);
        System.out.println("canton = " + cant);
        Iterator<Cantones> iterator = provincias.getCantonesList().iterator();
        while (iterator.hasNext()) {
            Cantones next = iterator.next();
            if (next.getCanton().equalsIgnoreCase(cant)) {
                lparroquias = next.getParroquiasList();
            }

        }

    }

    public void listen3(AjaxBehaviorEvent event) {
        List data = new ArrayList<HashMap>();
        data = parroquiasFacade.findProvCantParroq(provincias.getProvincia(), cant, parr);
//        System.out.println("data = " + data);
        Iterator<HashMap> iterator = data.iterator();

        String prov = "";
        String cant = "";
        String parr = "";

        while (iterator.hasNext()) {
            HashMap next = iterator.next();
            prov = next.get("CNT_PRV_CODIGO").toString();
            cant = next.get("CNT_CODIGO").toString();
            parr = next.get("CODIGO").toString();

//            System.out.println("fffffffffffffffffffffffffffffffffffffffff= " + prov + " " + cant + " " + parr);
        }
        ParroquiasPK p = new ParroquiasPK(prov, cant, parr);
        parroquias = parroquiasFacade.find(p);
        System.out.println("parroquias.getParroquia() " + parroquias.getParroquia());
    }

    public void recuperaCAnton() {
        lcantones = cantonesFacade.findByProvincia(provincias);
        Iterator<Cantones> iterator = lcantones.iterator();
        listaCantones.clear();
        while (iterator.hasNext()) {
            Cantones next = iterator.next();
            listaCantones.add(new SelectItem(next.getCanton(), next.getCanton()));
        }
//        System.out.println("listaCantones = " + listaCantones);

    }

    public String cambiarPCP() {
        paciente.setParroquias(parroquias);
        pacientesFacade.edit(paciente);
        parr = "";
        cant = "";
        return "";
    }

    /**
     * @return the listaCantones
     */
    public List<SelectItem> getListaCantones() {

        return listaCantones;
    }

    /**
     * @return the cant
     */
    public String getCant() {
        return cant;
    }

    /**
     * @param cant the cant to set
     */
    public void setCant(String cant) {
        this.cant = cant;
    }

    /**
     * @param listaCantones the listaCantones to set
     */
    public void setListaCantones(List<SelectItem> listaCantones) {
        this.listaCantones = listaCantones;
    }
}
