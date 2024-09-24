package appswing;

import modelo.Conta;
import modelo.ContaEspecial;
import modelo.ContaSimples;
import modelo.Correntista;
import regras_negocio.Fachada;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainInterface extends JFrame {
    private JTextField cpfField;
    private JTextField nomeField;
    private JTextField senhaField;
    private JTextField contaIdField; // Novo campo para ID da conta
    private JTextArea outputArea;

    public MainInterface() {
        setTitle("Sistema Bancário");
        setSize(400, 600); // Aumentado para acomodar novos elementos
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Campo para CPF
        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(20, 20, 80, 25);
        add(lblCpf);
        cpfField = new JTextField();
        cpfField.setBounds(100, 20, 250, 25);
        add(cpfField);

        // Campo para Nome
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 60, 80, 25);
        add(lblNome);
        nomeField = new JTextField();
        nomeField.setBounds(100, 60, 250, 25);
        add(nomeField);

        // Campo para Senha
        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(20, 100, 80, 25);
        add(lblSenha);
        senhaField = new JTextField();
        senhaField.setBounds(100, 100, 250, 25);
        add(senhaField);

        // Campo para ID da Conta
        JLabel lblContaId = new JLabel("ID da Conta:");
        lblContaId.setBounds(20, 140, 80, 25);
        add(lblContaId);
        contaIdField = new JTextField();
        contaIdField.setBounds(100, 140, 250, 25);
        add(contaIdField);

        // Botão para Cadastrar Correntista
        JButton btnCadastrar = new JButton("Cadastrar Correntista");
        btnCadastrar.setBounds(50, 180, 250, 30);
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cadastrarCorrentista();
            }
        });
        add(btnCadastrar);

        // Botão para Criar Conta
        JButton btnCriarConta = new JButton("Criar Conta");
        btnCriarConta.setBounds(50, 220, 250, 30);
        btnCriarConta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                criarConta();
            }
        });
        add(btnCriarConta);

        // Botão para Listar Correntistas e Contas
        JButton btnListar = new JButton("Listar Correntistas/Contas");
        btnListar.setBounds(50, 260, 250, 30);
        btnListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listarCorrentistasEContas();
            }
        });
        add(btnListar);

        // Botão para Apagar Conta
        JButton btnApagar = new JButton("Apagar Conta");
        btnApagar.setBounds(50, 300, 250, 30);
        btnApagar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                apagarConta();
            }
        });
        add(btnApagar);

        // Área de Saída
        outputArea = new JTextArea();
        outputArea.setBounds(20, 340, 350, 200);
        outputArea.setEditable(false);
        add(outputArea);

        setVisible(true);
    }

    private void cadastrarCorrentista() {
        String cpf = cpfField.getText();
        String nome = nomeField.getText();
        String senha = senhaField.getText();

        try {
            Fachada.criarCorrentista(cpf, nome, senha);
            outputArea.setText("Correntista cadastrado com sucesso: " + nome);
        } catch (Exception e) {
            outputArea.setText("Erro ao cadastrar correntista: " + e.getMessage());
        }
    }

    private void criarConta() {
        String cpfText = cpfField.getText(); // Obter CPF como String
        if (cpfText.isEmpty()) {
            outputArea.setText("Erro: CPF não pode ser vazio.");
            return;
        }

        try {
            // Tentar encontrar o correntista pelo CPF
            Correntista correntista = Fachada.localizarCorrentista(cpfText);
            if (correntista == null) {
                outputArea.setText("Erro: Correntista com CPF " + cpfText + " não encontrado.");
                return;
            }

            // Pergunta ao usuário qual tipo de conta deseja criar
            String tipoConta = JOptionPane.showInputDialog("Digite 'simples' para Conta Simples ou 'especial' para Conta Especial:");

            if ("simples".equalsIgnoreCase(tipoConta)) {
                // Criar uma conta simples
                Conta novaConta = new ContaSimples(correntista);
                Fachada.criarConta(novaConta); // Chama o método da fachada para criar a conta
                outputArea.setText("Conta simples criada com sucesso para o correntista: " + correntista.getNome());
            } else if ("especial".equalsIgnoreCase(tipoConta)) {
                // Pergunta pelo limite da conta especial
                String limiteInput = JOptionPane.showInputDialog("Digite o limite da conta especial (mínimo 50):");
                double limite = Double.parseDouble(limiteInput);
                Conta novaConta = new ContaEspecial(correntista, limite);
                Fachada.criarConta(novaConta); // Chama o método da fachada para criar a conta
                outputArea.setText("Conta especial criada com sucesso para o correntista: " + correntista.getNome());
            } else {
                outputArea.setText("Tipo de conta inválido. Digite 'simples' ou 'especial'.");
            }
        } catch (Exception e) {
            outputArea.setText("Erro ao criar conta: " + e.getMessage());
        }
    }



    private void listarCorrentistasEContas() {
        StringBuilder output = new StringBuilder();
        output.append("\n--------- Listagem de Correntistas -----\n");
        for (Correntista correntista : Fachada.listarCorrentistas()) {
            output.append(correntista.toString()).append("\n");
        }
        output.append("\n--------- Listagem de Contas ----\n");
        for (Conta conta : Fachada.listarContas()) {
            output.append(conta.toString()).append("\n");
        }
        outputArea.setText(output.toString());
    }

    private void apagarConta() {
        String input = JOptionPane.showInputDialog("Digite o ID da conta que deseja remover:");
        if (input != null) {
            try {
                int id = Integer.parseInt(input);
                Fachada.removerConta(id);
                outputArea.setText("Conta removida com sucesso.");
            } catch (Exception e) {
                outputArea.setText("Erro ao remover conta: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new MainInterface();
    }
}

