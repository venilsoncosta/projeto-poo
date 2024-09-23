package console;

import regras_negocio.Fachada;

public class Apagar {
    public Apagar(){
        try{
            Fachada.apagarConta(1);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
