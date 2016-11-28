/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.mil.he1.portal.paciente.servlet;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author christian_ruiz
 */
public class DatosDinamicos {

    Connection con;

    private String moduloPAcientes = "";
    private Object initContext;

    /**
     * @return the moduloPAcientes
     */
    public String getModuloPAcientes() {

        try {
            con = getDataSource().getConnection();
            ResultSet rs = con.prepareStatement("SELECT ID, URL_DESCRIP, URL FROM SEGURIDADES.SEG_URLS  WHERE ID = 1").executeQuery();
            while (rs.next()) {
                moduloPAcientes = rs.getString("URL");
            }
//            Insert into SEG_URLS                (ID, URL_DESCRIP, URL)              Values                (1, 'Sistema de Pacientes', '"/Paciente/userHE1?xphsumf=" ');

            con.close();
            con = null;
        } catch (Exception e) {
        }

        return moduloPAcientes;
    }

    public String loginPaciente(String usuario, String clave) throws NamingException, SQLException {
        String login = "";
        con = getDataSource().getConnection();

        CallableStatement prepareCall = con.prepareCall("{call SEGURIDADES.PACK_SEGURIDADES.LOGIN_PACIENTE (?, ?, ?) }");
        prepareCall.registerOutParameter(3, Types.VARCHAR);
        prepareCall.setString(1, usuario);
        prepareCall.setString(2, clave);
        prepareCall.executeQuery();
        login = prepareCall.getString(3);
        con.close();
        return login;
    }

    public String encripta(String usuario) throws NamingException, SQLException {
        String usuarioEncriptado = "";
        con = getDataSource().getConnection();
                                                                            
        CallableStatement prepareCall1 = con.prepareCall("{call  SEGURIDADES.PACK_SEGURIDADES.P_ENCRIPT_PALABRA (?, ?) }");
        prepareCall1.registerOutParameter(2, Types.VARCHAR);
        prepareCall1.setString(1, usuario);
        prepareCall1.executeQuery();
        usuarioEncriptado = prepareCall1.getString(2);
        con.close();
        return usuarioEncriptado;
    }

    private DataSource getDataSource() throws NamingException {
        Context c = new InitialContext();
        return (DataSource) c.lookup("java:comp/env/DataSource");
    }

    public String decrypt(String palabra) throws SQLException, NamingException {
        //en base a la cedula damos el hc
        String hc = "";
        con = getDataSource().getConnection();
        String usuarioEncriptado;
        try (CallableStatement prepareCall1 = con.prepareCall("{call  SEGURIDADES.PACK_SEGURIDADES.P_DECRIPT_PALABRA (?, ?) }")) {
            prepareCall1.registerOutParameter(2, Types.VARCHAR);
            prepareCall1.setString(1, palabra);
            prepareCall1.executeQuery();
            usuarioEncriptado = prepareCall1.getString(2);
        }
        ResultSet resultSet = con.prepareStatement("SELECT NUMERO_HC FROM SIS.PACIENTES  WHERE cedula = '" + usuarioEncriptado + "' AND ROWNUM = 1").executeQuery();

        while (resultSet.next()) {
            hc = resultSet.getString("NUMERO_HC");
        }
        con.close();
        return hc;

    }

        public String findURL() {

        try {
            con = getDataSource().getConnection();
            ResultSet rs = con.prepareStatement("SELECT ID, URL_DESCRIP, URL FROM SEGURIDADES.SEG_URLS  WHERE ID = 44").executeQuery();
            while (rs.next()) {
                moduloPAcientes = rs.getString("URL");
            }
//            Insert into SEG_URLS                (ID, URL_DESCRIP, URL)              Values                (1, 'Sistema de Pacientes', '"/Paciente/userHE1?xphsumf=" ');

            con.close();
            con = null;
        } catch (Exception e) {
        }

        return moduloPAcientes;
    }
    
    
    public String getPresionarSalir() {

        try {
            con = getDataSource().getConnection();
            ResultSet rs = con.prepareStatement("SELECT ID, URL_DESCRIP, URL FROM SEGURIDADES.SEG_URLS  WHERE ID = 2").executeQuery();
            while (rs.next()) {
                moduloPAcientes = rs.getString("URL");
            }
//            Insert into SEG_URLS                (ID, URL_DESCRIP, URL)              Values                (1, 'Sistema de Pacientes', '"/Paciente/userHE1?xphsumf=" ');

            con.close();
            con = null;
        } catch (Exception e) {
        }

        return moduloPAcientes;
    }

}
