import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {

        ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
        ArrayList<Profissional> profissionais = new ArrayList<Profissional>();
        ArrayList<Consulta> consultas = new ArrayList<Consulta>();
        ArrayList<Atendimento> atendimentos = new ArrayList<Atendimento>();
        ArrayList<Pagamento> pagamentos = new ArrayList<Pagamento>();
        ArrayList<Double> multas = new ArrayList<Double>();

        HashSet<String> cpfsCadastrados = new HashSet<String>();

        HashMap<String, Paciente> pacientesPorCpf = new HashMap<String, Paciente>();
        HashMap<String, Profissional> profissionaisPorNome = new HashMap<String, Profissional>();

        String cpf = "123.456.789-00";

        if (cpfsCadastrados.contains(cpf)) {
            System.out.println("CPF ja cadastrado.");
        }

        if (!cpfsCadastrados.add(cpf)) {
            System.out.println("CPF já cadastrado.");
        }

        if (!cpfsCadastrados.add(cpf)) {
            System.out.println("CPF já cadastrado.");
        }

        Paciente paciente = new Paciente(
                "Victor Manoel",
                cpf,
                20,
                "83999999999",
                "Unimed"
        );

        pacientes.add(paciente);
        pacientesPorCpf.put(paciente.getCpf(), paciente);

        Profissional profissional = new Profissional(
                "Ana Costa",
                "psicologia",
                "CRP-12345",
                150.00
        ) {
            @Override
            public void registrarEspecifico() {
                System.out.println("Registro especifico do profissional.");
            }
        };

        profissional.diasDisponiveis.add("segunda");
        profissional.diasDisponiveis.add("quarta");

        profissionais.add(profissional);
        profissionaisPorNome.put(profissional.getNome(), profissional);

        Consulta consulta = new Consulta(
                paciente.getCpf(),
                profissional.getNome(),
                "16/06/2026",
                "14:00",
                "inicial"
        );

        consultas.add(consulta);

        Atendimento atendimento = new Atendimento(0, "Paciente relatou melhora.", "Evolucao positiva");
        atendimento.adicionarProcedimento("Avaliacao inicial");
        atendimento.adicionarProcedimento("Orientacao de cuidados");

        atendimentos.add(atendimento);

        Pagamento pagamento = new Pagamento(0, Pagamento.calcularValor(profissional.valorConsulta), "pix");
        pagamentos.add(pagamento);
        multas.add(0.0);

        Paciente primeiroPaciente = pacientes.get(0);
        System.out.println("\nPrimeiro paciente cadastrado:");
        primeiroPaciente.exibirResumo();

        System.out.println("\nPacientes cadastrados:");
        for (Paciente p : pacientes) {
            p.exibirResumo();
        }

        System.out.println("\nProfissionais cadastrados:");
        for (Profissional prof : profissionais) {
            prof.exibirResumo();
        }

        try {
            Paciente pacienteEncontrado = buscarPacientePorCpf(pacientesPorCpf, cpf);
            System.out.println("\nBusca por CPF:");
            pacienteEncontrado.exibirResumo();
        } catch (PacienteNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }

        String nomeProfissional = "Ana Costa";
        if (profissionaisPorNome.containsKey(nomeProfissional)) {
            Profissional profissionalEncontrado = profissionaisPorNome.get(nomeProfissional);
            System.out.println("\nBusca por nome do profissional:");
            profissionalEncontrado.exibirResumo();
        }

        System.out.println("\nValores do mapa de pacientes:");
        for (Paciente p : pacientesPorCpf.values()) {
            p.exibirResumo();
        }

        Relatorio.gerarRelatorio(consultas, atendimentos);
        Relatorio.gerarResumoFinanceiro(consultas, pagamentos, multas);

    }

    public static Paciente buscarPacientePorCpf(HashMap<String, Paciente> pacientesPorCpf,
                                                String cpf)
            throws PacienteNaoEncontradoException {

        if (!pacientesPorCpf.containsKey(cpf)) {
            throw new PacienteNaoEncontradoException(
                    "Paciente não encontrado para o CPF informado."
            );
        }

        return pacientesPorCpf.get(cpf);
    }

}
