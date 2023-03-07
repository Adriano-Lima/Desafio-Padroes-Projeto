package dio.padroes.java.puro.facade;

import dio.padroes.java.puro.subsistema1.crm.CrmService;
import dio.padroes.java.puro.subsistema2.cep.CepService;

/**
 * Criacao de uma interface mais amigavel, mais simples para uso
 **/
public class Facade {
    public void migrarCliente(String nome, String cep) {
        String cidade = CepService.getInstancia().recuperarCidade(cep);
        String estado = CepService.getInstancia().recuperarEstado(cep);

        CrmService.gravarCliente(nome, cep, cidade, estado);
    }
}
