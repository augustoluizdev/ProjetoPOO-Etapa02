public class Fisioterapeuta extends Profissional {

    private int totalSessoesPrevistas;

    public Fisioterapeuta(String nome) {
        super(nome, "fisioterapia");
        this.totalSessoesPrevistas = 0;
    }

    public Fisioterapeuta(String nome, String registro, double valor, int totalSessoesPrevistas) {
        super(nome, "fisioterapia", registro, valor);
        this.totalSessoesPrevistas = totalSessoesPrevistas;
    }

    @Override
    public void exibirResumo() {
        System.out.println("[Fisioterapeuta] " + getNome()
                + formatarDadosProfissionais()
                + " | Sessoes previstas: " + totalSessoesPrevistas);
    }

    @Override
    public void registrarEspecifico() {
        System.out.println("[Fisioterapeuta] Sessoes previstas no plano: " + totalSessoesPrevistas);
    }

    @Override
    public void registrarEspecifico(Atendimento atendimento) {
        atendimento.adicionarProcedimento("Fisioterapia - sessoes previstas: " + totalSessoesPrevistas);
    }

    public int getTotalSessoesPrevistas() { return totalSessoesPrevistas; }
    public void setTotalSessoesPrevistas(int t) { this.totalSessoesPrevistas = t; }
}
