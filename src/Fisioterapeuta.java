// Hierarquia de 3 niveis: Pessoa -> Profissional -> Fisioterapeuta
public class Fisioterapeuta extends Profissional {

    private int totalSessoesPrevistas;

    // SOBRECARGA de construtores
    public Fisioterapeuta(String nome) {
        super(nome, "fisioterapia");
        this.totalSessoesPrevistas = 0;
    }

    public Fisioterapeuta(String nome, String registro, double valor, int totalSessoesPrevistas) {
        super(nome, "fisioterapia", registro, valor);
        this.totalSessoesPrevistas = totalSessoesPrevistas;
    }

    // SOBRESCRITA: classe filha redefine comportamento (resolvido em tempo de execucao)
    @Override
    public void exibirResumo() {
        System.out.println("[Fisioterapeuta] " + getNome()
                + " | Reg: " + registroProfissional
                + " | Valor: R$" + valorConsulta
                + " | Sessoes previstas: " + totalSessoesPrevistas);
    }

    @Override
    public void registrarEspecifico() {
        System.out.println("[Fisioterapeuta] Sessoes previstas no plano: " + totalSessoesPrevistas);
    }

    public int getTotalSessoesPrevistas() { return totalSessoesPrevistas; }
    public void setTotalSessoesPrevistas(int t) { this.totalSessoesPrevistas = t; }
}