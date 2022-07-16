package controller;

import model.*;
import database.*;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.sql.*;

/**
 * @author Jacob Uc
 * Repo: https://github.com/JacobUc/BankNET-2.0
 */



public class CuentaClienteController {
    
    /*
     * Como es el controller, se va a encargar de enlazar nuestra DB y nuestras clases de datos
     * Para ello, necesitamos una conexion y un lugar en donde almacenar nuestras cuentas clientes
     */
    private ArrayList <CuentaCliente> cuentasCliente;
    private DBCuentaClienteQuery consulta;
    private DBCuentaBancariaQuery consultaCuentasBancarias;

    
    public CuentaClienteController(){
        this.cuentasCliente  = new ArrayList <> ();
        this.consulta = new DBCuentaClienteQuery();
        this.consultaCuentasBancarias = new DBCuentaBancariaQuery();
    }


    /* ----------------------- GETTER ARRAY CUENTAS CLIENTE ----------------------- */

    public ArrayList<CuentaCliente> getCuentasCliente(){
        return this.cuentasCliente;
    }
    

    /* ----------------------------- METODOS CRUD ----------------------------- */
    // Estos 4 primeros metodos son los que podria usar algun administrador del banco.


    /* ----------------------- CREATE ----------------------- */

    public int insertarCuentaCliente( String nombreS, String apellidoPaterno, String apellidoMaterno, String email,
                String password ){

        int rowsAffected = 0;

        CuentaCliente nuevoCliente = new CuentaCliente( nombreS, apellidoPaterno, apellidoMaterno, email, password );

        rowsAffected = this.consulta.agregarCuentaCliente( nuevoCliente );

        this.consulta.cerrarConexion();

        return rowsAffected;
        
    }


    /* ----------------------- READ ----------------------- */
    /**
     * Recupera todas las cuentas cliente de la base de datos.
     */
    public void recuperarDatosCuentasCliente(){

        ResultSet tabla;
        this.consulta.consultarTabla();
        tabla = consulta.getResultSetQuery();

        CuentaCliente nuevaCuentaCliente;

        try {

            while( tabla.next() ){

                nuevaCuentaCliente = new CuentaCliente(
                    tabla.getInt("numeroCliente"), 
                    tabla.getString("nombres"), 
                    tabla.getString("apellidoPaterno"),
                    tabla.getString("apellidoMaterno"),
                    tabla.getString("email") );
                
                recuperarCuentasBancarias( nuevaCuentaCliente );
                System.out.println( nuevaCuentaCliente );
                this.cuentasCliente.add( nuevaCuentaCliente );

            }

            

        } catch ( SQLException sqlexc ) {
            sqlexc.printStackTrace();
        }

        this.consulta.cerrarConexion();
        
    }

    
    /* ----------------------- UPDATE ----------------------- */
    
    /**
     * Actualiza los datos de una cuenta cliente.
     * @param numeroCliente
     * @param nombreS
     * @param apellidoPaterno
     * @param apellidoMaterno
     * @param email
     * @param password
     */
    public void actualizarCuentaCliente( int numeroCliente, String nombreS, String apellidoPaterno, String apellidoMaterno, 
        String email, String password ){

        CuentaCliente nuevoCliente = new CuentaCliente( nombreS, apellidoPaterno, apellidoMaterno, email, password );
            
        this.consulta.actualizarDatosCuentaCliente( numeroCliente, nuevoCliente );

        this.consulta.cerrarConexion();
    }

    
    /* ----------------------- DELETE ----------------------- */

    public int eliminarCuentaCliente( int numeroCliente ){

        int rowsAffected = this.consulta.eliminarCuentaCliente( numeroCliente );

        this.consulta.cerrarConexion();
        
        return rowsAffected;
    }



    /* ----------------------- UPDATE DATOS ----------------------- */

    //Este metodo se usa en la clase ActualizarDatosController
    public void actualizarDatosCuentaCliente( int numeroCliente, String nombreS, String apellidoPaterno, String apellidoMaterno,
        String email ){

        CuentaCliente nuevoCliente = new CuentaCliente( nombreS, apellidoPaterno, apellidoMaterno, email );
        this.consulta.actualizarDatosCuentaCliente( numeroCliente, nuevoCliente );

        this.consulta.cerrarConexion();
    }
    

    
    /* ---------------- RECUPERAR TODOS LOS DATOS DE LA CUENTA CLIENTE ---------------- */

    public CuentaCliente recuperarDatosCuentaCliente( CuentaCliente cuenta ){

        ResultSet resultSet;
        this.consulta.consultarInformacionCuenta( cuenta.getNumeroCliente() );
        resultSet = this.consulta.getResultSetQuery();

        CuentaCliente cuentaCliente = null;

        try {

            
            if( resultSet.next() ){
                
                cuentaCliente = new CuentaCliente(
                    resultSet.getInt("numeroCliente"),
                    resultSet.getString("nombres"), 
                    resultSet.getString("apellidoPaterno"), 
                    resultSet.getString("apellidoMaterno"), 
                    resultSet.getString("email"),
                    resultSet.getString("password") );

                recuperarCuentasBancarias( cuentaCliente );

            }

            
            
        } catch ( SQLException sqlexc ) {
            sqlexc.printStackTrace();
        }

        this.consulta.cerrarConexion();

        return cuentaCliente;

    }


    /* ----------------------- CONSULTAR CREDENCIALES ----------------------- */
    
    public boolean consultarCredenciales( int numeroCliente, String password ){

        boolean flag = false;

        this.consulta.consultarCredencialesCliente( numeroCliente, password );
        ResultSet resultSet = this.consulta.getResultSetQuery();

        try {

            if( resultSet.next() ){

                flag = true;
            }

            
        } catch ( SQLException e) {
            e.printStackTrace();
        }

        this.consulta.cerrarConexion();

        return flag;
    }



    /* ---------------- RECUPERAR CUENTAS BANCARIAS DEL CLIENTE ---------------- */
    
    public void recuperarCuentasBancarias( CuentaCliente cliente ){

        ResultSet resultSet;
        this.consultaCuentasBancarias.consultarCuentasBancarias( cliente.getNumeroCliente() );

        resultSet = this.consultaCuentasBancarias.getResultSetQuery();

        CuentaBancaria nuevaCuentaBancaria;

        try {

            while( resultSet.next() ){

                nuevaCuentaBancaria = new CuentaBancaria(
                    resultSet.getInt("numeroCuenta"), 
                    resultSet.getDouble("saldo") );
                
                
                cliente.addCuentaBancaria( nuevaCuentaBancaria );

            }

        } catch ( SQLException sqlexc ) {
            sqlexc.printStackTrace();
        }

        this.consulta.cerrarConexion();

    }
    

   
    /* ---------------- METODOS PARA VALIDAR LOS DATOS DEL CLIENTE ---------------- */


    public boolean validarNombres( String nombre ){

        boolean flag = true;

        String nombres[] = nombre.split(" ");
        String nameRegex = "[a-zA-Z]+";

        int size = 30;

        //Comprobando que los nombres no excedan los 30 caracteres
        if( nombre != null && !nombre.isEmpty() && nombre.length() <= size ){

            for( String n: nombres ){

                if( !Pattern.matches( nameRegex, n ) ){
                    flag = false;
                }

            }

        } 
        else {

            flag = false;
        }
        
        return flag;
    }


    public boolean validarApellido( String apellido ){
        
        boolean flag = false;

        String nameRegex = "[a-zA-Z]+";
        int size = 20;

        if( apellido != null && !apellido.isEmpty() && apellido.length() <= size ){

            if( Pattern.matches( nameRegex, apellido ) ){
                flag = true;
            }

        }

        return flag;
    }


    public boolean validarEmail( String email ){

        boolean flag = false;

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        int size = 255;

        if( email != null && !email.isEmpty() && email.length() <= size){

            Pattern pattern = Pattern.compile( emailRegex );

            if( pattern.matcher( email ).matches() ){
                flag = true;
            }
        }

        return flag;
    }


    public boolean validarPassword( String password ){

        /*
        Password must contain at least one digit [0-9].
        Password must contain at least one lowercase Latin character [a-z].
        Password must contain at least one uppercase Latin character [A-Z].
        Password must contain at least one special character like ! @ # & ( ).
        Password must contain a length of at least 8 characters and a maximum of 20 characters.
        */

        boolean flag = false;

        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()" +
                                "–[{}]:;',?/*~$^+=<>]).{8,20}$";

        Pattern pattern = Pattern.compile( passwordRegex );

        if( pattern.matcher( password ).matches() ){
            flag = true;
        }

        return flag;
    }



    /*public static void main(String[] args) {
        
         
        String nombreS = "Luis Eduardo";
        String apellidoPaterno = "Guerrero";
        String apellidoMaterno = "Sanchez";
        String email = "luissanchez07@gmail.com";
        String password = "@luisEduardo07";
        
        //Falta validar cada uno de los datos
        CuentaClienteController ccc = new CuentaClienteController();
        ccc.insertarCuentaCliente(nombreS, apellidoPaterno, apellidoMaterno, email, password);
        
    }*/

}
