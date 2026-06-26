public class Paciente extends Pessoa {

    private int idade;
    private Convenio convenio;
    private boolean ativo;


    public Paciente(String nome, String cpf) {

        super(nome, cpf, "", "");

        setIdade(0);
        setConvenioNome("");
        this.ativo = true;

    }


    public Paciente(String nome, String cpf, int idade, String telefone) {

        super(nome, cpf, telefone, "");

        setIdade(idade);
        setConvenioNome("");
        this.ativo = true;

    }


    public Paciente(String nome, String cpf, int idade,
                    String telefone, String convenioNome) {

        super(nome, cpf, telefone, "");

        setIdade(idade);
        setConvenioNome(convenioNome);
        this.ativo = true;

    }


    public void complementar(int idade, String telefone) {

        setIdade(idade);
        setTelefone(telefone);

    }


    public void complementar(int idade, String telefone, String convenioNome) {

        setIdade(idade);
        setTelefone(telefone);
        setConvenioNome(convenioNome);

    }


    public void desativar() {

        this.ativo = false;

    }


    public int getIdade() {
        return idade;
    }


    public void setIdade(int idade) {
        if (idade < 0) {
            this.idade = 0;
        } else {
            this.idade = idade;
        }
    }


    public String getConvenioNome() {
        if (convenio == null) {
            return "";
        }
        return convenio.getNome();
    }


    public void setConvenioNome(String convenioNome) {
        this.convenio = Convenio.criarPadrao(convenioNome);
    }


    public Convenio getConvenio() {
        return convenio;
    }


    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }


    public boolean isAtivo() {
        return ativo;
    }


    @Override
    public void exibirResumo() {

        String status = ativo ? "Sim" : "Nao";


        System.out.println(
            "Paciente: " + getNome() +
            " | CPF: " + getCpf() +
            " | Idade: " + idade +
            " | Telefone: " + getTelefone() +
            " | Convenio: " + getConvenioNome() +
            " | Ativo: " + status
        );

    }

}
