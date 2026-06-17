import java.util.ArrayList;
import java.util.HashMap;

public class ClinicaServico {

    public Paciente buscarPacientePorCpf(HashMap<String, Paciente> pacientesPorCpf,
                                         String cpf)
            throws PacienteNaoEncontradoException {

        if (!pacientesPorCpf.containsKey(cpf)) {
            throw new PacienteNaoEncontradoException(
                    "Paciente nao encontrado para o CPF informado."
            );
        }

        return pacientesPorCpf.get(cpf);
    }

    public Profissional buscarProfissionalPorNome(HashMap<String, Profissional> profissionaisPorNome,
                                                  String nome)
            throws ProfissionalNaoEncontradoException {

        if (!profissionaisPorNome.containsKey(nome)) {
            throw new ProfissionalNaoEncontradoException(
                    "Profissional nao encontrado para o nome informado."
            );
        }

        return profissionaisPorNome.get(nome);
    }

    public Consulta agendarConsulta(HashMap<String, Paciente> pacientesPorCpf,
                                    HashMap<String, Profissional> profissionaisPorNome,
                                    ArrayList<Consulta> consultas,
                                    String cpfPaciente,
                                    String nomeProfissional,
                                    String data,
                                    String horario,
                                    String tipo,
                                    String diaSemana)
            throws PacienteNaoEncontradoException, ProfissionalNaoEncontradoException,
            PacienteInativoException, HorarioIndisponivelException {

        Paciente paciente = buscarPacientePorCpf(pacientesPorCpf, cpfPaciente);
        if (!paciente.isAtivo()) {
            throw new PacienteInativoException(
                    "Nao e possivel agendar consulta para paciente inativo."
            );
        }

        Profissional profissional = buscarProfissionalPorNome(profissionaisPorNome, nomeProfissional);
        if (!profissional.atendeNoDia(diaSemana)) {
            throw new HorarioIndisponivelException(
                    "O profissional nao atende no dia informado."
            );
        }

        for (Consulta consultaExistente : consultas) {
            if (consultaExistente.nomeProfissional.equals(nomeProfissional)
                    && consultaExistente.data.equals(data)
                    && consultaExistente.horario.equals(horario)
                    && !consultaExistente.status.equals("cancelada")) {
                throw new HorarioIndisponivelException(
                        "O horario informado ja esta ocupado."
                );
            }
        }

        Consulta consulta = new Consulta(cpfPaciente, nomeProfissional, data, horario, tipo);
        consultas.add(consulta);
        return consulta;
    }

    public Consulta buscarConsulta(ArrayList<Consulta> consultas,
                                   String cpfPaciente,
                                   String data,
                                   String horario)
            throws ConsultaNaoEncontradaException {

        for (Consulta consulta : consultas) {
            if (consulta.cpfPaciente.equals(cpfPaciente)
                    && consulta.data.equals(data)
                    && consulta.horario.equals(horario)) {
                return consulta;
            }
        }

        throw new ConsultaNaoEncontradaException(
                "Consulta nao encontrada para CPF, data e horario informados."
        );
    }

    public void cancelarConsulta(Consulta consulta) throws OperacaoInvalidaException {
        if (consulta.status.equals("realizada")) {
            throw new OperacaoInvalidaException(
                    "Nao e possivel cancelar uma consulta ja realizada."
            );
        }

        if (consulta.status.equals("cancelada")) {
            throw new OperacaoInvalidaException(
                    "A consulta informada ja esta cancelada."
            );
        }

        consulta.cancelar();
    }

    public Pagamento registrarPagamento(int indiceConsulta,
                                        double valorFinal,
                                        String tipoPagamento,
                                        int parcelas)
            throws PagamentoInvalidoException {

        if (valorFinal < 0) {
            throw new PagamentoInvalidoException(
                    "O valor do pagamento nao pode ser negativo."
            );
        }

        if (!tipoPagamento.equals("pix")
                && !tipoPagamento.equals("dinheiro")
                && !tipoPagamento.equals("cartao")
                && !tipoPagamento.equals("convenio")) {
            throw new PagamentoInvalidoException(
                    "Tipo de pagamento invalido: " + tipoPagamento
            );
        }

        if (tipoPagamento.equals("cartao") && (parcelas < 1 || parcelas > 6)) {
            throw new PagamentoInvalidoException(
                    "Pagamento em cartao aceita de 1 ate 6 parcelas."
            );
        }

        return new Pagamento(indiceConsulta, valorFinal, tipoPagamento, parcelas);
    }
}
