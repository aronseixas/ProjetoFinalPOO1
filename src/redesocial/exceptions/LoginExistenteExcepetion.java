package redesocial.exceptions;

public class LoginExistenteExcepetion extends RuntimeException {
    public String getMessageLEE(){
        return "\nAção Inválida. \nMotivo: O login solicitado já existe. \nVocê será redirecionado ao Menu Inical \n";
    }
}