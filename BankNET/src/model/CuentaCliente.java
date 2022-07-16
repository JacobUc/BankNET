package model;

import java.util.ArrayList;

/**
 * @author Jacob Uc
 * Repo: https://github.com/JacobUc/BankNET-2.0
 */

public class CuentaCliente implements Comparable<CuentaCliente> {
    
    private int numeroCliente; //PK
    private String password;
    private String nombreS;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String email;
    private ArrayList <CuentaBancaria> cuentasBancarias;


    public CuentaCliente( String nombreS, String apellidoPaterno, String apellidoMaterno, String email ){
        
        this.nombreS = nombreS;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.email = email;
        this.cuentasBancarias = new ArrayList<>();

    }

    public CuentaCliente( String nombreS, String apellidoPaterno, String apellidoMaterno, String email,
        String password ){
        
        this.nombreS = nombreS;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.email = email;
        this.password = password;
        this.cuentasBancarias = new ArrayList<>();

    }


    public CuentaCliente( int numeroCliente, String nombreS, String apellidoPaterno, String apellidoMaterno,
        String email ){

        this.numeroCliente = numeroCliente;
        this.nombreS = nombreS;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.email = email;
        this.cuentasBancarias = new ArrayList<>();

    }

    public CuentaCliente( int numeroCliente, String nombreS, String apellidoPaterno, String apellidoMaterno,
        String email, String password ){
        
        this.numeroCliente = numeroCliente;
        this.nombreS = nombreS;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.email = email;
        this.password = password;
        this.cuentasBancarias = new ArrayList<>();
    }
    

    public CuentaCliente( int numeroCliente, String password ){
        this.numeroCliente = numeroCliente;
        this.password = password;
        this.cuentasBancarias = new ArrayList<>();
    }


    /*
     * METODOS GETTERS Y SETTERS
     */

    public int getNumeroCliente(){
        return this.numeroCliente;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword( String password ){
        this.password = password;
    }

    public String getNombres(){
        return this.nombreS;
    }

    public void setNombres( String nombreS ){
        this.nombreS = nombreS;
    }

    public String getApellidoPaterno(){
        return this.apellidoPaterno;
    }

    public void setApellidoPaterno( String  apellidoPaterno ){
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno(){
        return this.apellidoMaterno;
    }

    public void setApellidoMaterno( String  apellidoMaterno ){
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail( String email ){
        this.email = email;
    }

    public ArrayList<CuentaBancaria> getCuentasBancarias(){
        return this.cuentasBancarias;
    }

    public int getNumeroCuentasBancarias(){
        return this.cuentasBancarias.size();
    }
    
    public void addCuentaBancaria( CuentaBancaria cuentaBancaria ){
        this.cuentasBancarias.add( cuentaBancaria );
    }


    /*
     * METODOS SOBREESCRITOS
     */

    @Override
    public String toString() {
        return "Numero cliente: " + this.numeroCliente + 
                "\nNombre completo: " + this.nombreS + " " + this.apellidoPaterno + " " + this.apellidoMaterno +
                "\nEmail: " + this.email + //"\n" +
                "\nNumero de cuentas bancarias: " + this.cuentasBancarias.size() + "\n";
    }
    
    @Override
    public int compareTo(CuentaCliente o) {

        int comparacion = this.numeroCliente - o.numeroCliente;

        return comparacion;
    }
}
