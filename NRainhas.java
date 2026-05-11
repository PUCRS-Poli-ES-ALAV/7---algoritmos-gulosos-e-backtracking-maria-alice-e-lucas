import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NRainhas {

    static int iteracoes = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o valor de N (>= 2): ");
        int n = scanner.nextInt();

        if (n < 2) {
            System.out.println("N deve ser >= 2");
            scanner.close();
            return;
        }

        // achar uma solucao
        System.out.println("\n--- Primeira solucao ---\n");
        int[] tab = new int[n];
        iteracoes = 0;
        boolean achou = resolver(tab, 0, n);

        if (achou) {
            mostrar(tab, n);
            System.out.println("Iteracoes: " + iteracoes);
        } else {
            System.out.println("Sem solucao para N = " + n);
        }

        // complexidade
        System.out.println("\nComplexidade: O(N!)");
        System.out.println("Cada linha tenta N colunas, mas a poda reduz pra ~N! no total\n");

        // achar todas
        System.out.println("--- Todas as solucoes ---\n");
        List<int[]> todas = new ArrayList<>();
        int[] tab2 = new int[n];
        iteracoes = 0;
        resolverTodas(todas, tab2, 0, n);

        if (todas.isEmpty()) {
            System.out.println("Sem solucao para N = " + n);
        } else {
            System.out.println("Total: " + todas.size() + " solucoes");
            System.out.println("Iteracoes: " + iteracoes + "\n");
            for (int i = 0; i < todas.size(); i++) {
                System.out.println("Solucao " + (i + 1) + ":");
                mostrar(todas.get(i), n);
            }
        }

        scanner.close();
    }

    // backtracking que para na primeira
    static boolean resolver(int[] rainhas, int linha, int n) {
        if (linha == n)
            return true;

        for (int col = 0; col < n; col++) {
            iteracoes++;
            if (posicaoValida(rainhas, linha, col)) {
                rainhas[linha] = col;
                if (resolver(rainhas, linha + 1, n))
                    return true;
            }
        }
        return false;
    }

    // backtracking que acha todas
    static void resolverTodas(List<int[]> solucoes, int[] rainhas, int linha, int n) {
        if (linha == n) {
            solucoes.add(rainhas.clone());
            return;
        }

        for (int col = 0; col < n; col++) {
            iteracoes++;
            if (posicaoValida(rainhas, linha, col)) {
                rainhas[linha] = col;
                resolverTodas(solucoes, rainhas, linha + 1, n);
            }
        }
    }

    static boolean posicaoValida(int[] rainhas, int linha, int col) {
        for (int i = 0; i < linha; i++) {
            if (rainhas[i] == col)
                return false;
            if (Math.abs(rainhas[i] - col) == Math.abs(i - linha))
                return false;
        }
        return true;
    }

    static void mostrar(int[] rainhas, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (rainhas[i] == j)
                    System.out.print("Q ");
                else
                    System.out.print(". ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
