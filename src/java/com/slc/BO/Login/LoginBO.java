
package com.slc.BO.Login;

import com.slc.configure.GenericResponse;
import com.slc.configure.ResultStatus;
import com.slc.database.Persister;
import java.util.HashMap;

/**
 *
 * @author stiven
 */
public class LoginBO {
    //static Persister con;   
    
    public GenericResponse Login( String user, String password)
    {
        GenericResponse response = new GenericResponse();
        Persister con = new Persister();
        HashMap<String, String> map = new HashMap<String, String>();
        
        try {
            
            String sql = "select count(*) from usuarios where user = '"+ user +"' " +"and password = '"+ password +"' ";
            
            map.put("User", user);
//            map.put("Status", con.setSelectSql(sql));
            
            response.setResultStatus(ResultStatus.OK);
            response.setObject( map ); 
        } 
        catch (Exception e) {
           response.setResultStatus(ResultStatus.FAILURE);
        }
        
        return response;
    }
}
