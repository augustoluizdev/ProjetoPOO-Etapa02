public class ClinicoGeral extends Profissional {

    private String encaminhamento;

    public ClinicoGeral(String nome) {
        super(nome, "clinica geral");
        this.encaminhamento = "";
    }

    public ClinicoGeral(String nome, String registro, double valor, String encaminhamento) {
        super(nome, "clinica geral", registro, valor);
        this.encaminhamento = encaminhamento;
    }

    @Override
    public void exibirResumo() {
        System.out.println("[ClinicoGeral] " + getNome()
                + formatarDadosProfissionais()
                + " | Encaminhamento: " + encaminhamento);
    }

    @Override
    public void registrarEspecifico() {
        System.out.println("[ClinicoGeral] Encaminhamento para: " + encaminhamento);
    }

    @Override
    public void registrarEspecifico(Atendimento atendimento) {
        atendimento.adicionarProcedimento("Clinica geral - encaminhamento: " + encaminhamento);
    }

    public String getEncaminhamento() { return encaminhamento; }
    public void setEncaminhamento(String e) { this.encaminhamento = e; }
}
