import java.util.ArrayList;

public class Convenio {

    private String nome;
    private double percentualCobertura;
    private ArrayList<String> especialidadesCobertas;

    public Convenio(String nome, double percentualCobertura) {
        setNome(nome);
        setPercentualCobertura(percentualCobertura);
        this.especialidadesCobertas = new ArrayList<String>();
    }

    public Convenio(String nome, double percentualCobertura, ArrayList<String> especialidadesCobertas) {
        this(nome, percentualCobertura);
        for (String especialidade : especialidadesCobertas) {
            adicionarEspecialidadeCoberta(especialidade);
        }
    }

    public static Convenio criarPadrao(String nome) {
        ArrayList<String> coberturas = new ArrayList<String>();
        double percentual = 0;

        if (nome == null) {
            return new Convenio("", 0);
        }

        String normalizado = nome.trim().toLowerCase();
        if (normalizado.equals("saudeplus")) {
            percentual = 0.40;
            coberturas.add("clinica geral");
            coberturas.add("fisioterapia");
        } else if (normalizado.equals("vidamais")) {
            percentual = 0.30;
            coberturas.add("psicologia");
            coberturas.add("nutricao");
        } else if (normalizado.equals("bemestar")) {
            percentual = 0.50;
            coberturas.add("clinica geral");
            coberturas.add("fisioterapia");
            coberturas.add("psicologia");
            coberturas.add("nutricao");
        }

        return new Convenio(nome.trim(), percentual, coberturas);
    }

    public boolean cobreEspecialidade(String especialidade) {
        for (String coberta : especialidadesCobertas) {
            if (coberta.equalsIgnoreCase(especialidade)) {
                return true;
            }
        }
        return false;
    }

    public void adicionarEspecialidadeCoberta(String especialidade) {
        if (especialidade != null && !especialidade.trim().isEmpty()) {
            especialidadesCobertas.add(especialidade.trim().toLowerCase());
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            this.nome = "";
        } else {
            this.nome = nome.trim();
        }
    }

    public double getPercentualCobertura() {
        return percentualCobertura;
    }

    public void setPercentualCobertura(double percentualCobertura) {
        if (percentualCobertura < 0 || percentualCobertura > 1) {
            this.percentualCobertura = 0;
        } else {
            this.percentualCobertura = percentualCobertura;
        }
    }

    public ArrayList<String> getEspecialidadesCobertas() {
        return new ArrayList<String>(especialidadesCobertas);
    }
}
