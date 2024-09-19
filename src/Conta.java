import java.util.ArrayList;
import java.util.Date;

public abstract class Conta {
    private static int contador = 1;
    private int id;
    private double saldo;
    private Correntista titular;
    private ArrayList<Correntista> correntistas;
    private Date dataAbertura;

    public Conta(Correntista titular){
        this.id = contador++;
        this.saldo = 0.0;
        this.titular = titular;
        this.correntistas = new ArrayList<>();
        this.dataAbertura = new Date();
    }

    //até poderíamos fazer a verificação do valor ser maior que 0, mas o próprio professor disse que
    //essa parte não tem tanta relevancia, então não fiz pra evitar mais linhas de código
    public void creditar(double valor){
        this.saldo += valor;
    }

    //método que vai ser implementado nas subclasses
    public abstract void debitar(double valor) throws Exception;

    public void removerCorrentista(Correntista correntista) throws Exception{
        if(!correntistas.remove(correntista)){
            throw new Exception("Correntista não encontrado");
        }
    }

    public void adicionarCorrentista(Correntista correntista) {
        correntistas.add(correntista);
    }



    //**************getters e setters************
    //aqui pra baixo são só os getters e setters.
    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Conta.contador = contador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Correntista getTitular() {
        return titular;
    }

    public void setTitular(Correntista titular) {
        this.titular = titular;
    }

    public ArrayList<Correntista> getCorrentistas() {
        return correntistas;
    }

    public void setCorrentistas(ArrayList<Correntista> correntistas) {
        this.correntistas = correntistas;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }
}
