public class Medico extends Profissional {
    private String crm;

    // Construtor que repassa os dados base para a classe pai (Profissional)
    public Medico(String nome, String especialidade, String registroProfissional, double valorConsulta, String crm) {
        super(nome, especialidade, registroProfissional, valorConsulta);
        this.crm = crm;
    }

    // Getter e Setter para o atributo específico
    public String getCrm() {
        return this.crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    // Implementação obrigatória do método abstrato criado pelo Victor
    @Override
    public void registrarEspecifico() {
        System.out.println("Validando credenciais médicas no CFM...");
        System.out.println("Médico(a) " + getNome() + " registrado com sucesso! CRM: " + this.crm);
    }
}
