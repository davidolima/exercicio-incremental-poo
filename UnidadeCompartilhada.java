public class UnidadeCompartilhada extends Imovel{
    String identificacao;
    Condominio cond;
    public UnidadeCompartilhada(int iptu, int cep,
                           String rua, int num,
                           Estado estado, String cidade,
                           TipoImovel tipo, UtilizacaoImovel utilizacao,
                           boolean ocupacao, String identificacao,
                           Condominio cond) {
        super(iptu, cep, rua, num, estado,
              cidade, tipo, utilizacao, ocupacao);
        this.identificacao = identificacao;
        this.cond = cond;
    }
    public UnidadeCompartilhada(int iptu, int cep,
                           String rua, int num,
                           TipoImovel tipo, UtilizacaoImovel utilizacao,
                           boolean ocupacao, String identificacao,
                           Condominio cond) {
        super(iptu, cep, rua, num, tipo, utilizacao);
        this.identificacao = identificacao;
        this.cond = cond;
    }
    public UnidadeCompartilhada(int iptu, int cep,
                           String rua, int num,
                           Estado estado, String cidade,
                           TipoImovel tipo, UtilizacaoImovel utilizacao,
                           String identificacao, Condominio cond) {
        super(iptu, cep, rua, num, estado,
              cidade, tipo, utilizacao);
        this.identificacao = identificacao;
        this.cond = cond;
    }
    public UnidadeCompartilhada(int iptu, int cep,
                           String rua, int num,
                           Estado estado, String cidade,
                           TipoImovel tipo, UtilizacaoImovel utilizacao,
                           boolean ocupacao, String identificacao,
                           String rua_condominio, int num_condominio) {
        super(iptu, cep, rua, num, estado,
              cidade, tipo, utilizacao, ocupacao);
        this.identificacao = identificacao;
        this.cond = new Condominio(rua_condominio,num_condominio,cep,estado,cidade);
    }
    public float calcularRefAluguel(){
        if (this.cond.qtdLazer() > 0){
            return this.getIptu()*this.cond.qtdLazer();
        } else {
            return this.getIptu()*0.1f;
        }
    }

    @Override
    public String toString(){
        return "(UC) " + super.toString();
    }
}
