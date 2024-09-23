package console;

import modelo.Conta;
import modelo.Correntista;
import regras_negocio.FachadaAntiga;

public class Listar {
    public Listar(){
        try{

            System.out.println("\n---------listagem de correntistas-----");
            for(Correntista correntista : FachadaAntiga.listarCorrentistas())
                System.out.println(correntista);

            System.out.println("\n---------listagem de contas ----");
            for(Conta conta : FachadaAntiga.listarContas())
                System.out.println(conta);




        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void main (String[] args)
    {
        new Listar();
    }
}
