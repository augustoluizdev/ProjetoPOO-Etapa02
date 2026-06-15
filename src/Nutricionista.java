public class Nutricionista extends Profissional {
    private String crn;

    // Construtor repassando os dados para a superclasse
    public Nutricionista(String nome, String especialidade, String registroProfissional, double valorConsulta, String crn) {
        super(nome, especialidade, registroProfissional, valorConsulta);
        this.crn = crn;
    }

    // Getter e Setter
    public String getCrn() {
        return this.crn;
    }

    public void setCrn(String crn) {
        this.crn = crn;
    }

    // Implementação obrigatória adaptada para a Nutrição
    @Override
    public void registrarEspecifico() {
        System.out.println("Verificando registro profissional no CRN...");
        System.out.println("Nutricionista " + getNome() + " ativado(a) no sistema! CRN: " + this.crn);
    }
}
