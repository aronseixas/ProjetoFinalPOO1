package redesocial.usuarios;

import redesocial.exceptions.CampoVazioException;

import java.util.Scanner;

public class Usuario {
    private String nome;
    private String login;
    private String senha;
    private Post[] listaDePosts = new Post[100];
    private int quantPosts = 0;

    public Usuario(String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Post[] getListaDePosts() {
        return listaDePosts;
    }

    public int getQuantPosts() {
        return quantPosts;
    }

    public void setQuantPosts(int quantPosts) {
        this.quantPosts = quantPosts;
    }

    public Post PostarTimeLine () throws CampoVazioException {
        Scanner entrada = new Scanner(System.in);
        System.out.print("\nVocê solicitou a inserção de um post na sua timeline. \nPara isso, digite o texto do post: ");
        String postTexto = entrada.nextLine();

        System.out.println("\nValidando informações inseridas...\n");

        if (postTexto.isEmpty()){
            throw new CampoVazioException();
        } else {
            return new Post(postTexto);
        }
    }

    public boolean validarSenha(String senhaUsuario){
        return senhaUsuario.equals(senha);
    }
}