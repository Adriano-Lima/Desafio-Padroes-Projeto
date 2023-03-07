package dio.padroes.java.spring.service.impl;

import java.util.*;

import dio.padroes.java.spring.model.Cliente;
import dio.padroes.java.spring.model.ClienteRepository;
import dio.padroes.java.spring.model.Endereco;
import dio.padroes.java.spring.model.EnderecoRepository;
import dio.padroes.java.spring.service.ClienteService;
import dio.padroes.java.spring.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Implementação da <b>Strategy</b> {@link ClienteService}, a qual pode ser
 * injetada pelo Spring (via {@link Autowired}). Com isso, como essa classe é um
 * {@link Service}, ela será tratada como um <b>Singleton</b>.
 *
 */
@Service
public class ClienteServiceImpl implements ClienteService {

    // Singleton: Injetar os componentes do Spring com @Autowired.
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

    // Strategy: Implementar os métodos definidos na interface.
    // Facade: Abstrair integrações com subsistemas, provendo uma interface simples.

    @Override
    public Iterable<Cliente> buscarTodos() {
        // Buscar todos os Clientes.
        return clienteRepository.findAll();
    }

    @Override
    public Iterable<Cliente> buscarTodosCep(String cep) {
        // Buscar todos os Clientes que moram no mesmo cep
        Iterable<Cliente> todosClientes = clienteRepository.findAll();
        Iterator<Cliente> iterator = todosClientes.iterator();
        List<Cliente> clientesMesmoCep = new ArrayList<>();

        while (iterator.hasNext()) {
            Cliente cliente = iterator.next();
            if(cliente.getEndereco().getCep().equals(cep)) {
                clientesMesmoCep.add(cliente);
            }
        }
        return clientesMesmoCep;
    }

    @Override
    public Cliente buscarPorId(Long id) {
        // Buscar Cliente por ID.
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public Cliente buscarPorNome(String nome) {
        // Buscar Cliente por ID.
        Iterable<Cliente> todosClientes = clienteRepository.findAll();
        Iterator<Cliente> iterator = todosClientes.iterator();

        while (iterator.hasNext()) {
            Cliente cliente = iterator.next();
            if(cliente.getNome().equals(nome)) {
               return cliente;
            }
        }

        return new Cliente();
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarClienteComCep(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        // Buscar Cliente por ID, caso exista:
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if (clienteBd.isPresent()) {
            salvarClienteComCep(cliente);
        }
    }

    @Override
    public void deletar(Long id) {
        // Deletar Cliente por ID.
        clienteRepository.deleteById(id);
    }

    private void salvarClienteComCep(Cliente cliente) {
        // Verificar se o Endereco do Cliente já existe (pelo CEP).
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            // Caso não exista, integrar com o ViaCEP e persistir o retorno.
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        // Inserir Cliente, vinculando o Endereco (novo ou existente).
        clienteRepository.save(cliente);
    }

}
