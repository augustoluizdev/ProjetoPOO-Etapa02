public class Paciente extends Pessoa {

    private int idade;
    private String convenioNome;
    private Convenio convenio;
    private boolean ativo;


    public Paciente(String nome, String cpf) {

        super(nome, cpf, "", "");

        this.idade = 0;
        this.convenioNome = "";
        this.convenio = null;
        this.ativo = true;

    }


    public Paciente(String nome, String cpf, int idade, String telefone) {

        super(nome, cpf, telefone, "");

        this.idade = idade;
        this.convenioNome = "";
        this.convenio = null;
        this.ativo = true;

    }


    public Paciente(String nome, String cpf, int idade,
                    String telefone, String convenioNome) {

        super(nome, cpf, telefone, "");

        this.idade = idade;
        this.convenioNome = convenioNome;
        this.convenio = new Convenio(convenioNome, 0.0);
        this.ativo = true;

    }


    public void complementar(int idade, String telefone) {

        this.idade = idade;
        setTelefone(telefone);

    }


    public void complementar(int idade, String telefone, String convenioNome) {

        this.idade = idade;
        setTelefone(telefone);
        this.convenioNome = convenioNome;
        this.convenio = new Convenio(convenioNome, 0.0);

    }


    public void desativar() {

        this.ativo = false;

    }


    public int getIdade() {
        return idade;
    }


    public void setIdade(int idade) {
        this.idade = idade;
    }


    public String getConvenioNome() {
        return convenioNome;
    }


    public void setConvenioNome(String convenioNome) {
        this.convenioNome = convenioNome;
        this.convenio = new Convenio(convenioNome, 0.0);
    }


    // ASSOCIACAO: paciente conhece o convenio, mas os dois existem separadamente.
    public Convenio getConvenio() {
        return convenio;
    }


    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
        if (convenio != null) {
            this.convenioNome = convenio.getNome();
        } else {
            this.convenioNome = "";
        }
    }


    public boolean isAtivo() {
        return ativo;
    }


    @Override
    public void exibirResumo() {

        String status = ativo ? "Sim" : "Nao";
        String nomeConvenio = convenio != null ? convenio.getNome() : convenioNome;


        System.out.println(
            "Paciente: " + getNome() +
            " | CPF: " + getCpf() +
            " | Idade: " + idade +
            " | Telefone: " + getTelefone() +
            " | Convenio: " + nomeConvenio +
            " | Ativo: " + status
        );

    }

}
