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
        Integer[] moedas = {100, 25, 10, 5, 1};
        
        int valor1 = 289;
        System.out.println("--- Caso de Teste 1 ---");
        List<Integer> resultado1 = calcularTroco(moedas, valor1);
        System.out.println("Moedas escolhidas [" + valor1 + "]: " + resultado1);
        System.out.println("Quantidade total de moedas: " + resultado1.size() + "\n");

        int valor2 = 550;
        System.out.println("--- Caso de Teste 2 ---");
        List<Integer> resultado2 = calcularTroco(moedas, valor2);
        System.out.println("Moedas escolhidas [" + valor2 + "]: " + resultado2);
        System.out.println("Quantidade total de moedas: " + resultado2.size() + "\n");

        int valor3 = 43;
        System.out.println("--- Caso de Teste 3 ---");
        List<Integer> resultado3 = calcularTroco(moedas, valor3);
        System.out.println("Moedas escolhidas [" + valor3 + "]: " + resultado3);
        System.out.println("Quantidade total de moedas: " + resultado3.size() + "\n");
    }
}
