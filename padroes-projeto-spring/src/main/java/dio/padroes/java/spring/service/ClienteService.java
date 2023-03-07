package dio.padroes.java.spring.service;

import dio.padroes.java.spring.model.Cliente;

/**
 * Interface que define o padrão <b>Strategy</b> no domínio de cliente. Com
 * isso, se necessário, podemos ter multiplas implementações dessa mesma
 * interface.

 */
public interface ClienteService {

    Iterable<Cliente> buscarTodos();

    Iterable<Cliente> buscarTodosCep(String cep);

    Cliente buscarPorId(Long id);

    Cliente buscarPorNome(String nome);

    void inserir(Cliente cliente);

    void atualizar(Long id, Cliente cliente);

    void deletar(Long id);

}
