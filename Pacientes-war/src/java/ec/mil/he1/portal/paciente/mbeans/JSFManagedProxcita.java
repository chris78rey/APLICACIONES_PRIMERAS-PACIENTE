/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mil.he1.portal.paciente.mbeans;

import he1.utilities.SesionSeguridades;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author christian_ruiz
 */
@ManagedBean(name = "proxima")
@ViewScoped
public class JSFManagedProxcita implements Serializable {

    @EJB
    private SesionSeguridades sesionSeguridades;

    /**
     * Creates a new instance of JSFManagedProxcita
     */
    public JSFManagedProxcita() {
    }
    private List<Map> listaturnossiguientes = new ArrayList<>();

    /**
     * @return the listaturnossiguientes
     */
    public List<Map> getListaturnossiguientes() {
        List data = new ArrayList<HashMap>();
        data = sesionSeguridades.listaturnossiguientes();
        listaturnossiguientes = data;
        return listaturnossiguientes;
    }
}
