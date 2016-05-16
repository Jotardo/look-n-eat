package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;

import javax.swing.JOptionPane;

public class ConnectDB{
	private static Connection Conn;
	 
    public void MySQLConnection(String user, String pass, String db_name){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/"+db_name, user, pass);
            System.out.println("Successful connection with the server!");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    public void closeConnection() {
        try {
            Conn.close();
            System.out.println("Successful disconnection with the server!");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    public void createDB(String name) {
        try {
            String Query = "CREATE DATABASE " + name;
            Statement st = Conn.createStatement();
            st.executeUpdate(Query);
            MySQLConnection("root", "", name);
            JOptionPane.showMessageDialog(null, "The database " + name + " has been created successfully!");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    public void createTableNames(String name){
        try {
            String Query = "CREATE TABLE "+ name + ""
            		+ "(idRest int(255) NOT NULL AUTO_INCREMENT, "
            		+ "nRest text NOT NULL, "
            		+ "urlRest text NOT NULL, "
            		+ "idComm int(255), "
            		+ "idVal int(255), "
            		+ "idInfo int(255), "
            		+ "PRIMARY KEY(idRest))"
            		+ "ENGINE=InnoDB DEFAULT CHARSET=latin1";
            
            JOptionPane.showMessageDialog(null, "The table " + name + " has been created successfully!");
            Statement st = Conn.createStatement();
            st.executeUpdate(Query);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createTableComm(String name){
        try {
            String Query = "CREATE TABLE "+ name + ""
            		+ "(idComm int(255) NOT NULL AUTO_INCREMENT, "
            		+ "idRest int(255) NOT NULL, "
            		+ "ctext text, "
            		+ "PRIMARY KEY(idComm))"
            		+ "ENGINE=InnoDB DEFAULT CHARSET=latin1";
            
            JOptionPane.showMessageDialog(null, "The table " + name + " has been created successfully!");
            Statement st = Conn.createStatement();
            st.executeUpdate(Query);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertDataTableNames(String tName, String nRest, String urlRest){
        try {
            String Query = "INSERT INTO "+tName+" (idRest, nRest, urlRest, idComm, idVal, idInfo) "
            		+ "VALUES (NULL, '"+nRest+"', '"+urlRest+"', NULL, NULL, NULL);";
            
            Statement st = Conn.createStatement();
            st.executeUpdate(Query);
            //JOptionPane.showMessageDialog(null, "Datos almacenados de forma exitosa");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en el almacenamiento de datos del restaurante "+nRest);
        }
    }
    
    public void insertDataTableComm(String tName, int idRest, String comm){
    	//System.out.println("Entrando en funci�n de inserci�n en BD");
        try {
            String Query = "INSERT INTO "+tName+" (idComm, idRest, ctext) "
            		+ "VALUES (NULL, '"+idRest+"', '"+comm+"');";
            
            //System.out.println("Query creada");
            Statement st = Conn.createStatement();
            st.executeUpdate(Query);
            //System.out.println("Query ejecutada en BD");
            //JOptionPane.showMessageDialog(null, "Datos almacenados de forma exitosa");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en el almacenamiento de comentarios de restaurante");
        }
    }
    
    public ResultSet getNames(String table_name){
    	ResultSet rSet = null;
    	
        try {
            String Query = "SELECT idRest, nRest, urlRest FROM " + table_name;
            
            Statement st = Conn.createStatement();
            rSet = st.executeQuery(Query);
 
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la adquisici�n de datos");
        }
        
        return rSet;
    }
    
    public void deleteTable(String name) {
        try {
            String Query = "DELETE TABLE "+ name + "";
            
            JOptionPane.showMessageDialog(null, "The table " + name + " has been deleted successfully!");
            Statement st = Conn.createStatement();
            st.executeUpdate(Query);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}