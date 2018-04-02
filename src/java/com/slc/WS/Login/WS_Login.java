/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slc.WS.Login;


import com.slc.BO.Login.LoginBO;
import com.slc.configure.GenericResponse;
import com.slc.configure.ResultStatus;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/*
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
*/

/**
 *
 * @author stiven
 */

//@Path ("/Login")
@Path("/Login")
@Produces (MediaType.APPLICATION_JSON)

public class WS_Login {
        
    static LoginBO login = new LoginBO();
    
    @Path("/LoadLogin")
    @POST    
    public GenericResponse LoadLogin (@FormParam("User") String User, @FormParam("Password") String Password){

        GenericResponse response = new GenericResponse();

        response.setObject( login.Login(User, Password) );
        return response;

    }

    @Path("/Test")
    @POST    
    public void Test (@FormParam("User") String User, @FormParam("Password") String Password){

        //GenericResponse response = new GenericResponse();
        try {

            System.out.println("User: "+ User + " Pass: " +Password);

        } 
        catch (Exception e) {
            System.out.println("eX: " +e);

        }

    }
        
        

}







































/* public String LoadLogin()
	{  
            
            try {
                System.out.println("Stiven Lenis Cardona");
                //Stiven = "Stiven Lenis Cardona";
            } catch (Exception e) {
                    System.out.println("Error De Programador");
                   // return "Consumiste tu primer servicio";//"Consumiste tu primer servicio";
            }
		 return Stiven;
	}*/