package console;
import regras_negocio.FachadaAntiga;

public class Cadastrar {
    public Cadastrar(){
        try{
            FachadaAntiga.criarCorrentista("09876596792", "Venilson Venilson", "1234");
            FachadaAntiga.criarCorrentista("09876599008", "Pedro Pedro", "mada");
            FachadaAntiga.criarCorrentista("09994499008", "Louise Louise", "blea");

            FachadaAntiga.criarConta("09876599008");



        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main (String[] args)
    {
        new Cadastrar();
    }
}
