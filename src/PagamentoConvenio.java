public class PagamentoConvenio extends Pagamento {
    public double percentualCobertura;

    public PagamentoConvenio(int indiceConsulta, double percentualCobertura) {
        super(indiceConsulta, "Convenio");
        this.percentualCobertura = percentualCobertura;
    }

    @Override
    public double calcularValorFinal() {
        double desconto = valorBase * percentualCobertura / 100.0;
        double valorCalculado = valorBase - desconto;

        if (valorCalculado < 0) {
            return 0.0;
        }

        return valorCalculado;
    }

    @Override
    public String exibirResumo() {
        return super.exibirResumo() + " | Cobertura: " + percentualCobertura + "%";
    }
}
