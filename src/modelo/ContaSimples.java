package modelo;

public class ContaSimples extends Conta {


    public ContaSimples(Correntista titular) {
        super(titular);
    }

    @Override
    public void debitar(double valor) throws Exception {
        // Verifica se o saldo atual menos o valor do débito é menor que zero.
        // Se for, isso significa que não há saldo suficiente para realizar a operação.
        if (getSaldo() - valor < 0) {
            throw new Exception("Você não tem saldo suficiente."); // Lança uma exceção se o saldo for insuficiente.
        }
        // Se houver saldo suficiente, chama o método 'creditar' com o valor negativo,
        // diminuindo o saldo da conta pelo valor passado no parametro.
        creditar(-valor);
    }
}
