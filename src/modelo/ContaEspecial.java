package modelo;

public class ContaEspecial extends Conta {

    private double limite;

    public ContaEspecial(Correntista titular, double limite) throws Exception {
        super(titular);
        if(limite < 50){
            throw new Exception("O limite tem que ser maior ou igual a 50");
        }
        this.limite = limite;
    }

    @Override
    public void debitar(double valor) throws Exception {
        if (getSaldo() - valor < -limite) {
            throw new Exception("Saldo insuficiente, excedeu o limite da conta.");
        }
        creditar(-valor);
    }
}
