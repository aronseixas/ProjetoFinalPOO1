package redesocial.exceptions;

public class UserNotFoundException extends RuntimeException{
    public String getMessageUNFE(){
        return "\nUsuário inexistente! Retornando ao Menu Inicial \n";
    }
}
