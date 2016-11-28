/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mil.he1.portal.paciente.mbeans;


import he1.utilities.SesionPersona;
import he1.utilities.SesionSeguridades;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author christian_ruiz
 */
@ManagedBean(name = "factura")
@ViewScoped
public class JSFManagedBeanFactura implements Serializable {

    @Resource(name = "he1")
    private DataSource he1;

    @EJB
    private SesionPersona sesionPersona;

    PacienteJSFManagedBean pacienteJSFManagedBean;
    @EJB
    private SesionSeguridades sesionSeguridades;

    /**
     * Creates a new instance of JSFManagedBeanFactura
     */
    public JSFManagedBeanFactura() {
        PacienteJSFManagedBean pacienteJSFManagedBean = new PacienteJSFManagedBean();
    }

    @PostConstruct
    private void init() {

    }

    //esto se coloca en el Managed Bean  
    private List<Map> listaFacturasPorHC = new ArrayList<>();

    /**
     * @return the listaFacturasPorHC
     */
    public List<Map> getListaFacturasPorHC() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        pacienteJSFManagedBean = (PacienteJSFManagedBean) session.getAttribute("pacienteJSFManagedBean");
        listaFacturasPorHC = sesionPersona.listaFacturasPorHC(pacienteJSFManagedBean.getPacientes().getNumeroHc().toString());
        return listaFacturasPorHC;
    }

    public String buscaIDFactura(String rid, String valor) {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            //PASA PARAM ID de la factura y la caha
            ec.redirect(ec.getRequestContextPath() + "/xldrHnd?kkjdndj=" + rid + "&sddvaxd=" + valor);
        } catch (IOException ex) {
            Logger.getLogger(JSFManagedBeanFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public StreamedContent doReport() throws Exception {
        // setting up some basic stuff
        // This is not so pretty, I would much rather like not to pull web beans into here, 
        // but you know...

        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ectx = context.getExternalContext();
        HttpServletRequest req = (HttpServletRequest) ectx.getRequest();
        ServletContext servletContext = req.getSession().getServletContext();

        //HashMap<String, String> hashMap = new HashMap<String, String>();
        HashMap hashMap = new HashMap();
        hashMap.put("rid", "AAA2PEAAGAAKbcbAAC");
        hashMap.put("valor", "36.88");

        String reportFilename = null;
        Connection connection = null;
        byte[] bytes = null;
        try {
            String jrxmlPath = servletContext.getRealPath("/WEB-INF/report/");
            String reportfile = "FacturasHE1.jasper";
            // directories where the report files are 
            String jrxmlfile = jrxmlPath + "/" + reportfile;

            //InputStream is = this.getClass().getClassLoader().getResourceAsStream("/WEB-INF/report/"+jrxmlfile);
            JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlfile);
            //is.close();

            // we use the ReportsCharts class as data source 
            // instead of connecting directly to the database
            connection = he1.getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hashMap, connection);

            // and directories where the reports will be generated
            String reportsPath = servletContext.getRealPath("/WEB-INF/report/");
            reportFilename = "reports_" + req.getSession().getId() + ".html";
            String reportFile = reportsPath + "/" + reportFilename;

            bytes = JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException e) {
            System.out.println("e = " + e.getLocalizedMessage());
        } finally {
            connection.close();
        }

//        HttpServletResponse response = (HttpServletResponse) ectx.getResponse();
//
//        response.setHeader("Content-disposition", "inline;filename=morningShift.pdf;");
//        response.setContentType("application/pdf");
//        response.setContentLength(bytes.length);
//
//        response.getOutputStream().write(bytes);
//        response.flushBuffer();
//        context.responseComplete();
        // Redirect user browser to the report page
//        String redirect_file = "/report/" + reportFilename;
//        //String redirect_page = "/ejbca/adminweb" + redirect_file;
//
//        ectx.dispatch(redirect_file);
//        context.responseComplete();

        return new DefaultStreamedContent(new ByteArrayInputStream(bytes));
    }

}
