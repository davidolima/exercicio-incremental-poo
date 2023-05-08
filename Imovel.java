
public class Imovel {
	private int iptu;
	private TipoImovel tipo;
	private UtilizacaoImovel utilizacao;  
	private boolean ocupacao;

	private Endereco endereco;
	
    public Imovel(int iptu, int cep,  String rua, int num, Estado estado, String cidade, TipoImovel tipo, UtilizacaoImovel utilizacao)    
    {
    	this.endereco = new Endereco(rua, num, cep, estado, cidade);
    	this.iptu = iptu;
        this.tipo = tipo;
        this.utilizacao = utilizacao;
        this.ocupacao = false;
    }
    public Imovel(int iptu, int cep,  String rua, int num, TipoImovel tipo, UtilizacaoImovel utilizacao)    
    {
    	this.endereco = new Endereco(rua, num, cep, Estado.BA, "Salvador");
        this.tipo = tipo;
        this.utilizacao = utilizacao;
        this.ocupacao = false;
    }
    public Imovel(int iptu, int cep,  String rua, int num, Estado estado, String cidade, TipoImovel tipo, UtilizacaoImovel utilizacao, boolean ocupacao)    
    {
    	this.endereco = new Endereco(rua, num, cep, estado, cidade);
    	this.iptu = iptu;
        this.tipo = tipo;
        this.utilizacao = utilizacao;
        this.ocupacao = ocupacao;
    }
    public int getIptu(){
        return this.iptu;
    }
    public void setIptu(int new_iptu) {
        this.iptu = new_iptu;
    }
    public Endereco getEndereco() {
    	return this.endereco;
    }
    public TipoImovel getTipo(){
        return this.tipo;
    }
    public void setTipo(TipoImovel new_tipo) {
    	this.tipo = new_tipo;
    }
    public UtilizacaoImovel getUtilizacao(){
        return this.utilizacao;
    }
    public void setUtilizacao(UtilizacaoImovel new_utilizacao){
        this.utilizacao = new_utilizacao;
    }
    public void setOcupacao(boolean ocupacao) {
    	this.ocupacao = ocupacao;
    }
    public boolean getOcupacao() {
    	return this.ocupacao;
    }
    public String toString(){
        String s = this.tipo.toString().toLowerCase() + ", Ocupado: " + this.ocupacao + ", End.: " + this.endereco.toString();
        return s;
    }
}
