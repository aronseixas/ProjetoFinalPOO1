package redesocial;

import redesocial.exceptions.*;
import redesocial.usuarios.Post;
import redesocial.usuarios.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RedeSocial {
    private Scanner entrada = new Scanner(System.in);
    List<Usuario> listaDeUsuarios = new ArrayList<>();
    private int posicaoDoUsuario = 0;
    private static final RedeSocial instance = new RedeSocial();

    public static void main(String[] args) {
        RedeSocial.getInstance().AcessarRedeSocial();
    }
    private RedeSocial() {}

    public static RedeSocial getInstance() {
            return instance;
    }

    private void AcessarRedeSocial(){
        String opcaoMenuPrincipal;
        TelaBoasVindas();

        do{
            opcaoMenuPrincipal = TelaInicial();

            switch (opcaoMenuPrincipal) {
                case "C":
                    try{
                        cadastroDeUsuario();
                    }catch (CampoVazioException e){
                        System.out.print(e.getMessageCVE());
                    } catch (LoginExistenteExcepetion e){
                        System.out.print(e.getMessageLEE());
                    }
                    break;
                case "E":
                    try{
                        loginDeUsuario();
                    }catch (NoUserPlataformException e){
                        System.out.print(e.getMessageNUP());
                    } catch (UserNotFoundException e){
                        System.out.print(e.getMessageUNFE());
                    } catch (InvalidPasswordException e){
                        System.out.print(e.getMessageIPE());
                    } catch (CampoVazioException e){
                        System.out.print(e.getMessageCVE());
                    }
                    break;
                case "F":
                    System.out.println("\nVocê solicitou a saída da Rede #Dev_Makers. \nEstamos ansiosos para uma próxima visita.");
                    break;
                case "L":
                    try{
                        ListarUsuarios();
                    }catch (SemUsuariosCadastradosException e){
                        System.out.println(e.getMessageSUCE());
                    }
                    break;
                default:
                    System.out.println("\nFoi digitado um código inválido. Retornando para o Menu principal.");
                    break;
            }
            System.out.println("------------------------------------------------------------------------------------------------------");
        }while (!opcaoMenuPrincipal.equals("F"));
    }

    private void TelaBoasVindas(){
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("Seja Bem-vindo a rede social #Dev_Makers.");
        System.out.println("------------------------------------------------------------------------------------------------------");
    }

    private String TelaInicial(){
        System.out.println("MENU INICIAL");
        System.out.println("Neste momento voce está na página inical. Digite o código referente a ação que desejas fazer.");
        System.out.println("'C': Cadastro");
        System.out.println("'E': Entrar");
        System.out.println("'L': Listar Usuários");
        System.out.println("'F': Fechar");
        System.out.print("Opção desejada: ");
        return entrada.nextLine().toUpperCase();
    }
    private void ListarUsuarios() throws SemUsuariosCadastradosException{
        System.out.println("\nSolicitação de listagem de usuários na Rede Social #Dev_Makers");
        if(listaDeUsuarios.isEmpty()){
            throw new SemUsuariosCadastradosException();
        } else {
            for (int i = 0; i < listaDeUsuarios.size(); i++) {
                System.out.printf("Usuário %d - Nome: %s login: %s\n",i+1, listaDeUsuarios.get(i).getNome(), listaDeUsuarios.get(i).getLogin());
            }
            System.out.println("\nListagem de usuários cadastrados completa");
        }
    }
    private void cadastroDeUsuario() throws CampoVazioException, LoginExistenteExcepetion{
        System.out.print("\nSolicitação de cadastro de novo usuário solicitada. \nPara isso é necessário inserir algumas informações. \nDigite o nome desejado: ");
        String nomeUsuario = entrada.nextLine();
        System.out.print("Digite o Login: ");
        String loginUsuario = entrada.nextLine();
        System.out.print("Digite a senha: ");
        String senhaUsuario = entrada.nextLine();
        System.out.println("\nValidando infomações inseridas ...");

        if (nomeUsuario.isEmpty() || senhaUsuario.isEmpty() || loginUsuario.isEmpty()){
            throw new CampoVazioException();
        }

        for (Usuario listaDeUsuario : listaDeUsuarios) {
            if (loginUsuario.equals(listaDeUsuario.getLogin())) {
                throw new LoginExistenteExcepetion();
            }
        }

        Usuario novoUsuario = new Usuario(nomeUsuario, loginUsuario, senhaUsuario);
        listaDeUsuarios.add(novoUsuario);
        System.out.printf("\nAção realizada com sucesso. \nSeja bem-vindo %s, seu cadastro foi inserido com sucesso e seu login a partir de agora é: %s\n", nomeUsuario, loginUsuario);
    }
    private void loginDeUsuario() throws CampoVazioException, NoUserPlataformException, UserNotFoundException, InvalidPasswordException{

        boolean checkUsuario = false;

        if(listaDeUsuarios.isEmpty()){
            throw new NoUserPlataformException();
        }

        System.out.println("\nSolicitação de Login realizada. \nRedirecionando para Tela de Login de usuário");
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("MENU DE ENTRADA DO PERFIL.");
        System.out.println("Para entrar no seu perfil, insira suas informações ");
        System.out.print("DIGITE SEU LOGIN: ");
        String loginUsuario = entrada.nextLine();
        System.out.print("DIGITE SUA SENHA: ");
        String senhaUsuario = entrada.nextLine();
        System.out.println("\nValidando informações inseridas...");

        if (loginUsuario.isEmpty() || senhaUsuario.isEmpty()){
            throw new CampoVazioException();
        }

        for (int i = 0; i < listaDeUsuarios.size(); i++) {
            if (loginUsuario.equals(listaDeUsuarios.get(i).getLogin())){
                checkUsuario = true;
                posicaoDoUsuario = i;
            }
        }
        if(checkUsuario){
            System.out.print("\nLogin ✓");
        }else {
            throw new UserNotFoundException();
        }

        if(listaDeUsuarios.get(posicaoDoUsuario).validarSenha(senhaUsuario)){
            System.out.println("\nSenha ✓");
        } else {
            throw new InvalidPasswordException();
        }
        AcessarPerfil();
    }

    private void AcessarPerfil(){

        String opcaoPerfilUsuario;

            do{
                opcaoPerfilUsuario = TelaDePerfil();

                switch (opcaoPerfilUsuario) {
                    case "P":
                        try{
                            Post post = listaDeUsuarios.get(posicaoDoUsuario).PostarTimeLine();
                            listaDeUsuarios.get(posicaoDoUsuario).getListaDePosts().add(post);
                            System.out.printf("Post %d inserido com sucesso\n", listaDeUsuarios.get(posicaoDoUsuario).getListaDePosts().size());
                        } catch (CampoVazioException e){
                            System.out.print(e.getMessageCVE());
                        }
                        break;
                    case "T":
                        System.out.println("\nVocê solicitou a exibição da sua timeline.");
                        try{
                            ExibirTimeLine(posicaoDoUsuario);
                        }catch (NoPostsOnPlataformException e){
                            System.out.println(e.getMessageNPOP());
                        }
                        break;
                    case "S":
                        System.out.println("\nVocê Optou por sair do perfil. \nObrigado pela visita e estamos ansiosos pelo seu retorno.");
                        break;
                    default:
                        System.out.println("\nFoi Digitado um comando inválido. Insira um comando válido.");
                        break;
                }
            }while(!opcaoPerfilUsuario.equals("S"));
    }

    private String TelaDePerfil(){
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.printf("Bem-Vindo ao seu Perfil %s.", listaDeUsuarios.get(posicaoDoUsuario).getNome());
        System.out.println("\nO que você gostaria de fazer?");
        System.out.println("\nDigite o código referente ao seu desejo.");
        System.out.println("'P': Postar");
        System.out.println("'T': TimeLine");
        System.out.println("'S': Sair");
        System.out.print("Opção desejada: ");
        return entrada.nextLine().toUpperCase();
    }
    private void ExibirTimeLine(int posicaoUsuario) throws NoPostsOnPlataformException{
        if(this.listaDeUsuarios.get(posicaoUsuario).getListaDePosts().isEmpty()){
            throw new NoPostsOnPlataformException();
        }else{
            for (int i = 0; i < listaDeUsuarios.get(posicaoUsuario).getListaDePosts().size(); i++) {
                System.out.printf("Post %d - ",i+1);
                System.out.printf("Dia: %s ", this.listaDeUsuarios.get(posicaoUsuario).getListaDePosts().get(i).getData());
                System.out.printf("às %s. ", this.listaDeUsuarios.get(posicaoUsuario).getListaDePosts().get(i).getHora());
                System.out.println(this.listaDeUsuarios.get(posicaoUsuario).getListaDePosts().get(i).getTexto());
            }
        }
    }
}