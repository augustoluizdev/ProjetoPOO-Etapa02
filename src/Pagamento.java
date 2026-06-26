public abstract class Pagamento implements Exportavel {

    private int indiceConsulta;
    protected double valorBase;
    private String tipoPagamento;

    public Pagamento(int indiceConsulta, double valorBase) {
        this(indiceConsulta, valorBase, "desconhecido");
    }

    public Pagamento(int indiceConsulta, double valorBase, String tipoPagamento) {
        setIndiceConsulta(indiceConsulta);
        setValorBase(valorBase);
        setTipoPagamento(tipoPagamento);
    }

    public abstract double calcularValorFinal();

    public String exibirResumo() {
        double valorFinal = Math.round(calcularValorFinal() * 100.0) / 100.0;
        return "Consulta #" + indiceConsulta
                + " | Tipo: " + tipoPagamento
                + " | Valor base: R$" + valorBase
                + " | Valor final: R$" + valorFinal;
    }

    @Override
    public String exportarDados() {
        return "PAGAMENTO;" + indiceConsulta + ";" + tipoPagamento + ";"
                + valorBase + ";" + calcularValorFinal();
    }

    public int getIndiceConsulta() {
        return indiceConsulta;
    }

    public void setIndiceConsulta(int indiceConsulta) {
        this.indiceConsulta = indiceConsulta;
    }

    public double getValorBase() {
        return valorBase;
    }

    public void setValorBase(double valorBase) {
        this.valorBase = valorBase < 0 ? 0 : valorBase;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento == null || tipoPagamento.trim().isEmpty()
                ? "desconhecido"
                : tipoPagamento.trim();
    }

    public static double calcularValor(double valorBase) {
        return valorBase;
    }

    public static double calcularValor(double valorBase, double percentualDesconto) {
        double desconto = valorBase * percentualDesconto / 100;
        double valor = valorBase - desconto;
        return valor < 0 ? 0 : valor;
    }

    public static double calcularValor(double valorBase, double percentualDesconto, double multa) {
        double desconto = valorBase * percentualDesconto / 100;
        double valor = valorBase - desconto + multa;
        return valor < 0 ? 0 : valor;
    }
}
