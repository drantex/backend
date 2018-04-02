/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slc.database;

//import bimmer.MAEFAC_SLENIS;
//import bimmer.MIS_ZBMR_SLENIS;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stiven Lenis
 */
public class ConexionSga {
   
    /*Atributos de conexion*/
    public Connection con;
    private String DRIVER= "oracle.jdbc.driver.OracleDriver";
    
    //Servidor de pruebas SGA 0
    private String urlPrue= "jdbc:oracle:thin:@10.244.141.154:1523:SGA10";
    private String userPrue= "COLLECTIONS";
    private String passPrue= "Soport3#col";
    
    //Servidor de prefacturacion SGA 1
    private String urlCofac= "jdbc:oracle:thin:@10.244.141.154:1523:ficsga16";
    private String userCofac= "COLLECTIONS";
    private String passCofac= "Soport3#col";
    
    //Servidor de produccion SGA 2
    private String urlProd= "jdbc:oracle:thin:@10.244.140.36:1527:cofacprd";
    private String userProd= "SUPPORTGROUP";
    private String passProd= "Cambio_20_17";
         
    //Servidor de Produccion Onyx SGA 3
    private String urlOnyx= "jdbc:sqlserver://COLBTASQL01:1433";
    private String userOnyx= "IROMERO_BILLING";
    private String passOnyx= "Ipt0t4l2017";
    
    //Servidor de Produccion MISS 4
    private String urlMis= "jdbc:oracle:thin:@cobsrt05:1527:SRTMIS";
    private String userMis= "CEMARTINEZ";
    private String passMis= "YE6KaSORT1$";
    
    /*Atributos de clase*/
    private Statement stm = null;
    private PreparedStatement preStatement = null;
    private ResultSet rs = null;
    private ResultSetMetaData rsmd = null;
    private ArrayList<List> ListObject = null;
    private List<String> ListItems = null;
    private Object obj = null;
    
    
    
    public ConexionSga(int schema){
        try{              
            if(schema == 0){
                con = (Connection) DriverManager.getConnection(this.urlPrue,this.userPrue,this.passPrue);
                System.out.println("Conexion SGA pruebas!!");
            }
            else if(schema == 1){
                con = (Connection) DriverManager.getConnection(this.urlCofac,this.userCofac,this.passCofac);
                System.out.println("Conexion SGA Prefacturación!!");
            }
            else if(schema == 2){
                
                con = (Connection) DriverManager.getConnection(this.urlProd,this.userProd,this.passProd);
                System.out.println("Conexion SGA Produccion!!");
            }
            else if(schema == 3){
                DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
                con	= (Connection) DriverManager.getConnection(this.urlOnyx,this.userOnyx,this.passOnyx);
                System.out.println("Conexion Onyx Produccion!!");
            }  
            else if(schema == 4){
                
                con	= (Connection) DriverManager.getConnection(this.urlMis,this.userMis,this.passMis);
                System.out.println("Conexion MIS Produccion!!");
            }
            
            Class.forName(DRIVER);
            
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    
    /**
     * @name setDeleteSql 
     * @Description Metodo encargado de eliminar registros basado en una sentencia SQL
     * @param sql
     * @return
     * @throws SQLException 
     */
    public int setDeleteSql (String sql) throws SQLException{

        try 
        {
            stm = con.createStatement();
            System.out.println("Retunr setDeleteSql: True");
            return stm.executeUpdate(sql);
            
        }
        catch (Exception e) {
            System.out.println("Retunr setDeleteSql: False---> "+ e);
            return -1;
        }

    }
    
    /**
     * @name setUpdateSql 
     * @Description Metodo encargado de modificar registros basado en una sentencia SQL
     * @param sql
     * @return
     * @throws SQLException 
     */
    public int setUpdateSql (String sql) throws SQLException{

       try {
           stm = con.createStatement();
           System.out.println("Retunr setUpdateSql: Ok");
           return stm.executeUpdate(sql);
        }
        catch (Exception e) {
           System.out.println("Retunr setUpdateSql: False---> "+ e);
           return -1;
        }

    }
         
    /**
     * @name setInsertSql 
     * @Description Metodo encargado de insertar registros basado en una sentencia SQL
     * @param sql
     * @return
     * @throws SQLException 
     */     
    public int setInsertSql (String sql) throws SQLException{

        try {
            Statement stmstm = con.createStatement();
            System.out.println("Return setInsertSql: True");
           return stmstm.executeUpdate(sql);
        }
        catch (Exception e) {
            System.out.println("Return setInsertSql: False--> "+e);
            return -1;
        }
    }
    
    public ArrayList<List> getSelectSql(String sql) throws SQLException
	{
            try
            {
                preStatement = con.prepareStatement(sql);		
                rs           = preStatement.executeQuery();
                rsmd         = rs.getMetaData();
                ListObject   = new ArrayList<List>();

                System.out.println("Columnas: "+ rsmd.getColumnCount());
                while(rs.next())
                {

                    for(int i = 1, findColumn = rsmd.getColumnCount(); i <= findColumn; i++)
                    {
                        if(i == 1)
                        {
                            ListItems = new ArrayList<String>();
                            ListItems.add(rs.getString(i));
                        }
                        else if(i == findColumn)
                        {
                            ListItems.add(rs.getString(i));
                            ListObject.add(ListItems);
                        }
                        else
                        {
                            ListItems.add(rs.getString(i));
                        }

                        if( findColumn == 1)
                        {
                            ListObject.add(ListItems);
                        }
                    }
                }
                    obj = ListObject;
                    //obj = new Gson().toJson(obj);
            }
            catch (SQLException e) 
            {

                    System.out.println("exception getSqlSelect: "+e);
                  //  return e;
            }
            //return (ArrayList) ListObject;
           return ListObject;
	}
    
    public ResultSet getSqlSelect (String sql){
        
        try {
            preStatement = con.prepareStatement(sql);		
            rs           = preStatement.executeQuery();
        } 
        catch (SQLException e) {
            System.out.println("Error en getSqlSelect "+ e);
        }
        finally {
            try {
                //rs.close();
                //preStatement.close();
               // con.close();
                
            } catch (Exception e) {
            }
            
        
        }
        return rs;
    }
    
    public ResultSet getSqlSelect2 (String sql){
        
        try {
            preStatement = con.prepareStatement(sql);		
            rs           = preStatement.executeQuery();
        } 
        catch (SQLException e) {
            System.out.println("Error en getSqlSelect "+ e);
        }
        finally {
            try {
                //rs.close();
                //preStatement.close();
               // con.close();
                
            } catch (Exception e) {
            }
            
        
        }
        return rs;
    }
    
    public boolean batch(String sql, ResultSet rs){
        
        try {
            
            /*se setea el insert*/
            PreparedStatement ps = con.prepareStatement(sql);
            
            /*Variable que define el tamaño del batch, se realiza insercion cada 5mil reg*/
            final int batchSize = 5000;
            int count = 0;
            while(rs.next())
            {
                ps.setInt(1, rs.getInt(1));
//                ps.setInt(2, rs.getInt(2));
//                ps.setString(3, rs.getString(3));
//                ps.setInt(4, rs.getInt(4));
//                ps.setInt(5, rs.getInt(5));
//                ps.setDouble(6, rs.getDouble(6));
//                ps.setDouble(7, rs.getDouble(7));
//                ps.setInt(8, rs.getInt(8));
                ps.addBatch();
                count++;
                
                if(count % batchSize == 0){
                     ps.executeBatch();
                }
                
            }
            ps.executeBatch(); // insert remaining records
            ps.close();
            return true;
            
        } catch (SQLException e) {
            System.out.println("Error ahhhhhh!!: "+e);
            return false;
        }
        
    }
    
    public ResultSet getSqlSelectWithParam (String sql, ArrayList<String> params){
        
        try {
            preStatement = con.prepareStatement(sql);
            
            for(int i = 0; i<params.size(); i++){
                preStatement.setString( (i + 1), params.get(i));
            }
            
            rs = preStatement.executeQuery();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }        
        return rs;
    }
    


    
    public PreparedStatement setPrepared(String sql){
        
        PreparedStatement ps = null;
        
        try {
            ps = con.prepareStatement(sql);
        } catch (Exception e) {
        }
        
        
        
        return ps;
    }
    public void matar(){
        
     /*stm.close();
     preStatement = null;
    private ResultSet rs = null;
    private ResultSetMetaData rsmd = null;
    private ArrayList<List> ListObject = null;
    private List<String> ListItems = null;
    private Object obj = null;
             */
        try {
            con.close();
        } catch (Exception e) {
            System.out.println("Se tosto esta mierda");
        }
        
    
    }
    
   
}
