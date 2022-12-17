package redesocial.usuarios;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Post {
    private String texto;
    private String data = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    private String hora = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));


    public Post(String texto) {
        this.texto = texto;
    }

    public String getData() {
        return data;
    }

    public String getHora() {
        return hora;
    }

    public String getTexto() {
        return texto;
    }
}
