public class Prontuario {
    private String observacoes;
    private String diagnostico;
    private String[] procedimentos;
    private int totalProcedimentos;
    private String dataRegistro;

    // COMPOSICAO: construtor package-private para reforcar criacao apenas via Atendimento.
    Prontuario(String observacoes, String diagnostico) {
        this.observacoes = observacoes;
        this.diagnostico = diagnostico;
        this.procedimentos = new String[10];
        this.totalProcedimentos = 0;
        this.dataRegistro = "16/06/2026";
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getDataRegistro() {
        return dataRegistro;
    }

    public void adicionarProcedimento(String procedimento) {
        if (totalProcedimentos < procedimentos.length) {
            procedimentos[totalProcedimentos] = procedimento;
            totalProcedimentos++;
        }
    }

    public String exibirResumo() {
        String resumo = "Observacoes: " + observacoes;

        if (!diagnostico.equals("")) {
            resumo = resumo + "\nDiagnostico: " + diagnostico;
        }

        if (totalProcedimentos > 0) {
            resumo = resumo + "\nProcedimentos: ";
            for (int i = 0; i < totalProcedimentos; i++) {
                resumo = resumo + procedimentos[i];
                if (i < totalProcedimentos - 1) {
                    resumo = resumo + ", ";
                }
            }
        }

        return resumo + "\nData do prontuario: " + dataRegistro;
    }
}
