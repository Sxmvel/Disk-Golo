package diskgolo;

public class Cerveja extends Bebidas {

    protected String malte;

    public Cerveja(int id, String nomeDaBebida, int quantidadeEstoque, int quantidadeMinima, int tamanho, double precoDeCusto, double precoDeVenda, String malte) {
        super(id, nomeDaBebida, quantidadeEstoque, quantidadeMinima, tamanho, precoDeCusto, precoDeVenda);
        this.malte = malte;
    }

    public String getMalte() {
        return malte;
    }

    
    
    @Override
    public String toString() {
        StringBuilder mostrar = new StringBuilder();
        mostrar.append("ID: ").append(id);
        mostrar.append(" MARCA: ").append(nomeDaBebida).append(" ");
        mostrar.append("- TAMANHO: ").append(tamanho).append("ml ");
        mostrar.append("- PRECO: ").append(precoDeVenda).append(" R$ ");
        mostrar.append("- Tipo do Malte: ").append(malte);
        return mostrar.toString();
    }

}
