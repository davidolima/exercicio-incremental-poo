public class Main {
	public static void main(String[] args) {

		// Incremento 1
		System.out.println("> Incremento 1 ---");
		Imovel casa_de_praia = new Imovel(900, 99999999,"Rua X", 10, Estado.BA, "Salvador", TipoImovel.CASA, UtilizacaoImovel.PRAIA);
		Imovel apt_campo = new Imovel(300, 99999999,"Rua Y", 1, Estado.BA, "Salvador", TipoImovel.APARTAMENTO, UtilizacaoImovel.CAMPO);
		Proprietario proprietario1 = new Proprietario("Jose da Silva", "99999999999", "9999999999", "Rua X", 10, 99999999, Estado.BA, "Salvador");
		Proprietario proprietario2 = new Proprietario("Maria da Silva", "99999999999", "9999999999", "Rua X", 10, 99999999, Estado.BA, "Salvador");
		System.out.println(casa_de_praia.getEndereco().toString());
		System.out.println(apt_campo.getTipo());
		System.out.println(proprietario1.getNome());

		// Incremento 2
		System.out.println("> Incremento 2 ---");
		System.out.println(proprietario1.getEndereco().toString());
		proprietario1.setEndereco(11111111, "Rua Y", 2);
		System.out.println(proprietario1.getEndereco().toString());
		proprietario1.setEndereco(22222222, "Rua Z", 3, Estado.RJ, "Rio de Janeiro");
		System.out.println(proprietario1.getEndereco().toString());

		// Incremento 3
		System.out.println("> Incremento 3 ---");
		proprietario2.alocar(casa_de_praia);  // Não aloca pois proprietario2 ocupa esse imovel
		System.out.println(proprietario2.getImoveis());
		proprietario2.alocar(apt_campo); // aloca
		System.out.println(proprietario2.getImoveis());

		// Incremento 4
		System.out.println("> Incremento 4 ---");
		Imovel casa2 = new Imovel(100, 23, "Rua Z", 9, TipoImovel.CASA, UtilizacaoImovel.CAMPO);
		Imovel apto2 = new Imovel(200, 32, "Rua W", 8, TipoImovel.APARTAMENTO, UtilizacaoImovel.PRAIA);
		proprietario2.alocar(casa2);
		proprietario2.alocar(apto2);
		proprietario2.listarImoveis(TipoImovel.APARTAMENTO); // Lista apenas imóveis de tipo APARTAMENTO
		proprietario2.listarImoveis(TipoImovel.CASA); // Lista apenas imóveis de tipo CASA

                // Incremento 5
		System.out.println("> Incremento 5 ---");
		proprietario1.disponibilizarImovel(casa_de_praia.getEndereco(), 9, 5, 23);
		proprietario2.alugarImovel(casa_de_praia, 9, 5, 23);
		System.out.println(casa_de_praia.checarDisponibilidade(9,5,23));
	}
}
