package negocio;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GerenciadoraContasTest {

    private GerenciadoraContas gerenciadoraContas;
    int idConta01, idConta02, qtdContasCadastradas;
    double VARIACAO_PERMITIDA = 0.001;

    @Before
    public void setUp() {
        idConta01 = 1;
        idConta02 = 2;
        ContaCorrente conta01 = new ContaCorrente(idConta01, 0, true);
        ContaCorrente conta02 = new ContaCorrente(idConta02, 0, true);

        List<ContaCorrente> contasDoBanco = new ArrayList<>();
        contasDoBanco.add(conta01);
        contasDoBanco.add(conta02);
        qtdContasCadastradas = contasDoBanco.size();
        gerenciadoraContas = new GerenciadoraContas(contasDoBanco);
    }

    @After
    public void tearDown() {
        gerenciadoraContas.removeConta(idConta01);
        gerenciadoraContas.removeConta(idConta02);

    }

    // Convencao adotada para nomes dos testes : nomeDoMetodo_estadoSobreTeste_respostaEsperada()

    @Test
    public void testContaAtivo_contaCadastradaAtiva_contaAtiva() {
        assertTrue(gerenciadoraContas.contaAtiva(idConta01));
    }

    @Test
    public void testContasDoBanco_validarQtdContasEncontradas_qtdDeContasRetornadasIgualAQtdCadastrada() {
        int qtdClientes = gerenciadoraContas.getContasDoBanco().size();

        assertEquals(qtdContasCadastradas, qtdClientes);

    }
    @Test
    public void testAdicionaConta_adicaoDeNovaConta_contaCadastradaComSucesso() {
        int idNovaConta = 3;
        int saldoNovaConta = 0;
        Boolean statusNovaConta = true;

        ContaCorrente novaConta = new ContaCorrente(idNovaConta, saldoNovaConta, statusNovaConta);
        gerenciadoraContas.adicionaConta(novaConta);

        assertEquals(idNovaConta, gerenciadoraContas.pesquisaConta(idNovaConta).getId());
        assertEquals(saldoNovaConta, gerenciadoraContas.pesquisaConta(idNovaConta).getSaldo(), VARIACAO_PERMITIDA);
        assertEquals(statusNovaConta, gerenciadoraContas.pesquisaConta(idNovaConta).isAtiva());

    }

    @Test
    public void testTransfereValor_transferencia100ComSaldo200_100Restantes() {
        gerenciadoraContas.pesquisaConta(idConta01).setSaldo(200);
        gerenciadoraContas.pesquisaConta(idConta02).setSaldo(0);

        boolean sucesso = gerenciadoraContas.transfereValor(idConta01, 100, idConta02);

        assertTrue(sucesso);
        assertEquals(100.0, gerenciadoraContas.pesquisaConta(idConta01).getSaldo(), VARIACAO_PERMITIDA);
        assertEquals(100.0, gerenciadoraContas.pesquisaConta(idConta02).getSaldo(), VARIACAO_PERMITIDA);
    }

    @Test
    public void testTransfereValor_transferencia200ComSaldo100_naoPermitido() {
        gerenciadoraContas.pesquisaConta(idConta01).setSaldo(100);
        gerenciadoraContas.pesquisaConta(idConta02).setSaldo(0);

        boolean sucesso = gerenciadoraContas.transfereValor(idConta01, 200, idConta02);

        assertFalse(sucesso);
        assertEquals(100, gerenciadoraContas.pesquisaConta(idConta01).getSaldo(), VARIACAO_PERMITIDA);
        assertEquals(0, gerenciadoraContas.pesquisaConta(idConta02).getSaldo(), VARIACAO_PERMITIDA);
    }

}
