public class Paciente extends Pessoa {

    public int idade;
    public String convenioNome;
    public boolean ativo;


    // construtor básico
    public Paciente(String nome, String cpf) {

        super(nome, cpf, "", "");

        this.idade = 0;
        this.convenioNome = "";
        this.ativo = true;

    }


    // construtor com idade e telefone
    public Paciente(String nome, String cpf, int idade, String telefone) {

        super(nome, cpf, telefone, "");

        this.idade = idade;
        this.convenioNome = "";
        this.ativo = true;

    }


    // construtor completo
    public Paciente(String nome, String cpf, int idade, String telefone, String convenioNome) {

        super(nome, cpf, telefone, "");

        this.idade = idade;
        this.convenioNome = convenioNome;
        this.ativo = true;

    }


    // atualiza idade e telefone
    public void complementar(int idade, String telefone) {

        this.idade = idade;
        setTelefone(telefone);

    }


    // atualiza idade, telefone e convenio
    public void complementar(int idade, String telefone, String convenioNome) {

        this.idade = idade;
        setTelefone(telefone);
        this.convenioNome = convenioNome;

    }


    public void desativar() {

        this.ativo = false;

    }


    @Override
    public void exibirResumo() {

        String status = "Sim";

        if (!ativo) {
            status = "Nao";
        }


        System.out.println(
            "Nome: " + getNome() +
            " | CPF: " + getCpf() +
            " | Idade: " + idade +
            " | Tel: " + getTelefone() +
            " | Convenio: " + convenioNome +
            " | Ativo: " + status
        );

    }

}