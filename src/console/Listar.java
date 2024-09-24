package console;

import modelo.Conta;
import modelo.Correntista;
import regras_negocio.Fachada;

public class Listar {
    public Listar() {
        try {
            System.out.println("\n--------- Listagem de Correntistas -----");
            for (Correntista correntista : Fachada.listarCorrentistas()) {
                System.out.println(correntista);
            }

            System.out.println("\n--------- Listagem de Contas ----");
            for (Conta conta : Fachada.listarContas()) {
                System.out.println(conta);
            }
        } catch (Exception e) {
            e.printStackTrace();  // Exibir o erro no console para facilitar o debug
        }
    }

    public static void main(String[] args) {
        new Listar();
    }
}
