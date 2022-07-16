package controller;

import model.*;
import view.*;
import java.awt.event.*;

/**
 * @author Jacob Uc
 * Repo: https://github.com/JacobUc/BankNET-2.0
 */



public class MenuCuentaClienteController {


    private CuentaCliente cuentaCliente;
    private MenuCuentaClienteUI menuCuentaClienteUI;


    public MenuCuentaClienteController( MenuCuentaClienteUI menuCuentaCliente, CuentaCliente cuentaCliente ){

        this.cuentaCliente = cuentaCliente;
        this.menuCuentaClienteUI = menuCuentaCliente;

        menuCuentaCliente.botonConsultarInformacion( new BotonConsultarInformacion() );
        menuCuentaCliente.botonAccederCuentasBancarias( new BotonAccederCuentaBancaria() );
        menuCuentaCliente.botonActualizarDatos( new BotonActualizarDatos() );
        menuCuentaCliente.botonSalir( new BotonSalir() );
        menuCuentaCliente.setVisible( true );
        
    }


    /* ----------------------- BOTON CONSULTAR INFORMACION ----------------------- */

    class BotonConsultarInformacion implements ActionListener{

        public void actionPerformed( ActionEvent actionEvent ){

            ConsultarInformacionClienteController cicc = new ConsultarInformacionClienteController( new ConsultarInformacionClienteUI(), cuentaCliente, menuCuentaClienteUI );
            cicc.setInformacion();
            menuCuentaClienteUI.setVisible( false);

        }
    }


    /* ----------------------- BOTON ACCEDER A LA CUENTA BANCARIA ----------------------- */

    class BotonAccederCuentaBancaria implements ActionListener{
        
        public void actionPerformed( ActionEvent actionEvent ){

            AccederCuentaBancariaController acb = new AccederCuentaBancariaController( new AccederCuentaBancariaUI(), menuCuentaClienteUI, cuentaCliente );
            acb.setCuentasBancariasComboBox();
            menuCuentaClienteUI.setVisible( false );
        }
    }


    /* ----------------------- BOTON ACTUALIZAR DATOS ----------------------- */

    class BotonActualizarDatos implements ActionListener{

        public void actionPerformed( ActionEvent actionEvent ){

            ActualizarDatosClienteController adc = new ActualizarDatosClienteController( new ActualizarDatosUI(), cuentaCliente, menuCuentaClienteUI );
            adc.setDatosClienteUI();
            menuCuentaClienteUI.setVisible( false);
        }
    }


    /* ----------------------- BOTON SALIR ----------------------- */

    class BotonSalir implements ActionListener{

        public void actionPerformed( ActionEvent actionEvent ){

            System.exit(0);

        }
    }


}
