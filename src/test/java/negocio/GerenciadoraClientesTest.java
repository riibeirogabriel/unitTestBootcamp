package negocio;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GerenciadoraClientesTest {

    private GerenciadoraClientes gerenciadoraClientes;
    private int idCLiente01 = 1;
    private int idCLiente02 = 2;
    private int idClienteIdadeInvalida01 = 3;
    private int idClienteIdadeInvalida02 = 4;
    private int qtdClientesCadastrados;

    @Before
    public void setUp() {
        Cliente cliente01 = new Cliente(idCLiente01, "Gustavo Farias", 31, "gugafarias@gmail.com", 1, true);
        Cliente cliente02 = new Cliente(idCLiente02, "Felipe Augusto", 34, "felipeaugusto@gmail.com", 1, true);
        Cliente cliente03 = new Cliente(idClienteIdadeInvalida01, "Felipe José", 68, "felipejose@gmail.com", 1, true);
        Cliente cliente04 = new Cliente(idClienteIdadeInvalida02, "Mario José", 15, "felipejose@gmail.com", 1, true);

        List<Cliente> clientesDoBanco = new ArrayList<>();
        clientesDoBanco.add(cliente01);
        clientesDoBanco.add(cliente02);
        clientesDoBanco.add(cliente03);
        clientesDoBanco.add(cliente04);

        qtdClientesCadastrados = clientesDoBanco.size();
        gerenciadoraClientes = new GerenciadoraClientes(clientesDoBanco);
    }

    @After
    public void tearDown() {
        gerenciadoraClientes.limpa();
    }

    // Convencao adotada para nomes dos testes : nomeDoMetodo_estadoSobreTeste_respostaEsperada()

    @Test
    public void testClienteAtivo_clienteCadastradoAtivo_clienteAtivo() {
        assertTrue(gerenciadoraClientes.clienteAtivo(idCLiente01));
    }

    @Test
    public void testAdicionaCliente_adicaoDeNovoCliente_clienteCadastradoComSucesso() {
        int idNovoCliente = 5;
        Cliente novoCLiente = new Cliente(idNovoCliente, "Gabriel Farias", 45, "gugafarias@outlook.com", 1, true);
        gerenciadoraClientes.adicionaCliente(novoCLiente);

        assertEquals(idNovoCliente, gerenciadoraClientes.pesquisaCliente(idNovoCliente).getId());

    }

    @Test
    public void testBuscaDeClientes_buscaDosClientesCadastrados_qtdDeClientesRetornadosIgualAQtdCadastrada() {
        int qtdClientes = gerenciadoraClientes.getClientesDoBanco().size();

        assertEquals(qtdClientesCadastrados, qtdClientes);

    }

    @Test
    public void testPesquisaCliente_pesquisaDeClienteCadastrado_IdIgualAoCadastrado() {
        Cliente cliente = gerenciadoraClientes.pesquisaCliente(idCLiente01);

        assertEquals(idCLiente01, cliente.getId());

    }

    @Test
    public void testPesquisaCliente_pesquisaDeClienteInexistente_null() {
        Cliente cliente = gerenciadoraClientes.pesquisaCliente(1001);

        assertNull(cliente);
    }

    @Test
    public void testRemoveCliente_remocaoDeClienteExistente_true() {

        boolean clienteRemovido = gerenciadoraClientes.removeCliente(idCLiente02);

        assertTrue(clienteRemovido);
        assertNull(gerenciadoraClientes.pesquisaCliente(idCLiente02));

    }

    @Test
    public void testRemoveCliente_remocaoDeClienteInexistente_false() {
        boolean clienteRemovido = gerenciadoraClientes.removeCliente(1001);

        assertFalse(clienteRemovido);

    }

    @Test
    public void testValidaIdade_clienteComIdadeAceitavel_true() {
        boolean idadeValida = gerenciadoraClientes.validaIdade(gerenciadoraClientes.pesquisaCliente(idCLiente01).getIdade());

        assertTrue(idadeValida);
    }


    @Test
    public void testValidaIdade_clienteComIdadeAcimaDaAceitavel_false() {

        boolean idadeValida = gerenciadoraClientes.validaIdade(gerenciadoraClientes.pesquisaCliente(this.idClienteIdadeInvalida01).getIdade());

        assertFalse(idadeValida);
    }

    @Test
    public void testValidaIdade_clienteComIdadeAbaixoDaAceitavel_false() {

        boolean idadeValida = gerenciadoraClientes.validaIdade(gerenciadoraClientes.pesquisaCliente(this.idClienteIdadeInvalida02).getIdade());

        assertFalse(idadeValida);
    }


}