package negocio.cartao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CartaoCredito {
    double valorFatura;
    int diaVencimento;
    int diaFechamento;
    double rendaMensal;
    double valorLimite;
    int idCartao;

    List<Compra> compras = new ArrayList<>();

    public CartaoCredito(int idCartao, double valorLimite, double rendaMensal, int diaFechamento, int diaVencimento) {
        this.idCartao = idCartao;
        this.valorLimite = valorLimite;
        this.rendaMensal = rendaMensal;
        this.diaFechamento = diaFechamento;
        this.diaVencimento = diaVencimento;
        valorFatura = 0;
    }

    public double getValorFatura() {
        return valorFatura;
    }

    public void setValorFatura(double valorFatura) {
        this.valorFatura = valorFatura;
    }

    public int getDiaVencimento() {
        return diaVencimento;
    }

    public void setDiaVencimento(int diaVencimento) {
        this.diaVencimento = diaVencimento;
    }

    public int getDiaFechamento() {
        return diaFechamento;
    }

    public void setDiaFechamento(int diaFechamento) {
        this.diaFechamento = diaFechamento;
    }

    public double getRendaMensal() {
        return rendaMensal;
    }

    public void setRendaMensal(double rendaMensal) {
        this.rendaMensal = rendaMensal;
    }

    public double getValorLimite() {
        return valorLimite;
    }

    public void setValorLimite(double valorLimite) {
        this.valorLimite = valorLimite;
    }

    public int getIdCartao() {
        return idCartao;
    }

    public void setIdCartao(int idCartao) {
        this.idCartao = idCartao;
    }

    public Boolean adicionarCompra(int id, String descricao, double valorCompra) {
        if (valorCompra + valorFatura > valorLimite)
            return false;

        if (this.idCadastradoEmCompras(id))
            return false;

        compras.add(new Compra(id, descricao, valorCompra));
        valorFatura += valorCompra;
        return true;

    }

    private Boolean idCadastradoEmCompras(int id) {
        for (Compra compra : compras) {
            if (compra.id == id)
                return true;
        }
        return false;
    }

    public Boolean removerCompra(int id) {
        if (idCadastradoEmCompras(id) == false)
            return false;

        Iterator<Compra> comprasIterator = compras.iterator();
        while (comprasIterator.hasNext()) {
            Compra compra = comprasIterator.next();
            if (compra.id == id) {
                valorFatura -= compra.valor;
                comprasIterator.remove();
            }
        }
        return true;
    }

}
