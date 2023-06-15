import java.util.ArrayList;

class Condominio {
    public Endereco endereco;
    public ArrayList<String> lazer;

    public Condominio (String rua, int num, int cep, Estado estado, String cidade, ArrayList<String> lazer){
        this.endereco = new Endereco(rua, num, cep, estado, cidade);
        this.lazer = lazer;
    }
    public Condominio (String rua, int num, int cep, Estado estado, String cidade){
        this(rua, num, cep, estado, cidade, new ArrayList<String>());
    }
    public void adicionarLazer(String l){
        this.lazer.add(l);
    }
    public int qtdLazer(){
        return this.lazer.size();
    }
}
