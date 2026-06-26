import java.util.ArrayList;

public class Prontuario {

    private String observacoes;
    private String diagnostico;
    private ArrayList<String> procedimentos;
    private String dataRegistro;

    Prontuario(String observacoes, String diagnostico) {
        this.observacoes = observacoes;
        this.diagnostico = diagnostico;
        this.procedimentos = new ArrayList<String>();
        this.dataRegistro = java.time.LocalDate.now().toString();
    }

    public void adicionarProcedimento(String procedimento) {
        if (procedimento != null && !procedimento.trim().isEmpty()) {
            procedimentos.add(procedimento.trim());
        }
    }

    public void adicionarProcedimentos(ArrayList<String> novosProcedimentos) {
        for (String procedimento : novosProcedimentos) {
            adicionarProcedimento(procedimento);
        }
    }

    public String getObservacoes() {
        return observacoes;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public ArrayList<String> getProcedimentos() {
        return new ArrayList<String>(procedimentos);
    }

    public String getDataRegistro() {
        return dataRegistro;
    }

    public String exibirResumo() {
        String resumo = "Observacoes: " + observacoes;

        if (!diagnostico.equals("")) {
            resumo += "\nDiagnostico: " + diagnostico;
        }

        if (!procedimentos.isEmpty()) {
            resumo += "\nProcedimentos: ";
            for (int i = 0; i < procedimentos.size(); i++) {
                resumo += procedimentos.get(i);
                if (i < procedimentos.size() - 1) {
                    resumo += ", ";
                }
            }
        }

        resumo += "\nData do registro: " + dataRegistro;
        return resumo;
    }
}
