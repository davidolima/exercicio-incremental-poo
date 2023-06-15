public class UnidadeAutonoma extends Imovel{
    int area_util;
    int area_construida;
    public UnidadeAutonoma(int iptu, int cep, String rua, int num,
                           Estado estado, String cidade,
                           TipoImovel tipo, UtilizacaoImovel utilizacao,
                           boolean ocupacao, int area_util,
                           int area_construida){
        super(iptu, cep, rua, num, estado,
              cidade, tipo, utilizacao, ocupacao);
        this.area_util = area_util;
        this.area_construida = area_construida;
    }
    public UnidadeAutonoma(int iptu, int cep, String rua, int num,
                           Estado estado, String cidade,
                           TipoImovel tipo, UtilizacaoImovel utilizacao,
                           int area_util, int area_construida){
        super(iptu, cep, rua, num, estado,
              cidade, tipo, utilizacao);
        this.area_util = area_util;
        this.area_construida = area_construida;
    }
    public UnidadeAutonoma(int iptu, int cep, String rua, int num,
                           TipoImovel tipo, UtilizacaoImovel utilizacao,
                           int area_util, int area_construida){
        super(iptu, cep, rua, num, tipo, utilizacao);
        this.area_util = area_util;
        this.area_construida = area_construida;
    }
    public float calcularRefAluguel(){
        return this.area_construida*15;
    }
    @Override
    public String toString(){
        return "(UA) " + super.toString();
    }
}
