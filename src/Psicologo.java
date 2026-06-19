// Hierarquia de 3 niveis: Pessoa -> Profissional -> Psicologo
public class Psicologo extends Profissional {

    private String abordagem;

    // SOBRECARGA de construtores
    public Psicologo(String nome) {
        super(nome, "psicologia");
        this.abordagem = "";
    }

    public Psicologo(String nome, String registro, double valor, String abordagem) {
        super(nome, "psicologia", registro, valor);
        this.abordagem = abordagem;
    }

    // SOBRESCRITA
    @Override
    public void exibirResumo() {
        System.out.println("[Psicologo] " + getNome()
                + " | Reg: " + registroProfissional
                + " | Valor: R$" + valorConsulta
                + " | Abordagem: " + abordagem);
    }

    @Override
    public void registrarEspecifico() {
        System.out.println("[Psicologo] Abordagem terapeutica: " + abordagem);
    }

    public String getAbordagem() { return abordagem; }
    public void setAbordagem(String abordagem) { this.abordagem = abordagem; }
}