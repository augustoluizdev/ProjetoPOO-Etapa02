public class PagamentoDinheiro extends Pagamento {

    public PagamentoDinheiro(int indiceConsulta) {
        super(indiceConsulta, "Dinheiro");
    }

    @Override
    public double calcularValorFinal() {
        return valorBase;
    }
}
