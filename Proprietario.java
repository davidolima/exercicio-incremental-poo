import java.util.ArrayList;
import java.util.Iterator;

public class Proprietario {
	private String nome;
    private String cpf; // CPF como string pois não iremos tratá-lo como número.
    private String identidade; // Mesma coisa para a identidade.
    
    private Endereco endereco;
 
    private ArrayList<Imovel> imoveis; // Imoveis alocados
    public Proprietario(String nome, String cpf, String identidade, String rua, int num, int cep, Estado estado, String cidade)
    {
        this.nome = nome;
        this.cpf = cpf;
        this.identidade = identidade;
        this.endereco = new Endereco(rua, num, cep, estado, cidade);
        this.imoveis = new ArrayList<Imovel>();
    }
    public String getIdentidade() {
    	return this.identidade;
    }
    public String getCpf() {    	
    	return this.cpf;
    }
    public String getNome() {
    	return this.nome;
    }
    public Endereco getEndereco() {
    	return this.endereco;
    }
    public void setEndereco(int cep, String rua, int num, Estado estado, String cidade) {
        Endereco novo_endereco = new Endereco(rua, num, cep, estado, cidade);
        this.endereco = novo_endereco;
    }
    public void setEndereco(int cep, String rua, int num) {
        this.endereco.setRua(rua);
        this.endereco.setNum(num);
        this.endereco.setCep(cep);
    }
    public void alocar(Imovel i) {
    	if(i.getEndereco().equals(this.getEndereco())) {
    		return;
    	}
    	this.imoveis.add(i);
    }
    public void alocar(int iptu, int cep,  String rua, int num, Estado estado, String cidade, TipoImovel tipo, UtilizacaoImovel utilizacao) {
    	if(this.getEndereco().equals(rua, num, cep, estado, cidade)) {
    		return;
    	}
    	Imovel i = new Imovel(iptu, cep, rua, num, estado, cidade, tipo, utilizacao);
    	this.imoveis.add(i);
    }
    public ArrayList<Imovel> getImoveis() {
        return this.imoveis;
    };

    public void listarImoveis(TipoImovel tipo){
        System.out.print(tipo.toString().toLowerCase() + "s de " + this.getNome() + ":\n");
        for (Imovel i : this.imoveis){
            if (i.getTipo() == tipo){
                System.out.print(i.toString() + "; ");
            }
        }
        System.out.print("\n");
    }

    public void listarImoveis(){
        System.out.print("Imóveis de " + this.getNome() + ":\n");
        for (Imovel i : this.imoveis){
            System.out.print(i.toString() + "; ");
        }
        System.out.print("\n");
    }
}
