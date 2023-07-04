class UsuarioExistenteException extends Exception{
    public UsuarioExistenteException(String nome_usuario, String cpf){
        super("Usuário de CPF " + cpf + " já existe no sistema com o nome \"" + nome_usuario + "\".");
    }
    public UsuarioExistenteException(Proprietario a, Proprietario b){
        this(b.getNome(), a.getCpf());
    }
}
