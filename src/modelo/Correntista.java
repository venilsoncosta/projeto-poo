package modelo;

public class Correntista {
    private final String cpf;  // CPF imutável
    private String nome;
    private String senha;  // Deveria ser armazenada de forma mais segura (hash)

    // Construtor com validações
    public Correntista(String cpf, String nome, String senha) {
        validarCpf(cpf);
        validarNome(nome);
        validarSenha(senha);

        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;  // Sugestão: armazenar senha de forma segura
    }

    @Override
    public String toString() {
        return "Correntista{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }


    // Construtor vazio - caso a gente precise. tava dando erro, então fiz o cpf recebendo nada. ai parou. rs
    public Correntista() {
        this.cpf = "";
    }

    // Getters e Setters com as validações feitas lá em baixo nos métodos

    public String getCpf() {
        return cpf;  // CPF não tem setter, pois não podemos mudá-lo.
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        validarNome(nome);
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        validarSenha(senha);
        this.senha = senha;
    }

    // pesquisei por formas de validar o cpf e deixar mais robusto.
    private void validarCpf(String cpf) {
        if (cpf == null || !cpf.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF inválido. Deve ter 11 dígitos.");
        }
    }

    //a mesma coisa fiz em nome. tirei espaços em branco e verifiquei se tá vazio.
    private void validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
    }

    private void validarSenha(String senha) {
        if (senha == null || senha.length() < 4) {
            throw new IllegalArgumentException("A senha deve ter pelo menos 4 caracteres.");
        }
    }
}
