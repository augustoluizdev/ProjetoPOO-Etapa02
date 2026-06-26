import java.util.ArrayList;

public abstract class Profissional extends Pessoa {

    private String especialidade;
    private String registroProfissional;
    private double valorConsulta;
    private ArrayList<HorarioDisponivel> horariosDisponiveis;

    public Profissional(String nome, String especialidade) {

        super(nome, "", "", "");

        setEspecialidade(especialidade);
        setRegistroProfissional("");
        setValorConsulta(0);
        this.horariosDisponiveis = new ArrayList<HorarioDisponivel>();

    }

    public Profissional(String nome, String especialidade,
                        String registroProfissional, double valorConsulta) {

        super(nome, "", "", "");

        setEspecialidade(especialidade);
        setRegistroProfissional(registroProfissional);
        setValorConsulta(valorConsulta);
        this.horariosDisponiveis = new ArrayList<HorarioDisponivel>();

    }

    public Profissional(String nome, String especialidade,
                        String registroProfissional,
                        double valorConsulta,
                        ArrayList<String> dias) {

        super(nome, "", "", "");

        setEspecialidade(especialidade);
        setRegistroProfissional(registroProfissional);
        setValorConsulta(valorConsulta);
        this.horariosDisponiveis = new ArrayList<HorarioDisponivel>();

        for (String dia : dias) {
            adicionarHorarioDisponivel(new HorarioDisponivel(dia, "integral"));
        }

    }

    public void atualizar(String registro, double valor) {

        setRegistroProfissional(registro);
        setValorConsulta(valor);

    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        if (especialidade == null || especialidade.trim().isEmpty()) {
            this.especialidade = "nao informada";
        } else {
            this.especialidade = especialidade.trim().toLowerCase();
        }
    }

    public String getRegistroProfissional() {
        return registroProfissional;
    }

    public void setRegistroProfissional(String registroProfissional) {
        this.registroProfissional = registroProfissional == null ? "" : registroProfissional.trim();
    }

    public double getValorConsulta() {
        return valorConsulta;
    }

    public void setValorConsulta(double valorConsulta) {
        this.valorConsulta = valorConsulta < 0 ? 0 : valorConsulta;
    }

    public void atualizar(String registro, double valor,
                          ArrayList<String> dias) {

        setRegistroProfissional(registro);
        setValorConsulta(valor);

        this.horariosDisponiveis.clear();

        for (String dia : dias) {
            adicionarHorarioDisponivel(new HorarioDisponivel(dia, "integral"));
        }

    }

    public void adicionarDiaDisponivel(String dia) {
        adicionarHorarioDisponivel(new HorarioDisponivel(dia, "integral"));
    }

    public void adicionarHorarioDisponivel(HorarioDisponivel horario) {
        if (horario != null) {
            horariosDisponiveis.add(horario);
        }
    }

    public ArrayList<HorarioDisponivel> getHorariosDisponiveis() {
        return new ArrayList<HorarioDisponivel>(horariosDisponiveis);
    }

    public boolean atendeNoDia(String dia) {

        for (HorarioDisponivel horarioDisponivel : horariosDisponiveis) {

            if (horarioDisponivel.getDiaSemana().equalsIgnoreCase(dia)) {
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

        for (int i = 0; i < horariosDisponiveis.size(); i++) {

            if (i > 0) {
                dias += ", ";
            }

            dias += horariosDisponiveis.get(i).exibirResumo();

        }

        System.out.println(
            "Nome: " + getNome() +
            " | Espec: " + especialidade +
            " | Reg: " + registroProfissional +
            " | Valor: R$" + valorConsulta +
            " | Dias: " + dias
        );

    }

    protected String formatarDadosProfissionais() {
        return " | Reg: " + registroProfissional + " | Valor: R$" + valorConsulta;
    }

    public void registrarEspecifico() {
        System.out.println("Registro especifico nao informado.");
    }

    public abstract void registrarEspecifico(Atendimento atendimento);

}
