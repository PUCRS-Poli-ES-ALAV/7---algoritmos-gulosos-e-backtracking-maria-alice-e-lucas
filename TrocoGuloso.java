import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TrocoGuloso {

    public static List<Integer> calcularTroco(Integer[] moedasDisponiveis, int valorTroco) {
        List<Integer> troco = new ArrayList<>();
        int iteracoes = 0;

        Arrays.sort(moedasDisponiveis, Collections.reverseOrder());

        int valorRestante = valorTroco;

        for (int moeda : moedasDisponiveis) {
            iteracoes++;
            
            while (valorRestante >= moeda) {
                iteracoes++;
                troco.add(moeda);
                valorRestante -= moeda;
            }
            
            if (valorRestante == 0) {
                break;
            }
        }

        System.out.println("Numero de iteracoes realizadas para o troco de " + valorTroco + ": " + iteracoes);
        
        if (valorRestante > 0) {
            System.out.println("Aviso: Nao foi possivel devolver o troco exato com as moedas disponiveis.");
        }
        
        return troco;
    }

    public static void main(String[] args) {
        int[] valoresTeste = {289, 550, 43};
        
        Integer[] moedasConjunto1 = {100, 20, 10, 5, 1};
        System.out.println("TESTE COM CONJUNTO DE MOEDAS: [100, 20, 10, 5, 1]");
        for (int valor : valoresTeste) {
            System.out.println("--- Valor: " + valor + " ---");
            List<Integer> resultado = calcularTroco(moedasConjunto1, valor);
            System.out.println("Moedas escolhidas: " + resultado);
            System.out.println("Quantidade total de moedas: " + resultado.size() + "\n");
        }

        Integer[] moedasConjunto2 = {100, 30, 25, 10, 5, 1};
        System.out.println("TESTE COM CONJUNTO DE MOEDAS: [100, 30, 25, 10, 5, 1]");
        for (int valor : valoresTeste) {
            System.out.println("--- Valor: " + valor + " ---");
            List<Integer> resultado = calcularTroco(moedasConjunto2, valor);
            System.out.println("Moedas escolhidas: " + resultado);
            System.out.println("Quantidade total de moedas: " + resultado.size() + "\n");
        }
    }
}
