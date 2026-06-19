public abstract class Pagamento {

    public int indiceConsulta;
    public double valorBase;
    public String tipoPagamento;

    // SOBRECARGA de construtores
    public Pagamento(int indiceConsulta, double valorBase) {
        this.indiceConsulta = indiceConsulta;
        this.valorBase = valorBase;
        this.tipoPagamento = "desconhecido";
    }

    public Pagamento(int indiceConsulta, double valorBase, String tipoPagamento) {
        this.indiceConsulta = indiceConsulta;
        this.valorBase = valorBase;
        this.tipoPagamento = tipoPagamento;
    }

    // Metodo abstrato: cada subclasse calcula o valor final de forma diferente
    // LIGACAO DINAMICA: o metodo executado depende do tipo REAL do objeto, nao do tipo da referencia
    public abstract double calcularValorFinal();

    // Metodo concreto compartilhado por todas as subclasses
    public String exibirResumo() {
        double valorFinal = Math.round(calcularValorFinal() * 100.0) / 100.0;
        return "Consulta #" + indiceConsulta
                + " | Tipo: " + tipoPagamento
                + " | Valor base: R$" + valorBase
                + " | Valor final: R$" + valorFinal;
    }

    // Metodos estaticos mantidos para nao quebrar codigo existente
    // SOBRECARGA: mesmo nome, parametros diferentes (resolvido em tempo de compilacao)
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