package controller;

import model.*;
import database.*;
import java.util.ArrayList;
import java.sql.*;

/**
 * @author Jacob Uc
 * Repo: https://github.com/JacobUc/BankNET-2.0
 */



public class CuentaBancariaController {
    

    ArrayList <CuentaBancaria> cuentas;
    DBCuentaBancariaQuery consulta;


    public CuentaBancariaController(){

        this.cuentas = new ArrayList<>();
        this.consulta = new DBCuentaBancariaQuery();

    }


    /* ----------------------- GETTER ARRAY CUENTAS BANCARIAS ----------------------- */

    public ArrayList<CuentaBancaria> getCuentasBancarias(){
        return this.cuentas;
    }
    
    
    

    /* ----------------------- RECUPERAR UNA SOLA CUENTA BANCARIA, CON TRANSACCIONES ----------------------- */

    public CuentaBancaria recuperarCuentaBancaria( int numeroCuenta ){

        ResultSet resultSet;
        this.consulta.consultarCuentaBancaria( numeroCuenta );
        resultSet = consulta.getResultSetQuery();

        CuentaBancaria cuentaBancaria = null;

        try {

            if( resultSet.next() ){

                TransaccionController transaccionController = new TransaccionController();

                cuentaBancaria = new CuentaBancaria(
                    resultSet.getInt("numeroCuenta"), 
                    resultSet.getDouble("saldo") );

                transaccionController.recuperarTransacciones( cuentaBancaria );
                
            }
            
        } catch ( SQLException sqlexc ) {
            sqlexc.printStackTrace();
        }

        this.consulta.cerrarConexion();

        return cuentaBancaria;
    }



    /* ----------------------- OBTENER EL SALDO DE UNA CUENTA ----------------------- */

    public double getSaldo( int numeroCuentaBancaria ){

        double saldo = -1;
        ResultSet resultSet;
        this.consulta.consultarSaldoCuenta( numeroCuentaBancaria );

        resultSet = this.consulta.getResultSetQuery();

        try {
            
            if( resultSet.next() ){

                saldo = resultSet.getDouble("saldo");

            }

        } catch ( SQLException sqlexc) {
            sqlexc.printStackTrace();
        }

        this.consulta.cerrarConexion();

        return saldo;

    }



    /* ----------------------- ACTUALIZAR SALDO ----------------------- */
    
    public void actualizarSaldo( int numeroCuentaBancaria, double monto ){

        this.consulta.actualizarSaldo( numeroCuentaBancaria, monto );

        this.consulta.cerrarConexion();
    }
    


    /* ----------------------- VERIFICAR SI EXISTE UNA CUENTA BANCARIA ----------------------- */

    public boolean existeCuentaBancaria( int numeroCuenta ){

        boolean flag = false;

        ResultSet resultSet;
        this.consulta.consultarCuentaBancaria(numeroCuenta);
        resultSet = this.consulta.getResultSetQuery();

        try {
            
            if( resultSet.next() ){
                flag = true;
            }

        } catch ( SQLException sqlexc ) {
            sqlexc.printStackTrace();
        }

        this.consulta.cerrarConexion();

        return flag;

    }


    
    /* ----------------------- RECUPERAR LAS TRANSACCIONES ----------------------- */

    public void recuperarTransacciones( CuentaBancaria cuentaBancaria ){

        
        TransaccionController transaccionController = new TransaccionController();
        transaccionController.recuperarTransacciones( cuentaBancaria );

        this.consulta.cerrarConexion();
        
    }


}
