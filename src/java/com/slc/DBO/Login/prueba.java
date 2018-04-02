
package com.slc.DBO.Login;


import com.slc.database.Persister;
import java.sql.SQLException;


public class prueba {
    
    
    
    public static void main (String ARGS[]) 
    {
            //System.out.println("Usuario: "+ user + "  Pass: "+password);
        prueba pp = new prueba();
        System.out.println("obj: "+pp.result());
       
            //response.setObject( con.getSelectSql(sql) );
    }
    
    
    
    public Object result (){
        
        Persister con = new Persister();
        String user = "stiven_lenis";
        String password = "0fe4ac68a6";
        try 
        {
            //String sql = "select count(*) from user where username = '"+user+"' and userpass =  '"+password+"'";
             String sql = "select * from user "
                        +"where username = '"+user +"' "
                        +"and userpass = '"+ password +"' ";
           /* String sql = "select count(*) from user "
                        +"where username = "+user+" "
                        +"and userpass = "+ password +"; ";*/
            
             return con.getSelectSql(sql) ;
        } catch (Exception e) {
            return e;
        }
    }
    
}
