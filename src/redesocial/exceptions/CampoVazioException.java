package redesocial.exceptions;

public class CampoVazioException extends RuntimeException{
    public String getMessageCVE(){
        return "\nAção Inválida. \nNão é possivel realizar a ação contendo campos vazios. \nVocê será redirecionado para Tela principal.\n";
    }
}