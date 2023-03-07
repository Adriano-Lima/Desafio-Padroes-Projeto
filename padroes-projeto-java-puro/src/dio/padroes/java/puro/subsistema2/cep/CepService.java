package dio.padroes.java.puro.subsistema2.cep;

public class CepService {
    private static CepService instancia = new CepService();

    private CepService() {
        super();
    }

    public static CepService getInstancia() {
        return instancia;
    }

    public String recuperarCidade(String cep){
        return "Espinosa";
    }

    public String recuperarEstado(String cep){
        return "Minas Gerais";
    }





}
