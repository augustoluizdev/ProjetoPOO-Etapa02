public class Atendimento {
    public int indiceConsulta;
    public String observacoes;
    public String diagnostico;
    public String[] procedimentos;
    public int totalProcedimentos;
    private Prontuario prontuario;

    // COMPOSICAO: o prontuario e criado dentro do atendimento e nao existe sem ele.
    public Atendimento(int indiceConsulta, String observacoes) {
        this.indiceConsulta = indiceConsulta;
        this.observacoes = observacoes;
        this.diagnostico = "";
        this.procedimentos = new String[10];
        this.totalProcedimentos = 0;
        this.prontuario = new Prontuario(observacoes, "");
    }

    public Atendimento(int indiceConsulta, String observacoes, String diagnostico) {
        this.indiceConsulta = indiceConsulta;
        this.observacoes = observacoes;
        this.diagnostico = diagnostico;
        this.procedimentos = new String[10];
        this.totalProcedimentos = 0;
        this.prontuario = new Prontuario(observacoes, diagnostico);
    }

    public Atendimento(int indiceConsulta, String observacoes, String diagnostico,
                       String[] procedimentos, int totalProcedimentos) {
        this.indiceConsulta = indiceConsulta;
        this.observacoes = observacoes;
        this.diagnostico = diagnostico;
        this.procedimentos = new String[10];
        this.totalProcedimentos = totalProcedimentos;
        this.prontuario = new Prontuario(observacoes, diagnostico);

        for (int i = 0; i < totalProcedimentos; i++) {
            this.procedimentos[i] = procedimentos[i];
            this.prontuario.adicionarProcedimento(procedimentos[i]);
        }
    }

    public Prontuario getProntuario() {
        return prontuario;
    }

    public void adicionarProcedimento(String procedimento) {
        if (totalProcedimentos < 10) {
            procedimentos[totalProcedimentos] = procedimento;
            totalProcedimentos++;
            prontuario.adicionarProcedimento(procedimento);
        }
    }

    public void adicionarProcedimento(String[] procs, int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            if (totalProcedimentos < 10) {
                procedimentos[totalProcedimentos] = procs[i];
                totalProcedimentos++;
                prontuario.adicionarProcedimento(procs[i]);
            }
        }
    }

    public void atualizarObservacoes(String observacoes) {
        this.observacoes = observacoes;
        prontuario.setObservacoes(observacoes);
    }

    public void atualizarDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
        prontuario.setDiagnostico(diagnostico);
    }

    public String exibirResumo() {
        return prontuario.exibirResumo();
    }
}
