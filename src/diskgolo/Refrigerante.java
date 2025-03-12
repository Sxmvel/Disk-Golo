package diskgolo;

public class Refrigerante extends Bebidas {

    private String zero;

    public Refrigerante(int id, String nomeDaBebida, int quantidadeEstoque, int quantidadeMinima, int tamanho, double precoDeCusto, double precoDeVenda, String zero) {
        super(id, nomeDaBebida, quantidadeEstoque, quantidadeMinima, tamanho, precoDeCusto, precoDeVenda);
        this.zero = zero;
    }

    @Override
    public String toString() {
        StringBuilder mostrar = new StringBuilder();
        mostrar.append("ID: ").append(id);
        mostrar.append(" MARCA: ").append(nomeDaBebida);
        mostrar.append(" - TAMANHO: ").append(tamanho).append("ml ");
        mostrar.append(" - PRECO: ").append(precoDeVenda).append(" R$ ");
        mostrar.append(" - TIPO: ").append(zero);
        return mostrar.toString();
    }

    public String getZero() {
        return zero;
    }
    
    

}
