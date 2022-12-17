package redesocial.exceptions;

public class InvalidPasswordException extends RuntimeException{
    public String getMessageIPE(){
        return "\nSenha incorreta. \n\nNão é possível realizar a operação. \nDigite a senha correta. \nRetornando ao Menu Inicial\n";
    }
}
