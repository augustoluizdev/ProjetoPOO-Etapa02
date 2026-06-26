public class HorarioDisponivel {

    private String diaSemana;
    private String turno;

    public HorarioDisponivel(String diaSemana, String turno) {
        setDiaSemana(diaSemana);
        setTurno(turno);
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        if (diaSemana == null || diaSemana.trim().isEmpty()) {
            this.diaSemana = "indefinido";
        } else {
            this.diaSemana = diaSemana.trim().toLowerCase();
        }
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        if (turno == null || turno.trim().isEmpty()) {
            this.turno = "integral";
        } else {
            this.turno = turno.trim().toLowerCase();
        }
    }

    public String exibirResumo() {
        return diaSemana + " (" + turno + ")";
    }
}
