import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class Tela {
  String titulo;
  String[] desc_cmd;

  public Tela() {
  }

  public Tela(TipoTela tipo) {
    this.changeTela(tipo);
  }

  public String toString() {
    // Gera a tela
    String out = this.titulo + " --- " + App.getNomeUsuario() + "\n";
    HashMap buffer = App.getMemoria();
    for (int i = 0; i < this.desc_cmd.length; i++) {
      String desc;
      if (buffer.isEmpty()){
        desc = this.desc_cmd[i].replace("{0}", "");
      } else {
        desc = MessageFormat.format(this.desc_cmd[i], buffer.get(i));
      }
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
    String[] lista;
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
            "Bloquear um imóvel",
        };
        break;
      case CADASTRO_IMOVEL:
        titulo = "Cadastrar Imóvel";
        desc = new String[] {
            "Voltar",
            "IPTU: {0}",
            "Rua: {0}",
            "Número: {0}",
            "CEP: {0}",
            "Estado: {0}",
            "Cidade: {0}",
            "Tipo de imóvel: {0}",
            "Utilização: {0}",
            "Terminar Cadastro",
        };
        break;
      case BLOQUEAR_IMOVEL:
        titulo = "Bloquear imóvel";
        ArrayList<Imovel> imoveis = App.getUsuario().getImoveis();
        if (imoveis.size() <= 0){
          App.setMsg("Você não possui imóveis!\n");
          break;
        }
        desc = new String[imoveis.size()+1];
        desc[0] = "Sair";
        for (int i = 0; i < imoveis.size(); i++){
          desc[i+1] = imoveis.get(i).toString();
        }
        break;
      case CADASTRO_PROPRIETARIO:
        titulo = "Cadastrar Proprietário";
        desc = new String[] {
            "Voltar",
            "Nome: {0}",
            "CPF: {0}",
            "Identidade: {0}",
            "Endereço de residência: {0}",
            "Número da casa/prédio: {0}",
            "CEP: {0}",
            "Estado: {0}",
            "Cidade: {0}",
            "Terminar Cadastro",
        };
        break;
      case ALUGAR_IMOVEL:
        titulo = "Alugar Imóvel";
        break;
      case LISTAR_IMOVEIS:
        titulo = "Lista de Imóveis";
        lista = App.getListaImoveis();
        desc = new String[lista.length+1];
        desc[0] = "Sair";
        for (int i = 0; i < lista.length; i++){
          desc[i+1] = lista[i];
        }
        break;
      case SELECIONAR_PROPRIETARIO:
        titulo = "Selecionar Proprietário";
        lista = App.getListaUsuarios();
        desc = new String[lista.length+1];
        desc[0] = "Sair";
        for (int i = 0; i < lista.length; i++){
          desc[i+1] = lista[i];
        }
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
