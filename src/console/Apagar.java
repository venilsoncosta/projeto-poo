package console;

import regras_negocio.Fachada;

import java.util.Scanner;

public class Apagar {
    public Apagar() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Digite o ID da conta que deseja remover: ");
            int id = scanner.nextInt();
            Fachada.removerConta(id);
            System.out.println("Conta removida com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();  // Exibir o erro no console para facilitar o debug
        }
    }

    public static void main(String[] args) {
        new Apagar();
    }
}
