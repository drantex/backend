/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slc.WS.inventario;

import com.slc.BO.inventory.InventoryBO;
import com.slc.configure.GenericResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Slenis
 */
@Path("/Inventory")
@Produces (MediaType.APPLICATION_JSON)
public class WsInventario {
    static InventoryBO inventario = new InventoryBO();
    //static LoginBO login = new LoginBO();
     
    /**
     * Carga un registro del inventario en base a un id
     * @param id identificador del registro
     * @return 
     */
    @Path("/LoadInventory")
    @POST    
    public GenericResponse LoadInventory (@FormParam("Id") int id){ 

        GenericResponse response = new GenericResponse();
        response.setObject( inventario.LoadInventory(id) );
        return response;

    }
    
    /**
     * Metodo encargado de cargar todos los registros de la tabla inventario
     * @return 
     */
    @Path("/LoadAllInventory")
    @POST    
    public GenericResponse LoadAllInventory (){

        GenericResponse response = new GenericResponse();
        response.setObject( inventario.LoadAllInventories() );
        return response;

    }
    
    /**
     * DeleteInventory Metodo encargado de eliminar un registro en base a un id
     * @param id identificador del registro
     * @return 
     */
    @Path("/DeleteInventory")
    @POST    
    public GenericResponse DeleteInventory (@FormParam("Id") int id){

        GenericResponse response = new GenericResponse();
        response.setObject( inventario.DeleteInventory(id) );
        return response;

    }
    
    /**
     * Metodo encargado de ingresar un nuevo registro al inventario
     * @param proucto nombre del producto
     * @param existencia cantidad en stock
     * @param precio precio del producto por unidad
     * @param proveedor proveedor del producto
     * @param foto imagen del producto
     * @return 
     */
    @Path("/SaveInventory")
    @POST    
    public GenericResponse SaveInventory (@FormParam("Producto") String proucto, 
                                          @FormParam("Existencia") int existencia, 
                                          @FormParam("Precio") int precio, 
                                          @FormParam("Proveedor") String proveedor,
                                          @FormParam("Foto") String foto){

        GenericResponse response = new GenericResponse();
        response.setObject( inventario.SaveInventory(proucto, existencia, precio, proveedor, foto) );
        return response;

    }
    
    /**
     * Metodo encargado de modificar un registro del inventario
     * @param id Identificador del producto
     * @param proucto nombre del producto
     * @param existencia cantidad en stock
     * @param precio precio del producto por unidad
     * @param proveedor proveedor del producto
     * @param foto imagen del producto
     * @return 
     */
    @Path("/UpdateInventory")
    @POST    
    public GenericResponse UpdateInventory (@FormParam("Id") int id,
                                            @FormParam("Producto") String proucto, 
                                            @FormParam("Existencia") int existencia, 
                                            @FormParam("Precio") int precio, 
                                            @FormParam("Proveedor") String proveedor,
                                            @FormParam("Foto") String foto){

        GenericResponse response = new GenericResponse();
        response.setObject( inventario.UpdateInventory(proucto, existencia, precio, proveedor, foto, id) );
        return response;

    }
    
}