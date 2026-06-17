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
        ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
        ClinicaServico clinicaServico = new ClinicaServico();

        HashSet<String> cpfsCadastrados = new HashSet<String>();

        HashMap<String, Paciente> pacientesPorCpf = new HashMap<String, Paciente>();
        HashMap<String, Profissional> profissionaisPorNome = new HashMap<String, Profissional>();

        String cpf = "123.456.789-00";
        if (!cpfsCadastrados.add(cpf)) {
            System.out.println("CPF ja cadastrado.");
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
        pessoas.add(paciente);

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
        pessoas.add(profissional);

        try {
            Consulta consulta = clinicaServico.agendarConsulta(
                    pacientesPorCpf,
                    profissionaisPorNome,
                    consultas,
                    paciente.getCpf(),
                    profissional.getNome(),
                    "16/06/2026",
                    "14:00",
                    "inicial",
                    "segunda"
            );
            System.out.println("\nConsulta agendada com sucesso:");
            System.out.println(consulta.exibirResumo());
        } catch (PacienteNaoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (ProfissionalNaoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (PacienteInativoException e) {
            System.out.println(e.getMessage());
        } catch (HorarioIndisponivelException e) {
            System.out.println(e.getMessage());
        }

        Atendimento atendimento = new Atendimento(0, "Paciente relatou melhora.", "Evolucao positiva");
        atendimento.adicionarProcedimento("Avaliacao inicial");
        atendimento.adicionarProcedimento("Orientacao de cuidados");
        atendimentos.add(atendimento);

        try {
            Pagamento pagamento = clinicaServico.registrarPagamento(
                    0,
                    Pagamento.calcularValor(profissional.valorConsulta),
                    "pix",
                    1
            );
            pagamentos.add(pagamento);
        } catch (PagamentoInvalidoException e) {
            System.out.println(e.getMessage());
        }
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

        System.out.println("\nDemonstracao de Dynamic Casting:");
        for (Pessoa pessoa : pessoas) {

            if (pessoa instanceof Paciente) {
                Paciente pacienteConvertido = (Paciente) pessoa;
                System.out.println("Convenio do paciente: " + pacienteConvertido.getConvenioNome());
            }

            if (pessoa instanceof Profissional) {
                Profissional profissionalConvertido = (Profissional) pessoa;
                System.out.println("Especialidade do profissional: " + profissionalConvertido.getEspecialidade());
            }
        }

        try {
            Paciente pacienteEncontrado = clinicaServico.buscarPacientePorCpf(pacientesPorCpf, cpf);
            System.out.println("\nBusca por CPF:");
            pacienteEncontrado.exibirResumo();
        } catch (PacienteNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }

        try {
            Profissional profissionalEncontrado = clinicaServico.buscarProfissionalPorNome(
                    profissionaisPorNome,
                    "Ana Costa"
            );
            System.out.println("\nBusca por nome do profissional:");
            profissionalEncontrado.exibirResumo();
        } catch (ProfissionalNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nValores do mapa de pacientes:");
        for (Paciente p : pacientesPorCpf.values()) {
            p.exibirResumo();
        }

        try {
            clinicaServico.agendarConsulta(
                    pacientesPorCpf,
                    profissionaisPorNome,
                    consultas,
                    paciente.getCpf(),
                    profissional.getNome(),
                    "16/06/2026",
                    "14:00",
                    "retorno",
                    "segunda"
            );
        } catch (PacienteNaoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (ProfissionalNaoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (PacienteInativoException e) {
            System.out.println(e.getMessage());
        } catch (HorarioIndisponivelException e) {
            System.out.println("\nExcecao de horario tratada:");
            System.out.println(e.getMessage());
        }

        paciente.desativar();
        try {
            clinicaServico.agendarConsulta(
                    pacientesPorCpf,
                    profissionaisPorNome,
                    consultas,
                    paciente.getCpf(),
                    profissional.getNome(),
                    "18/06/2026",
                    "15:00",
                    "retorno",
                    "quarta"
            );
        } catch (PacienteNaoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (ProfissionalNaoEncontradoException e) {
            System.out.println(e.getMessage());
        } catch (PacienteInativoException e) {
            System.out.println("\nExcecao de paciente inativo tratada:");
            System.out.println(e.getMessage());
        } catch (HorarioIndisponivelException e) {
            System.out.println(e.getMessage());
        }

        try {
            clinicaServico.buscarProfissionalPorNome(profissionaisPorNome, "Profissional Inexistente");
        } catch (ProfissionalNaoEncontradoException e) {
            System.out.println("\nExcecao de profissional nao encontrado tratada:");
            System.out.println(e.getMessage());
        }

        try {
            clinicaServico.buscarConsulta(consultas, paciente.getCpf(), "20/06/2026", "09:00");
        } catch (ConsultaNaoEncontradaException e) {
            System.out.println("\nExcecao de consulta nao encontrada tratada:");
            System.out.println(e.getMessage());
        }

        try {
            Consulta consultaRealizada = clinicaServico.buscarConsulta(
                    consultas,
                    paciente.getCpf(),
                    "16/06/2026",
                    "14:00"
            );
            consultaRealizada.realizar();
            clinicaServico.cancelarConsulta(consultaRealizada);
        } catch (ConsultaNaoEncontradaException e) {
            System.out.println(e.getMessage());
        } catch (OperacaoInvalidaException e) {
            System.out.println("\nExcecao de operacao invalida tratada:");
            System.out.println(e.getMessage());
        }

        try {
            clinicaServico.registrarPagamento(1, 50.0, "cheque", 1);
        } catch (PagamentoInvalidoException e) {
            System.out.println("\nExcecao de pagamento invalido tratada:");
            System.out.println(e.getMessage());
        }

        Relatorio.gerarRelatorio(consultas, atendimentos);
        Relatorio.gerarResumoFinanceiro(consultas, pagamentos, multas);
    }
}
