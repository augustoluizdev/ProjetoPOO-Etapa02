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
            if (consultaExistente.getNomeProfissional().equals(nomeProfissional)
                    && consultaExistente.getData().equals(data)
                    && consultaExistente.getHorario().equals(horario)
                    && !consultaExistente.getStatus().equals("cancelada")) {
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
            if (consulta.getCpfPaciente().equals(cpfPaciente)
                    && consulta.getData().equals(data)
                    && consulta.getHorario().equals(horario)) {
                return consulta;
            }
        }

        throw new ConsultaNaoEncontradaException(
                "Consulta nao encontrada para CPF, data e horario informados."
        );
    }

    public void cancelarConsulta(Consulta consulta) throws OperacaoInvalidaException {
        if (consulta.getStatus().equals("realizada")) {
            throw new OperacaoInvalidaException(
                    "Nao e possivel cancelar uma consulta ja realizada."
            );
        }

        if (consulta.getStatus().equals("cancelada")) {
            throw new OperacaoInvalidaException(
                    "A consulta informada ja esta cancelada."
            );
        }

        consulta.cancelar();
    }

    public void remarcarConsulta(Consulta consulta,
                                 ArrayList<Consulta> consultas,
                                 String novaData,
                                 String novoHorario)
            throws OperacaoInvalidaException, HorarioIndisponivelException {

        for (Consulta consultaExistente : consultas) {
            if (consultaExistente != consulta
                    && consultaExistente.getNomeProfissional().equals(consulta.getNomeProfissional())
                    && consultaExistente.getData().equals(novaData)
                    && consultaExistente.getHorario().equals(novoHorario)
                    && !consultaExistente.getStatus().equals("cancelada")) {
                throw new HorarioIndisponivelException("Novo horario ja esta ocupado.");
            }
        }

        consulta.remarcar(novaData, novoHorario);
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

        if (tipoPagamento.equals("cartao")) {
            return new PagamentoCartao(indiceConsulta, valorFinal, parcelas);
        } else if (tipoPagamento.equals("convenio")) {
            return new PagamentoConvenio(indiceConsulta, valorFinal, "");
        } else {
            return new PagamentoDinheiro(indiceConsulta, valorFinal, tipoPagamento);
        }
    }

    public Pagamento registrarPagamentoConvenio(int indiceConsulta,
                                                double valorFinal,
                                                Paciente paciente,
                                                Profissional profissional)
            throws PagamentoInvalidoException, ConvenioNaoCobreException {

        if (valorFinal < 0) {
            throw new PagamentoInvalidoException("O valor do pagamento nao pode ser negativo.");
        }

        if (paciente == null || paciente.getConvenio() == null
                || paciente.getConvenioNome().trim().isEmpty()) {
            throw new ConvenioNaoCobreException("Paciente nao possui convenio cadastrado.");
        }

        if (!paciente.getConvenio().cobreEspecialidade(profissional.getEspecialidade())) {
            throw new ConvenioNaoCobreException(
                    "Convenio nao cobre a especialidade: " + profissional.getEspecialidade()
            );
        }

        return new PagamentoConvenio(indiceConsulta, valorFinal, paciente.getConvenio());
    }
}
