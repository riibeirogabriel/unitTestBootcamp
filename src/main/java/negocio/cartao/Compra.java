package negocio.cartao;

public class Compra {
    public int id;
    public String descricao;
    public double valor;

    Compra(int id, String descricao, double valor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
    }
}
