package database;

import java.sql.*;
import model.CuentaCliente;

/**
 * @author Jacob Uc
 * Repo: https://github.com/JacobUc/BankNET-2.0
 */


 
public class DBCuentaClienteQuery {

    private DBConnection connection;
    private ResultSet resultSetQuery;
    private PreparedStatement statement;

    //Los dividimos porque las tablas pueden estar en distintas bases de datos y usuarios
    private static final String DBurlPathName = "src\\archivos\\DBurl.properties";
    private static final String DBCredentialsPathName = "src\\archivos\\DBCredentials.properties";
    private static final String tableName = "cuentascliente";

    
    public DBCuentaClienteQuery(){

        this.connection = new DBConnection( DBurlPathName, DBCredentialsPathName );
        this.resultSetQuery = null;
        this.statement = null;

    }


    /* ----------------------- GETTER RESULTSET ----------------------- */

    public ResultSet getResultSetQuery(){
        return this.resultSetQuery;
    }


    /* ----------------------- CERRAR CONEXION ----------------------- */

    public void cerrarConexion(){
        this.connection.closeConnection( this.connection.getConnection() );
    }


    /* ----------------------------- METODOS CRUD ----------------------------- */

    /* ----------------------- CREATE ----------------------- */

    public int agregarCuentaCliente( CuentaCliente cliente ){

        int rowsAffected = 0;
        try {

            String sql = "INSERT INTO " + tableName + 
                        " ( nombres, apellidoPaterno, apellidoMaterno, email, password ) VALUES" +
                        " ( ?, ?, ?, ?, ? );";
            this.statement = this.connection.getConnection().prepareStatement( sql );

            this.statement.setString( 1, cliente.getNombres() );
            this.statement.setString( 2, cliente.getApellidoPaterno() );
            this.statement.setString( 3, cliente.getApellidoMaterno() );
            this.statement.setString( 4, cliente.getEmail() );
            this.statement.setString( 5, cliente.getPassword() );

            rowsAffected = this.statement.executeUpdate();

        } catch ( SQLException sqlexc ) {
            sqlexc.printStackTrace();

        }


        return rowsAffected;
    }


    /* ----------------------- READ ----------------------- */

    public void consultarTabla(){

        try {
            //creamos un nuevo objeto para establecer la conexion con la DB
            String sql = "SELECT * FROM " + tableName + " ;";
            this.statement = this.connection.getConnection().prepareStatement( sql );

            this.resultSetQuery = this.statement.executeQuery();

        } catch ( SQLException sqlexc ) {
            sqlexc.printStackTrace();
        }
        
    }


    /* ----------------------- DELETE ----------------------- */
    
    public int eliminarCuentaCliente( int numeroCliente ){

        int rowsAffected = 0;
        try {

            String sql = "DELETE FROM " + tableName + " WHERE numeroCliente = ? ;" ;
            this.statement = this.connection.getConnection().prepareStatement( sql );

            this.statement.setInt( 1, numeroCliente );

            rowsAffected = this.statement.executeUpdate();
            
        } catch ( SQLException sqlexc ) {
            sqlexc.printStackTrace();
        }

        return rowsAffected;
    }




    /**
     * Recupera todos los datos de una sola cuenta cliente.
     * @param numeroCliente es el numero de cliente de la cuenta que se desea recuperar.
     */
    public void consultarInformacionCuenta( int numeroCliente ){

        try {

            String sql = "SELECT * FROM " + tableName + " WHERE numeroCliente = ? ;";
            this.statement = this.connection.getConnection().prepareStatement( sql );

            this.statement.setInt( 1, numeroCliente );

            this.resultSetQuery = this.statement.executeQuery();
            
        } catch ( SQLException sqlexc ) {
            sqlexc.printStackTrace();
        }
    }



    /**
     * Recupera todas las cuentas bancarias asociadas a una sola cuenta cliente.
     * @param numeroCliente es el numero de cliente que se desea recuperar todas sus cuentas bancarias.
     */
    public void consultarCuentasBancarias( int numeroCliente ){

        try{

            String sql = "SELECT cb.numeroCuenta, cb.saldo FROM " + tableName + " cc LEFT JOIN " + 
                         "cuentasbancarias cb ON cc.numeroCliente = cb.num_cliente " +
                         "WHERE cc.numerocliente = ? ;";
            this.statement = this.connection.getConnection().prepareStatement( sql );

            this.statement.setInt( 1, numeroCliente);

            this.resultSetQuery = this.statement.executeQuery();


        } catch ( SQLException sqlexc ) {
            sqlexc.printStackTrace();
        }

    }



    /* ----------------- PARA EL LOGIN Y PARA ACTUALIZAR DATOS CLIENTE ----------------- */
    /**
     * Recupera todos los datos de una cuenta cliente a traves de su numero de cliente y su password.
     * @param numeroCliente es el numero de cliente de la cuenta a recuperar.
     * @param password es la contraseña de la cuenta cliente.
     */
    public void consultarCredencialesCliente( int numeroCliente, String password ){

        try {

            String sql = "SELECT * FROM " + tableName + " WHERE numeroCliente = ? AND password = ? ;";
            this.statement = this.connection.getConnection().prepareStatement( sql );

            this.statement.setInt( 1, numeroCliente );
            this.statement.setString( 2, password );

            this.resultSetQuery = this.statement.executeQuery();
            
        } catch ( SQLException sqlexc ) {
            sqlexc.printStackTrace();
        }

    }


    
    /* ----------------------- UPDATE CUENTA CLIENTE ----------------------- */
    /**
     * Actualiza todos los datos de la cuenta cliente, excepto el numeroCliente y el password.
     * @param numeroCliente
     * @param cliente es la cuenta cliente que contiene las modificaciones.
     * @return el numero de filas afectadas en la base de datos. Debe ser 1 si se realizó exitosamente.
     */
    public int actualizarDatosCuentaCliente( int numeroCliente, CuentaCliente cliente ){

        int rowsAffected = 0;
        try {

            String sql = "UPDATE " + tableName + 
                         " SET nombres = ?, apellidoPaterno = ?, apellidoMaterno = ?, email = ?" + 
                         " WHERE numeroCliente = ? ;";
            this.statement = this.connection.getConnection().prepareStatement( sql );

            this.statement.setString( 1, cliente.getNombres() );
            this.statement.setString( 2, cliente.getApellidoPaterno() );
            this.statement.setString( 3, cliente.getApellidoMaterno() );
            this.statement.setString( 4, cliente.getEmail() );

            this.statement.setInt( 5, numeroCliente );

            rowsAffected = this.statement.executeUpdate();
            
        } catch ( SQLException sqlexc ) {
            sqlexc.printStackTrace();
        }

        return rowsAffected;
    }



}
