package database;

import java.sql.*;
import model.CuentaBancaria;

/**
 * @author Jacob Uc
 * Repo: https://github.com/JacobUc/BankNET-2.0
 */


 
public class DBCuentaBancariaQuery {
    
    private DBConnection connection;
    private ResultSet resultSetQuery;
    private PreparedStatement statement;

    private static final String DBurlPathName = "src\\archivos\\DBurl.properties";
    private static final String DBCredentialsPathName = "src\\archivos\\DBCredentials.properties";
    private static final String tableName = "cuentasbancarias";


    public DBCuentaBancariaQuery(){

        this.connection = new DBConnection( DBurlPathName, DBCredentialsPathName );
        this.resultSetQuery = null;

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
    // Estos 3 primeros metodos son los que podria usar algun administrador del banco. 

    /* ----------------------- CREAR UNA NUEVA CUENTA BANCARIA ----------------------- */

    public int crearCuentaBancaria( CuentaBancaria cuentaBancaria, int numeroCliente ){

        int rowsAffected = 0;

        try {
            
            String sql = "INSERT INTO " + tableName +
                         " ( saldo, num_cliente) VALUES" +
                         " ( ?, ? ) ;";
            this.statement = this.connection.getConnection().prepareStatement( sql );

            this.statement.setDouble( 1, cuentaBancaria.getSaldo() );
            this.statement.setInt( 2, numeroCliente );

            rowsAffected = this.statement.executeUpdate();


        } catch ( SQLException sqlexc ) {
            sqlexc.printStackTrace();
        }

        return rowsAffected;
    }



    /* ----------------------- READ ----------------------- */

    /**
     * Recupera todos los datos de la tabla 'cuentasbancarias'
     */
    public void consultarTabla(){

        try {

            String sql = "SELECT * FROM " + tableName + " ;";
            this.statement = this.connection.getConnection().prepareStatement( sql );
            this.resultSetQuery = this.statement.executeQuery();


        } catch ( SQLException sqlexc ) {
            sqlexc.printStackTrace();
        }
    }


    /* ----------------------- DELETE ----------------------- */

    public int eliminarCuentaCliente( int numeroCuenta ){

        int rowsAffected = 0;
        try {

            String sql = "DELETE FROM " + tableName + " WHERE numeroCuenta = ? ;" ;
            this.statement = this.connection.getConnection().prepareStatement( sql );

            this.statement.setInt( 1, numeroCuenta );

            rowsAffected = this.statement.executeUpdate();
            
        } catch ( SQLException sqlexc ) {
            sqlexc.printStackTrace();
        }

        return rowsAffected;
    }



    /* ----------------------------- METODOS SALDO ----------------------------- */
    
    /* ----------------------- CONSULTAR SALDO ----------------------- */

    public void consultarSaldoCuenta( int numeroCuentaBancaria ){

        try {
            
            String sql = "SELECT saldo FROM " + tableName + 
                        " WHERE numeroCuenta = ? ;";
            this.statement = this.connection.getConnection().prepareStatement( sql );

            this.statement.setInt( 1, numeroCuentaBancaria );

            this.resultSetQuery = this.statement.executeQuery();

        } catch ( SQLException sqlexc ) {
            sqlexc.printStackTrace();
        }

    }

    /* ----------------------- ACTUALIZAR SALDO ----------------------- */

    public int actualizarSaldo( int numeroCuentaBancaria, double monto ){

        int rowsAffected = 0;
        try {
            
            String sql = "UPDATE " + tableName + 
                        " SET saldo = ? WHERE numeroCuenta = ? ;";
            this.statement = this.connection.getConnection().prepareStatement( sql );

            
            this.statement.setDouble( 1, monto );
            this.statement.setInt( 2, numeroCuentaBancaria );

            rowsAffected = this.statement.executeUpdate();

        } catch ( SQLException sqlexc ) {
            sqlexc.printStackTrace();
        }

        return rowsAffected;

    }


    /* ----------------------------- READ CUENTAS BANCARIAS ----------------------------- */

    /**
     * Recupera todas las cuentas bancarias de un solo cliente.
     * @param numeroCliente es el numero de cliente del que se desea recuperar todas sus cuentas bancarias.
     */
    public void consultarCuentasBancarias( int numeroCliente ){

        try{

            String sql = "SELECT cb.numeroCuenta, cb.saldo FROM " + tableName + " cb LEFT JOIN " + 
                         "cuentascliente cc ON cc.numeroCliente = cb.num_cliente " +
                         "WHERE cc.numerocliente = ? ;";
            this.statement = this.connection.getConnection().prepareStatement( sql );

            this.statement.setInt( 1, numeroCliente);

            this.resultSetQuery = this.statement.executeQuery();


        } catch ( SQLException sqlexc ) {
            sqlexc.printStackTrace();
        }

    }



    /* ---------------- RECUPERAR CUENTA BANCARIA, SI ES QUE EXISTE ---------------- */

    /**
     * Recupera todos los datos de una sola cuenta bancaria, si es que esta existe.
     * @param numeroCuenta es el numero de cuenta bancaria de la que se desea recuperar.
     */
    public void consultarCuentaBancaria( int numeroCuenta ){

        try {

            String sql = "SELECT * FROM " + tableName + 
                        " WHERE numeroCuenta = ? ;";
            this.statement = this.connection.getConnection().prepareStatement( sql );

            this.statement.setInt( 1, numeroCuenta );

            this.resultSetQuery = this.statement.executeQuery();

        } catch ( SQLException sqlexc ) {
            sqlexc.printStackTrace();
        }

    }


    /* 
    public static void main(String[] args) throws SQLException{

        ResultSet tabla;
        DBCuentaBancariaQuery consulta = new DBCuentaBancariaQuery();
        consulta.consultarCuentaBancaria( 5 );
        tabla = consulta.getResultSetQuery();

        
        while( tabla.next() ){

            System.out.println( tabla.getInt("numeroCuenta") );
            System.out.println( tabla.getDouble("saldo") );
            System.out.println( tabla.getInt("num_cliente") );
        }
    }*/
    
}
