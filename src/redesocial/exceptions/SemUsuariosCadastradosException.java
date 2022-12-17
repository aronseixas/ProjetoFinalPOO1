package redesocial.exceptions;

public class SemUsuariosCadastradosException extends RuntimeException{
    public String getMessageSUCE(){
        return "\nNão existem usuários cadastrados na Rede Social #Dev_Makers.";
    }
}
