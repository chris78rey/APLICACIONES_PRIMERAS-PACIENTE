/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mil.he1.portal.paciente.servlet;

import ec.mil.he1.portal.paciente.mbeans.PacienteJSFManagedBean;
import he1.seguridades.entities.SegUsuario;
import he1.seguridades.entities.nuevos.VUsuariosClasif;
import he1.seguridades.sessions.SegUsuarioFacade;
import he1.seguridades.sessions.VUsuariosClasifFacade;
import he1.sis.entities.Pacientes;
import he1.sis.sessions.PacientesFacade;
import he1.utilities.SesionSeguridades;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author christian_ruiz
 */
@WebServlet(name = "UsuarioServlet", urlPatterns = {"/userHE1"})
public class UsuarioServlet extends HttpServlet {

    @EJB
    private PacientesFacade pacientesFacade;
    Pacientes pacientes;

    @EJB
    private SegUsuarioFacade segUsuarioFacade;

    @EJB
    private SesionSeguridades encryptFacade;

    @EJB
    private VUsuariosClasifFacade vUsuariosClasifFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            //historia clinica
            String hc = "";
            String usuid = "";
            //usuario encryptado
            String usuarioEncriptado = (request.getParameter("xphsumf"));
            //usuarioEncriptado=segUsuarioFacade.p_encript_palabra("687140");
            usuid = segUsuarioFacade.p_decript_palabra(usuarioEncriptado);

            SegUsuario segusuario = segUsuarioFacade.find(new BigDecimal(usuid));

            hc = segusuario.getPacientes().getNumeroHc().toString();
            pacientes = pacientesFacade.findByHc(new Integer(hc));

            HttpSession session = request.getSession(true);
            PacienteJSFManagedBean pacienteJSFManagedBean = new PacienteJSFManagedBean();
            pacienteJSFManagedBean.setPacientes(pacientes);
            session.setAttribute("pacienteJSFManagedBean", pacienteJSFManagedBean);
            session.setAttribute("cedulausuario", pacienteJSFManagedBean.getPacientes().getCedula());
       
            //se almacena el usuario estandarizado
            ServletContext servletContext = request.getServletContext();
            String contextPath = servletContext.getContextPath();
            System.out.println("servletContext = " + servletContext);
            //aca se cambia
            VUsuariosClasif findByCedulaLogin = vUsuariosClasifFacade.findByCedulaLogin( pacienteJSFManagedBean.getPacientes().getCedula() );
            session.setAttribute("findByCedulaLogin", findByCedulaLogin);

            if (!usuarioEncriptado.equalsIgnoreCase("SIN DATO")) {
                request.getServletContext().getRequestDispatcher("/faces/Menu/Menu.xhtml").forward(request, response);
            }

        } catch (SQLException | NamingException | NumberFormatException | ServletException | IOException e) {
            System.out.println("e.getLocalizedMessage() = " + e.getLocalizedMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
