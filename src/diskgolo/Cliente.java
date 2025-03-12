package diskgolo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Cliente {

    private String nomeDoCliente; // Nome do Cliente 

    private String EnderecoCliente; // Endereço do cliente 

    private int idadeDoCliente; // Informamos a idade do cliente >> Verificar se eh maior que 18 anos. 

    private double valorTotal; // SOMAR TODOS OS PEDIDO DO CLIENTE E PASSA PARA ELE 

    private int numeroDoPedido; // Cadastrados o numero do pedido do cliente.

    private ArrayList<Bebidas> pedidoDoCliente = new ArrayList<>(); // ArrayList de pedidos do Ciente.

    public Cliente(String nomeDoCliente, String EnderecoCliente, int idadeDoCliente, int numeroDoPedido) {
        this.nomeDoCliente = nomeDoCliente;
        this.EnderecoCliente = EnderecoCliente;
        this.idadeDoCliente = idadeDoCliente;
        this.numeroDoPedido = numeroDoPedido;
    }

    public Cliente() {
    }

    public int getIdadeDoCliente() {
        return idadeDoCliente;
    }

    public void setPedidoDoCliente(ArrayList<Bebidas> pedidoDoCliente) {
        this.pedidoDoCliente = pedidoDoCliente;
    }

    public String getNomeDoCliente() {
        return nomeDoCliente;
    }

    public void setNomeDoCliente(String nomeDoCliente) {
        this.nomeDoCliente = nomeDoCliente;
    }

    public void adicionaBebida(Bebidas bebida) {
        pedidoDoCliente.add(bebida);
        valorTotal += bebida.precoDeVenda;
    }

    public String getEnderecoCliente() {
        return EnderecoCliente;
    }

    public void setEnderecoCliente(String EnderecoCliente) {
        this.EnderecoCliente = EnderecoCliente;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getNumeroDoPedido() {
        return numeroDoPedido;
    }

    public void setNumeroDoPedido(int numeroDoPedido) {
        this.numeroDoPedido = numeroDoPedido;
    }

    public void exibeProdutosClientes() {

        for (int i = 0; i < pedidoDoCliente.size(); i++) {

            int qVezes = 1;
            System.out.println("\nNome da bebida: " + pedidoDoCliente.get(i).nomeDaBebida
                    + "\nValor unitario: " + pedidoDoCliente.get(i).precoDeVenda);

            while (i + 1 < pedidoDoCliente.size() && pedidoDoCliente.get(i) == pedidoDoCliente.get(i + 1)) {
                qVezes++; // atualizo a quantidade de produtos iguais
                i++; // aumento um na bebida verificada, para evitar de repetir o pedido
            }

            System.out.println("Quantidade: " + qVezes);

        }

        System.out.println("\nValor total: " + valorTotal + "R$");

    }

    public ArrayList<Bebidas> getPedidoDoCliente() {
        return pedidoDoCliente;
    }

        

}
