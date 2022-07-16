package controller;

import model.CuentaBancaria;
import model.Transaccion;
import view.AccederCuentaBancariaUI;
import view.ConsultarEstadoCuentaUI;
import java.awt.event.*;
import java.time.format.DateTimeFormatter;

/**
 * @author Jacob Uc
 * Repo: https://github.com/JacobUc/BankNET-2.0
 */



public class ConsultarEstadoCuentaController {
    
    ConsultarEstadoCuentaUI consultarEstadoCuentaUI;
    CuentaBancaria cuentaBancaria;
    AccederCuentaBancariaUI accederCuentaBancariaUI;
    TransaccionController transaccionController;


    public ConsultarEstadoCuentaController( ConsultarEstadoCuentaUI consultarEstadoCuentaUI, CuentaBancaria cuentaBancaria,
        AccederCuentaBancariaUI accederCuentaBancariaUI ){

        this.consultarEstadoCuentaUI = consultarEstadoCuentaUI;
        this.cuentaBancaria = cuentaBancaria;
        this.accederCuentaBancariaUI = accederCuentaBancariaUI;
        this.transaccionController = new TransaccionController();
        
        consultarEstadoCuentaUI.botonCerrar( new BotonCerrar() );
        consultarEstadoCuentaUI.setVisible( true );
    }


    /* ----------------------- AGREGAMOS LAS TRANSACCIONES A LA JTABLE ----------------------- */

    public void setTransacciones(){
        
        String datosTransaccion[] = new String [4];

        //Establecemos el formato de la fecha
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        for( Transaccion transaccion : this.cuentaBancaria.getTransacciones() ){
            datosTransaccion[0] = String.valueOf( transaccion.getNumeroCuentaOrigen() );
            datosTransaccion[1] = String.valueOf( transaccion.getNumeroCuentaDestino());
            datosTransaccion[2] = String.valueOf( transaccion.getMontoTransferido() );
            datosTransaccion[3] = String.valueOf( dtf.format( transaccion.getFecha() )  );
            this.consultarEstadoCuentaUI.addFila( datosTransaccion );
        }
    }


    /* ----------------------- AGREGAMOS EL SALDO AL JTEXTFIELD ----------------------- */

    public void setSaldo(){
        this.consultarEstadoCuentaUI.setSaldo( String.valueOf( this.cuentaBancaria.getSaldo() ) );
    }


    public void setDatosCuenta(){
        setSaldo();
        setTransacciones();
    }


    /* ----------------------- BOTON CERRAR ----------------------- */

    class BotonCerrar implements ActionListener{

        public void actionPerformed( ActionEvent actionEvent ){

            accederCuentaBancariaUI.setVisible( true);
            consultarEstadoCuentaUI.setVisible( false );
        }
    }
    

}
