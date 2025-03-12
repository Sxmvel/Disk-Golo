package diskgolo;

import java.io.*;
import java.nio.file.Paths;
import java.time.*;
import java.util.*;

public class Bar {

    private String nomeDoBar; // Informamos o nome do Bar do projeto.   
    private String localizacaoBar; // Informamaos a localizao do bar.
    private String responavelBar; // Nome do responsavel de cadastrar os clientes.

    private LocalDate diaDeFuncionamento = LocalDate.now(); // Dia do funcionamento do Bar.
    private ArrayList<Refrigerante> menuDeRefrigerantes = new ArrayList<>(); // ARRAY LIST COM OS REFRIGERANTE CADASTRADOS 
    private ArrayList<Energetico> menuDeEnergetico = new ArrayList<>(); // ARRAY LIST COM OS ENERGETICOS CADASTRADOS 
    private ArrayList<Cerveja> menuDeCerveja = new ArrayList<>(); // ARRAY LIST COM CERVEJAS CADASTRADAS 
    private ArrayList<Suco> menuDeSuco = new ArrayList<>(); // ARRAY LIST COM SUCOS CADASTRADOS
    private ArrayList<Cliente> clientesRegistrados = new ArrayList<>(); // ARRAY LIST COM CLIENTE CADASTRADOS.
    private int lucroTotal;

    public Bar() {
        carregarRefrigerante();
        carregarEnergetico();
        carregarCerveja();
        carregarSuco();
    }

    public Bar(String nomeDoBar, String localizacaoBar, String responavelBar) {
        this.nomeDoBar = "DiskGolo";
        this.localizacaoBar = "Rua dos bobo, N° 0";
        this.responavelBar = "Seu Jose";
    }

    public void menuDoBar() {
        Scanner t = new Scanner(System.in);
        System.out.println("\nSeja Bem Vindo ao Disk-Golo: " + this.diaDeFuncionamento + "\n1 - realizar pedido " + "\n2 - Verificar estoque \n3 - Registrar Cliente \n4 - Encerrar O Dia");

        int opcao = t.nextInt();

        switch (opcao) {
            case 1:
                int indiceCliente = selecionarClienteDoPedido();
                menuDeSelecaoPedido(indiceCliente);
                break;
            case 2:
                verificarEstoqueMinimo();
                menuDoBar();
                break;
            case 3:
                registrarCliente();
                break;

            case 4:
                System.out.println("Encerrando o dia \nLucro total: " + lucroTotal + "R$");
                atualizaEstoque();//Esse método vai sobrescrever o estoque no código
                System.exit(0);
            default:
                System.out.println("Opcao invalida!");
                menuDoBar();
        }
    }

    // METODO PARA APRESENTAR AS OPCOES DE PEDIDO PARA O CLIENTE.
    public void menuDeSelecaoPedido(int indiceDoCliente) {
        Scanner t = new Scanner(System.in);
        System.out.println("O QUE VOCE DESEJA?\n1 - REFRIGERANTE \n2 - SUCO \n3 - ENERGETICO \n4 - CERVEJA \n5 - FECHAR PEDIDO ");
        int opcao = t.nextInt();

        switch (opcao) {
            case 1:
                imprimirMenuRefri();
                registrarPedidoRefri(indiceDoCliente);
                break;

            case 2:
                imprimirMenuDeSuco();
                registraPedidoSuco(indiceDoCliente);
                break;
            case 3:
                imprimirMenuEnergetico();
                registraPedidoEnergetico(indiceDoCliente);
                break;
            case 4:
                //Verifica se o Cliente eh maior de idade ou nao
                if (clientesRegistrados.get(indiceDoCliente).getIdadeDoCliente() >= 18) {
                    imprimirMenuCerveja();
                    registrarPedidoCerveja(indiceDoCliente);

                    break;
                } else {
                    System.out.println("VOCE NAO PODE COMPRAR BEBIDAS ALCOOLICAS.\n");
                    menuDeSelecaoPedido(indiceDoCliente);
                }
            case 5:
                fecharPedido(indiceDoCliente);
            //se pedidos for nulo, voltar ao menu inicial, senão, imprimir os pedidos

            default:
                System.out.println("Opcao invalida!");
                menuDeSelecaoPedido(indiceDoCliente);
        }
    }

    // Metodo para o realizar o cadastramento do cliente.
    public void registrarCliente() {
        Scanner t = new Scanner(System.in);

        System.out.println("Informe seu nome: ");
        String nome = t.nextLine(); // Cadastrando nome do cliente 
        while (nome.isEmpty()) { // >> CASO O CLIENTE DEIXE EM BRANCO O SEU NOME
            System.out.println("Por favor, informe seu nome:");
            nome = t.nextLine();
        }

        System.out.println("Informe Seu endereco: ");
        String endereco = t.nextLine();// Cadastrando o endereco do cliente 
        while (endereco.isEmpty()) { // >> CASO O CLIENTE DEIXE EM BRANCO O SEU ENDERECO
            System.out.println("Por favor, informe seu endereco");
            endereco = t.nextLine();
        }

        int idade = -1; // Cadastrando a idade do cliente
        do {
            try {
                System.out.println("Informe Sua Idade: ");
                idade = t.nextInt();
                if (idade > 110) {
                    System.out.println("IDADE INVALIDA!");
                }
                if (idade < 16) {
                    System.out.println("Menores de 16 anos precisa de acompanhante para frequentar o bar.");
                }
            } catch (InputMismatchException verificaEntrada) {
                // Tratamento de exceção para garantir que o usuario nao informe entradas diferentes de números. 
                System.out.println("Favor inserir somente a idade em numeros");
                t.next(); // Limpa a entrada inválida           
            }
        } while (idade > 100 || idade < 16);

        Random pedido = new Random(); // para cadastrar um numero aleatorio no pedido do cliente.
        int nPedido = pedido.nextInt(1, 99);

        Cliente novoCliente = new Cliente(nome, endereco, idade, nPedido);
        clientesRegistrados.add(novoCliente); // Registrando um cliente no array list de cliente do bar.

        menuDoBar();
    }

    // Metodo para informar o cliente que esta fazendo o pedido.
    public int selecionarClienteDoPedido() {
        System.out.println("Selecione o cliente para registrar o seu pedido: \n");
        if (clientesRegistrados.size() != 0) {
            Scanner teclado = new Scanner(System.in);
            for (int indiceDoCliente = 0; indiceDoCliente < clientesRegistrados.size(); indiceDoCliente++) {
                System.out.println(indiceDoCliente + " - " + clientesRegistrados.get(indiceDoCliente).getNomeDoCliente());   
            }

            int selecao = teclado.nextInt();

            if (selecao > clientesRegistrados.size() && selecao < 0) {
                System.out.println("Invalido, tente novamente. ");
                selecionarClienteDoPedido();
            }

            return selecao;
        } else {
            System.out.println("Nao existe clientes registrados, por favor, registrar cliente primeiro. ");
            menuDoBar();
        }
        return 0;
    }

    //registra Pedido Refri 
    public void registrarPedidoRefri(int indiceDoCliente) {

        int selecaoRefri = selecionaBebida();
        for (int i = 0; i < menuDeRefrigerantes.size(); i++) {
            if (selecaoRefri == menuDeRefrigerantes.get(i).id) {
                int quantidade = selecionaQuantidade();
                verificaEstoque(menuDeRefrigerantes.get(i), quantidade, indiceDoCliente);
                infoBebida(menuDeRefrigerantes.get(i), quantidade);
                decrementarQuantidade(menuDeRefrigerantes.get(i), indiceDoCliente, quantidade);
                menuDeSelecaoPedido(indiceDoCliente);

            }

        }

        System.err.println("Bebida invalida ou indisponivel");
        menuDeSelecaoPedido(indiceDoCliente);

    }

    //registraPedidoSuco
    public void registraPedidoSuco(int indiceDoCliente) {
        int selecaoSuco = selecionaBebida();

        for (int i = 0; i < menuDeSuco.size(); i++) {

            if (selecaoSuco == menuDeSuco.get(i).id) {

                int quantidade = selecionaQuantidade();
                verificaEstoque(menuDeSuco.get(i), quantidade, indiceDoCliente);
                infoBebida(menuDeSuco.get(i), quantidade);
                decrementarQuantidade(menuDeSuco.get(i), indiceDoCliente, quantidade);
                menuDeSelecaoPedido(indiceDoCliente);

            }

        }

        System.err.println("Bebida invalida ou indisponivel");
        menuDeSelecaoPedido(indiceDoCliente);
    }

    //registra pedido do energetico
    public void registraPedidoEnergetico(int indiceDoCliente) {
        int selecaoSuco = selecionaBebida();

        for (int i = 0; i < menuDeEnergetico.size(); i++) {
            if (selecaoSuco == menuDeEnergetico.get(i).id) {
                int quantidade = selecionaQuantidade();
                verificaEstoque(menuDeEnergetico.get(i), quantidade, indiceDoCliente);
                infoBebida(menuDeEnergetico.get(i), quantidade);
                decrementarQuantidade(menuDeEnergetico.get(i), indiceDoCliente, quantidade);
                menuDeSelecaoPedido(indiceDoCliente);

            }

        }

        System.err.println("Bebida invalida ou indisponivel");
        menuDeSelecaoPedido(indiceDoCliente);
    }

    //Registra Pedido Cerveja
    public void registrarPedidoCerveja(int indiceDoCliente) {

        int selecaoCerveja = selecionaBebida();

        for (int i = 0; i < menuDeCerveja.size(); i++) {

            if (selecaoCerveja == menuDeCerveja.get(i).id) {

                int quantidade = selecionaQuantidade();
                verificaEstoque(menuDeCerveja.get(i), quantidade, indiceDoCliente);
                infoBebida(menuDeCerveja.get(i), quantidade);
                decrementarQuantidade(menuDeCerveja.get(i), indiceDoCliente, quantidade);

                menuDeSelecaoPedido(indiceDoCliente);

            }

        }

        System.err.println("Bebida invalida ou indisponivel");
        menuDeSelecaoPedido(indiceDoCliente);
    }

    //Seleciona a Quantidade
    public int selecionaQuantidade() {
        Scanner t = new Scanner(System.in);
        System.out.println("Informe a quantidade que voce deseja pedir: ");
        int quantidade = t.nextInt();
        return quantidade;
    }

    //Informação da quantidade
    private void infoBebida(Bebidas bebida, int quantidade) {

        System.out.println("Bebida escolhida: " + bebida.nomeDaBebida + "\nTamanho da bebida: "
                + bebida.tamanho + "ml\nQuantidade: " + quantidade + "\nPreco Unitario: " + bebida.precoDeVenda
                + " R$\nPreco Total: " + bebida.precoDeVenda * quantidade + " R$");

    }

    //Decrementar quantidade
    public void decrementarQuantidade(Bebidas bebida, int indiceDoCliente, int quantidade) {
        while (quantidade != 0) {
            clientesRegistrados.get(indiceDoCliente).adicionaBebida(bebida);
            lucroTotal += bebida.precoDeVenda - bebida.precoDeCusto; //aqui eu calculo o lucro total
            bebida.quantidadeEstoque--;
            quantidade--;
        }
    }

    //fecha o pedido do cliente
    public void fecharPedido(int indiceCliente) {
        //preciso do Cliente

        System.out.println("Encerrando O Pedido\n"
                + "\nNome do Cliente: " + clientesRegistrados.get(indiceCliente).getNomeDoCliente()
                + "\nNumero do pedido: " + clientesRegistrados.get(indiceCliente).getNumeroDoPedido()
                + "\nIdade do cliente: " + clientesRegistrados.get(indiceCliente).getIdadeDoCliente());

        clientesRegistrados.get(indiceCliente).exibeProdutosClientes();
        System.out.println("\nObrigado pela preferencia");
        //Aqui, tenho que imprimir o arquivo do cliente com seus dados e seu pedido
        escrevePedidoDoCliente(indiceCliente);
        //aqui eu removo o cliente, uma vez que não sera mais necessario
        clientesRegistrados.remove(indiceCliente);

        menuDoBar();

    }

    //METODO PARA REUTILIZAR NA SELECAO DE BEBIDAS. 
    private int selecionaBebida() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Selecione qual bebida voce deseja pedir: ");
        int bebida = teclado.nextInt();
        return bebida;
    }

    //Método verifica se tem bebida disponivel no estoque 
    private void verificaEstoque(Bebidas bebida, int quantidade, int indiceDoCliente) {

        if (bebida.quantidadeEstoque < quantidade) {
            System.err.println("Bebidas em estoque indisponivel");
            menuDeSelecaoPedido(indiceDoCliente);

        }

        if (quantidade <= 0) {
            System.err.println("Informe um valor valido");
            menuDeSelecaoPedido(indiceDoCliente);
        }

    }

    public void verificarEstoqueMinimo() {
        Scanner t = new Scanner(System.in);
        System.out.println("\nVERIFICA ESTOQUE DE: \n1 - CERVEJA \n2 - ENERGETICO \n3 - REFRIGERANTE\n4 - SUCO\n5 - MENU DO BAR");

        int opcao = t.nextInt();

        switch (opcao) {
            case 1:
                adicionarCerveja();
                break;
            case 2:
                adicionarEnergetico();
                break;
            case 3:
                adicionarRefrigerante();
                break;
            case 4:
                adicionarSuco();
                break;
            case 5:
                menuDoBar();
                break;
            default:
                System.out.println("Opcao invalida!");
                verificarEstoqueMinimo();
        }

    }

    private void adicionarCerveja() {

        Scanner t = new Scanner(System.in);

        System.out.println("CERVEJAS COM ESTOQUE MINIMO: ");

        for (int i = 0; i < menuDeCerveja.size(); i++) {
            if (menuDeCerveja.get(i).quantidadeEstoque <= menuDeCerveja.get(i).quantidadeMinima) { // ESSE IF IRA VERIFICAR SE A CERVEJA PRECISA REPOR SEU ESTOQUE 
                System.out.println("ID: " + i + " Nome da Cerveja: " + menuDeCerveja.get(i).nomeDaBebida + " Tamanho: " + menuDeCerveja.get(i).tamanho + "ml" + " Quantidade em Estoque: " + menuDeCerveja.get(i).quantidadeEstoque);
                menuDeCerveja.get(i).setPrecisaRepor(true); // CASO A CERVEJA NECESSITA DE ALTERACAO EM SEU ESTOQUE, ESSE SET IRA MUDAR SEU ATRIBUTO. 
            }
        }
        System.out.println("\nINFORME O ID DO CERVEJA QUE DESEJA ALTERAR NO ESTOQUE:");
        int id = t.nextInt();

        for (int i = 0; i < menuDeCerveja.size(); i++) {

            if (menuDeCerveja.get(id).precisaRepor == true) { // ESSE IF VERIFICA SE A CERVEJA PRECISA REPOR OU NAO.

                System.out.println("Informe a quantidade que voce deseja adicionar no estoque da cerveja: " + menuDeCerveja.get(id).nomeDaBebida + " " + menuDeCerveja.get(id).tamanho + "ml");
                int quantidade = t.nextInt(); // AQUI O RESPONSAVEL DO BAR INFORMA A QUANTIDADE QUE DESEJA ADICIONAR NO ESTOQUE DA CERVEJA

                menuDeCerveja.get(id).setQuantidadeEstoque(menuDeCerveja.get(id).getQuantidadeEstoque() + quantidade); // ESSE SET ADICIONA A QUANTIDADE INFORMADA NO ESTOQUE DA CERVEJA.

                menuDeCerveja.get(id).setPrecisaRepor(false); // APOS A ALTERACAO DA QUANTIDADE ESSE SET MUDA A SITUACAO DA CERVEJA.

                System.out.println("Voce adicionou: " + quantidade + " unidades no estoque da cerveja " + menuDeCerveja.get(id).nomeDaBebida + " - " + menuDeCerveja.get(id).tamanho + "ml");
                verificarEstoqueMinimo();
                break;
            } else {
                if (menuDeCerveja.get(id).precisaRepor == false) {
                    System.out.println("A CERVEJA INFORMADA NAO PRECISA DE ALTERACAO NO ESTOQUE! \n");
                    adicionarCerveja();
                    break;
                }
            }
        }
    }

    private void adicionarEnergetico() {

        Scanner t = new Scanner(System.in);

        System.out.println("ENERGETICOS COM ESTOQUE MINIMO: ");
        for (int i = 0; i < menuDeEnergetico.size(); i++) {

            if (menuDeEnergetico.get(i).quantidadeEstoque <= menuDeEnergetico.get(i).quantidadeMinima) { // VERIFICA SE O ENERGETICO ESTA COM O ESTOQUE ABAIXO DO ESPERADO 
                System.out.println("ID: " + i + " Nome do Energetico : " + menuDeEnergetico.get(i).nomeDaBebida + " Tamanho: " + menuDeEnergetico.get(i).tamanho + "ml" + " Quantidade em Estoque: " + menuDeEnergetico.get(i).quantidadeEstoque);
                menuDeEnergetico.get(i).setPrecisaRepor(true); // IRA ALTERAR E COLOCAR PARA REPOR SEU ESTOQUE.
            }
        }

        System.out.println("\nINFORME O ID DO ENERGETICO QUE DESEJA ALTERAR NO ESTOQUE:");
        int id = t.nextInt();

        for (int i = 0; i < menuDeEnergetico.size(); i++) {

            if (menuDeEnergetico.get(id).precisaRepor == true) { // ESSE IF VERIFICA SE O ENERGETICO PRECISA REPOR OU NAO.

                System.out.println("Informe a quantidade que voce deseja adicionar no estoque do energetico: " + menuDeEnergetico.get(id).nomeDaBebida + " - " + menuDeEnergetico.get(id).tamanho + "ml");

                int quantidade = t.nextInt(); // AQUI O RESPONSAVEL DO BAR INFORMA A QUANTIDADE QUE DESEJA ADICIONAR EM ESTOQUE.

                menuDeEnergetico.get(id).setQuantidadeEstoque(menuDeEnergetico.get(id).getQuantidadeEstoque() + quantidade);

                menuDeEnergetico.get(id).setPrecisaRepor(false); // APOS A ALTERACAO DA QUANTIDADE ESSE SET MUDA A SITUACAO DO ENERGETICO.

                System.out.println("Voce adicionou: " + quantidade + " unidades no estoque do energetico: " + menuDeEnergetico.get(id).nomeDaBebida + " - " + menuDeEnergetico.get(id).tamanho + "ml");
                verificarEstoqueMinimo();
                break;
            } else {
                if (menuDeEnergetico.get(id).precisaRepor == false) {
                    System.out.println("O ENERGETICO INFORMADO NAO PRECISA DE ALTERACAO NO ESTOQUE! \n");
                    adicionarEnergetico();
                    break;
                }
            }
        }
    }

    private void adicionarRefrigerante() {
        Scanner t = new Scanner(System.in);

        System.out.println("REFRIGERANTES COM ESTOQUE MINIMO: ");
        for (int i = 0; i < menuDeRefrigerantes.size(); i++) {

            if (menuDeRefrigerantes.get(i).quantidadeEstoque <= menuDeRefrigerantes.get(i).quantidadeMinima) { // VERIFICA SE O REFRIGERANTE ESTA COM O ESTOQUE ABAIXO DO ESPERADO 
                System.out.println("ID: " + i + " Nome do Refrigerante : " + menuDeRefrigerantes.get(i).nomeDaBebida + " Tamanho: " + menuDeRefrigerantes.get(i).tamanho + "ml" + " Quantidade em Estoque: " + menuDeRefrigerantes.get(i).quantidadeEstoque);
                menuDeRefrigerantes.get(i).setPrecisaRepor(true); // ESSE SET INFORMA QUE PRECISA REPOR O ESTOQUE DO REFRIGERANTE.
            }
        }

        System.out.println("\nINFORME O ID DO REFRIGERANTE QUE DESEJA ALTERAR NO ESTOQUE:");
        int id = t.nextInt();

        for (int i = 0; i < menuDeRefrigerantes.size(); i++) {
            if (menuDeRefrigerantes.get(id).precisaRepor == true) { // ESSE IF VERIFICA SE O REFRIGERANTE PRECISA REPOR OU NAO.

                System.out.println("Informe a quantidade que voce deseja adicionar no estoque do refrigerante: " + menuDeRefrigerantes.get(id).nomeDaBebida + " " + menuDeRefrigerantes.get(id).tamanho + "ml");
                int quantidade = t.nextInt(); // AQUI O RESPONSAVEL DO BAR INFORMA A QUANTIDADE QUE DESEJA ADICIONAR EM ESTOQUE.

                menuDeRefrigerantes.get(id).setQuantidadeEstoque(menuDeRefrigerantes.get(id).getQuantidadeEstoque() + quantidade);
                menuDeRefrigerantes.get(id).setPrecisaRepor(false); // APOS A ALTERACAO DA QUANTIDADE ESSE SET MUDA A SITUACAO DO REFRIGERANTE.
                System.out.println("Voce adicionou: " + quantidade + " unidades no estoque do refrigerante: " + menuDeRefrigerantes.get(id).nomeDaBebida + " " + menuDeRefrigerantes.get(id).tamanho + "ml");
                verificarEstoqueMinimo();
                break;
            } else {
                if (menuDeRefrigerantes.get(id).precisaRepor == false) {
                    System.out.println("O REFRIGERANTE INFORMADO NAO PRECISA DE ALTERACAO NO ESTOQUE! \n");
                    adicionarRefrigerante();
                    break;
                }
            }
        }

    }

    private void adicionarSuco() {
        Scanner t = new Scanner(System.in);

        System.out.println("SUCO COM ESTOQUE MINIMO: ");
        for (int i = 0; i < menuDeSuco.size(); i++) {

            if (menuDeSuco.get(i).quantidadeEstoque <= menuDeSuco.get(i).quantidadeMinima) { // VERIFICA SE O SUCO ESTA COM O ESTOQUE ABAIXO DO ESPERADO 
                System.out.println("ID: " + i + " sabor do suco : " + menuDeSuco.get(i).nomeDaBebida + " Tamanho: " + menuDeSuco.get(i).tamanho + "ml" + " Quantidade em Estoque: " + menuDeSuco.get(i).quantidadeEstoque);
                menuDeSuco.get(i).setPrecisaRepor(true);
            }
        }

        System.out.println("\nINFORME O ID DO SUCO QUE DESEJA ALTERAR NO ESTOQUE:");
        int id = t.nextInt();

        for (int i = 0; i < menuDeSuco.size(); i++) {
            if (menuDeSuco.get(id).precisaRepor == true) { // ESSE IF VERIFICA SE O REFRIGERANTE PRECISA REPOR OU NAO.

                System.out.println("Informe a quantidade que voce deseja adicionar no estoque do suco: " + menuDeSuco.get(id).nomeDaBebida + " " + menuDeSuco.get(id).tamanho + "ml");
                int quantidade = t.nextInt(); // AQUI O RESPONSAVEL DO BAR INFORMA A QUANTIDADE QUE DESEJA ADICIONAR EM ESTOQUE.

                menuDeSuco.get(id).setQuantidadeEstoque(menuDeSuco.get(id).getQuantidadeEstoque() + quantidade);
                menuDeSuco.get(id).setPrecisaRepor(false); // APOS A ALTERACAO DA QUANTIDADE ESSE SET MUDA A SITUACAO DO SUCO.
                System.out.println("Voce adicionou: " + quantidade + " unidades no estoque do suco: " + menuDeSuco.get(id).nomeDaBebida + " " + menuDeSuco.get(id).tamanho + "ml");
                verificarEstoqueMinimo();
                break;

            } else {
                if (menuDeSuco.get(id).precisaRepor == false) {
                    System.out.println("O SUCO INFORMADO NAO PRECISA DE ALTERACAO NO ESTOQUE! \n");
                    adicionarSuco();
                    break;
                }
            }
        }

    }

    // Metodo para ler as informacoes de REFRIGERANTE utilizando BufferReader.
    private void carregarRefrigerante() {
        File arquivo = new File("dados/refrigerantes.txt"); // Endereco do arquivo TXT.
        if (arquivo.exists() && arquivo.canRead() && arquivo.isFile()) { // Verificando se endereco existe.
            try (BufferedReader bufLeitura = new BufferedReader(new FileReader(arquivo))) {

                String linha = bufLeitura.readLine();

                while (linha != null) {

                    if (linha != null) {

                        String pedacosLinha[] = linha.split(",");
                        Refrigerante novoRefrigerante = new Refrigerante(
                                Integer.parseInt(pedacosLinha[0]), // Id da Bebida
                                pedacosLinha[1], // nome Da Bebida
                                Integer.parseInt(pedacosLinha[2]), // quantidade em Estoque
                                Integer.parseInt(pedacosLinha[3]), // Quantidade minima em estoque.
                                Integer.parseInt(pedacosLinha[4]), // tamanho do refrigerante.
                                Double.parseDouble(pedacosLinha[5]), //  preco De Custo
                                Double.parseDouble(pedacosLinha[6]), // preco de venda
                                pedacosLinha[7]);  // Refrigerante zero ou nao
                        this.menuDeRefrigerantes.add(novoRefrigerante);

                        linha = bufLeitura.readLine();
                    }
                }
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }

    // Metodo para ler as informacoes de ENERGETICO utilizando BufferReader.
    private void carregarEnergetico() {
        File arquivo = new File("dados/energetico.txt"); // PASSANDO O ENDERECO DO ARQUIVO TXT COM AS INF. DE ENERGETICO
        if (arquivo.exists() && arquivo.canRead() && arquivo.isFile()) { // verificando se o arquivo existe
            try (BufferedReader bufLeitura = new BufferedReader(new FileReader(arquivo))) {
                String linha = bufLeitura.readLine();

                while (linha != null) {

                    //verificar se não esgotamos TODAS as linhas do arq.
                    if (linha != null) {

                        String pedacosLinha[] = linha.split(",");
                        Energetico novoEnergetico = new Energetico(
                                Integer.parseInt(pedacosLinha[0]), // ID DO ENERGETICO
                                pedacosLinha[1], // Sabor do energetico
                                pedacosLinha[2], // Marca Do Energetico
                                pedacosLinha[3], // Nome Da Bebida
                                Integer.parseInt(pedacosLinha[4]), // Quantidade em estoque
                                Integer.parseInt(pedacosLinha[5]), // Quantidade minima em estoque
                                Integer.parseInt(pedacosLinha[6]), // Tamanho da bebida
                                Double.parseDouble(pedacosLinha[7]), // Preco de custa 
                                Double.parseDouble(pedacosLinha[8])); // Preco de venda
                        this.menuDeEnergetico.add(novoEnergetico);
                        linha = bufLeitura.readLine();
                    }
                }
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }

    // Metodo para ler as informacoes de CERVEJA utilizando BufferReader.
    private void carregarCerveja() {

        File arquivo = new File("dados/cerveja.txt"); // Passando o endereco do arquivo onde esta as informacoes da cerveja.

        if (arquivo.exists() && arquivo.canRead() && arquivo.isFile()) { //Verificando se o arquivo existe.
            try (BufferedReader bufLeitura = new BufferedReader(new FileReader(arquivo))) {
                String linha = bufLeitura.readLine();
                while (linha != null) {
                    //verificar se não esgotamos TODAS as linhas do arq.
                    if (linha != null) {

                        String pedacosLinha[] = linha.split(",");

                        Cerveja novaCerveja = new Cerveja(
                                Integer.parseInt(pedacosLinha[0]),// ID DA BEBIDA.
                                pedacosLinha[1], // NOME DA CERVEJA.
                                Integer.parseInt(pedacosLinha[2]), // QUANTIDADE EM ESTOQUE.
                                Integer.parseInt(pedacosLinha[3]), // QUANTIDADE MINIMA EM ESTOQUE.
                                Integer.parseInt(pedacosLinha[4]), // TAMANHO DA CERVEJA.
                                Double.parseDouble(pedacosLinha[5]), // PRECO DE CUSTO.
                                Double.parseDouble(pedacosLinha[6]),// PRECO DE VENDA.
                                pedacosLinha[7]); // NOME DO MALTE DA CERVEJA.
                        this.menuDeCerveja.add(novaCerveja);
                        linha = bufLeitura.readLine();
                    }
                }
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }

    // Metodo para ler as informacoes de SUCO utilizando BufferReader.
    private void carregarSuco() {

        File arquivo = new File("dados/suco.txt"); // PASSANDO O ENDERECO DO ARQUIVO TXT COM AS INF. DE SUCO

        if (arquivo.exists() && arquivo.canRead() && arquivo.isFile()) { // verificando se o arquivo existe
            try (BufferedReader bufLeitura = new BufferedReader(new FileReader(arquivo))) {
                String linha = bufLeitura.readLine();

                while (linha != null) {

                    //verificar se não esgotamos TODAS as linhas do arq.
                    if (linha != null) {

                        String pedacosLinha[] = linha.split(",");

                        Suco novoSuco = new Suco(
                                Integer.parseInt(pedacosLinha[0]), // ID DA BEBIDA
                                pedacosLinha[1], // SABOR DO SUCO
                                pedacosLinha[2], // NOME DA BEBIDA
                                Integer.parseInt(pedacosLinha[3]), // QUANTIDADE DE ESTOQUE
                                Integer.parseInt(pedacosLinha[4]), // QUANTIDADE MINIMA EM ESTOQUE
                                Integer.parseInt(pedacosLinha[5]), // TAMANHO DO SUCO 
                                Double.parseDouble(pedacosLinha[6]), // PRECO DE CUSTO 
                                Double.parseDouble(pedacosLinha[7]), // PRECO DE VENDA
                                pedacosLinha[8]); // COM ACUCAR OU NAO
                        this.menuDeSuco.add(novoSuco);
                        linha = bufLeitura.readLine();
                    }
                }
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }

    // Metodo para imprimir o menu de REFRIGERANTE.
    public void imprimirMenuRefri() {
        for (int i = 0; i < this.menuDeRefrigerantes.size(); i++) {
            System.out.println(this.menuDeRefrigerantes.get(i).toString());
        }
    }

    // Metodo para imprimir o menu de ENERGETICO.
    public void imprimirMenuEnergetico() {
        for (int i = 0; i < this.menuDeEnergetico.size(); i++) {
            System.out.println(this.menuDeEnergetico.get(i).toString());
        }
    }

    // Metodo para imprimir o menu de CERVEJA.
    public void imprimirMenuCerveja() {
        for (int i = 0; i < this.menuDeCerveja.size(); i++) {
            System.out.println(this.menuDeCerveja.get(i).toString());
        }
    }

    // Metodo para imprimir o menu de SUCO.
    public void imprimirMenuDeSuco() {
        for (int i = 0; i < this.menuDeSuco.size(); i++) {
            System.out.println(this.menuDeSuco.get(i).toString());

        }
    }

    public void escrevePedidoDoCliente(int indiceCliente) {
        File caminho = new File("dados/pedidos/pedidoN" + clientesRegistrados.get(indiceCliente).getNumeroDoPedido());

        try (BufferedWriter escrever = new BufferedWriter(new FileWriter(caminho))) {
            escrever.write("Nome Do Cliente: " + clientesRegistrados.get(indiceCliente).getNomeDoCliente());
            escrever.newLine();

            escrever.write("Numero do pedido: " + clientesRegistrados.get(indiceCliente).getNumeroDoPedido());
            escrever.newLine();

            escrever.write("Pedidos do cliente:");
            escrever.newLine();

            for (int i = 0; i < clientesRegistrados.get(indiceCliente).getPedidoDoCliente().size(); i++) {
                escrever.write("Nome: " + clientesRegistrados.get(indiceCliente).getPedidoDoCliente().get(i).nomeDaBebida);
                escrever.newLine();

                escrever.write("Preco: " + clientesRegistrados.get(indiceCliente).getPedidoDoCliente().get(i).precoDeVenda + " R$"); // ✅ Espaço antes do "R$"
                escrever.newLine();
            }

            escrever.write("Valor total: " + clientesRegistrados.get(indiceCliente).getValorTotal() + " R$");
            escrever.newLine();  // 

        } catch (IOException erro) {
            erro.printStackTrace();
        }
    }

    public void atualizaEstoque() {
        atualizaEstoqueCerveja();
        atualizaEstoqueEnergetico();
        atualizaEstoqueRefrigerante();
        atualizaEstoqueSuco();

    }

    private void atualizaEstoqueCerveja() {
        File arquivo = new File("dados/cerveja.txt"); // Passando o endereco do arquivo onde esta as informacoes da cerveja.

        if (arquivo.exists() && arquivo.canRead() && arquivo.isFile() && arquivo.canWrite()) { //Verificando se o arquivo existe.
            try (BufferedWriter bufEscrita = new BufferedWriter(new FileWriter(arquivo))) {

                for (int i = 0; i < menuDeCerveja.size(); i++) {

                    String linha = this.menuDeCerveja.get(i).getId() + ","
                            + this.menuDeCerveja.get(i).getNomeDaBebida() + ","
                            + this.menuDeCerveja.get(i).getQuantidadeEstoque() + ","
                            + this.menuDeCerveja.get(i).getQuantidadeMinima() + ","
                            + this.menuDeCerveja.get(i).getTamanho() + ","
                            + this.menuDeCerveja.get(i).getPrecoDeCusto() + ","
                            + this.menuDeCerveja.get(i).getPrecoDeVenda() + ","
                            + this.menuDeCerveja.get(i).getMalte();

                    bufEscrita.write(linha);
                    bufEscrita.newLine();

                }

            } catch (IOException e) {
                e.getMessage();
            }
        } else {

            System.out.println("Erro: O arquivo de cervejas não pôde ser acessado.");

        }
    }

    private void atualizaEstoqueEnergetico() {
        File arquivo = new File("dados/energetico.txt"); // Passando o endereco do arquivo onde esta as informacoes da cerveja.

        if (arquivo.exists() && arquivo.canRead() && arquivo.isFile() && arquivo.canWrite()) { //Verificando se o arquivo existe.
            try (BufferedWriter bufEscrita = new BufferedWriter(new FileWriter(arquivo))) {

                for (int i = 0; i < menuDeEnergetico.size(); i++) {

                    String linha = this.menuDeEnergetico.get(i).getId() + ","
                            + this.menuDeEnergetico.get(i).getSabor() + ","
                            + this.menuDeEnergetico.get(i).getMarca() + ","
                            + this.menuDeEnergetico.get(i).getNomeDaBebida() + ","
                            + this.menuDeEnergetico.get(i).getQuantidadeEstoque() + ","
                            + this.menuDeEnergetico.get(i).getQuantidadeMinima() + ","
                            + this.menuDeEnergetico.get(i).getTamanho() + ","
                            + this.menuDeEnergetico.get(i).getPrecoDeCusto() + ","
                            + this.menuDeEnergetico.get(i).getPrecoDeVenda();

                    bufEscrita.write(linha);
                    bufEscrita.newLine();

                }

            } catch (IOException e) {
                e.getMessage();
            }
        } else {

            System.out.println("Erro: O arquivo de cervejas não pôde ser acessado.");

        }
    }

    private void atualizaEstoqueRefrigerante() {
        File arquivo = new File("dados/refrigerantes.txt"); // Passando o endereco do arquivo onde esta as informacoes da cerveja.

        if (arquivo.exists() && arquivo.canRead() && arquivo.isFile() && arquivo.canWrite()) { //Verificando se o arquivo existe.
            try (BufferedWriter bufEscrita = new BufferedWriter(new FileWriter(arquivo))) {

                for (int i = 0; i < menuDeRefrigerantes.size(); i++) {

                    String linha = this.menuDeRefrigerantes.get(i).getId() + ","
                            + this.menuDeRefrigerantes.get(i).getNomeDaBebida() + ","
                            + this.menuDeRefrigerantes.get(i).getQuantidadeEstoque() + ","
                            + this.menuDeRefrigerantes.get(i).getQuantidadeMinima() + ","
                            + this.menuDeRefrigerantes.get(i).getTamanho() + ","
                            + this.menuDeRefrigerantes.get(i).getPrecoDeCusto() + ","
                            + this.menuDeRefrigerantes.get(i).getPrecoDeVenda() + ","
                            + this.menuDeRefrigerantes.get(i).getZero();

                    bufEscrita.write(linha);
                    bufEscrita.newLine();

                }

            } catch (IOException e) {
                e.getMessage();
            }
        } else {

            System.out.println("Erro: O arquivo de cervejas não pôde ser acessado.");

        }
    }

    private void atualizaEstoqueSuco() {
        File arquivo = new File("dados/suco.txt"); // Passando o endereco do arquivo onde esta as informacoes da cerveja.

        if (arquivo.exists() && arquivo.canRead() && arquivo.isFile() && arquivo.canWrite()) { //Verificando se o arquivo existe.
            try (BufferedWriter bufEscrita = new BufferedWriter(new FileWriter(arquivo))) {

                for (int i = 0; i < menuDeSuco.size(); i++) {

                    String linha = this.menuDeSuco.get(i).getId() + ","
                            + this.menuDeSuco.get(i).getSabor() + ","
                            + this.menuDeSuco.get(i).getNomeDaBebida() + ","
                            + this.menuDeSuco.get(i).getQuantidadeEstoque() + ","
                            + this.menuDeSuco.get(i).getQuantidadeMinima() + ","
                            + this.menuDeSuco.get(i).getTamanho() + ","
                            + this.menuDeSuco.get(i).getPrecoDeCusto() + ","
                            + this.menuDeSuco.get(i).getPrecoDeVenda() + ","
                            + this.menuDeSuco.get(i).getDiet();

                    bufEscrita.write(linha);
                    bufEscrita.newLine();

                }

            } catch (IOException e) {
                e.getMessage();
            }
        } else {

            System.out.println("Erro: O arquivo de cervejas não pôde ser acessado.");

        }
    }
}
