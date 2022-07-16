package controller;

import model.*;
import view.*;
import java.awt.event.*;

/**
 * @author Jacob Uc
 * Repo: https://github.com/JacobUc/BankNET-2.0
 */



public class ActualizarDatosClienteController {


    private ActualizarDatosUI actualizarDatosUI;
    private CuentaClienteController cuentaClienteController;
    private CuentaCliente cuentaCliente;
    private MenuCuentaClienteUI menuCuentaClienteUI;
    

    public ActualizarDatosClienteController( ActualizarDatosUI actualizarDatosUI, CuentaCliente cuentaCliente, 
        MenuCuentaClienteUI menuCuentaClienteUI ){
        
        this.actualizarDatosUI = actualizarDatosUI;
        cuentaClienteController = new CuentaClienteController();
        this.cuentaCliente = cuentaCliente;
        this.menuCuentaClienteUI = menuCuentaClienteUI;

        actualizarDatosUI.botonActualizar( new BotonActualizar() );
        actualizarDatosUI.botonCerrar( new BotonCerrar() );
        actualizarDatosUI.setVisible( true );

    }


    
    /* ----------------------- RECUPERAMOS LOS DATOS DE LA CUENTA CLIENTE ----------------------- */

    private void recuperarDatosCliente(){

        this.cuentaCliente = this.cuentaClienteController.recuperarDatosCuentaCliente( this.cuentaCliente ); 
    }


    /* ----------------------- AGREGAMOS LOS DATOS A LA UI ----------------------- */

    public void setDatosClienteUI(){

        recuperarDatosCliente();

        this.actualizarDatosUI.setNumeroCliente( this.cuentaCliente.getNumeroCliente() + "" );
        this.actualizarDatosUI.setNombres( this.cuentaCliente.getNombres() );
        this.actualizarDatosUI.setApellidoPaterno( this.cuentaCliente.getApellidoPaterno() );
        this.actualizarDatosUI.setApellidoMaterno( this.cuentaCliente.getApellidoMaterno() );
        this.actualizarDatosUI.setEmail( this.cuentaCliente.getEmail() );
        this.actualizarDatosUI.setNumeroCuentasBancarias( this.cuentaCliente.getNumeroCuentasBancarias() + "" );

    }
    


    /* ----------------------- BOTON ACTUALIZAR DATOS ----------------------- */

    class BotonActualizar implements ActionListener{

        String message = "";

        public void actionPerformed( ActionEvent a ){


            boolean validacionNombres = cuentaClienteController.validarNombres( actualizarDatosUI.getNombres() );
            boolean validacionApellidoPaterno = cuentaClienteController.validarApellido( actualizarDatosUI.getApellidoPaterno() );
            boolean validacionApellidoMaterno = cuentaClienteController.validarApellido( actualizarDatosUI.getApellidoMaterno() );
            boolean validacionEmail = cuentaClienteController.validarEmail( actualizarDatosUI.getEmail() );

            try {

                if( !validacionNombres ){
                    message = "EL NOMBRE INGRESADO ES INVÁLIDO. CONTIENE CARACTERES NO PERMITIDOS COMO @,!,+ "+
                                    "O EXCEDE LOS 30 CARACTERES";
                    throw new Exception();
                }
    
                if( !validacionApellidoPaterno ){
                    message = "EL APELLIDO PATERNO INGRESADO ES INVÁLIDO. CONTIENE CARACTERES NO PERMITIDOS COMO @,!,+,etc. " +
                                    "O EXCEDE LOS 20 CARACTERES";
                    throw new Exception();
                }
    
                if( !validacionApellidoMaterno ){
                    message = "EL APELLIDO MATERNO INGRESADO ES INVÁLIDO. CONTIENE CARACTERES NO PERMITIDOS COMO @,!,+,etc." +
                                    "O EXCEDE LOS 20 CARACTERES";
                    throw new Exception();
                }
    
                if( !validacionEmail ){
                    message = "EL CORREO INGRESADO ES INVÁLIDO. NO SIGUE EL FORMATO \"@*.com\". ";
                    throw new Exception();
                }

                
                //Validamos el password
                boolean validacionCredenciales = cuentaClienteController.consultarCredenciales(
                                                    cuentaCliente.getNumeroCliente(), 
                                                    actualizarDatosUI.getPassword() );
                
                if( validacionCredenciales ){

                    //Como se cumple la validacion,ahora realizamos el update
                    
                    cuentaClienteController.actualizarDatosCuentaCliente(
                        cuentaCliente.getNumeroCliente(), 
                        actualizarDatosUI.getNombres(), 
                        actualizarDatosUI.getApellidoPaterno(), 
                        actualizarDatosUI.getApellidoMaterno(), 
                        actualizarDatosUI.getEmail() );
                    
                    message = "SE HAN ACTUALIZADO LOS DATOS EXITOSAMENTE";
                    actualizarDatosUI.mostrarMensaje( message );

                }else{

                    message = "LA CONTRASEÑA INGRESADA ES INCORRECTA";
                    actualizarDatosUI.mostrarMensaje( message );

                }

                
            } catch ( Exception e ) {
                
                actualizarDatosUI.mostrarMensaje(message);
                e.printStackTrace();

            }
           

        }


    }



    /* ----------------------- BOTON CERRAR ----------------------- */

    class BotonCerrar implements ActionListener{

        public void actionPerformed( ActionEvent a ){

            actualizarDatosUI.dispose();
            menuCuentaClienteUI.setVisible( true );
        }

    }


    
}
