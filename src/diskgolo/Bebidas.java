package diskgolo;

public class Bebidas {

    public int id; // Indice Da Bebida
    protected String nomeDaBebida; // Nome da bebida
    protected int quantidadeEstoque; // Quantidade Em Estoque
    protected int quantidadeMinima; // Quantidade Minima em estoque
    protected int tamanho; // Tamnho da Bebida
    protected double precoDeCusto; // Preco de custo da bebida
    protected double precoDeVenda; // Preco de venda da bebida 
    protected boolean precisaRepor = false; // Verificar se a bebida chegou em seu estoque minino

    public Bebidas(int id, String nomeDaBebida, int quantidadeEstoque, int quantidadeMinima, int tamanho, double precoDeCusto, double precoDeVenda) {
        this.id = id;
        this.nomeDaBebida = nomeDaBebida;
        this.quantidadeEstoque = quantidadeEstoque;
        this.quantidadeMinima = quantidadeMinima;
        this.tamanho = tamanho;
        this.precoDeCusto = precoDeCusto;
        this.precoDeVenda = precoDeVenda;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public boolean isPrecisaRepor() {
        return precisaRepor;
    }

    public void setPrecisaRepor(boolean precisaRepor) {
        this.precisaRepor = precisaRepor;
    }

    public int getId() {
        return id;
    }

    public String getNomeDaBebida() {
        return nomeDaBebida;
    }

    public int getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public int getTamanho() {
        return tamanho;
    }

    public double getPrecoDeCusto() {
        return precoDeCusto;
    }

    public double getPrecoDeVenda() {
        return precoDeVenda;
    }
    
    
    
}
