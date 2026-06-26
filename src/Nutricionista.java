public class Nutricionista extends Profissional {

    private String planoAlimentar;

    public Nutricionista(String nome) {
        super(nome, "nutricao");
        this.planoAlimentar = "";
    }

    public Nutricionista(String nome, String registro, double valor, String planoAlimentar) {
        super(nome, "nutricao", registro, valor);
        this.planoAlimentar = planoAlimentar;
    }

    @Override
    public void exibirResumo() {
        System.out.println("[Nutricionista] " + getNome()
                + formatarDadosProfissionais()
                + " | Plano alimentar: " + planoAlimentar);
    }

    @Override
    public void registrarEspecifico() {
        System.out.println("[Nutricionista] Plano alimentar: " + planoAlimentar);
    }

    @Override
    public void registrarEspecifico(Atendimento atendimento) {
        atendimento.adicionarProcedimento("Nutricao - plano alimentar: " + planoAlimentar);
    }

    public String getPlanoAlimentar() { return planoAlimentar; }
    public void setPlanoAlimentar(String plano) { this.planoAlimentar = plano; }
}
