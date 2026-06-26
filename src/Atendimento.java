import java.util.ArrayList;

public class Atendimento implements Exportavel {
    private int indiceConsulta;
    private Prontuario prontuario;

    public Atendimento(int indiceConsulta, String observacoes) {
        this(indiceConsulta, observacoes, "");
    }

    public Atendimento(int indiceConsulta, String observacoes, String diagnostico) {
        this.indiceConsulta = indiceConsulta;
        this.prontuario = new Prontuario(observacoes, diagnostico);
    }

    public Atendimento(int indiceConsulta, String observacoes, String diagnostico,
                       ArrayList<String> procedimentos) {
        this(indiceConsulta, observacoes, diagnostico);
        for (String procedimento : procedimentos) {
            this.prontuario.adicionarProcedimento(procedimento);
        }
    }

    public void adicionarProcedimento(String procedimento) {
        prontuario.adicionarProcedimento(procedimento);
    }

    public void adicionarProcedimento(ArrayList<String> procs) {
        prontuario.adicionarProcedimentos(procs);
    }

    public String exibirResumo() {
        return prontuario.exibirResumo();
    }

    @Override
    public String exportarDados() {
        return "ATENDIMENTO;" + indiceConsulta + ";" + prontuario.getObservacoes()
                + ";" + prontuario.getDiagnostico() + ";" + prontuario.getDataRegistro();
    }

    public int getIndiceConsulta() {
        return indiceConsulta;
    }

    public Prontuario getProntuario() {
        return prontuario;
    }

    public String getDiagnostico() {
        return prontuario.getDiagnostico();
    }
}
