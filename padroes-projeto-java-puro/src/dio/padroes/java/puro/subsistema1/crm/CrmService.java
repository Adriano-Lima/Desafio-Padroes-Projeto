package dio.padroes.java.puro.subsistema1.crm;

public class CrmService {

    private CrmService() {
        super();
    }

    public static void gravarCliente(String nome, String cep, String cidade , String estado){
        System.out.println("Cliente salvo no sistema de CRM " + nome + " " + cep + " " + cidade + " " + estado);
    }
}
