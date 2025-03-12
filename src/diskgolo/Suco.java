package diskgolo;

public class Suco extends Bebidas {

    private String diet;
    private String sabor;

    public Suco(int id, String sabor, String nomeDaBebida, int quantidadeEstoque, int quantidadeMinima, int tamanho, double precoDeCusto, double precoDeVenda, String diet) {
        super(id, nomeDaBebida, quantidadeEstoque, quantidadeMinima, tamanho, precoDeCusto, precoDeVenda);
        this.diet = diet;
        this.sabor = sabor;
    }

    @Override
    public String toString() {
        StringBuilder mostrar = new StringBuilder();
        mostrar.append("ID: ").append(id);
        mostrar.append(" SABOR: ").append(sabor);
        mostrar.append(" - TAMANHO: ").append(tamanho).append("ml ");
        mostrar.append("- PRECO: ").append(precoDeVenda).append(" R$ ");
        mostrar.append(diet);
        return mostrar.toString();
    }

    public String getDiet() {
        return diet;
    }

    public String getSabor() {
        return sabor;
    }

    
    
}
