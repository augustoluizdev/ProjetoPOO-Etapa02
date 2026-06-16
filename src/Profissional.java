public abstract class Profissional extends Pessoa {

    public String especialidade;
    public String registroProfissional;
    public double valorConsulta;
    public String[] diasDisponiveis;
    private HorarioDisponivel[] horariosDisponiveis;
    private int totalHorarios;
    public int totalDias;


    public Profissional(String nome, String especialidade) {

        super(nome, "", "", "");

        this.especialidade = especialidade;
        this.registroProfissional = "";
        this.valorConsulta = 0;
        this.diasDisponiveis = new String[7];
        this.horariosDisponiveis = new HorarioDisponivel[10];
        this.totalHorarios = 0;
        this.totalDias = 0;

    }


    public Profissional(String nome, String especialidade,
                        String registroProfissional, double valorConsulta) {

        super(nome, "", "", "");

        this.especialidade = especialidade;
        this.registroProfissional = registroProfissional;
        this.valorConsulta = valorConsulta;
        this.diasDisponiveis = new String[7];
        this.horariosDisponiveis = new HorarioDisponivel[10];
        this.totalHorarios = 0;
        this.totalDias = 0;

    }


    public Profissional(String nome, String especialidade,
                        String registroProfissional,
                        double valorConsulta,
                        String[] dias,
                        int totalDias) {

        super(nome, "", "", "");

        this.especialidade = especialidade;
        this.registroProfissional = registroProfissional;
        this.valorConsulta = valorConsulta;
        this.diasDisponiveis = new String[7];
        this.horariosDisponiveis = new HorarioDisponivel[10];
        this.totalHorarios = 0;
        this.totalDias = totalDias;


        for (int i = 0; i < totalDias; i++) {

            this.diasDisponiveis[i] = dias[i];

        }

    }


    public void atualizar(String registro, double valor) {

        this.registroProfissional = registro;
        this.valorConsulta = valor;

    }


    public void atualizar(String registro, double valor,
                          String[] dias, int totalDias) {

        this.registroProfissional = registro;
        this.valorConsulta = valor;
        this.totalDias = totalDias;


        for (int i = 0; i < totalDias; i++) {

            this.diasDisponiveis[i] = dias[i];

        }

    }


    // AGREGACAO: o profissional usa horarios disponiveis, mas eles sobrevivem sem ele.
    public void adicionarHorario(HorarioDisponivel horario) {
        if (totalHorarios < horariosDisponiveis.length) {
            horariosDisponiveis[totalHorarios] = horario;
            totalHorarios++;
        }
    }


    public HorarioDisponivel[] getHorariosDisponiveis() {
        return horariosDisponiveis;
    }


    public int getTotalHorarios() {
        return totalHorarios;
    }


    public boolean atendeNoDia(String dia) {

        for (int i = 0; i < totalDias; i++) {

            if (diasDisponiveis[i].equals(dia)) {

                return true;

            }

        }

        for (int i = 0; i < totalHorarios; i++) {
            if (horariosDisponiveis[i].getDiaSemana().equals(dia)) {
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
        String horarios = "";

        for (int i = 0; i < totalDias; i++) {

            if (i > 0) {
                dias += ", ";
            }

            dias += diasDisponiveis[i];

        }

        for (int i = 0; i < totalHorarios; i++) {

            if (i > 0) {
                horarios += ", ";
            }

            horarios += horariosDisponiveis[i].exibirResumo();

        }


        System.out.println(
            "Nome: " + getNome() +
            " | Espec: " + especialidade +
            " | Reg: " + registroProfissional +
            " | Valor: R$" + valorConsulta +
            " | Dias: " + dias +
            " | Horarios: " + horarios
        );

    }


    public abstract void registrarEspecifico();

}
