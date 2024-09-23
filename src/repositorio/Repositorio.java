package repositorio;

import modelo.Correntista;
import modelo.Conta;
import modelo.ContaSimples;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Repositorio {

    private List<Correntista> correntistas = new ArrayList<>();
    private List<Conta> contas = new ArrayList<>();

    public Repositorio() {
        carregarCorrentistas();
        carregarContas();
    }


    public void adicionarCorrentista(Correntista correntista) {
        correntistas.add(correntista);
    }

    public void removerCorrentista(Correntista correntista) {
        correntistas.remove(correntista);
    }

    public Correntista localizarCorrentistaPorCpf(String cpf) {
        for (Correntista c : correntistas) {
            if (c.getCpf().equals(cpf)) {
                return c;
            }
        }
        return null;
    }

    public List<Correntista> getCorrentistas() {
        return correntistas;
    }

    // Contas
    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    public void removerConta(Conta conta) {
        contas.remove(conta);
    }

    public Conta localizarContaPorId(int id) {
        for (Conta conta : contas) {
            if (conta.getId() == id) {
                return conta;
            }
        }
        return null;
    }

    public List<Conta> getContas() {
        return contas;
    }

    // correntistas no arquivo CSV
    private void carregarCorrentistas() {
        try {
            File arquivo = new File(new File("./correntistas.csv").getCanonicalPath());
            if (!arquivo.exists()) {
                return;
            }

            Scanner scanner = new Scanner(arquivo);
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine().trim();
                String[] partes = linha.split(";");

                if (partes.length < 3) continue;

                String cpf = partes[0];
                String nome = partes[1];
                String senha = partes[2];

                Correntista correntista = new Correntista(cpf, nome, senha);
                adicionarCorrentista(correntista);
            }
            scanner.close();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar correntistas: " + e.getMessage());
        }
    }

    // contas no arquivo CSV
    private void carregarContas() {
        try {
            File arquivo = new File(new File("./contas.csv").getCanonicalPath());
            if (!arquivo.exists()) {
                return;
            }

            Scanner scanner = new Scanner(arquivo);
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine().trim();
                String[] partes = linha.split(";");

                if (partes.length < 5) continue;

                int id = Integer.parseInt(partes[0]);
                String cpfTitular = partes[1];
                double saldo = Double.parseDouble(partes[2]);
                String dataAbertura = partes[3];

                Correntista titular = localizarCorrentistaPorCpf(cpfTitular);
                if (titular == null) continue;

                Conta conta = new ContaSimples(titular);
                conta.creditar(saldo);
                adicionarConta(conta);
            }
            scanner.close();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar contas: " + e.getMessage());
        }
    }


    public void salvarCorrentistas() {
        try {
            File arquivo = new File(new File("./correntistas.csv").getCanonicalPath());
            FileWriter writer = new FileWriter(arquivo);

            for (Correntista correntista : correntistas) {
                writer.write(correntista.getCpf() + ";" + correntista.getNome() + ";"
                        + correntista.getSenha() + "\n");
            }

            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar correntistas: " + e.getMessage());
        }
    }

    // salvar as contas no arquivo CSV
    public void salvarContas() {
        try {
            File arquivo = new File(new File("./contas.csv").getCanonicalPath());
            FileWriter writer = new FileWriter(arquivo);

            for (Conta conta : contas) {
                writer.write(conta.getId() + ";" + conta.getTitular().getCpf() + ";"
                        + conta.getSaldo() + ";" + conta.getDataAbertura() + "\n");
            }

            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar contas: " + e.getMessage());
        }
    }
}
