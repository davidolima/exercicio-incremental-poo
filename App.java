import java.util.Scanner;
import java.util.ArrayList;

public class App {
  private Tela tela;
  TipoTela tela_atual;
  Scanner in;
  boolean running;
  
  static ArrayList<Proprietario> usuarios; 
  static ArrayList<Imovel> imoveis;

  public App() {
    // Inicialização
    running = true;
    this.tela_atual = TipoTela.PRINCIPAL;
    this.tela = new Tela();
    usuarios = new ArrayList<Proprietario>();
    imoveis = new ArrayList<Imovel>();
    this.in = new Scanner(System.in);
    String input;
    
    while (running) {
      gerenciarInterface();
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
          default:
            break;
        }
        break;
      case CADASTRO_PROPRIETARIO:
        switch (input) {
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
        switch (input) {
          default:
            break;
        }
        break;
      default:
        throw new RuntimeException("Erro: Unreachable. (gerenciarInput)");
    }
  }

  private void gerenciarInterface() {
    tela.changeTela(this.tela_atual);
    System.out.print(tela.toString());
    System.out.print("Escolha uma opção: ");
  }

  public static ArrayList<Imovel> getImoveis(){
    return imoveis;
  }
}
