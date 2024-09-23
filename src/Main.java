import regras_negocio.Fachada;

public class Main {
    public static void main(String[] args) {
        try {

            Fachada.criarCorrentista("12345678900", "Pedro", "1234");
            Fachada.criarCorrentista("09876543211", "Venilson", "5678");

            System.out.println("Correntistas:");
            Fachada.listarCorrentistas().forEach(c ->
                    System.out.println("CPF: " + c.getCpf() + ", Nome: " + c.getNome())
            );

            Fachada.criarConta("12345678900");
            Fachada.criarContaEspecial("09876543211", 100);

            System.out.println("\nContas:");
            Fachada.listarContas().forEach(c ->
                    System.out.println("ID: " + c.getId() + ", Titular: " + c.getTitular().getNome()
                            + ", Saldo: " + c.getSaldo())
            );


            Fachada.creditarValor(1, "12345678900", "1234", 500);
            Fachada.creditarValor(2, "09876543211", "5678", 200);

            Fachada.debitarValor(1, "12345678900", "1234", 200);
            Fachada.debitarValor(2, "09876543211", "5678", 150);


            Fachada.transferirValor(1, "12345678900", "1234", 100, 2);

            System.out.println("\nSaldos após operações:");
            Fachada.listarContas().forEach(c ->
                    System.out.println("ID: " + c.getId() + ", Titular: " + c.getTitular().getNome()
                            + ", Saldo: " + c.getSaldo())
            );

            Fachada.removerCorrentistaConta(1, "09876543211");

            Fachada.apagarConta(1);

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

}