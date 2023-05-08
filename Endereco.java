
public class Endereco {
    private String rua;
    private int num;
    private int cep;
    private Estado estado;
    private String cidade;
    
    public Endereco(String rua, int num, int cep, Estado estado, String cidade) {
    	this.setRua(rua);
        this.setNum(num);
        this.setCep(cep);
        this.setEstado(estado);
        this.setCidade(cidade);
    }

    public boolean equals(Endereco other) {
    	return this.rua == other.getRua() && this.num == other.getNum() && 
    				this.cep == other.getCep() && this.estado == other.getEstado() &&
    				this.cidade == other.getCidade();
    }
    public boolean equals(String rua, int num, int cep, Estado estado, String cidade) {
    	return this.rua == rua && this.num == num && 
    				this.cep == cep && this.estado == estado &&
    				this.cidade == cidade;
    }
    public String toString() {
    	return String.valueOf(this.cep) + ", " + this.rua + ", n. " + String.valueOf(this.num) + ", " + this.cidade + " - " + this.estado;
    }
    
	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getCep() {
		return cep;
	}

	public void setCep(int cep) {
		this.cep = cep;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
}
