package negocio;

import negocio.cartao.CartaoCredito;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CartaoCreditoTest {
    double VARIACAO_PERMITIDA = 0.001;
    CartaoCredito cartaoCredito;
    int ID_COMPRA_01 = 34;
    double VALOR_COMPRA_01 = 344.56;
    String DESCRICAO_COMPRA_01 = "Americanas S.A.";

    int ID_COMPRA_02 = 12;
    String DESCRICAO_COMPRA_02 = "Cia Riachuello Varejo";

    @Before
    public void setUp() {
        int diaVencimento = 15;
        int diaFechamento = 8;
        double rendaMensal = 4800;
        double valorLimite = 3800.56;
        int idCartao = 9854;

        cartaoCredito = new CartaoCredito(idCartao, valorLimite, rendaMensal, diaFechamento, diaVencimento);
        cartaoCredito.adicionarCompra(ID_COMPRA_01, DESCRICAO_COMPRA_01, VALOR_COMPRA_01);


    }

    @After
    public void tearDown() {
    }

    // Convencao adotada para nomes dos testes : nomeDoMetodo_estadoSobreTeste_respostaEsperada()

    @Test
    public void testAdicionarCartaoDeCredito_criacaoDoObjeto_dadosPreenchidosDeAcordoComACriacao() {
        double valorfatura = 0;
        int diaVencimento = 15;
        int diaFechamento = 8;
        double rendaMensal = 3400;
        double valorLimite = 1800.56;
        int idCartao = 13443;

        CartaoCredito cartaoCredito = new CartaoCredito(idCartao, valorLimite, rendaMensal, diaFechamento, diaVencimento);

        assertEquals(idCartao, cartaoCredito.getIdCartao());
        assertEquals(valorfatura, cartaoCredito.getValorFatura(), VARIACAO_PERMITIDA);
        assertEquals(valorLimite, cartaoCredito.getValorLimite(), VARIACAO_PERMITIDA);
        assertEquals(rendaMensal, cartaoCredito.getRendaMensal(), VARIACAO_PERMITIDA);
        assertEquals(diaVencimento, cartaoCredito.getDiaVencimento());
        assertEquals(diaFechamento, cartaoCredito.getDiaFechamento());

    }

    @Test
    public void testAdicionarCompra_adicionarCompraAoCartao_faturaComValorAtualizadoCorretamente() {

        double valorFatura = cartaoCredito.getValorFatura();
        double valorCompra = 156.99;

        Boolean resultado = cartaoCredito.adicionarCompra(ID_COMPRA_02, DESCRICAO_COMPRA_02, valorCompra);

        assertTrue(resultado);
        assertEquals(valorCompra + valorFatura, cartaoCredito.getValorFatura(), VARIACAO_PERMITIDA);
    }

    @Test
    public void testAdicionarCompra_adicionarCompraAcimaDoLimiteAoCartao_adicaoInvalida() {

        double valorFatura = cartaoCredito.getValorFatura();
        double valorCompra = 4006.99;

        Boolean resultado = cartaoCredito.adicionarCompra(ID_COMPRA_02, DESCRICAO_COMPRA_02, valorCompra);

        assertFalse(resultado);
        assertEquals(valorFatura, cartaoCredito.getValorFatura(), VARIACAO_PERMITIDA);
    }

    @Test
    public void testRemoverCompra_removerCompraInexistenteAoCartao_remocaoInvalida() {

        double valorFatura = cartaoCredito.getValorFatura();
        double valorCompra = 4006.99;

        Boolean resultado = cartaoCredito.removerCompra(ID_COMPRA_02);

        assertFalse(resultado);
        assertEquals(valorFatura, cartaoCredito.getValorFatura(), VARIACAO_PERMITIDA);
    }

    @Test
    public void testRemoverCompra_removerCompraAdicionadoAoCartao_remocaoComSucesso() {

        double valorFatura = cartaoCredito.getValorFatura();

        Boolean resultado = cartaoCredito.removerCompra(ID_COMPRA_01);

        assertTrue(resultado);
        assertEquals(valorFatura - VALOR_COMPRA_01, cartaoCredito.getValorFatura(), VARIACAO_PERMITIDA);
    }

}
