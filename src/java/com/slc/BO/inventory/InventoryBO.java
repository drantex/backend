/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slc.BO.inventory;

import com.slc.configure.GenericResponse;
import com.slc.configure.ResultStatus;
import com.slc.database.Persister;

/**
 *
 * @author Usuario
 */
public class InventoryBO {
    
    /**
     * Metodo encargado de consultar los datos de la entidad inventario por id 
     * @param id registro unico identificador
     * @return GenericResponse informacion del inventario
     */
    public GenericResponse LoadInventory( int id )
    {
        GenericResponse response = new GenericResponse();
        Persister con = new Persister();
        
        try {
            String sql = "select * from inventario where id = '"+ id +"' ";
            response.setResultStatus(ResultStatus.OK);
            response.setObject( con.getSelectSqlMap(sql) ); 
        } 
        catch (Exception e) {
           response.setResultStatus(ResultStatus.FAILURE);
        }
        
        return response;
    }
    
    public GenericResponse LoadAllInventories()
    {
        GenericResponse response = new GenericResponse();
        Persister con = new Persister();
        
        try {
            String sql = "select * from inventario ";
            response.setResultStatus(ResultStatus.OK);
            response.setObject( con.getSelectSqlMap(sql) ); 
        } 
        catch (Exception e) {
           response.setResultStatus(ResultStatus.FAILURE);
        }
        
        return response;
    }
    
    public GenericResponse DeleteInventory( int id )
    {
        GenericResponse response = new GenericResponse();
        Persister con = new Persister();
        
        try {
            String sql = "delete from inventario where id = '" + id + "'";
            con.setDeleteSql(sql);
            response.setResultStatus(ResultStatus.OK);
             
        } 
        catch (Exception e) {
           response.setResultStatus(ResultStatus.FAILURE);
        }
        
        return response;
    }
    
    public GenericResponse SaveInventory( String proucto, 
                                          int existencia, 
                                          int precio, 
                                          String proveedor,
                                          String foto )
    {
        GenericResponse response = new GenericResponse();
        Persister con = new Persister();
        
        try {
            String sql = "insert into inventario (producto, existencia, precio, proveedor, foto) values ('"+ proucto +"' ,'"+ existencia +"','"+ precio +"','"+ proveedor +"','"+ foto +"')";
            con.setInsertSql(sql);
            response.setResultStatus(ResultStatus.OK);
             
        } 
        catch (Exception e) {
           response.setResultStatus(ResultStatus.FAILURE);
        }
        
        return response;
    }
    
    public GenericResponse UpdateInventory( String proucto, 
                                            int existencia, 
                                            int precio, 
                                            String proveedor,
                                            String foto,
                                            int id)
    {
        GenericResponse response = new GenericResponse();
        Persister con = new Persister();
        
        try {
            String sql = "update inventario set producto ='"+ proucto +"' , existencia = '"+ existencia +"', precio = '"+ precio +"', proveedor = '"+ proveedor +"', foto = '"+ foto +"' where id = '"+ id + "'" ;
            con.setInsertSql(sql); 
            response.setResultStatus(ResultStatus.OK);
            
        } 
        catch (Exception e) {
           response.setResultStatus(ResultStatus.FAILURE);
        }
        
        return response;
    }
    
    
    
}
