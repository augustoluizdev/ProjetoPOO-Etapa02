// Hierarquia de 3 niveis: Pessoa -> Profissional -> Nutricionista
public class Nutricionista extends Profissional {

    private String planoAlimentar;

    // SOBRECARGA de construtores
    public Nutricionista(String nome) {
        super(nome, "nutricao");
        this.planoAlimentar = "";
    }

    public Nutricionista(String nome, String registro, double valor, String planoAlimentar) {
        super(nome, "nutricao", registro, valor);
        this.planoAlimentar = planoAlimentar;
    }

    // SOBRESCRITA
    @Override
    public void exibirResumo() {
        System.out.println("[Nutricionista] " + getNome()
                + " | Reg: " + registroProfissional
                + " | Valor: R$" + valorConsulta
                + " | Plano alimentar: " + planoAlimentar);
    }

    @Override
    public void registrarEspecifico() {
        System.out.println("[Nutricionista] Plano alimentar: " + planoAlimentar);
    }

    public String getPlanoAlimentar() { return planoAlimentar; }
    public void setPlanoAlimentar(String plano) { this.planoAlimentar = plano; }
}