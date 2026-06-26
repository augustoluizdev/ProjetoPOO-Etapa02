
public class PagamentoConvenio extends Pagamento {

    private String nomeConvenio;
    private double percentualCobertura;

    public PagamentoConvenio(int indiceConsulta, double valorBase, String nomeConvenio) {
        super(indiceConsulta, valorBase, "convenio");
        this.nomeConvenio = nomeConvenio;
        this.percentualCobertura = resolverPercentual(nomeConvenio);
    }

    public PagamentoConvenio(int indiceConsulta, double valorBase, Convenio convenio) {
        super(indiceConsulta, valorBase, "convenio");
        if (convenio == null) {
            this.nomeConvenio = "";
            this.percentualCobertura = 0;
        } else {
            this.nomeConvenio = convenio.getNome();
            this.percentualCobertura = convenio.getPercentualCobertura();
        }
    }

    public PagamentoConvenio(int indiceConsulta, double valorBase,
                              String nomeConvenio, double percentualCobertura) {
        super(indiceConsulta, valorBase, "convenio");
        this.nomeConvenio = nomeConvenio;
        this.percentualCobertura = percentualCobertura;
    }

    private double resolverPercentual(String convenio) {
        if (convenio == null) return 0;
        switch (convenio.toLowerCase()) {
            case "saudeplus": return 0.40;
            case "vidamais":  return 0.30;
            case "bemestar":  return 0.50;
            default:          return 0;
        }
    }

    @Override
    public double calcularValorFinal() {
        double cobertura = valorBase * percentualCobertura;
        double valorRestante = valorBase - cobertura;
        return valorRestante < 0 ? 0 : valorRestante;
    }

    @Override
    public String exibirResumo() {
        double cobertura = Math.round(valorBase * percentualCobertura * 100.0) / 100.0;
        double valorFinal = Math.round(calcularValorFinal() * 100.0) / 100.0;
        return super.exibirResumo()
                + " | Convenio: " + nomeConvenio
                + " | Cobertura: " + (int)(percentualCobertura * 100) + "% (R$" + cobertura + ")"
                + " | Paciente paga: R$" + valorFinal;
    }

    public String getNomeConvenio() { return nomeConvenio; }
    public double getPercentualCobertura() { return percentualCobertura; }
}
