package carrinhodecompras;

/**
 * Solução para o problema do carrinho de compras, usando força bruta.
 *
 * @author daniellucredio
 */
public class SolucaoForcaBruta {

    /**
     * Esse método resolve uma instância do problema do carrinho de compras,
     * utilizando uma abordagem do tipo força bruta. Calcula todos os
     * subconjuntos possíveis do problema, e testa um a um em busca daquele com
     * o maior valor e que não ultrapassa o limite de peso do carrinho.
     *
     * @param problema
     * @param pesoMaximoPermitido
     * @return
     */
    public ListaProdutos resolver(Problema problema, int pesoMaximoPermitido) {
        // A variável ret vai armazenar as melhores
        // soluções para o problema
        ListaProdutos ret = new ListaProdutos();

        // Primeiro encontramos todos os subconjuntos possíveis
        // da lista de produtos do problema

        // O número de subconjuntos é 2 ^ tamanho do problema
        long numeroSubConjuntos = (long) Math.pow(2, problema.getTamanho());

        // A variável numero vai ser convertida em string binário
        // Ela é incrementada a cada iteração
        // As posições do binário com caractere '1' correspondem
        // a um produto presente no subconjunto
        long numero = 0;
        for (long i = 0; i < numeroSubConjuntos; i++) {
            ListaProdutos listaProdutos = new ListaProdutos();
            String strNumeroBinario = Long.toBinaryString(numero);
            int tamanhoNumeroBinario = strNumeroBinario.length();

            for (int j = tamanhoNumeroBinario - 1; j >= 0; j--) {
                if (strNumeroBinario.charAt(j) == '1') {
                    listaProdutos.adicionar(problema.get(tamanhoNumeroBinario - j - 1));
                }
            }
            numero++;

            // listaProdutos corresponde a um subconjunto do problema
            // isto é, uma possível solução

            // Primeiro verificamos se o peso não ultrapassa o máximo
            // permitido. Se ultrapassar, vai para a próxima iteração
            // do laço
            int peso = listaProdutos.getPeso();
            if (peso > pesoMaximoPermitido) {
                continue;
            }

            // Vamos agora verificar se ela é melhor do que as soluções
            // obtidas até o momento.
            // Se for melhor, apaga as anteriores e adiciona a nova
            // Se for igual, adiciona a solução junto às outras, verificando
            // se a mesma ainda não existe
            int valor = listaProdutos.getValor();
            int valorAteOMomento = ret.getValor();
            if (valor > valorAteOMomento) {
                ret = listaProdutos;
            }
        }

        return ret;
    }
}
