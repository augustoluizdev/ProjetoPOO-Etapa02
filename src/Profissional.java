import java.util.ArrayList;

public abstract class Profissional extends Pessoa {

    public String especialidade;
    public String registroProfissional;
    public double valorConsulta;
    public ArrayList<String> diasDisponiveis;


    public Profissional(String nome, String especialidade) {

        super(nome, "", "", "");

        this.especialidade = especialidade;
        this.registroProfissional = "";
        this.valorConsulta = 0;
        this.diasDisponiveis = new ArrayList<String>();

    }


    public Profissional(String nome, String especialidade,
                        String registroProfissional, double valorConsulta) {

        super(nome, "", "", "");

        this.especialidade = especialidade;
        this.registroProfissional = registroProfissional;
        this.valorConsulta = valorConsulta;
        this.diasDisponiveis = new ArrayList<String>();

    }


    public Profissional(String nome, String especialidade,
                        String registroProfissional,
                        double valorConsulta,
                        ArrayList<String> dias) {

        super(nome, "", "", "");

        this.especialidade = especialidade;
        this.registroProfissional = registroProfissional;
        this.valorConsulta = valorConsulta;
        this.diasDisponiveis = new ArrayList<String>();

        for (String dia : dias) {

            this.diasDisponiveis.add(dia);
        }

    }


    public void atualizar(String registro, double valor) {

        this.registroProfissional = registro;
        this.valorConsulta = valor;

    }


    public void atualizar(String registro, double valor,
                          ArrayList<String> dias) {

        this.registroProfissional = registro;
        this.valorConsulta = valor;

        this.diasDisponiveis.clear();

        for (String dia : dias) {

            this.diasDisponiveis.add(dia);
        }

    }


    public boolean atendeNoDia(String dia) {

        for (String diaDisponivel : diasDisponiveis) {

            if (diaDisponivel.equals(dia)) {

                return true;

            }

        }

        return false;

    }


    public static boolean especialidadeValida(String esp) {

        if (esp.equals("clinica geral")) return true;
        if (esp.equals("fisioterapia")) return true;
        if (esp.equals("psicologia")) return true;
        if (esp.equals("nutricao")) return true;

        return false;

    }


    @Override
    public void exibirResumo() {

        String dias = "";

        for (int i = 0; i < diasDisponiveis.size(); i++) {

            if (i > 0) {
                dias += ", ";
            }

            dias += diasDisponiveis.get(i);

        }


        System.out.println(
            "Nome: " + getNome() +
            " | Espec: " + especialidade +
            " | Reg: " + registroProfissional +
            " | Valor: R$" + valorConsulta +
            " | Dias: " + dias
        );

    }


    public abstract void registrarEspecifico();

}

