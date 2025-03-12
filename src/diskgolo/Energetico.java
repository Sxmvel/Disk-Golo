package diskgolo;

public class Energetico extends Bebidas {

    private String sabor;
    private String marca;

   // 1,tradicional,redbull,redbull,10,350,4.75,9.50
    
    public Energetico(int id, String sabor, String marca, String nomeDaBebida, int quantidadeEstoque, int quantidadeMinima, int tamanho, double precoDeCusto, double precoDeVenda) {
        super(id, nomeDaBebida, quantidadeEstoque, quantidadeMinima, tamanho, precoDeCusto, precoDeVenda);
        this.sabor = sabor;
        this.marca = marca;
    }

    public String getSabor() {
        return sabor;
    }

    public String getMarca() {
        return marca;
    }
    
    

    @Override
    public String toString() {
        StringBuilder mostrar = new StringBuilder();
        mostrar.append("ID: ").append(id);
        mostrar.append(" MARCA: ").append(marca);
        mostrar.append(" - TAMANHO: ").append(tamanho).append("ml ");
        mostrar.append(" - PRECO: ").append(precoDeVenda).append(" R$ ");
        mostrar.append(" - SABOR: ").append(sabor);
        return mostrar.toString();
    }

}
