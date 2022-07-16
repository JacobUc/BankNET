package controller;

import java.awt.event.*;
import model.CuentaBancaria;
import model.Transaccion;
import view.AccederCuentaBancariaUI;
import view.RealizarTransaccionUI;

/**
 * @author Jacob Uc
 * Repo: https://github.com/JacobUc/BankNET-2.0
 */



public class RealizarTransaccionController {


    RealizarTransaccionUI realizarTransaccionUI;
    CuentaBancaria cuentaBancaria;
    AccederCuentaBancariaUI accederCuentaBancariaUI;
    TransaccionController transaccionController;

    
    RealizarTransaccionController( RealizarTransaccionUI realizarTransaccionUI, CuentaBancaria cuentaBancaria,
        AccederCuentaBancariaUI accederCuentaBancariaUI ){

        this.realizarTransaccionUI = realizarTransaccionUI;
        this.cuentaBancaria = cuentaBancaria;
        this.accederCuentaBancariaUI = accederCuentaBancariaUI;
        transaccionController = new TransaccionController();

        realizarTransaccionUI.botonConfimar( new BotonConfirmar() );
        realizarTransaccionUI.botonCancelar( new BotonCancelar() );
        realizarTransaccionUI.setVisible( true );
    }


    /* ----------------------- BOTON CONFIRMAR ----------------------- */

    class BotonConfirmar implements ActionListener{

        public void actionPerformed( ActionEvent actionEvent ){

            String message = "";

            try {
                

                CuentaBancariaController cuentaBancariaController = new CuentaBancariaController();

                int numeroCuentaDestino = Integer.valueOf( realizarTransaccionUI.getNumeroCuentaDestino() );
                double montoATransferir = Double.valueOf( realizarTransaccionUI.getMonto() );

                boolean existeCuentaDestino = cuentaBancariaController.existeCuentaBancaria( numeroCuentaDestino );
                
                if( !existeCuentaDestino ){
                    message = "NO EXISTE NINGUNA CUENTA BANCARIA CON ESE NUMERO";
                    throw new Exception();
                }

                boolean saldoSuficiente = transaccionController.saldoSuficienteATransferir( 
                    cuentaBancaria.getNumeroCuenta(), 
                    montoATransferir );

                if( !saldoSuficiente ){
                    message = "SU SALDO NO ES SUFICIENTE PARA COMPLETAR LA OPERACION";
                    throw new Exception();
                }

                transaccionController.addBalance( numeroCuentaDestino, montoATransferir );
                transaccionController.subtractBalance( cuentaBancaria.getNumeroCuenta(), montoATransferir );

                Transaccion nuevaTransaccion = new Transaccion( 
                    cuentaBancaria.getNumeroCuenta(), 
                    numeroCuentaDestino, 
                    montoATransferir );
                
                transaccionController.insertarNuevaTransaccion( nuevaTransaccion );

                message = "LA TRANSACCION FUE REALIZADA EXITOSAMENTE";
                realizarTransaccionUI.mostrarMensaje( message );

                
            } catch ( NumberFormatException nfe ) {
                
                message = "INGRESE EL FORMATO CORRECTO DE LOS DATOS";
                realizarTransaccionUI.mostrarMensaje( message );
                nfe.printStackTrace();

            } catch ( Exception nfe ) {
                
                realizarTransaccionUI.mostrarMensaje( message );
                nfe.printStackTrace();
            }

                
        }

    }


    /* ----------------------- BOTON CANCELAR ----------------------- */

    class BotonCancelar implements ActionListener{

        public void actionPerformed( ActionEvent actionEvent ){

            realizarTransaccionUI.dispose();
            accederCuentaBancariaUI.setVisible( true );
        }

    }
    

}
