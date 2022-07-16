import controller.*;
import view.LoginUI;

/**
 * @author Jacob Uc
 * Repo: https://github.com/JacobUc/BankNET-2.0
 */ 

public class App {

    public static void main(String[] args){
        

        LoginUI loginUI = new LoginUI();
        LoginController loginController = new LoginController( loginUI );
        loginUI.setVisible( true );
        
    }
}
