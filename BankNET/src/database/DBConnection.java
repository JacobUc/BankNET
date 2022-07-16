package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author Jacob Uc
 * Repo: https://github.com/JacobUc/BankNET-2.0
 */



public class DBConnection {
    
    private Connection connection;
    private Properties DBurl;
    private Properties DBCredentials;

    
    private static String DBurlName;
    private static String DBCredentialsName;


    public DBConnection( String DBurlPathName, String DBCredentialsPathName ){

        DBurlName = DBurlPathName;
        DBCredentialsName = DBCredentialsPathName;
        this.connection = null;
        this.DBurl = new Properties();
        this.DBCredentials = new Properties();
    }


    
    public Connection getConnection(){

        try {
            String DBurl = "";
            getPropertiesDBURL( DBurlName ); //Llama al archivo 'DBurl.properties'
            getPropertiesCredentials( DBCredentialsName ); //Llama al archivo 'DBCredentials.properties'
            DBurl = getDBurlStringConnection( this.DBurl );
            //System.out.println("---->" + DBurl + "<-----ESTE ES EL URL-----");
            connection = DriverManager.getConnection( DBurl, DBCredentials );

        } catch ( SQLException sqlexc ) {

            sqlexc.printStackTrace();
        }

        return connection;
    }



    public boolean closeConnection( Connection connection ){

        boolean flag = false;

        try {

            connection.close();
            flag = true;

        } catch ( SQLException sqlexc ) {

            sqlexc.printStackTrace();
        }

        return flag;
    }


    
    private void getPropertiesDBURL( String dBurlproperties ){

        try{
            InputStream input = new FileInputStream( dBurlproperties );
            this.DBurl.load( input );//Carga un archivo de texto a un properties
        }
        catch(IOException ioexc){
            ioexc.printStackTrace();    
        }

    }



    private void getPropertiesCredentials( String dbCredentialsproperties ) {
        try{
            InputStream input =  new FileInputStream( dbCredentialsproperties );
            this.DBCredentials.load(input);//Carga el archivo DBCredentials
        }
        catch(IOException ioexc){
            ioexc.printStackTrace();
        }
    }



    private String getDBurlStringConnection( Properties DBurl ) {
        
        String urlStringConnection = "";
        urlStringConnection = DBurl.get("db.connector")+":"+
                              DBurl.get("db.dbms")+"://"+
                              DBurl.get("db.serverName")+":"+
                              DBurl.get("db.portNumber")+"/"+
                              DBurl.get("db.dbName");
        
        return urlStringConnection;
    }


}
