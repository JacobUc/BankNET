package controller;

import model.*;
import view.*;
import java.awt.event.*;

/**
 * @author Jacob Uc
 * Repo: https://github.com/JacobUc/BankNET-2.0
 */



public class AccederCuentaBancariaController {


    private AccederCuentaBancariaUI accederCuentaBancariaUI;
    private MenuCuentaClienteUI menuCuentaCliente;
    private CuentaClienteController cuentaClienteController;
    private CuentaCliente cuentaCliente;
    

    public AccederCuentaBancariaController( AccederCuentaBancariaUI accederCuentaBancariaUI, MenuCuentaClienteUI menuCuentaCliente,
            CuentaCliente cuentaCliente ){

        this.accederCuentaBancariaUI = accederCuentaBancariaUI;
        this.menuCuentaCliente = menuCuentaCliente;
        this.cuentaClienteController = new CuentaClienteController();
        this.cuentaCliente = cuentaCliente;

        accederCuentaBancariaUI.botonConsultarEstadoCuenta( new BotonConsultarEstadoCuenta() );
        accederCuentaBancariaUI.botonRealizarTransferencia( new BotonRealizarTransferencia() );
        accederCuentaBancariaUI.botonVolver( new BotonVolver() );
        accederCuentaBancariaUI.setVisible( true );
    }



    /* ----------------------- RECUPERAMOS TODAS LAS CUENTAS BANCARIAS DEL CLIENTE ----------------------- */
    
    public void recuperarCuentasBancarias(){
        
        this.cuentaClienteController.recuperarCuentasBancarias( this.cuentaCliente );

    }


    /* ----------------------- OBTENEMOS LA CUENTA CON LA QUE EL USUARIO DESEA OPERAR ----------------------- */

    public CuentaBancaria getCuentaBancariaSeleccionadaUI(){
        
        int cuentaSeleccionada = Integer.valueOf( accederCuentaBancariaUI.getCuentaSeleccionada() );

        CuentaBancariaController cuentaBancariaController = new CuentaBancariaController();
        CuentaBancaria cuentaBancaria = cuentaBancariaController.recuperarCuentaBancaria( cuentaSeleccionada  );

        return cuentaBancaria;
    }
    

    /* ----------------------- AGREGAMOS LAS CUENTAS BANCARIAS AL JCOMBOBOX ----------------------- */

    public void setCuentasBancariasComboBox(){

        recuperarCuentasBancarias();
        try {

            if( this.cuentaCliente.getNumeroCuentasBancarias() == 0 ){
                
                throw new Exception();
                
            }else {

                for( CuentaBancaria cuenta: this.cuentaCliente.getCuentasBancarias() ){

                    this.accederCuentaBancariaUI.addCuentaBancariaComboBox( String.valueOf( cuenta.getNumeroCuenta() )  );   
                }

            }

        } catch ( Exception e ) {

            String messageError = "NO EXISTE NINGUNA CUENTA BANCARIA. NO SE PUEDE REALIZAR NINGUNA OPERACION";
            accederCuentaBancariaUI.mostrarMensaje( messageError );
            this.accederCuentaBancariaUI.desactivarBotonConsultarEstadoCuenta();
            this.accederCuentaBancariaUI.desactivarBotonRealizarTransaccion();
            e.printStackTrace();
        }
    }

    

    /* ----------------------- BOTON CONSULTAR ESTADO DE CUENTA ----------------------- */

    class BotonConsultarEstadoCuenta implements ActionListener{

        public void actionPerformed( ActionEvent actionEvent ){

            ConsultarEstadoCuentaController cecc = new ConsultarEstadoCuentaController(
                new ConsultarEstadoCuentaUI(), 
                getCuentaBancariaSeleccionadaUI(), 
                accederCuentaBancariaUI );
            cecc.setDatosCuenta();;

            accederCuentaBancariaUI.setVisible( false );

        }
    }


    /* ----------------------- BOTON REALIZAR TRANSFERENCIA ----------------------- */

    class BotonRealizarTransferencia implements ActionListener{

        public void actionPerformed( ActionEvent actionEvent ){
            
            new RealizarTransaccionController( new RealizarTransaccionUI(), 
                getCuentaBancariaSeleccionadaUI(), 
                accederCuentaBancariaUI );
            
            accederCuentaBancariaUI.setVisible( false );

        }
    }


    /* ----------------------- BOTON VOLVER ----------------------- */

    class BotonVolver implements ActionListener{

        public void actionPerformed( ActionEvent actionEvent ){

            accederCuentaBancariaUI.dispose();
            menuCuentaCliente.setVisible( true );
        }
    }


}
