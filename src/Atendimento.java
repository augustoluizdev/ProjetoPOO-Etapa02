import java.util.ArrayList;

public class Atendimento {
    public int indiceConsulta;
    public String observacoes;
    public String diagnostico;
    
    public ArrayList<String> procedimentos;

    public Atendimento(int indiceConsulta, String observacoes) {
        this.indiceConsulta = indiceConsulta;
        this.observacoes = observacoes;
        this.diagnostico = "";
        this.procedimentos = new ArrayList<String>();
    }

    public Atendimento(int indiceConsulta, String observacoes, String diagnostico) {
        this.indiceConsulta = indiceConsulta;
        this.observacoes = observacoes;
        this.diagnostico = diagnostico;
        this.procedimentos = new ArrayList<String>();
    }

    public Atendimento(int indiceConsulta, String observacoes, String diagnostico,
                       ArrayList<String> procedimentos) {
        this.indiceConsulta = indiceConsulta;
        this.observacoes = observacoes;
        this.diagnostico = diagnostico;
        this.procedimentos = new ArrayList<String>();
        for (String procedimento : procedimentos) {
            this.procedimentos.add(procedimento);
        }
    }

    public void adicionarProcedimento(String procedimento) {
        procedimentos.add(procedimento);
    }

    public void adicionarProcedimento(ArrayList<String> procs) {
        for (String procedimento : procs) {
            procedimentos.add(procedimento);
        }
    }

    public String exibirResumo() {
        String resumo = "Observacoes: " + observacoes;

        if (!diagnostico.equals("")) {
            resumo = resumo + "\nDiagnostico: " + diagnostico;
        }

        if (procedimentos.size() > 0) {
            resumo = resumo + "\nProcedimentos: ";
            for (int i = 0; i < procedimentos.size(); i++) {
                resumo = resumo + procedimentos.get(i);
                if (i < procedimentos.size() - 1) {
                    resumo = resumo + ", ";
                }
            }
        }
        return resumo;
    }
}
