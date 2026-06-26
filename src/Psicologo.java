public class Psicologo extends Profissional {

    private String abordagem;

    public Psicologo(String nome) {
        super(nome, "psicologia");
        this.abordagem = "";
    }

    public Psicologo(String nome, String registro, double valor, String abordagem) {
        super(nome, "psicologia", registro, valor);
        this.abordagem = abordagem;
    }

    @Override
    public void exibirResumo() {
        System.out.println("[Psicologo] " + getNome()
                + formatarDadosProfissionais()
                + " | Abordagem: " + abordagem);
    }

    @Override
    public void registrarEspecifico() {
        System.out.println("[Psicologo] Abordagem terapeutica: " + abordagem);
    }

    @Override
    public void registrarEspecifico(Atendimento atendimento) {
        atendimento.adicionarProcedimento("Psicologia - abordagem terapeutica: " + abordagem);
    }

    public String getAbordagem() { return abordagem; }
    public void setAbordagem(String abordagem) { this.abordagem = abordagem; }
}
