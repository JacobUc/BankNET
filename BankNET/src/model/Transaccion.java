package model;

import java.time.*;

/**
 * @author Jacob Uc
 * Repo: https://github.com/JacobUc/BankNET-2.0
 */

public class Transaccion {


    private int numeroCuentaOrigen;
    private int numeroCuentaDestino;
    private double montoTransferido;
    private LocalDateTime fecha; 
    
    

    public Transaccion( int numeroCuentaOrigen, int numeroCuentaDestino, double montoTransferido ){

        this.numeroCuentaOrigen = numeroCuentaOrigen;
        this.numeroCuentaDestino = numeroCuentaDestino;
        this.montoTransferido = montoTransferido;
        this.fecha = LocalDateTime.now();
    }


    public Transaccion( int numeroCuentaOrigen, int numeroCuentaDestino, double montoTransferido, 
        LocalDateTime fecha ){

        this.numeroCuentaOrigen = numeroCuentaOrigen;
        this.numeroCuentaDestino = numeroCuentaDestino;
        this.montoTransferido = montoTransferido;
        this.fecha = fecha;
    }
    

    public int getNumeroCuentaOrigen(){
        return this.numeroCuentaOrigen;
    }

    public int getNumeroCuentaDestino(){
        return this.numeroCuentaDestino;
    }

    public double getMontoTransferido(){
        return this.montoTransferido;
    }

    public LocalDateTime getFecha(){
        return this.fecha;
    }


    @Override
    public String toString() {
        return "transferido " + this.montoTransferido + " de " + this.numeroCuentaOrigen + " a " + 
            this.numeroCuentaDestino + " el " + this.fecha;
    }

    
}
