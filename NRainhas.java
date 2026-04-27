import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NRainhas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o tamanho do tabuleiro (N >= 2): ");
        int n = scanner.nextInt();
        
        if (n < 2) {
            System.out.println("N deve ser maior ou igual a 2.");
            scanner.close();
            return;
        }
        
        List<List<String>> solucoes = resolverNQueens(n);
        
        if (solucoes.isEmpty()) {
            System.out.println("Não há soluções possíveis para N = " + n);
        } else {
            System.out.println("\nForam encontradas " + solucoes.size() + " soluções possíveis:");
            for (int i = 0; i < solucoes.size(); i++) {
                System.out.println("\nSolução " + (i + 1) + ":");
                imprimirTabuleiro(solucoes.get(i));
            }
        }
        
        scanner.close();
    }

    public static List<List<String>> resolverNQueens(int n) {
        List<List<String>> resultado = new ArrayList<>();
        char[][] tabuleiro = new char[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tabuleiro[i][j] = '.';
            }
        }
        
        backtrack(resultado, tabuleiro, 0, n);
        return resultado;
    }

    private static void backtrack(List<List<String>> resultado, char[][] tabuleiro, int linha, int n) {
        if (linha == n) {
            resultado.add(construirTabuleiro(tabuleiro));
            return;
        }

        for (int col = 0; col < n; col++) {
            if (ehSeguro(tabuleiro, linha, col, n)) {
                tabuleiro[linha][col] = 'Q';
                backtrack(resultado, tabuleiro, linha + 1, n);
                tabuleiro[linha][col] = '.';
            }
        }
    }

    private static boolean ehSeguro(char[][] tabuleiro, int linha, int col, int n) {
        for (int i = 0; i < linha; i++) {
            if (tabuleiro[i][col] == 'Q') {
                return false;
            }
        }

        for (int i = linha - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (tabuleiro[i][j] == 'Q') {
                return false;
            }
        }

        for (int i = linha - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (tabuleiro[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }

    private static List<String> construirTabuleiro(char[][] tabuleiro) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < tabuleiro.length; i++) {
            res.add(new String(tabuleiro[i]));
        }
        return res;
    }

    private static void imprimirTabuleiro(List<String> tabuleiro) {
        for (String linha : tabuleiro) {
            for (int i = 0; i < linha.length(); i++) {
                System.out.print(linha.charAt(i) + " ");
            }
            System.out.println();
        }
    }
}
