package console;
import regras_negocio.Fachada;

public class Cadastrar {
    public Cadastrar(){
        try{
            Fachada.criarCorrentista("09876596792", "Venilson Venilson", "1234");
            Fachada.criarCorrentista("09876599008", "Pedro Pedro", "madonna");
            Fachada.criarCorrentista("09994499008", "Louise Louise", "bleach");

            Fachada.criarConta("09876599008");



        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
