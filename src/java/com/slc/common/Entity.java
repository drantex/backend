/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slc.common;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Slenis
 */
public class Entity {
    
    /*Mapa encargado de contener las entidades existentes en un Schema*/
    private  String NameEntity;
    
    /*Mapa encargado de contener los campos de las entidades con su tipo y tamaño*/
    private HashMap <String, String > FieldsEntity;
    
    /*Mapa encargado de contener la llave primaria de la entidad*/
    private HashMap <Integer, String> PrimaryKeyEntity;
    
    
    /**Mapa encargado de contener la llave foranea de la entidad*/
    private HashMap <Integer, String> ForeingKeyEntity;
    
    /*Mapa encargado de contener los indices unicos de la entidad*/
    private HashMap <Integer, String> UnikIndexEntity;

    public Entity() {
    }
    
    
    /*Metodo constructor de clase*/

    public String getNameEntity() {
        return NameEntity;
    }

    public void setNameEntity(String NameEntity) {
        this.NameEntity = NameEntity;
    }
    
    public HashMap <String, String > getFieldsEntity() {
        return FieldsEntity;
    }

    public void setFieldsEntity(HashMap <String, String > FieldsEntity) {
        this.FieldsEntity = FieldsEntity;
    }

    public HashMap<Integer, String> getPrimaryKeyEntity() {
        return PrimaryKeyEntity;
    }

    public void setPrimaryKeyEntity(HashMap<Integer, String> PrimaryKeyEntity) {
        this.PrimaryKeyEntity = PrimaryKeyEntity;
    }

    public HashMap<Integer, String> getForeingKeyEntity() {
        return ForeingKeyEntity;
    }

    public void setForeingKeyEntity(HashMap<Integer, String> ForeingKeyEntity) {
        this.ForeingKeyEntity = ForeingKeyEntity;
    }

    public HashMap<Integer, String> getUnikIndexEntity() {
        return UnikIndexEntity;
    }

    public void setUnikIndexEntity(HashMap<Integer, String> UnikIndexEntity) {
        this.UnikIndexEntity = UnikIndexEntity;
    }

 
}
