package database;

import java.sql.*;
import model.Transaccion;

/**
 * @author Jacob Uc
 * Repo: https://github.com/JacobUc/BankNET-2.0
 */

public class DBTransaccionQuery {
    
    private DBConnection connection;
    private ResultSet resultSetQuery;
    private PreparedStatement statement;

    private static final String DBurlPathName = "src\\archivos\\DBurl.properties";
    private static final String DBCredentialsPathName = "src\\archivos\\DBCredentials.properties";
    private static final String tableName = "transacciones";


    public DBTransaccionQuery(){
        
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

    public int agregarTransaccion( Transaccion transaccion ){

        int rowsAffected = 0;
        try {

            
            String sql = "INSERT INTO " + tableName + 
                        " ( cuentaOrigen, cuentaDestino, monto, fecha ) VALUES" +
                        " ( ?, ?, ?, ? );";
            this.statement = this.connection.getConnection().prepareStatement( sql );

            this.statement.setInt( 1, transaccion.getNumeroCuentaOrigen() );
            this.statement.setInt( 2, transaccion.getNumeroCuentaDestino() );
            this.statement.setDouble( 3, transaccion.getMontoTransferido() );
            this.statement.setTimestamp( 4, Timestamp.valueOf( transaccion.getFecha() ) );

            rowsAffected = this.statement.executeUpdate();

        } catch ( SQLException sqlexc ) {
            sqlexc.printStackTrace();

        }

        return rowsAffected;
    }



    /* ----------------------- READ ----------------------- */
    /**
     * Recupera todas las transacciones donde se involucra una cuenta bancaria.
     * @param numeroCuenta es el numero de cuenta bancaria de la que se desea consultar sus transacciones.
     */
    public void consultarTransacciones( int numeroCuenta ){

        try {
            
            String sql = "SELECT tr.cuentaOrigen, tr.CuentaDestino, tr.monto, tr.fecha FROM " 
                         + tableName + " tr " +
                         "LEFT JOIN cuentasbancarias cb " + 
                         "ON tr.cuentaOrigen = cb.numeroCuenta OR tr.cuentaDestino = cb.numeroCuenta " +
                         "WHERE numeroCuenta = ? ;";
            this.statement = this.connection.getConnection().prepareStatement( sql );

            this.statement.setInt( 1, numeroCuenta );

            this.resultSetQuery = this.statement.executeQuery();

        } catch ( SQLException sqlexc ) {
            sqlexc.printStackTrace();
        }

    }



    /* ---------------- CONSULTAR SALDO ---------------- 
    public void consultarSaldoCuenta( int numeroCuentaBancaria ){

        try {
            //creamos un nuevo objeto para establecer la conexion con la DB
            String sql = "SELECT * FROM " + tableName + 
                        " WHERE cuentaDestino = ? ;";
            this.statement = this.connection.getConnection().prepareStatement( sql );

            this.statement.setInt( 1, numeroCuentaBancaria );

            this.resultSetQuery = this.statement.executeQuery();

        } catch ( SQLException sqlexc ) {
            sqlexc.printStackTrace();
        }

    }*/


    
}
