package redesocial.exceptions;

public class NoUserPlataformException extends RuntimeException{
    public String getMessageNUP(){
        return "\nComando Inválido. \nMotivo: Não é possível entrar na rede social #Dev_Makers pois ainda não existem usuários cadastrados \n";
    }
}
