import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;

public class Fachada {
    private static ArrayList<Correntista> correntistas = new ArrayList<>();
    private static ArrayList<Conta> contas = new ArrayList<>();

    //lista todos os correntistas e ordena isso pelo cpf.
    //usei de stream pra facilitar essa busca e pra deixar com menos código
    public static ArrayList<Correntista> listarCorrentistas() {
        //o Comparator.comparing faz a comparação com base no que voce passar, no caso o cpf
        //Correntista::getCpf nada mais é do que um for diferente, percorrendo a lista toda
        //o sort vai ordenar a lista, que é correntistas
        Collections.sort(correntistas, Comparator.comparing(Correntista::getCpf));
        return correntistas;
    }

    //aqui ele apenas retorna toda a lista sem fazer nada
    public static ArrayList<Conta> listarContas() {
        return contas;
    }

    // Método pra criar um novo correntista.
    // Recebe CPF, nome e senha como parametros.
    // Verifica se a senha tem exatamente 4 dígitos; se não tiver, lança uma exceção.
    public static void criarCorrentista(String cpf, String nome, String senha) throws Exception {
        if (senha.length() != 4){
            throw new Exception("Senha deve ter 4 dígitos.");
        }
        correntistas.add(new Correntista(cpf, nome, senha));
    }

    // cria uma conta simples associada a um correntista que já existe.
    // Recebe o CPF do correntista como parametro.
    // Busca o correntista e cria a conta, adicionando na lista de contas.
    public static void criarConta(String cpf) throws Exception {
        Correntista correntista = buscarCorrentista(cpf);
        contas.add(new ContaSimples(correntista));
    }

    //mesma lógica do criarConta, com a diferença do limite passaado como parametro
    public static void criarContaEspecial(String cpf, double limite) throws Exception {
        Correntista correntista = buscarCorrentista(cpf);
        contas.add(new ContaEspecial(correntista, limite));
    }


    // adiciona um correntista como cotitular a uma conta existente.
    // Recebe o ID da conta e o CPF do correntista a ser adicionado e adiciona.
    public static void inserirCorrentistaConta(int id, String cpf) throws Exception {
        Conta conta = buscarConta(id);
        Correntista correntista = buscarCorrentista(cpf);
        conta.adicionarCorrentista(correntista);
    }

    //mesma lógica do inserir, só que removendo rs
    public static void removerCorrentistaConta(int id, String cpf) throws Exception {
        Conta conta = buscarConta(id);
        Correntista correntista = buscarCorrentista(cpf);
        conta.removerCorrentista(correntista);
    }

    //passa um id como parametro, busca por esse id
    // verifica se tem saldo na conta. Se tiver zerada, então exclui
    public static void apagarConta(int id) throws Exception {
        Conta conta = buscarConta(id);
        if (conta.getSaldo() != 0){
            throw new Exception("Conta com saldo diferente de zero.");
        }
        contas.remove(conta);
    }

    // credita um valor na conta de um correntista autenticado.
    // Recebe o ID da conta, CPF, senha e o valor a ser creditado.
    //busca por esse correntista, verifica ele e então adiciona o valor
    public static void creditarValor(int id, String cpf, String senha, double valor) throws Exception {
        Conta conta = buscarConta(id);
        Correntista correntista = verificarAutenticacao(cpf, senha);
        conta.creditar(valor);
    }

    //mesma lógica do creditarValor rs. só que debitando da conta.
    public static void debitarValor(int id, String cpf, String senha, double valor) throws Exception {
        Conta conta = buscarConta(id);
        Correntista correntista = verificarAutenticacao(cpf, senha);
        conta.debitar(valor);
    }

    //acho que a lógica aqui também tá bem simples. Vai transferir valor pra outra conta
    //´por isso recebe esses Ids, que é da minha conta e da tua, por ex.
    //busca esses IDs e depois debita o valor de uma conta e credita na outra.
    public static void transferirValor(int id1, String cpf, String senha, double valor, int id2) throws Exception {
        Conta contaOrigem = buscarConta(id1);
        Conta contaDestino = buscarConta(id2);
        debitarValor(id1, cpf, senha, valor);
        contaDestino.creditar(valor);
    }

    //aqui nesse método tambem usei stream pra facilitar.
    private static Conta buscarConta(int id) throws Exception {
        // Filtra a lista de contas para encontrar a conta com o ID especificado
        //um Optional não deixar dar erro caso a busca retorne um false ou null.
        //criei uma contaopt que vai buscar na lista 'contas'.
        //o stream vai permitir um filtro melhor. O filter vai buscar conta onde o Id for igual
        //ao Id passado por parametro
        //o findFirst vai trazer a primeira ocorrencia que ele encontrar.
        //esse 'c' seria cada conta na lista, entende. Pode ser qualquer nome ali.
        Optional<Conta> contaOpt = contas.stream()
                .filter(c -> c.getId() == id)
                .findFirst();

        // Se a conta não for encontrada, lança uma exceção
        return contaOpt.orElseThrow(() -> new Exception("Conta não encontrada."));
    }

    //esse método aqui faz a mesma coisa do método anterior, só muda a sintaxe
    // se lá eu usei um optional, aqui foi o stream direto
    //quis deixar assim pra te mostar a diferença, mas a lógica é a mesma
    //aí tu me diz qual foi mais tranquilo de entender
    //perceba que o filtro é o mesmo, mas tudo feito em uma linha só
    private static Correntista buscarCorrentista(String cpf) throws Exception {
        return correntistas.stream().filter(c -> c.getCpf().equals(cpf)).findFirst().orElseThrow(() -> new Exception("Correntista não encontrado."));
    }

    //verificando pelo cpf, se existe e pela senha, se tá correta.
    private static Correntista verificarAutenticacao(String cpf, String senha) throws Exception {
        Correntista correntista = buscarCorrentista(cpf);
        if (!correntista.getSenha().equals(senha)) {
            throw new Exception("Senha incorreta.");
        }
        return correntista;
    }




    //getters e setters. nem era necessário, ams eu tenho toc e queria que o intellij parasse
    //de ficar dando aviso pra eu criar os getters e setters
    public static ArrayList<Correntista> getCorrentistas() {
        return correntistas;
    }

    public static void setCorrentistas(ArrayList<Correntista> correntistas) {
        Fachada.correntistas = correntistas;
    }

    public static ArrayList<Conta> getContas() {
        return contas;
    }

    public static void setContas(ArrayList<Conta> contas) {
        Fachada.contas = contas;
    }
}
