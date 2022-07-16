package controller;

import model.*;
import view.ConsultarInformacionClienteUI;
import view.MenuCuentaClienteUI;
import java.awt.event.*;

/**
 * @author Jacob Uc
 * Repo: https://github.com/JacobUc/BankNET-2.0
 */



public class ConsultarInformacionClienteController {
    
    private CuentaClienteController cuentaClienteController;
    private CuentaCliente cuentaCliente;
    private ConsultarInformacionClienteUI consultarInformacionClienteUI;
    private MenuCuentaClienteUI menuCuentaClienteUI;


    ConsultarInformacionClienteController( ConsultarInformacionClienteUI consultarInformacionClienteUI, 
        CuentaCliente cuentaCliente, MenuCuentaClienteUI menuCuentaClienteUI ){
        
        this.cuentaCliente = cuentaCliente;
        this.cuentaClienteController = new CuentaClienteController();
        this.menuCuentaClienteUI = menuCuentaClienteUI;

        this.consultarInformacionClienteUI = consultarInformacionClienteUI;
        consultarInformacionClienteUI.botonActualizar( new BotonActualizar() );
        consultarInformacionClienteUI.botonCerrar( new botonCerrar() );
        consultarInformacionClienteUI.setVisible( true );

    }



    /* ----------------------- RECUPERAMOS LOS DATOS DE LA CUENTA CLIENTE ----------------------- */

    private void recuperarDatosCliente(){

        this.cuentaCliente = this.cuentaClienteController.recuperarDatosCuentaCliente( cuentaCliente );
        
    }


    /* ----------------------- AGREGAMOS LOS DATOS A LA UI ----------------------- */

    public void setInformacion(){

        recuperarDatosCliente();

        this.consultarInformacionClienteUI.setNumeroCliente( this.cuentaCliente.getNumeroCliente() + "" );
        this.consultarInformacionClienteUI.setNombres( this.cuentaCliente.getNombres() );
        this.consultarInformacionClienteUI.setApellidoPaterno( this.cuentaCliente.getApellidoPaterno() );
        this.consultarInformacionClienteUI.setApellidoMaterno( this.cuentaCliente.getApellidoMaterno() );
        this.consultarInformacionClienteUI.setEmail( this.cuentaCliente.getEmail() );
        this.consultarInformacionClienteUI.setNumeroCuentasBancarias( this.cuentaCliente.getNumeroCuentasBancarias() + "" );

    }



    /* ----------------------- BOTON ACTUALIZAR DATOS ----------------------- */

    class BotonActualizar implements ActionListener{

        public void actionPerformed( ActionEvent actionEvent ){

            setInformacion();

        }

    }

    
    /* ----------------------- BOTON CERRAR ----------------------- */

    class botonCerrar implements ActionListener{

        public void actionPerformed( ActionEvent actionEvent ){

            consultarInformacionClienteUI.dispose();
            menuCuentaClienteUI.setVisible( true );

        }

    }
    
    
}
