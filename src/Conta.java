import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Conta {
    private static int contador = 1;
    private final int id;
    private double saldo;
    private Correntista titular;
    private List<Correntista> correntistas;
    private LocalDate dataAbertura;

    // Criei um contador pra servir como id único pra cada conta.
    // Por isso o this.id recebe contador++
    public Conta(Correntista titular) {
        this.id = contador++;
        this.saldo = 0.0;
        this.titular = titular;
        this.correntistas = new ArrayList<>();
        this.dataAbertura = LocalDate.now();
    }


    public void creditar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor de crédito deve ser maior que zero.");
        }
        this.saldo += valor;
    }

    public abstract void debitar(double valor) throws Exception;

    public void removerCorrentista(Correntista correntista) {
        if (!correntistas.remove(correntista)) {
            throw new IllegalArgumentException("Correntista não encontrado");
        }
    }

    public void adicionarCorrentista(final Correntista correntista) {
        correntistas.add(correntista);
    }

    public int getId() {
        return id;
    }

    public double getSaldo() {
        return saldo;
    }

    public Correntista getTitular() {
        return titular;
    }

    public void setTitular(final Correntista titular) {
        this.titular = titular;
    }

    public List<Correntista> getCorrentistas() {
        return this.correntistas;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(final LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    @Override
    public String toString() {
        return "Conta ID: " + id + ", Saldo: " + saldo + ", Titular: " + titular.getNome() + ", Data de Abertura: " + dataAbertura;
    }
}
