public abstract class Pagamento {
    public int indiceConsulta;
    public double valorBase;
    public double valorFinal;
    public String tipoPagamento;
    public int parcelas;

    protected Pagamento(int indiceConsulta, String tipoPagamento) {
        this.indiceConsulta = indiceConsulta;
        this.tipoPagamento = tipoPagamento;
        this.valorBase = 0.0;
        this.valorFinal = 0.0;
        this.parcelas = 1;
    }

    protected Pagamento(int indiceConsulta, String tipoPagamento, int parcelas) {
        this(indiceConsulta, tipoPagamento);
        this.parcelas = parcelas;
    }

    public void calcularAtendimento(Profissional prof, Atendimento atd) {
        double valorAcumulado = prof.valorConsulta;

        if (atd.totalProcedimentos > 0) {
            valorAcumulado += (atd.totalProcedimentos * 50.0);
        }

        this.valorBase = valorAcumulado;
        this.valorFinal = calcularValorFinal();
    }

    public abstract double calcularValorFinal();

    public String exibirResumo() {
        double valorBaseArredondado = Math.round(valorBase * 100.0) / 100.0;
        double valorFinalArredondado = Math.round(valorFinal * 100.0) / 100.0;

        String resumo = "Consulta #" + indiceConsulta
                + " | Valor Base: R$" + valorBaseArredondado
                + " | Valor Final: R$" + valorFinalArredondado
                + " | Tipo: " + tipoPagamento;

        if (parcelas > 1) {
            double valorParcela = Math.round((valorFinal / parcelas) * 100.0) / 100.0;
            resumo = resumo + " | Parcelas: " + parcelas + " (R$" + valorParcela + " cada)";
        }

        return resumo;
    }
}
