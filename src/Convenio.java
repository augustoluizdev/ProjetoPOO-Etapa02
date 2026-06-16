public class Convenio {
    private String nome;
    private double percentualCobertura;
    private String[] especialidadesCobertas;
    private int totalEspecialidades;

    public Convenio(String nome, double percentualCobertura) {
        this.nome = nome;
        this.percentualCobertura = percentualCobertura;
        this.especialidadesCobertas = new String[10];
        this.totalEspecialidades = 0;
    }

    public Convenio(String nome, double percentualCobertura,
                    String[] especialidadesCobertas, int totalEspecialidades) {
        this(nome, percentualCobertura);
        this.totalEspecialidades = totalEspecialidades;

        for (int i = 0; i < totalEspecialidades; i++) {
            this.especialidadesCobertas[i] = especialidadesCobertas[i];
        }
    }

    public String getNome() {
        return nome;
    }

    public double getPercentualCobertura() {
        return percentualCobertura;
    }

    public void adicionarEspecialidade(String especialidade) {
        if (totalEspecialidades < especialidadesCobertas.length) {
            especialidadesCobertas[totalEspecialidades] = especialidade;
            totalEspecialidades++;
        }
    }

    public boolean cobreEspecialidade(String especialidade) {
        for (int i = 0; i < totalEspecialidades; i++) {
            if (especialidadesCobertas[i].equals(especialidade)) {
                return true;
            }
        }
        return false;
    }

    public String exibirResumo() {
        String resumo = nome + " | Cobertura: " + percentualCobertura + "%";

        if (totalEspecialidades > 0) {
            resumo = resumo + " | Especialidades: ";
            for (int i = 0; i < totalEspecialidades; i++) {
                resumo = resumo + especialidadesCobertas[i];
                if (i < totalEspecialidades - 1) {
                    resumo = resumo + ", ";
                }
            }
        }

        return resumo;
    }
}
