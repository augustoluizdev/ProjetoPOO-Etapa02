public class Consulta implements Agendavel, Exportavel {
    private String cpfPaciente;
    private String nomeProfissional;
    private String data;
    private String horario;
    private String tipo;
    private String status;

    public Consulta(String cpfPaciente, String nomeProfissional, String data, String horario) {
        this(cpfPaciente, nomeProfissional, data, horario, "inicial", "agendada");
    }

    public Consulta(String cpfPaciente, String nomeProfissional, String data, String horario, String tipo) {
        this(cpfPaciente, nomeProfissional, data, horario, tipo, "agendada");
    }

    public Consulta(String cpfPaciente, String nomeProfissional, String data,
                    String horario, String tipo, String status) {
        setCpfPaciente(cpfPaciente);
        setNomeProfissional(nomeProfissional);
        setData(data);
        setHorario(horario);
        setTipo(tipo);
        setStatus(status);
    }

    @Override
    public void agendar() {
        this.status = "agendada";
    }

    @Override
    public void cancelar() throws OperacaoInvalidaException {
        if (status.equals("realizada")) {
            throw new OperacaoInvalidaException("Nao e possivel cancelar consulta realizada.");
        }
        if (status.equals("cancelada")) {
            throw new OperacaoInvalidaException("Consulta ja esta cancelada.");
        }
        this.status = "cancelada";
    }

    public String cancelar(String motivo) throws OperacaoInvalidaException {
        cancelar();
        return "Consulta cancelada. Motivo: " + motivo;
    }

    @Override
    public void remarcar(String novaData, String novoHorario) throws OperacaoInvalidaException {
        if (status.equals("realizada") || status.equals("cancelada")) {
            throw new OperacaoInvalidaException("Apenas consultas agendadas podem ser remarcadas.");
        }
        setData(novaData);
        setHorario(novoHorario);
        this.status = "remarcada";
    }

    public void remarcar() {
        this.status = "remarcada";
    }

    public void realizar() {
        this.status = "realizada";
    }

    public String exibirResumo() {
        return "Paciente(CPF): " + cpfPaciente + " | Prof: " + nomeProfissional
                + " | Data: " + data + " | Hora: " + horario
                + " | Tipo: " + tipo + " | Status: " + status;
    }

    @Override
    public String exportarDados() {
        return "CONSULTA;" + cpfPaciente + ";" + nomeProfissional + ";" + data
                + ";" + horario + ";" + tipo + ";" + status;
    }

    public String getCpfPaciente() { return cpfPaciente; }
    public void setCpfPaciente(String cpfPaciente) { this.cpfPaciente = cpfPaciente == null ? "" : cpfPaciente.trim(); }
    public String getNomeProfissional() { return nomeProfissional; }
    public void setNomeProfissional(String nomeProfissional) { this.nomeProfissional = nomeProfissional == null ? "" : nomeProfissional.trim(); }
    public String getData() { return data; }
    public void setData(String data) { this.data = data == null ? "" : data.trim(); }
    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario == null ? "" : horario.trim(); }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo == null || tipo.trim().isEmpty() ? "inicial" : tipo.trim(); }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status == null || status.trim().isEmpty() ? "agendada" : status.trim(); }
}
