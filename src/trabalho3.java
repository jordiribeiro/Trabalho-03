import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

/**
 * 
 * @author Jordi Ribeiro
 *
 */
public class trabalho3 {

	/**
	 * Faz a Leitura dos vetores do programa a partir de um numero N (Vetores J�
	 * instanciados)
	 * 
	 * @param preco
	 *            � o pre�o de cada produto
	 * @param codigo
	 *            � o codigo de cada produto
	 * @param descricao
	 *            descri��o sobre oque � cada produto
	 * @param quantidade
	 *            quantidade de cada produto no estoque
	 */
	public static void lerVetor(double[] preco, int[] codigo, String[] descricao, int[] quantidade) {
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(new Locale("en", "US"));
		sc.useLocale(Locale.ENGLISH);
		int i;
		for (i = 0; i < preco.length; i++) {
			System.out.println("Dados do produto" + (i + 1) + "\n");
			System.out.println("Pre�o|\tCodigo|\tdescri�ao|\tQuantidade|\t");
			preco[i] = sc.nextDouble();
			codigo[i] = sc.nextInt();
			descricao[i] = sc.next();
			quantidade[i] = sc.nextInt();
		}

	}

	/**
	 * Imprime a tabela que o usu�rio digitou
	 * 
	 * @param preco
	 * @param codigo
	 * @param descricao
	 * @param quantidade
	 */
	public static void imprimirTabela(double[] preco, int[] codigo, String[] descricao, int[] quantidade,
			double[] precoQTD) {
		int i;
		System.out.println("Dados dos produtos \n");
		for (i = 0; i < preco.length; i++) {
			System.out.println("Pre�o= " + preco[i] + "\t C�digo= " + codigo[i] + "\t Descri��o=" + descricao[i]
					+ "\t Quantidade =" + quantidade[i] + "\t Pre�o X Quantidade =" + precoQTD[i] + "\t");
			System.out.println(" ");

		}

	}

	/**
	 * Mostra o menu para o usu�rio selecionar uma op��o
	 * 
	 * 
	 */
	public static void mostrarMenu() {
		System.out.println("Menu de Acesso R�pido , Digite uma das Op��es para Continuar : ");
		System.out.println("1 � Imprimir tabela ");
		System.out.println("2 � Pesquisar produto");
		System.out.println("3 � Mostrar dados do produto mais caro ");
		System.out.println("4 � Mostrar c�digo e descri��o dos produtos cuja quantidade est� abaixo de 10");
		System.out.println("5 � Ativo total do estoque");
		System.out.println("6 � Efetuar uma venda");
		System.out.println("7 � Sair ");
	}

	/**
	 * Faz a opera��o de PRECO VS QUANTIDADE para todos os produtos
	 * 
	 * @param preco
	 * @param quantidade
	 * @param precoQTD
	 */
	public static void precoVSquantidade(double preco[], int quantidade[], double precoQTD[]) {
		int i;
		for (i = 0; i < preco.length; i++) {
			precoQTD[i] = preco[i] * quantidade[i];
		}

	}

	/**
	 * Fun��o para mostrar o produto mais caro dentre todos aqueles que est�o
	 * cadastrados no sistema
	 * 
	 * @param preco
	 * @param codigo
	 * @param descricao
	 * @param quantidade
	 * @param precoQTD
	 */
	public static void mostrarMaisCaroDentreTodos(double[] preco, int[] codigo, String[] descricao, int[] quantidade,
			double[] precoQTD) {
		int i, posicao = 0;
		double aux = preco[0];
		for (i = 1; i < preco.length; i++) {
			if (aux < preco[i]) {
				posicao = i;
				aux = preco[i];
			}
		}
		System.out.println("Produto Mais Caro\n");
		System.out.println("Descri��o = " + descricao[posicao]);
		System.out.println("Pre�o = " + preco[posicao]);
		System.out.println("C�digo = " + codigo[posicao]);
		System.out.println("Quantidade = " + quantidade[posicao]);
		System.out.println("Pre�o X Quantidade = " + precoQTD[posicao]);
		System.out.println("\n");
	}

	/**
	 * Fun��o cujo objetivo � retornar para o usu�rio os codigos e descri��es
	 * dos produtos com quantidade menor que 10
	 * 
	 * @param quantidade
	 * @param codigo
	 * @param descricao
	 */
	public static void mostrarProdutosQTDabaixo10(int quantidade[], int codigo[], String[] descricao) {
		int i, posicao;
		for (i = 0; i < quantidade.length; i++) {
			if (quantidade[i] < 10) {
				System.out.println("Produtos cuja quantidade est� abaixo de 10 ");
				System.out.println("Descri��o = " + descricao[i]);
				System.out.println("C�digo = " + codigo[i]);
			}
		}
	}

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(new Locale("en", "US"));
		sc.useLocale(Locale.ENGLISH);

		int N, id, cod;
		// Quantidade de produtos
		System.out.println("Digite o Numero de Produtos que ira entrar:");
		N = sc.nextInt();

		double preco[] = new double[N];
		int codigo[] = new int[N];
		String descricao[] = new String[N];
		int quantidade[] = new int[N];
		double precoQTD[] = new double[N];

		// ler vetores

		lerVetor(preco, codigo, descricao, quantidade);
		precoVSquantidade(preco, quantidade, precoQTD);
		// Menu de acesso do usu�rio

		mostrarMenu();
		id = sc.nextInt();

		while (id != 7) {

			if (id == 1) {
				imprimirTabela(preco, codigo, descricao, quantidade, precoQTD);
				System.out.println("\n");
				mostrarMenu();
				id = sc.nextInt();
			} else if (id == 2) {
				System.out.println("Digite o C�digo do produto para continuar : ");
				int pesqCod = sc.nextInt();
				int pesq = pesquisarProduto(pesqCod, codigo);
				imprimirDadosProduto(precoQTD, codigo, preco, descricao, quantidade, pesq);
				System.out.println("\n");
				mostrarMenu();
				id = sc.nextInt();
			} else if (id == 3) {
				mostrarMaisCaroDentreTodos(preco, codigo, descricao, quantidade, precoQTD);
				mostrarMenu();
				id = sc.nextInt();
			} else if (id == 4) {
				mostrarProdutosQTDabaixo10(quantidade, codigo, descricao);
				System.out.println("\n");
				mostrarMenu();
				id = sc.nextInt();

			} else if (id == 5) {
				double r = ativoTotalEstoque(precoQTD);
				System.out.println("Ativo Total =" + r);
				mostrarMenu();
				id = sc.nextInt();
				;
			}

			else {
				System.out.println("Digite o C�digo do produto para efutuar a venda");
				int codDigitado = sc.nextInt();
				int codR = buscarValorparaVenda(codigo, codDigitado);
				int qtd = sc.nextInt();
				efetuarVenda(codigo, codR, quantidade, sc, qtd);
				mostrarMenu();
				id = sc.nextInt();

			}

		}
		System.err.print("Obrigado");
		sc.close();
	}

	/**
	 * Fun��o que busca a posi��o de  um elemento para
	 * utilizar a posi��o como parametro para abater um
	 * produto
	 * 
	 * @param codigo
	 * @param codDigitado
	 * @return
	 */
	public static int buscarValorparaVenda(int[] codigo, int codDigitado) {
		int i;
		for (i = 0; i < codigo.length; i++) {
			if (codDigitado == codigo[i]) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Fun��o que efetua a venda de um item a partir de uma quantidade que o
	 * usu�rio informa , e verifica se � possivel realizar a venda.
	 * 
	 * @param codigo
	 *            vetor codigo
	 * @param codDigitado
	 *            usu�rio informa
	 * @param quantidade
	 *            vetor quantidade
	 * @param sc
	 *            scanner
	 */
	public static void efetuarVenda(int[] codigo, int codR, int[] quantidade, Scanner sc, int qtd) {
		int i, existe = 0;
			if (qtd > quantidade[codR]) {
				System.out.println("Quantidade � maior do que a do estoque");
			} else {
				quantidade[codR] = quantidade[codR] - qtd;
				System.out.println("Venda Realizada com sucesso !\n");
				System.out.println("Nova quantidade = "+quantidade[codR]);
			}
			
		
	}

	/**
	 * Fun��o que calcula o ativo total do estoque retornando o valor dele
	 * 
	 * @param precoQTD
	 * @return aux - � o retorno da fun��o ( o valor da opera��o )
	 */
	public static double ativoTotalEstoque(double[] precoQTD) {
		int i;
		double aux = 0;
		for (i = 0; i < precoQTD.length; i++) {
			aux += precoQTD[i];
		}
		return aux;

	}

	/**
	 * Fun��o Encontra a posi��o de um produto para ser utilizada em uma fun��o
	 * que ir� imprimir os dados do produto
	 * 
	 * @param pesqCod
	 *            c�digo informado pelo usu�rio
	 * @param codigo
	 *            vetor c�digo
	 * @param preco
	 * @param descricao
	 * @param quantidade
	 * @return
	 */
	public static int pesquisarProduto(int pesqCod, int codigo[]) {
		int i, pos;
		for (i = 0; i < codigo.length; i++) {
			if (pesqCod == codigo[i]) {
				return i;
			}
		}
		return -1;

	}

	/**
	 * Fun��o que tem por objetivo retornar todos os dados de um produto a
	 * partir de uma posi��o que foi encontrada em outra fun��o
	 * 
	 * @param codigo
	 *            vetores utilizados para imprimir os dados
	 * @param preco
	 *            vetores utilizados para imprimir os dados
	 * @param descricao
	 *            vetores utilizados para imprimir os dados
	 * @param quantidade
	 *            vetores utilizados para imprimir os dados
	 * @param pesq
	 *            posi��o utilizada para imprimir os dados
	 */
	public static void imprimirDadosProduto(double precoQTD[], int codigo[], double[] preco, String[] descricao,
			int[] quantidade, int pesq) {
		int i;

		if (pesq == -1) {
			System.out.println("Produto n�o Encontrado");
		} else {
			System.out.println("Sobre o produto pesquisado :");
			System.out.println("Produto = " + descricao[pesq]);
			System.out.println("Codigo = " + codigo[pesq]);
			System.out.println("Pre�o =" + preco[pesq]);
			System.out.println("Quantidade no Estoque =" + quantidade[pesq]);
			System.out.println("Preco X Quantidade =" + precoQTD[pesq]);
		}

	}
}
