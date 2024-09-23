import regras_negocio.FachadaAntiga;

public class Main {
    public static void main(String[] args) {
        try {

            FachadaAntiga.criarCorrentista("12345678900", "Pedro", "1234");
            FachadaAntiga.criarCorrentista("09876543211", "Venilson", "5678");

            System.out.println("Correntistas:");
            FachadaAntiga.listarCorrentistas().forEach(c ->
                    System.out.println("CPF: " + c.getCpf() + ", Nome: " + c.getNome())
            );

            FachadaAntiga.criarConta("12345678900");
            FachadaAntiga.criarContaEspecial("09876543211", 100);

            System.out.println("\nContas:");
            FachadaAntiga.listarContas().forEach(c ->
                    System.out.println("ID: " + c.getId() + ", Titular: " + c.getTitular().getNome()
                            + ", Saldo: " + c.getSaldo())
            );


            FachadaAntiga.creditarValor(1, "12345678900", "1234", 500);
            FachadaAntiga.creditarValor(2, "09876543211", "5678", 200);

            FachadaAntiga.debitarValor(1, "12345678900", "1234", 200);
            FachadaAntiga.debitarValor(2, "09876543211", "5678", 150);


            FachadaAntiga.transferirValor(1, "12345678900", "1234", 100, 2);

            System.out.println("\nSaldos após operações:");
            FachadaAntiga.listarContas().forEach(c ->
                    System.out.println("ID: " + c.getId() + ", Titular: " + c.getTitular().getNome()
                            + ", Saldo: " + c.getSaldo())
            );

            FachadaAntiga.removerCorrentistaConta(1, "09876543211");

            FachadaAntiga.apagarConta(1);

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

}