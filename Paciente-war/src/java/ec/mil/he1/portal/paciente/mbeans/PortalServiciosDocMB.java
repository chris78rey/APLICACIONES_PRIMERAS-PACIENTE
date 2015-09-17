/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mil.he1.portal.paciente.mbeans;

import he1.seguridades.entities.nuevos.PortalServiciosDoc;
import he1.seguridades.sessions.PortalServiciosDocFacade;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author christian_ruiz
 */
@ManagedBean
@RequestScoped
public class PortalServiciosDocMB {

    @EJB
    private PortalServiciosDocFacade portalServiciosDocFacade;

    @PostConstruct
    private void init() {

        setLposer(portalServiciosDocFacade.findAll());
    }

    /**
     * Creates a new instance of PortalServiciosDoc
     */
    public PortalServiciosDocMB() {
    }
    PortalServiciosDoc portalServiciosDoc = new PortalServiciosDoc();

    private List<PortalServiciosDoc> lposer = new ArrayList<>();

    /**
     * @return the lposer
     */
    public List<PortalServiciosDoc> getLposer() {
        return lposer;
    }

    /**
     * @param lposer the lposer to set
     */
    public void setLposer(List<PortalServiciosDoc> lposer) {
        this.lposer = lposer;
    }

}
