package console;

import modelo.ContaSimples;
import modelo.Correntista;
import regras_negocio.Fachada;

public class Cadastrar {
    public Cadastrar() {
        try {
            Fachada.criarCorrentista("09876596792", "Venilson Venilson", "1234");
            Fachada.criarCorrentista("09876599008", "Pedro Pedro", "mada");
            Fachada.criarCorrentista("09994499008", "Louise Louise", "blea");

            // Criar uma conta para um dos correntistas
            Correntista correntista = Fachada.localizarCorrentista("09876599008");
            if (correntista != null) {
                ContaSimples conta = new ContaSimples(correntista);
                Fachada.criarConta(conta);
                System.out.println("Conta criada para: " + correntista.getNome());
            } else {
                System.out.println("Correntista não encontrado ao criar conta.");
            }
        } catch (Exception e) {
            e.printStackTrace();  // Exibir o erro no console para facilitar o debug
        }
    }

    public static void main(String[] args) {
        new Cadastrar();
    }
}
