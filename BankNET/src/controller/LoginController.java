package controller;

import model.CuentaCliente;
import view.*;
import java.awt.event.*;

/**
 * @author Jacob Uc
 * Repo: https://github.com/JacobUc/BankNET-2.0
 */



public class LoginController {
    

    private LoginUI loginUI;
    private CuentaCliente cuentaCliente;
    private CuentaClienteController cuentaClienteController;


    public LoginController( LoginUI loginUI ){

        this.cuentaCliente = null;
        this.cuentaClienteController = new CuentaClienteController();

        this.loginUI = loginUI;
        loginUI.botonConfimar( new BotonConfirmar() );
        loginUI.botonSalir( new BotonSalir() );
        
    }


    /* ----------------------- BOTON CONFIRMAR ----------------------- */

    class BotonConfirmar implements ActionListener{

        public void actionPerformed( ActionEvent actionEvent ){

            int numeroCliente;
            String password = "";
            String errorMessage = "";
            
            try {
                numeroCliente = Integer.valueOf( loginUI.getNumeroCliente() );
                password = loginUI.getPasswordCliente();

                boolean validacionCredenciales = cuentaClienteController.consultarCredenciales( numeroCliente, password );
                
                if( validacionCredenciales ){

                    cuentaCliente = new CuentaCliente( numeroCliente, password );
                    loginUI.dispose();
                    
                    new MenuCuentaClienteController( new MenuCuentaClienteUI(), cuentaCliente );


                }else{
                    errorMessage = "EL NUMERO DE CLIENTE O LA CONTRASEÑA INGRESADA ES INCORRECTO";
                    loginUI.mostrarMensaje( errorMessage );
                }

                
            } catch ( NumberFormatException nfe ) {

                errorMessage = "EL FORMATO DEL NUMERO DE CLIENTE Y CONTRASEÑA ES INCORRECTO";
                loginUI.mostrarMensaje( errorMessage );
                nfe.printStackTrace();
            }
            
        }

    }


    /* ----------------------- BOTON SALIR ----------------------- */
    
    class BotonSalir implements ActionListener{

        public void actionPerformed( ActionEvent actionEvent ){

            System.exit(0);

        }
    }
    
    
}
