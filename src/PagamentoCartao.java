public class PagamentoCartao extends Pagamento {

    public PagamentoCartao(int indiceConsulta, int parcelas) {
        super(indiceConsulta, "Cartao", parcelas);
    }

    @Override
    public double calcularValorFinal() {
        return valorBase;
    }
}
