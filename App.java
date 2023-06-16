import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class App {
  private Tela tela;
  TipoTela tela_atual;
  static Scanner in;
  boolean running;
  public static HashMap<Integer, Object> buffer; // Buffer de memória
  
  static ArrayList<Proprietario> usuarios; 
  static ArrayList<Imovel> imoveis;
  static ArrayList<Condominio> condominios;

  static String usuario_selecionado;
  static Imovel imovel_selecionado;
  static String msg = "", dbg_msg = "";

  public App() {
    // Inicialização
    running = true;
    this.tela_atual = TipoTela.PRINCIPAL;
    this.tela = new Tela();
    App.usuarios = new ArrayList<Proprietario>();
    App.imoveis = new ArrayList<Imovel>();
    App.condominios = new ArrayList<Condominio>();
    App.buffer = new HashMap<Integer, Object>();
    App.in = new Scanner(System.in);
    App.usuario_selecionado = "";
    App.imovel_selecionado = null;
    String input;

    // teste --
    Proprietario prop1 = new Proprietario("abc", "abc", "abc", "abc", 1, 2, Estado.BA, "a");
    UnidadeAutonoma ua = new UnidadeAutonoma(250, 2, "Rua B", 3, Estado.BA, "Salvador", TipoImovel.APARTAMENTO, UtilizacaoImovel.CAMPO, false, 20, 15);
    ArrayList<String> l = new ArrayList<String>();
    l.add("piscina"); l.add("academia"); l.add("salao");
    Condominio cond = new Condominio("Rua A", 1,2, Estado.BA, "Salvador", l);
    UnidadeCompartilhada uc = new UnidadeCompartilhada(500, 3, "Rua A", 3, Estado.SP, "São Paulo", TipoImovel.CASA, UtilizacaoImovel.PRAIA, false, "10", cond);
    App.usuarios.add(prop1);
    App.imoveis.add(uc);
    App.imoveis.add(ua);
    App.condominios.add(cond);
    prop1.alocar(ua);
    // --

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
        this.tela_atual = TipoTela.ESCOLHA_TIPO_IMOVEL;
        break;

      case ESCOLHA_TIPO_IMOVEL:
      case ALUGAR_IMOVEL:
      case LISTAR_IMOVEIS:
      case CALC_VAL_REF_IMOVEL:
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
    String valor;
    int num, i;

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
          case "3":
            this.tela_atual = TipoTela.LISTAR_CONDOMINIO;
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
            this.tela_atual = TipoTela.ESCOLHA_TIPO_IMOVEL;
            break;
          case "2":
            if (!App.getUsuario().equals("")){
              this.tela_atual = TipoTela.ALUGAR_IMOVEL;
            } else {
              App.setMsg("É necessário escolher um proprietário para alugar um imóvel.");
            }
            break;
          case "3":
            this.tela_atual = TipoTela.LISTAR_IMOVEIS;
            break;
          case "4":
            if (!App.getUsuario().equals("")){
              this.tela_atual = TipoTela.BLOQUEAR_IMOVEL;
            } else {
              this.msg = "É necessário escolher um proprietário para bloquear um imóvel.\n";
            }
            break;
          case "5":
            this.tela_atual = TipoTela.CALC_VAL_REF_IMOVEL;
            break;
          default:
            break;
        }
        break;
      case CADASTRO_PROPRIETARIO:
        switch (input) {
          case "1":
            System.out.printf("> ");
            valor = App.in.nextLine();
            App.buffer.put(1, valor);
            break;
          case "2":
            System.out.printf("> ");
            valor = App.in.nextLine();
            App.buffer.put(2, valor);
            break;
          case "3":
            System.out.printf("> ");
            valor = App.in.nextLine();
            App.buffer.put(3, valor);
            break;
          case "4":
            System.out.printf("> ");
            valor = App.in.nextLine();
            App.buffer.put(4, valor);
            break;
          case "5":
            System.out.printf("> ");
            num = App.in.nextInt();
            App.buffer.put(5, num);
            break;
          case "6":
            System.out.printf("> ");
            num = App.in.nextInt();
            App.buffer.put(6, num);
            break;
          case "7":
            // Por enquanto estados serão representados por sua posição
            // no enum.
            System.out.printf("> ");
            num = App.in.nextInt();
            App.buffer.put(7, num);
            break;
          case "8":
            System.out.printf("> ");
            valor = App.in.nextLine();
            App.buffer.put(8, valor);
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
        }
        break;
      case CADASTRO_IMOVEL:
        if ((int) App.getMemoria().get(-1) != 1){
          switch (input) {
              case "1":
                System.out.printf("> ");
                num = App.in.nextInt();
                App.buffer.put(1, num);
                break;
              case "2":
                System.out.printf("> ");
                num = App.in.nextInt();
                App.buffer.put(2, num);
                break;
              case "3":
                System.out.printf("> ");
                valor = App.in.nextLine();
                App.buffer.put(3, valor);
                break;
              case "4":
                System.out.printf("> ");
                num = App.in.nextInt();
                App.buffer.put(4, num);
                break;
              case "5":
                System.out.printf("> ");
                num = App.in.nextInt();
                App.buffer.put(5, num);
                break;
              case "6":
                System.out.printf("> ");
                valor = App.in.nextLine();
                App.buffer.put(6, valor);
                break;
              case "7":
                // Por enquanto estados serão representados por sua posição
                // no enum.
                System.out.printf("> ");
                num = App.in.nextInt();
                App.buffer.put(7, num);
                break;
              case "8":
                System.out.printf("> ");
                num = App.in.nextInt();
                App.buffer.put(8, num);
                break;
              case "9":
                System.out.printf("> ");
                num = App.in.nextInt();
                App.buffer.put(9, num);
                break;
              case "10":
                System.out.printf("> ");
                num = App.in.nextInt();
                App.buffer.put(10, num);
                break;
              case "11":
                // FIXME: Enums (Como estado e utilizacao) tratados como int por enquanto.
                Imovel new_imovel = new UnidadeAutonoma((int) App.buffer.get(1),
                                             (int) App.buffer.get(2),
                                             (String) App.buffer.get(3),
                                             (int) App.buffer.get(4),
                                             Estado.values()[(int) App.buffer.get(5)],
                                             (String) App.buffer.get(6),
                                             TipoImovel.values()[(int) App.buffer.get(7)],
                                             UtilizacaoImovel.values()[(int) App.buffer.get(8)],
                                             false,
                                             (int) App.buffer.get(9),
                                             (int) App.buffer.get(10)
                                             );

                App.imoveis.add(new_imovel);
                if (!App.getNomeUsuario().equals("")){
                  App.getUsuario().alocar(new_imovel);
                }
                App.limparMem();
                this.gerenciarVoltar();
                break;
            default:
                break;
          }
        } else {
          switch (input) {
              case "1":
                System.out.printf("> ");
                num = App.in.nextInt();
                App.buffer.put(1, num);
                break;
              case "2":
                System.out.printf("> ");
                num = App.in.nextInt();
                App.buffer.put(2, num);
                break;
              case "3":
                System.out.printf("> ");
                valor = App.in.nextLine();
                App.buffer.put(3, valor);
                break;
              case "4":
                System.out.printf("> ");
                num = App.in.nextInt();
                App.buffer.put(4, num);
                break;
              case "5":
                System.out.printf("> ");
                num = App.in.nextInt();
                App.buffer.put(5, num);
                break;
              case "6":
                System.out.printf("> ");
                valor = App.in.nextLine();
                App.buffer.put(6, valor);
                break;
              case "7":
                // Por enquanto estados serão representados por sua posição
                // no enum.
                System.out.printf("> ");
                num = App.in.nextInt();
                App.buffer.put(7, num);
                break;
              case "8":
                System.out.printf("> ");
                num = App.in.nextInt();
                App.buffer.put(8, num);
                break;
              case "9":
                System.out.printf("> ");
                valor = App.in.nextLine();
                App.buffer.put(9, valor);
                break;
              case "10":
                System.out.printf("> ");
                valor = App.in.nextLine();
                App.buffer.put(10, valor);
                break;
              case "11":
                System.out.printf("> ");
                num = App.in.nextInt();
                App.buffer.put(11, num);
                break;
              case "12":
                Imovel new_imovel = new UnidadeCompartilhada((int) App.buffer.get(1),
                                                      (int) App.buffer.get(2),
                                                      (String) App.buffer.get(3),
                                                      (int) App.buffer.get(4),
                                                      Estado.values()[(int) App.buffer.get(5)],
                                                      (String) App.buffer.get(6),
                                                      TipoImovel.values()[(int) App.buffer.get(7)],
                                                      UtilizacaoImovel.values()[(int) App.buffer.get(8)],
                                                      false,
                                                      (String) App.buffer.get(9),
                                                      (String) App.buffer.get(10),
                                                      (int) App.buffer.get(11)
                                                      );
                App.imoveis.add(new_imovel);
                if (!App.getNomeUsuario().equals("")){
                  App.getUsuario().alocar(new_imovel);
                }
                App.limparMem();
                this.gerenciarVoltar();
                break;
              default:
                break;
              }
           }
        break;

      case ALUGAR_IMOVEL: // TODO
        switch (input) {
          default:
            break;
        }
        break;

      case LISTAR_IMOVEIS:
        break;

      case LISTAR_CONDOMINIO:
        break;

      case BLOQUEAR_IMOVEL:
        i = Integer.parseInt(input);
        Imovel imovel = App.getUsuario().getImoveis().get(i-1);

        System.out.println("Insira uma data (dd/mm/aaaa):");
        System.out.printf("> ");
        String[] data = App.in.nextLine().split("/");

        App.getUsuario().bloquearImovel(imovel.getEndereco(),
                                        Integer.parseInt(data[0]),
                                        Integer.parseInt(data[1]),
                                        Integer.parseInt(data[2]));
        tela.changeTela(TipoTela.BLOQUEAR_IMOVEL);
        break;
      case ESCOLHA_TIPO_IMOVEL:
        i = Integer.parseInt(input);
        App.buffer.put(-1, i);
        this.tela_atual = TipoTela.CADASTRO_IMOVEL;
        break;
      case SELECIONAR_PROPRIETARIO:
        // Se pensamor em muitos usuários, precisamos de paginação.
        // Para o propósito, isso serve.
        i = Integer.parseInt(input);
        String nome = App.getUsuarios().get(i-1).getNome();
        App.setUsuario(nome);
        gerenciarVoltar();
        break;

      case CALC_VAL_REF_IMOVEL:
        i = Integer.parseInt(input);
        System.out.printf("> Indice: ");
        int indice_saz = App.in.nextInt();
        App.in.nextLine();
        Imovel im = App.getImoveis().get(i-1);

        // ref + ref*indice
        float val_ref = im.calcularRefAluguel() + App.calcTaxaSazonalidade(im, indice_saz);
        App.setMsg(String.format("Valor de referência: %.2f + %.2f = %.2f",im.calcularRefAluguel(),App.calcTaxaSazonalidade(im, indice_saz), val_ref));
        break;
      default:
        throw new RuntimeException("Erro: Unreachable. (gerenciarInput)");
    }
  }

  private void gerenciarInterface(String arg) {
    tela.changeTela(this.tela_atual);
    if (!App.dbg_msg.equals("")){
      System.out.print("[DEBUG] "+App.dbg_msg);
    }
    System.out.print(App.msg);
    System.out.print(tela.toString());
    System.out.print("Escolha uma opção: ");
    App.msg = "";
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
  public static String[] getListaCondominios(){
    String[] r = new String[App.usuarios.size()];
    for (int i = 0; i < App.condominios.size(); i++){
      String x = App.usuarios.get(i).getNome();
      r[i] = x;
    }
    return r;
  }
  public static String[] getListaImoveis(){
    String[] r = new String[App.imoveis.size()];
    for (int i = 0; i < App.imoveis.size(); i++){
      String x = App.imoveis.get(i).toString();
      r[i] = x;
    }
    return r;
  }
  public static HashMap<Integer, Object> getMemoria(){
    return App.buffer;
  }
  public static void setUsuario(String nome){
    App.usuario_selecionado = nome;
  }
  public static void setImovel(Imovel imovel){
    App.imovel_selecionado = imovel;
  }
  public static String getNomeUsuario(){
    return App.usuario_selecionado;
  }
  public static Proprietario getUsuario(){
    for (Proprietario p : App.usuarios){
      if (p.getNome().equals(App.usuario_selecionado)){
        return p;
      }
    }
    return null;
  }
  public static void limparMem(){
    App.buffer.clear();
  }
  public static void setMsg(String newMsg){
    App.msg = newMsg + "\n";
  }
  public static void setDbgMsg(Object newMsg){
    App.dbg_msg = String.valueOf(newMsg) + "\n";
  }
  private static float calcTaxaSazonalidade(Imovel im, int indice){
    if (im instanceof UnidadeAutonoma){
      im = (UnidadeAutonoma) im;
      App.setDbgMsg("UnidadeAutonoma");
    } else if (im instanceof UnidadeCompartilhada){
      im = (UnidadeCompartilhada) im;
      App.setDbgMsg("UnidadeCompartilhada");
    }
    float r = im.calcularRefAluguel();
    switch (indice){
      case 1:
        r *= 0.20f;
        break;
      case 2:
        r *= 0.15f;
        break;
      case 3:
        r *= 0.10f;
        break;
      case 4:
        r *= 0.05f;
        break;
      default:
        r *= 0;
        break;
    }

    return r; // valor_ref * indice
  }
}
