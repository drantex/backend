package com.slc.database;


import com.google.gson.Gson;
import com.slc.common.Entity;
import com.slc.configure.ResultStatus;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Persister
 *
 * @author Stiven Lenisa
 * @since 08/12/2015
 * Historia de Modificaciones
 * -------------------------------------------------------------
 * Autor	            Fecha           Modificacion
 * -----------------  -------------- ----------------------------
 */

public class Persister implements java.io.Serializable{
	private Connection con;
        private String DRIVER= "com.mysql.jdbc.Driver";
        private String url= "jdbc:mysql://localhost/ionic";
        private String user= "root";
        private String passwd= "";
    
    
        private Statement stm = null;
        private PreparedStatement preStatement	= null;
        private ResultSet rs =	null;
        private ResultSetMetaData rsmd = null;
        
        private Object obj = null;
        private ArrayList<List> ListObjectList = null;
        private ArrayList<HashMap> ListObjectMaps = null;
        private List<String> ListItems = null;
        private HashMap<String, String> mapItems = null;

	//public Persister (DRIVER, url, user, passwd)
        public Persister ()
	{
            try
            {
                Class.forName(DRIVER);
                con = (Connection) DriverManager.getConnection(url,user,passwd);			
                System.out.println("Conexion Exitosa");
            }
            catch (SQLException e) 
            {
                System.out.println("contructor1");
                    e.printStackTrace();
            }
            catch (ClassNotFoundException e) 
            {
                System.out.println("contructor2");
                    e.printStackTrace();
            }
	}
        
        public static void main(String args[])
        {
            Persister res=new Persister();
            try
            {
                System.out.println( res.getSelectSqlList("select * from usuarios where user = 'dante' "));
                //res.getMetadata("COLLECTIONS");
                
            } 
            catch(Exception e)
            {
                e.printStackTrace();
            }
  
        }
        
        /**
         * Metodo utilizado para validar si existe información utilizando la funcion sql count.
         * @param sql
         * @return 
         */
        public ResultStatus setSelectSql (String sql){
            
            ResultStatus response = ResultStatus.FAILURE;
            
            try 
            {
                preStatement = con.prepareStatement(sql);		
                rs           = preStatement.executeQuery();
                if(rs.next())
                {
                    if(rs.getInt(1) > 0)
                    {
                        response =  ResultStatus.OK;
                    }
                }
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
                response = ResultStatus.FAILURE;
            }
            finally
            {
                try 
                {
                    rs.close();
                    preStatement.close();
                    con.close();
                    
                } catch (Exception e) 
                {
                    e.printStackTrace();
                }  
            }
            return response;
        }
        
        /**
         * Funcion encargada de eliminar un registro en base de datos
         * @param sql sentencia SQL a ejecutar
         * @return 
         */
        public ResultStatus setDeleteSql (String sql) {

            try {
                stm = con.createStatement();
                stm.executeUpdate(sql);
                return ResultStatus.OK;
            }
            catch (Exception e) {
                e.printStackTrace();
                return ResultStatus.FAILURE;
            }
            finally
            {
                try 
                {
                    stm.close();
                    con.close();
                } catch (Exception e) 
                {
                    e.printStackTrace();
                }  
            }

        }
        
        /**
         * Funcion encargada de modificar un registro en base de datos
         * @param sql sentencia SQL a ejecutar
         * @return 
         */
         public ResultStatus setUpdateSql (String sql) {

            try {
                stm = con.createStatement();
                 stm.executeUpdate(sql);
                 return ResultStatus.OK;
            }
            catch (Exception e) {
                e.printStackTrace();
                return ResultStatus.FAILURE;
            }
            finally
            {
                try 
                {
                    stm.close();
                    con.close();
                } catch (Exception e) 
                {
                    e.printStackTrace();
                }  
            }

        }
        
        /**
         * Funcion encargada de insertar un registro en base de datos
         * @param sql sentencia SQL a ejecutar
         * @return 
         */
        public ResultStatus setInsertSql (String sql) {

            try {
                stm = con.createStatement();
                stm.executeUpdate(sql);
                return ResultStatus.OK;
            }
            catch (Exception e) {
                e.printStackTrace();
                return ResultStatus.FAILURE;
            }
            finally
            {
                try 
                {
                    stm.close();
                    con.close();
                } catch (Exception e) 
                {
                    e.printStackTrace();
                }  
            }
        }
        
        public void getMetadata(String owner) {
            
            /*Mapa encargado de contener las entidades existentes en un Schema*/
            HashMap <Integer, Entity > contentEntities = new HashMap< >();
            
            String tipos[] = new String[]{"TABLE"};
            
            /*Variable encargada de llevar la secuencia de entidades obtenidas del schema*/
            int countEntity;
                        
            int countIndex;
            
            /*Variable encargada de contener el nombre de la tabla obtenida en la metadata del Schema*/
            String tableName;
            
            /*Variable encargada de indicar si un campo es requerido o no sobre la entidad*/
            String fielNull;
            
            String fielDescripcion;
            
            String nameIndex;
            
            /*Coleccion recuperada del schema*/
            ResultSet tablesRs;
            
            DatabaseMetaData metadatos;
            
            
            try {
                
                metadatos =  con.getMetaData();
                tablesRs = metadatos.getTables(null, owner, "%", tipos);
                
                countEntity = 0;
                while (tablesRs.next()) 
                {
                    
                    Entity objEntity = new Entity();
                    
                    tableName = tablesRs.getObject(3).toString() ;
                    
                    objEntity.setNameEntity(tableName);
                    
                    /*Se obtienen los campos de una entidad con su tipo, tamaño y se valida si estos son o no null*/
                    HashMap <String, String > fielEntity = new HashMap< >();
                    try (ResultSet entityColumsRs = metadatos.getColumns(null, owner, tableName, null)) {
                        
                        while(entityColumsRs.next()){
                            if(!entityColumsRs.getString(4).equals("null")){
                                if(entityColumsRs.getInt(11) == 0){
                                    fielNull = "Not Null";
                                }
                                else{
                                    fielNull = "Null";
                                }
                                fielDescripcion = entityColumsRs.getString(6) + "(" + entityColumsRs.getString(7) + ") " +fielNull;       
                                fielEntity.put(entityColumsRs.getString(4) , fielDescripcion);
                            }
                        }
                        objEntity.setFieldsEntity(fielEntity);
                    }
                    
                    /*Se obtiene la o las llaves primarias de una entidad*/
                    
                    HashMap <Integer, String > pkEntity = new HashMap< >();
                    try (ResultSet primaryKeysRs = metadatos.getPrimaryKeys(null, owner, tableName)) {
                        countIndex = 0;
                        while(primaryKeysRs.next()){
                            if(!primaryKeysRs.getString(4).equals("null")){
                                pkEntity.put(countIndex, primaryKeysRs.getString(4));
                                countIndex++;
                            }
                        }
                        objEntity.setPrimaryKeyEntity(pkEntity);                        
                    }
                    
                    /*Se obtiene la o las llaves foraneas de una entidad*/
                    
                    HashMap <Integer, String > contentPk = new HashMap< >();
                    try (ResultSet foreingKeysRs = metadatos.getImportedKeys(null, owner, tableName)) {
                        countIndex = 0;
                        while(foreingKeysRs.next()){
                            if(!foreingKeysRs.getString(8).equals("null")){
                                contentPk.put(countIndex, foreingKeysRs.getString(8));
                                countIndex++;
                            }
                        }
                        objEntity.setForeingKeyEntity(contentPk);
                    }
                    
                    /*Mapa encargado de contener la secuenncia de cada llave foranea*/
                    HashMap <Integer, String > unikIndex = new HashMap< >();
                    
                    try (ResultSet indexEntityRs = metadatos.getIndexInfo(null, owner, tableName, true, true)) {
                        countIndex = 0;
                        while(indexEntityRs.next()){
                            
                            nameIndex = indexEntityRs.getString("COLUMN_NAME");
                            if( nameIndex != null){
                                unikIndex.put(countIndex, nameIndex );
                                countIndex++;
                            }
                        }
                        objEntity.setUnikIndexEntity(unikIndex);
                    }
                    
                    contentEntities.put(countEntity, objEntity);
                    countEntity++;  
                }
                System.out.println(new Gson().toJson(contentEntities));
                
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        /**
         * Metodo encargdo de manejar el resulSet para entregarlo en un objeto manejable a devolver al front
         * @param sql
         * @return Objeto 
         * @throws SQLException 
         */
	public ArrayList<List> getSelectSqlList(String sql) 
	{
		
            try
            {
                preStatement = con.prepareStatement(sql);		
                rs           = preStatement.executeQuery();
                rsmd         = rs.getMetaData();
                ListObjectList   = new ArrayList<List>();
                
                System.out.println("Columnas: "+ rsmd.getColumnCount());
                while(rs.next())
                {
                    for(int i = 1, findColumn = rsmd.getColumnCount(); i <= findColumn; i++)
                    {
                        System.out.println( rsmd.getColumnName(i) );
                        //System.out.println(rsmd.getColumnTypeName(i ));
                        //System.out.println(rsmd.getPrecision(i ));
                        if(i == 1)
                        {
                            ListItems = new ArrayList<String>();
                            ListItems.add(rs.getString(i));
                        }
                        else if(i == findColumn)
                        {
                            ListItems.add(rs.getString(i));
                            ListObjectList.add(ListItems);
                        }
                        else
                        {
                            ListItems.add(rs.getString(i));
                        }

                        if( findColumn == 1)
                        {
                            ListObjectList.add(ListItems);
                        }
                    }
                }       
            }
            catch (SQLException e) 
            {
                System.out.println("exception getSqlSelect: "+e);        
            }
            finally
            {
                try 
                {
                    preStatement.close();
                    rs.close();
                    con.close();
                } catch (Exception e) 
                {
                    e.printStackTrace();
                }  
            }
            
            //con.close();
            
            return ListObjectList;
	}
        
        /**
         * Metodo encargdo de manejar el resulSet para entregarlo en un objeto manejable a devolver al front
         * @param sql
         * @return Objeto 
         * @throws SQLException 
         */
	public ArrayList<HashMap> getSelectSqlMap(String sql) 
	{
		
            try
            {
                preStatement = con.prepareStatement(sql);		
                rs = preStatement.executeQuery();
                rsmd = rs.getMetaData();
                ListObjectMaps = new ArrayList< HashMap >();
                
                while(rs.next())
                {
                    for(int i = 1, findColumn = rsmd.getColumnCount(); i <= findColumn; i++)
                    {
                        System.out.println( rsmd.getColumnName(i) );
                        if(i == 1)
                        {
                            mapItems = new HashMap<String, String>();
                            mapItems.put(rsmd.getColumnName(i), rs.getString(i));
                        }
                        else if(i == findColumn)
                        {
                            mapItems.put(rsmd.getColumnName(i), rs.getString(i));
                            ListObjectMaps.add(mapItems);
                        }
                        else
                        {
                            mapItems.put(rsmd.getColumnName(i), rs.getString(i));
                        }

                        if( findColumn == 1)
                        {
                           ListObjectMaps.add(mapItems);
                        }
                    }
                }       
            }
            catch (SQLException e) 
            {
                System.out.println("exception getSqlSelect: "+e);        
            }
            finally
            {
                try 
                {
                    preStatement.close();
                    rs.close();
                    con.close();
                } catch (Exception e) 
                {
                    e.printStackTrace();
                }  
            }
            return ListObjectMaps;
	}
}






























        
        
        
	
//	public void con() throws SQLException 
//	{
//		con.commit();
//	}
	
	
//	public void Conexion ()
//	{
//		try
//		{
//			Class.forName(DRIVER);
//			con		=	(Connection) DriverManager.getConnection(url,user,passwd);			
//                        System.out.println("Conexion Exitorsa");
//		}
//		catch (SQLException e) 
//		{
//			e.printStackTrace();
//		}
//		catch (ClassNotFoundException e) 
//		{
//			e.printStackTrace();
//		}
//	}
	
//	public  Connection returnConexion()
//	{
//		Connection con2 = 	null;
//                System.out.println(this.url+" - "+this.user+" - "+this.passwd+" -");
//		try
//		{
//			Class.forName(DRIVER);
//			con2		=	(Connection) DriverManager.getConnection(this.url,this.user,this.passwd);			
//		}
//		catch (SQLException e)
//		{
//			e.printStackTrace();
//		}
//		catch (ClassNotFoundException e) 
//		{
//			e.printStackTrace();
//		}
//		return con2;
//		
//	}
	
//	public ResultSet getQuery(String sql) throws SQLException
//	{
//		try
//		{
//			Statement stm		=	((java.sql.Connection)con).createStatement();		
//			return stm.executeQuery(sql);
//		}
//		catch (SQLException e) 
//		{			
//			throw e;
//		}
//	}
//	
//	public int setQuery(String sql)
//	{
//		try
//		{
//			Statement stm		=	con.createStatement();
//			return stm.executeUpdate(sql);
//                        
//			
//		}
//		catch (Exception e) 
//		{
//                    e.printStackTrace();
//                    return -1;
//		}
//	}
        