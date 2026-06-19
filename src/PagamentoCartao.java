public class PagamentoCartao extends Pagamento {

    private int parcelas;
    private static final int MAX_PARCELAS = 6;
    private static final double TAXA_PARCELA_EXTRA = 0.025;

    // SOBRECARGA de construtores
    public PagamentoCartao(int indiceConsulta, double valorBase) {
        super(indiceConsulta, valorBase, "cartao");
        this.parcelas = 1;
    }

    public PagamentoCartao(int indiceConsulta, double valorBase, int parcelas) {
        super(indiceConsulta, valorBase, "cartao");
        this.parcelas = parcelas;
    }

    // SOBRESCRITA
    // LIGACAO DINAMICA: quando chamado via referencia Pagamento, executa ESTA implementacao
    @Override
    public double calcularValorFinal() {
        double valor = valorBase;
        if (parcelas > 3) {
            int parcelasExtras = parcelas - 3;
            double taxa = parcelasExtras * TAXA_PARCELA_EXTRA;
            valor = valor + (valor * taxa);
        }
        return valor;
    }

    @Override
    public String exibirResumo() {
        double valorFinal = Math.round(calcularValorFinal() * 100.0) / 100.0;
        double valorParcela = Math.round((valorFinal / parcelas) * 100.0) / 100.0;
        return super.exibirResumo()
                + " | Parcelas: " + parcelas + "x de R$" + valorParcela;
    }

    public int getParcelas() { return parcelas; }
    public int getMaxParcelas() { return MAX_PARCELAS; }
}
