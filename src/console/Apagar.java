package console;

import regras_negocio.FachadaAntiga;

public class Apagar {
    public Apagar(){
        try{
            FachadaAntiga.apagarConta(1);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
