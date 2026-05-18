import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Subconjuntos {

    static int iteracoes = 0;

    public static void main(String[] args) {
        int[] ex1 = {-7, -3, -2, 5, 8};
        int[] ex2 = {1, 2, 3, 4, 5, 10};
        int[] ex3 = {-5, 2, 3, -1, 1};

        System.out.println("--- Primeiro subconjunto com soma zero ---\n");
        testarPrimeiro(ex1);
        testarPrimeiro(ex2);
        testarPrimeiro(ex3);

        System.out.println("Complexidade (primeiro): O(2^N) no pior caso");
        System.out.println("Complexidade (todos):    O(2^N), explora todas as combinacoes");
        System.out.println("Espaco: O(N) pela recursao\n");

        System.out.println("--- Todos os subconjuntos com soma zero ---\n");
        testarTodos(ex1);
        testarTodos(ex2);
        testarTodos(ex3);

        System.out.println("--- Teste de performance ---\n");
        testarPerformance();
    }

    static void testarPrimeiro(int[] conjunto) {
        System.out.print("Conjunto: ");
        mostrarConjunto(conjunto);

        List<Integer> resultado = new ArrayList<>();
        iteracoes = 0;
        boolean achou = encontrarPrimeiro(conjunto, 0, 0, new ArrayList<>(), resultado);

        if (achou) {
            System.out.println("Subconjunto: " + resultado);
        } else {
            System.out.println("Sem subconjunto com soma zero");
        }
        System.out.println("Iteracoes: " + iteracoes + "\n");
    }

    static void testarTodos(int[] conjunto) {
        System.out.print("Conjunto: ");
        mostrarConjunto(conjunto);

        List<List<Integer>> todos = new ArrayList<>();
        iteracoes = 0;
        encontrarTodos(conjunto, 0, 0, new ArrayList<>(), todos);

        if (todos.isEmpty()) {
            System.out.println("Sem subconjunto com soma zero");
        } else {
            System.out.println("Total: " + todos.size() + " subconjuntos");
            for (int i = 0; i < todos.size(); i++) {
                System.out.println("  " + (i + 1) + ": " + todos.get(i));
            }
        }
        System.out.println("Iteracoes: " + iteracoes + "\n");
    }

    // para no primeiro que achar
    static boolean encontrarPrimeiro(int[] conjunto, int idx, int somaAtual, List<Integer> atual, List<Integer> resultado) {
        if (!atual.isEmpty() && somaAtual == 0) {
            resultado.addAll(atual);
            return true;
        }

        for (int i = idx; i < conjunto.length; i++) {
            iteracoes++;
            atual.add(conjunto[i]);
            if (encontrarPrimeiro(conjunto, i + 1, somaAtual + conjunto[i], atual, resultado))
                return true;
            atual.remove(atual.size() - 1);
        }
        return false;
    }

    // acha todos os subconjuntos
    static void encontrarTodos(int[] conjunto, int idx, int somaAtual, List<Integer> atual, List<List<Integer>> todos) {
        if (!atual.isEmpty() && somaAtual == 0) {
            todos.add(new ArrayList<>(atual));
        }

        for (int i = idx; i < conjunto.length; i++) {
            iteracoes++;
            atual.add(conjunto[i]);
            encontrarTodos(conjunto, i + 1, somaAtual + conjunto[i], atual, todos);
            atual.remove(atual.size() - 1);
        }
    }

    static void testarPerformance() {
        int[] tamanhos = {10, 15, 20, 25};

        System.out.println(">> Encontrar PRIMEIRO:\n");
        for (int tam : tamanhos) {
            int[] conjunto = gerarConjunto(tam);
            iteracoes = 0;
            long inicio = System.currentTimeMillis();
            List<Integer> resultado = new ArrayList<>();
            boolean achou = encontrarPrimeiro(conjunto, 0, 0, new ArrayList<>(), resultado);
            long tempo = System.currentTimeMillis() - inicio;
            System.out.println("N=" + tam + " | Achou: " + achou + " | Iteracoes: " + iteracoes + " | Tempo: " + tempo + "ms");
        }

        System.out.println("\n>> Encontrar TODOS:\n");
        for (int tam : tamanhos) {
            int[] conjunto = gerarConjunto(tam);
            List<List<Integer>> todos = new ArrayList<>();
            iteracoes = 0;
            long inicio = System.currentTimeMillis();
            encontrarTodos(conjunto, 0, 0, new ArrayList<>(), todos);
            long tempo = System.currentTimeMillis() - inicio;
            System.out.println("N=" + tam + " | Solucoes: " + todos.size() + " | Iteracoes: " + iteracoes + " | Tempo: " + tempo + "ms");
        }

        System.out.println("\n>> Conjuntos grandes (so primeiro, todos seria lento demais):\n");
        int[] tamGrandes = {50, 100, 500, 1000};
        for (int tam : tamGrandes) {
            int[] conjunto = gerarConjunto(tam);
            iteracoes = 0;
            long inicio = System.currentTimeMillis();
            List<Integer> resultado = new ArrayList<>();
            boolean achou = encontrarPrimeiro(conjunto, 0, 0, new ArrayList<>(), resultado);
            long tempo = System.currentTimeMillis() - inicio;
            System.out.println("N=" + tam + " | Achou: " + achou + " | Iteracoes: " + iteracoes + " | Tempo: " + tempo + "ms");
        }
    }

    static int[] gerarConjunto(int tam) {
        Random rand = new Random(tam);
        int[] conjunto = new int[tam];
        for (int i = 0; i < tam; i++) {
            conjunto[i] = rand.nextInt(201) - 100;
        }
        return conjunto;
    }

    static void mostrarConjunto(int[] conjunto) {
        System.out.print("{");
        for (int i = 0; i < conjunto.length; i++) {
            System.out.print(conjunto[i]);
            if (i < conjunto.length - 1) System.out.print(", ");
        }
        System.out.println("}");
    }
}
