/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slc.configure;

/**
 *
 * @author Usuario
 */
public enum StandCommand {
    
    DRIVERORACLE("oracle.jdbc.driver.OracleDriver"),
    DRIVERMYSQL("com.mysql.jdbc.Driver"),
    DRIVERSQLSERVER("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    
    public final String value;
    
    private StandCommand(String value){
        this.value = value;
    }
    
    public String getValue(){
        return this.value;
    }
}
