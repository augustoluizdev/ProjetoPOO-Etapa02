public abstract class Pessoa {

    private String nome;
    private String cpf;
    private String telefone;
    private String dataNascimento;

    public Pessoa(String nome, String cpf, String telefone, String dataNascimento) {
        setNome(nome);
        setCpf(cpf);
        setTelefone(telefone);
        setDataNascimento(dataNascimento);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            this.nome = "Nome nao informado";
        } else {
            this.nome = nome.trim();
        }
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            this.cpf = "CPF nao informado";
        } else {
            this.cpf = cpf.trim();
        }
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone == null ? "" : telefone.trim();
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento == null ? "" : dataNascimento.trim();
    }


    public abstract void exibirResumo();
}
