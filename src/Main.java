public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DA CLINICA - ETAPAS 8 E 9 ===\n");

        // ASSOCIACAO: paciente e convenio existem de forma independente.
        Convenio convenio = new Convenio("Unimed", 35.0);
        convenio.adicionarEspecialidade("clinica geral");
        convenio.adicionarEspecialidade("nutricao");

        Paciente paciente = new Paciente("Victor Manoel", "123.456.789-00", 20, "83999999999", "Unimed");
        paciente.setConvenio(convenio);
        paciente.exibirResumo();
        System.out.println("Convenio associado: " + paciente.getConvenio().exibirResumo());
        System.out.println("--------------------------------------------");

        Medico medico = new Medico("Dr. Vinicius Mendes", "clinica geral", "CRM-PB 12345", 250.00, "12345");
        Nutricionista nutri = new Nutricionista("Dra. Maria Clara", "nutricao", "CRN-PB 6789", 180.00, "6789");

        // AGREGACAO: os horarios podem existir e ser reutilizados fora do profissional.
        HorarioDisponivel horarioManha = new HorarioDisponivel("Segunda", "Manha");
        HorarioDisponivel horarioTarde = new HorarioDisponivel("Quarta", "Tarde");
        medico.adicionarHorario(horarioManha);
        medico.adicionarHorario(horarioTarde);

        medico.registrarEspecifico();
        nutri.registrarEspecifico();
        System.out.println("--------------------------------------------");

        medico.exibirResumo();
        nutri.exibirResumo();
        System.out.println("--------------------------------------------");

        Consulta consulta = new Consulta(paciente.getCpf(), medico.getNome(), "15/06/2026", "14:00", "inicial");
        System.out.println(consulta.exibirResumo());

        consulta.realizar();
        System.out.println("--------------------------------------------");

        // COMPOSICAO: o prontuario nasce junto com o atendimento.
        int indiceDaConsulta = 0;
        Atendimento atendimento = new Atendimento(indiceDaConsulta,
                "Paciente relata cansaco e dores de cabeca.",
                "Enxaqueca leve");
        atendimento.adicionarProcedimento("Exame de Reflexo");
        atendimento.adicionarProcedimento("Afericao de Pressao");

        System.out.println("=== RESUMO DO ATENDIMENTO ===");
        System.out.println(atendimento.exibirResumo());
        System.out.println("Prontuario criado em: " + atendimento.getProntuario().getDataRegistro());
        System.out.println("--------------------------------------------");

        Pagamento pagamento = new PagamentoCartao(indiceDaConsulta, 2);
        pagamento.calcularAtendimento(medico, atendimento);

        System.out.println("=== RESUMO DO PAGAMENTO ===");
        System.out.println(pagamento.exibirResumo());
        System.out.println("--------------------------------------------");

        Consulta[] listaConsultas = { consulta };
        Atendimento[] listaAtendimentos = { atendimento };
        Pagamento[] listaPagamentos = { pagamento };
        double[] listaMultas = { 0.0 };

        Relatorio.gerarRelatorio(listaConsultas, 1, listaAtendimentos, 1);
        Relatorio.gerarResumoFinanceiro(listaConsultas, 1, listaPagamentos, 1, listaMultas, 0);
    }
}
