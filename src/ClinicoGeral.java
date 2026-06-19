public class ClinicoGeral extends Profissional {

    private String encaminhamento;

    // SOBRECARGA de construtores
    public ClinicoGeral(String nome) {
        super(nome, "clinica geral");
        this.encaminhamento = "";
    }

    public ClinicoGeral(String nome, String registro, double valor, String encaminhamento) {
        super(nome, "clinica geral", registro, valor);
        this.encaminhamento = encaminhamento;
    }

    // SOBRESCRITA
    @Override
    public void exibirResumo() {
        System.out.println("[ClinicoGeral] " + getNome()
                + " | Reg: " + registroProfissional
                + " | Valor: R$" + valorConsulta
                + " | Encaminhamento: " + encaminhamento);
    }

    @Override
    public void registrarEspecifico() {
        System.out.println("[ClinicoGeral] Encaminhamento para: " + encaminhamento);
    }

    public String getEncaminhamento() { return encaminhamento; }
    public void setEncaminhamento(String e) { this.encaminhamento = e; }
}