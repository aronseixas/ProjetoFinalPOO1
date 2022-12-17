package redesocial.exceptions;

public class NoPostsOnPlataformException extends RuntimeException{
    public String getMessageNPOP(){
        return "O perfil não possui Posts.";
    }
}
