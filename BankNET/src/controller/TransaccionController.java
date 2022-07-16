package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import database.DBTransaccionQuery;
import model.*;

/**
 * @author Jacob Uc
 * Repo: https://github.com/JacobUc/BankNET-2.0
 */



public class TransaccionController {
    

    DBTransaccionQuery dbTransaccionQuery;
    CuentaBancariaController cuentaBancariaController;
    ArrayList <Transaccion> transacciones;


    public TransaccionController(){

        this.dbTransaccionQuery = new DBTransaccionQuery();
        this.cuentaBancariaController = new CuentaBancariaController();
        this.transacciones = new ArrayList<>();
    }


    /* ----------------------- GETTER ARRAY TRANSACCIONES ----------------------- */

    public ArrayList<Transaccion> getCuentasBancarias(){
        return this.transacciones;
    }


    /*
     * PARA REALIZAR UNA TRANSACCION DEBEMOS:
     * 1.- Verificar que la cuenta a la que se enviara el monto, exista
     * 2.- Verificar que el saldo de la cuenta origen sea suficiente para realizar la operacion
     * 3.- Si se cumplen las 2 anteriores:
     *      3.1.- Agregar saldo a la cuenta destino
     *      3.2.- Descontar saldo a la cuenta origen
     *      3.3.- Registrar la transaccion
     * 4.-Sino, lanzar una excepcion
     */


    /* ----------------------- CREATE ----------------------- */

    public int insertarNuevaTransaccion( Transaccion transaccion ){

        int rowsAffected = 0;

        rowsAffected =  this.dbTransaccionQuery.agregarTransaccion( transaccion );

        return rowsAffected;

    }

    /* ----------------------- CREATE ----------------------- */
    //En este metodo ya se valida todo. Se puede hacer directamente para realizar una transferencia
    public boolean realizarTransaccion( int numCuentaOrigen, int numCuentaDestino, double monto ){

        boolean flag = false;

        //Validamos que la cuenta a la que se envia el monto, exista
        boolean existeCuentaDestino = this.cuentaBancariaController.existeCuentaBancaria( numCuentaDestino );

        if( existeCuentaDestino ){

            //Verificamos que el saldo de la cuenta origen sea suficiente
            boolean saldoSuficiente = saldoSuficienteATransferir( numCuentaOrigen, monto);

            if( saldoSuficiente ){

                //Balanceamos los saldos de las cuentas
                addBalance( numCuentaDestino, monto );
                subtractBalance( numCuentaOrigen, monto );

                //Creamos la transaccion
                Transaccion nuevaTransaccion = new Transaccion( numCuentaOrigen, numCuentaDestino, monto );

                //Registramos la transaccion
                this.insertarNuevaTransaccion( nuevaTransaccion );
                
                //--------------- COMO AGREGAMOS LA TRANSACCION A LOS 2 ARRAYS? --------------

                flag = true;

            }

        }

        return flag;
        
    }

    

    public boolean saldoSuficienteATransferir( int numeroCuentaBancaria, double monto ){

        boolean flag = false;

        //Validamos que el saldo sea suficiente
        double saldoCuenta = this.cuentaBancariaController.getSaldo( numeroCuentaBancaria );

        if( saldoCuenta >= monto ){

            flag = true;

        }

        return flag;

    }

    public void addBalance( int numeroCuentaBancaria, double monto ){

        //Recuperamos el saldo de la cuenta
        double saldoCuenta = this.cuentaBancariaController.getSaldo( numeroCuentaBancaria );

        //Sumamos el monto
        saldoCuenta += monto;

        //Realizamos el update
        this.cuentaBancariaController.actualizarSaldo( numeroCuentaBancaria, saldoCuenta );
    }


    public void subtractBalance( int numeroCuentaBancaria, double monto ){

        //Recuperamos el saldo de la cuenta
        double saldoCuenta = this.cuentaBancariaController.getSaldo( numeroCuentaBancaria );

        //Restamos el monto
        saldoCuenta -= monto;

        //Realizamos el update
        this.cuentaBancariaController.actualizarSaldo( numeroCuentaBancaria, saldoCuenta );
    }


    /* ----------------------- READ ----------------------- */
    
    /* ---------------- RECUPERAR LAS TRANSACCIONES ---------------- */

    public void recuperarTransacciones( CuentaBancaria cuenta ){

        ResultSet resultSet;
        this.dbTransaccionQuery.consultarTransacciones( cuenta.getNumeroCuenta() );
        resultSet = this.dbTransaccionQuery.getResultSetQuery();

        Transaccion transaccion;

        try {

            while( resultSet.next() ){

                transaccion = new Transaccion(
                    resultSet.getInt( "cuentaOrigen" ), 
                    resultSet.getInt( "cuentaDestino" ), 
                    resultSet.getDouble( "monto" ),
                    resultSet.getTimestamp( "fecha").toLocalDateTime() );
                
                cuenta.addTransaccion( transaccion );
                
            }
            
        } catch ( SQLException sqlexc) {
            sqlexc.printStackTrace();
        }

    }



}
