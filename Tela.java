import java.util.ArrayList;

public class Tela {
  String titulo;
  String[] desc_cmd;

  public Tela() {
  }

  public Tela(TipoTela tipo) {
    this.changeTela(tipo);
  }

  public String toString() {
    String out = this.titulo + " ---\n";
    for (int i = 0; i < this.desc_cmd.length; i++) {
      String desc = this.desc_cmd[i];
      out += "(" + i + ") - " + desc + "\n";
    }
    return out;
  }

  private void setInfo(String titulo, String[] desc_cmd) {
    this.titulo = titulo;
    this.desc_cmd = desc_cmd;
  }

  public void changeTela(TipoTela novaTela) {
    // Limpar tela do terminal
    System.out.print("\033[H\033[2J");
    System.out.flush();

    // muda as informações da tela
    String titulo = "";
    String[] desc = { "Sair" };
    switch (novaTela) {
      case PRINCIPAL:
        titulo = "Menu Principal";
        desc = new String[] {
            "Sair/Encerrar programa",
            "Proprietário",
            "Imóvel",
        };
        break;
      case PROPRIETARIO:
        titulo = "Proprietário";
        desc = new String[] {
            "Voltar para o menu principal",
            "Cadastrar um novo usuário",
            "Selecionar proprietário",
        };
        break;
      case IMOVEL:
        titulo = "Imóvel";
        desc = new String[] {
            "Voltar para o menu principal",
            "Cadastrar um novo imóvel",
            "Alugar imóveis disponíveis",
            "Listar todos os imóveis",
        };
        break;
      case CADASTRO_IMOVEL:
        titulo = "Cadastrar Imóvel";
        desc = new String[] {
            "Voltar",
            "IPTU:",
            "Rua:",
            "Número:",
            "CEP:",
            "Estado:",
            "Cidade:",
            "Tipo de imóvel:",
            "Utilização:",
            "Terminar Cadastro",
        };

        break;
      case CADASTRO_PROPRIETARIO:
        titulo = "Cadastrar Proprietário";
        desc = new String[] {
            "Voltar",
            "Nome:",
            "CPF:",
            "Identidade:",
            "Endereço de residência:",
            "Número da casa/prédio:",
            "CEP:",
            "Estado:",
            "Cidade:",
            "Terminar Cadastro",
        };
        break;
      case ALUGAR_IMOVEL:
        titulo = "Alugar Imóvel";
        break;
      case LISTAR_IMOVEIS:
        titulo = "Lista de Imóveis";

        break;
      case SELECIONAR_PROPRIETARIO:
        // String[] arr = new String[App.getImoveis().size()];
        titulo = "Selecionar Proprietário";
        desc = App.getImoveis().toArray(desc);
        break;
      default:
        throw new RuntimeException("ERRO: Unreachable. (changeTela)");
    }
    this.setInfo(titulo, desc);
  }

  public void changeTela(String titulo, String[] desc) {
    this.setInfo(titulo, desc);
  }
}