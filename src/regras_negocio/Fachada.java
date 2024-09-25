package regras_negocio;

import modelo.Correntista;
import modelo.Conta;
import repositorio.Repositorio;

import java.util.ArrayList;

public class Fachada {

    private Fachada() {}

    private static Repositorio repositorio = new Repositorio();

    // metodos para Correntistas
    public static ArrayList<Correntista> listarCorrentistas() {
        return new ArrayList<>(repositorio.getCorrentistas());
    }

    public static Correntista localizarCorrentista(String cpf) {
        return repositorio.localizarCorrentistaPorCpf(cpf);
    }

    public static void criarCorrentista(String cpf, String nome, String senha) throws Exception {
        cpf = cpf.trim();
        nome = nome.trim();
        senha = senha.trim();

        // Verifica se o correntista já existe
        if (repositorio.localizarCorrentistaPorCpf(cpf) != null) {
            throw new Exception("Correntista já cadastrado: " + nome);
        }
        
        if ((senha== null) || (!senha.matches("\\d{4}"))) {
            throw new Exception("Inserção inválida: a senha deve conter 4 dígitos");
        }

        // Cria o correntista
        Correntista correntista = new Correntista(cpf, nome, senha);
        repositorio.adicionarCorrentista(correntista);
        repositorio.salvarCorrentistas();
    }

    public static void removerCorrentista(String cpf) throws Exception {
        Correntista correntista = repositorio.localizarCorrentistaPorCpf(cpf);
        if (correntista == null) {
            throw new Exception("Correntista não encontrado: " + cpf);
        }

        repositorio.removerCorrentista(correntista);
        repositorio.salvarCorrentistas();
    }

    // metodos para Contas
    public static ArrayList<Conta> listarContas() {
        return new ArrayList<>(repositorio.getContas());
    }

    public static Conta localizarConta(int id) {
        return repositorio.localizarContaPorId(id);
    }

    public static void criarConta(Conta conta) throws Exception {
        repositorio.adicionarConta(conta);
        repositorio.salvarContas();
    }

    public static void removerConta(int id) throws Exception {
        Conta conta = repositorio.localizarContaPorId(id);
        if (conta == null) {
            throw new Exception("Conta não encontrada: " + id);
        }

        repositorio.removerConta(conta);
        repositorio.salvarContas();
    }
}
