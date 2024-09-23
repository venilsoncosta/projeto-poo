package regras_negocio;

import modelo.*;
import java.util.ArrayList;
import java.util.Comparator;

public class FachadaAntiga {
    private static ArrayList<Correntista> correntistas = new ArrayList<>();
    private static ArrayList<Conta> contas = new ArrayList<>();

    // Lista todos os correntistas e ordena pelo CPF.
    //o sort ordena, o comparator vai fazer o filtro pelo o que passarmos, que é o cpf. percorre tudo e depois devolve
    public static ArrayList<Correntista> listarCorrentistas() {
        correntistas.sort(Comparator.comparing(Correntista::getCpf));
        return correntistas;
    }

    // Retorna todas as contas. aqui não precisa de filtro nenhum.
    public static ArrayList<Conta> listarContas() {
        return contas;
    }

    // Cria um novo correntista, validando a senha.
    public static void criarCorrentista(String cpf, String nome, String senha) throws Exception {
        if (senha.length() != 4) {
            throw new Exception("Senha deve ter 4 dígitos.");
        }
        correntistas.add(new Correntista(cpf, nome, senha));
    }

    // Cria uma conta simples associada a um correntista existente.
    public static void criarConta(String cpf) throws Exception {
        Correntista correntista = buscarCorrentista(cpf);
        contas.add(new ContaSimples(correntista));
    }

    // Cria uma conta especial com limite associado a um correntista que já existe.
    public static void criarContaEspecial(String cpf, double limite) throws Exception {
        Correntista correntista = buscarCorrentista(cpf);
        contas.add(new ContaEspecial(correntista, limite));
    }

    // Adiciona um correntista como cotitular de uma conta existente.
    public static void inserirCorrentistaConta(int id, String cpf) throws Exception {
        Conta conta = buscarConta(id);
        Correntista correntista = buscarCorrentista(cpf);
        conta.adicionarCorrentista(correntista);
    }

    // Remove um correntista de uma conta existente.
    public static void removerCorrentistaConta(int id, String cpf) throws Exception {
        Conta conta = buscarConta(id);
        Correntista correntista = buscarCorrentista(cpf);
        conta.removerCorrentista(correntista);
    }

    // Apaga uma conta se o saldo for zero.
    public static void apagarConta(int id) throws Exception {
        Conta conta = buscarConta(id);
        if (conta.getSaldo() != 0) {
            throw new Exception("modelo.Conta com saldo diferente de zero.");
        }
        contas.remove(conta);
    }


    // Credita um valor na conta de um correntista autenticado.
    public static void creditarValor(int id, String cpf, String senha, double valor) throws Exception {
        Conta conta = buscarConta(id);
        verificarAutenticacao(cpf, senha);
        conta.creditar(valor);
    }

    // Debita um valor da conta de um correntista autenticado.
    public static void debitarValor(int id, String cpf, String senha, double valor) throws Exception {
        Conta conta = buscarConta(id);
        verificarAutenticacao(cpf, senha);
        conta.debitar(valor);
    }

    // Transfere um valor entre duas contas, debitando de uma e creditando em outra.
    public static void transferirValor(int idOrigem, String cpf, String senha, double valor, int idDestino) throws Exception {
        Conta contaOrigem = buscarConta(idOrigem);
        Conta contaDestino = buscarConta(idDestino);
        debitarValor(idOrigem, cpf, senha, valor);
        contaDestino.creditar(valor);
    }

    // Busca uma conta pelo ID.
    private static Conta buscarConta(int id) throws Exception {
        return contas.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new Exception("modelo.Conta não encontrada."));
    }

    // Busca um correntista pelo CPF.
    //usei stream pra facilitar o filtro. O filter faz o filtro com o parametro que a gente passa (o cpf)
    //o findfirst devolve o primeiro que ele encontra
    // e depois é uma excecao de forma resumida.
    private static Correntista buscarCorrentista(String cpf) throws Exception {
        return correntistas.stream()
                .filter(c -> c.getCpf().equals(cpf))
                .findFirst()
                .orElseThrow(() -> new Exception("modelo.Correntista não encontrado."));
    }

    // Verifica a autenticação de um correntista com CPF e senha.
    private static void verificarAutenticacao(String cpf, String senha) throws Exception {
        Correntista correntista = buscarCorrentista(cpf);
        if (!correntista.getSenha().equals(senha)) {
            throw new Exception("Senha incorreta.");
        }
    }

    // Getters e setters.
    public static ArrayList<Correntista> getCorrentistas() {
        return correntistas;
    }

    public static void setCorrentistas(ArrayList<Correntista> correntistas) {
        FachadaAntiga.correntistas = correntistas;
    }

    public static ArrayList<Conta> getContas() {
        return contas;
    }

    public static void setContas(ArrayList<Conta> contas) {
        FachadaAntiga.contas = contas;
    }
}
