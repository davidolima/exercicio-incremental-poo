import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class App {
  private Tela tela;
  TipoTela tela_atual;
  Scanner in;
  boolean running;
  public static HashMap<Integer, Object> buffer; // Buffer de memória
  
  static ArrayList<Proprietario> usuarios; 
  static ArrayList<Imovel> imoveis;

  static String usuario;

  public App() {
    // Inicialização
    running = true;
    this.tela_atual = TipoTela.PRINCIPAL;
    this.tela = new Tela();
    this.usuarios = new ArrayList<Proprietario>();
    this.imoveis = new ArrayList<Imovel>();
    this.buffer = new HashMap<Integer, Object>();
    this.in = new Scanner(System.in);
    this.usuario = "";
    String input;

    //teste
    Proprietario prop1 = new Proprietario("abc", "abc", "abc", "abc", 1, 2, Estado.BA, "a");
    this.usuarios.add(prop1);

    while (running) {
      gerenciarInterface(null);
      input = in.nextLine();
      gerenciarInput(input);
    }
  }

  private void gerenciarVoltar(){
    // TODO: Futuramente utilizar uma pilha para gerenciar a volta dos menus.
    switch (this.tela_atual){
      case PRINCIPAL:
        this.running = false;
        break;

      case CADASTRO_IMOVEL:
      case ALUGAR_IMOVEL:
      case LISTAR_IMOVEIS:
        this.tela_atual = TipoTela.IMOVEL;
        break;

      case CADASTRO_PROPRIETARIO:
      case SELECIONAR_PROPRIETARIO:
        this.tela_atual = TipoTela.PROPRIETARIO;
        break;
      
      default:
        this.tela_atual = TipoTela.PRINCIPAL;
    }
  }

  private void gerenciarInput(String input) {
    if (input.equals("0")) { // Todas as telas podem voltar.
      gerenciarVoltar();
    }

    switch (this.tela_atual) {
      case PRINCIPAL:
        switch (input) {
          case "1":
            this.tela_atual = TipoTela.PROPRIETARIO;
            break;
          case "2":
            this.tela_atual = TipoTela.IMOVEL;
            break;
          default:
            break;
        }
        break;
      case PROPRIETARIO:
        switch (input) {
          case "1":
            this.tela_atual = TipoTela.CADASTRO_PROPRIETARIO;
            break;
          case "2":
            this.tela_atual = TipoTela.SELECIONAR_PROPRIETARIO;
            break;
          default:
            break;
        }
        break;
      case IMOVEL:
        switch (input) {
          case "1":
            this.tela_atual = TipoTela.CADASTRO_IMOVEL;
            break;
          case "2":
            this.tela_atual = TipoTela.ALUGAR_IMOVEL;
            break;
          case "3":
            this.tela_atual = TipoTela.LISTAR_IMOVEIS;
            break;
          default:
            break;
        }
        break;
      case CADASTRO_IMOVEL:
        switch (input) {
          case "1":
            break;
          case "2":
            break;
          case "3":
            break;
          case "4":
            break;
          case "5":
            break;
          case "6":
            break;
          case "7":
            break;
          case "8":
            break;
          case "9":
            break;
          default:
            break;
        }
        break;
      case CADASTRO_PROPRIETARIO:
        String valor;
        int num;
        switch (input) {
          case "1":
            System.out.printf("> ");
            valor = this.in.nextLine();
            this.buffer.put(1, valor);
            break;
          case "2":
            System.out.printf("> ");
            valor = this.in.nextLine();
            this.buffer.put(2, valor);
            break;
          case "3":
            System.out.printf("> ");
            valor = this.in.nextLine();
            this.buffer.put(3, valor);
            break;
          case "4":
            System.out.printf("> ");
            valor = this.in.nextLine();
            this.buffer.put(4, valor);
            break;
          case "5":
            System.out.printf("> ");
            num = this.in.nextInt();
            this.buffer.put(5, num);
            break;
          case "6":
            System.out.printf("> ");
            num = this.in.nextInt();
            this.buffer.put(6, num);
            break;
          case "7":
            // Por enquanto estados serão representados por sua posição
            // no enum.
            System.out.printf("> ");
            num = this.in.nextInt();
            this.buffer.put(7, num);
            break;
          case "8":
            System.out.printf("> ");
            valor = this.in.nextLine();
            this.buffer.put(8, valor);
            break;
          case "9":
            Proprietario newprop = new Proprietario(
                                            (String) App.buffer.get(1),
                                            (String) App.buffer.get(2),
                                            (String) App.buffer.get(3),
                                            (String) App.buffer.get(4),
                                            (int) App.buffer.get(5),
                                            (int) App.buffer.get(6),
                                            Estado.values()[(int) App.buffer.get(7)],
                                            (String) App.buffer.get(8)
                                            );
            this.usuarios.add(newprop);
            App.limparMem();
            this.gerenciarVoltar();
            break;

          default:
            break;
        }
        break;
      case ALUGAR_IMOVEL:
        switch (input) {
          default:
            break;
        }
        break;
      case LISTAR_IMOVEIS:
        switch (input) {
          default:
            break;
        }
        break;
      case SELECIONAR_PROPRIETARIO:
        // Se pensamor em muitos usuários, precisamos de paginação.
        // Para o propósito, isso serve.
        int i = Integer.parseInt(input);
        System.out.println(input);
        String nome = App.getUsuarios().get(i-1).getNome();
        App.setUsuario(nome);
        gerenciarVoltar();
        break;
      default:
        throw new RuntimeException("Erro: Unreachable. (gerenciarInput)");
    }
  }

  private void gerenciarInterface(String arg) {
    tela.changeTela(this.tela_atual);
    System.out.print(tela.toString());
    System.out.print("Escolha uma opção: ");
 }

  public static ArrayList<Imovel> getImoveis(){
    return App.imoveis;
  }
  public static ArrayList<Proprietario> getUsuarios(){
    return App.usuarios;
  }
  public static String[] getListaUsuarios(){
    String[] r = new String[App.usuarios.size()];
    for (int i = 0; i < App.usuarios.size(); i++){
      String x = App.usuarios.get(i).getNome();
      r[i] = x;
    }
    return r;
  }
  public static HashMap<Integer, Object> getMemoria(){
    return App.buffer;
  }
  public static void setUsuario(String nome){
    App.usuario = nome;
  }
  public static String getUsuario(){
    return App.usuario;
  }
  public static void limparMem(){
    App.buffer.clear();
  }
}
