package carrinhodecompras;

public class SolucaoProgramacaoDinamica {

	public static ListaProdutos resolver(Problema p, int pesoMax) {
		ListaProdutos ret = new ListaProdutos();
		int N = p.getTamanho(); // Número de itens
		int W = pesoMax; // Peso máximo da mochila

		int[] valor = new int[N + 1];
		int[] peso = new int[N + 1];

		// Faz a cópia dos valores do problema, trabalhar com tipos de dados primitivos é mais rápido.
		for (int n = 1; n <= N; n++) {
			valor[n] = p.get(n - 1).valor;
			peso[n] = p.get(n - 1).peso;
		}

		// opt[n][w] = máximo valor dos itens 1..n com peso limite de w
		// sol[n][w] = a solução opt para empacotar itens 1..n com peso limite w inclui item n?
		int[][] opt = new int[N + 1][W + 1];
		boolean[][] sol = new boolean[N + 1][W + 1];

		for (int n = 1; n <= N; n++) {
			for (int w = 1; w <= W; w++) {
				// não inclui item n
				int option1 = opt[n - 1][w];
				// inclui item n
				int option2 = Integer.MIN_VALUE;
				if (peso[n] <= w)
					option2 = valor[n] + opt[n - 1][w - peso[n]];
				// seleciona a melhor das duas opções
				opt[n][w] = Math.max(option1, option2);
				sol[n][w] = (option2 > option1);
			}
		}

		// Determina quais itens serão obtidos
		boolean[] pegar = new boolean[N + 1];
		for (int n = N, w = W; n > 0; n--) {
			if (sol[n][w]) {
				pegar[n] = true;
				w = w - peso[n];
			} else {
				pegar[n] = false;
			}
		}

		// Imprime os resultados e adiciona os itens na lista de produtos
		// System.out.println("item" + "\t" + "valor" + "\t" + "peso" + "\t" + "pegar?");
		for (int n = 1; n <= N; n++) {
			// System.out.println(n + "\t" + valor[n] + "\t" + peso[n] + "\t" + pegar[n]);
			if (pegar[n]) {
				ret.adicionar(p.get(n - 1));
			}
		}
		return ret;
	}
}
