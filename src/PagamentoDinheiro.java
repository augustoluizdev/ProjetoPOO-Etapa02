public class PagamentoDinheiro extends Pagamento {

    private static final double DESCONTO = 0.05;

    public PagamentoDinheiro(int indiceConsulta, double valorBase) {
        super(indiceConsulta, valorBase, "dinheiro/pix");
    }

    public PagamentoDinheiro(int indiceConsulta, double valorBase, String subtipo) {
        super(indiceConsulta, valorBase, subtipo);
    }

    @Override
    public double calcularValorFinal() {
        double desconto = valorBase * DESCONTO;
        return valorBase - desconto;
    }

    @Override
    public String exibirResumo() {
        double valorFinal = Math.round(calcularValorFinal() * 100.0) / 100.0;
        return super.exibirResumo()
                + " | Desconto aplicado: 5%"
                + " | Voce paga: R$" + valorFinal;
    }
}