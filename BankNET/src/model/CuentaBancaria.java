package model;

import java.util.ArrayList;

/**
 * @author Jacob Uc
 * Repo: https://github.com/JacobUc/BankNET-2.0
 */

public class CuentaBancaria {
    
    private int numeroCuenta; 
    private double saldo;
    private ArrayList <Transaccion> transacciones;


    public CuentaBancaria( int numeroCuenta, double saldo ){
        
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.transacciones = new ArrayList<Transaccion>();
    }

    public int getNumeroCuenta(){
        return this.numeroCuenta;
    }

    public void setNumeroCuenta( int numeroCuenta ){
        this.numeroCuenta = numeroCuenta;
    }
     
    public double getSaldo(){
        return this.saldo;
    }

    public void setSaldo( double saldo ){
        this.saldo = saldo; 
    }

    public ArrayList< Transaccion > getTransacciones(){
        return this.transacciones;
    }

    public void addTransaccion( Transaccion transaccion ){
        this.transacciones.add( transaccion );
    }



    @Override
    public String toString() {

        return "numero de cuenta: " + this.numeroCuenta + "\nsaldo: " + this.saldo;

    }


}
