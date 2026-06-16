public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DA CLÍNICA - ETAPAS 5, 6 e 7 ===\n");

        // 1. Criando o Paciente (Base do Victor)
        Paciente paciente = new Paciente("Victor Manoel", "123.456.789-00", 20, "83999999999", "Unimed");
        paciente.exibirResumo();
        System.out.println("--------------------------------------------");

        // 2. ETAPA 5: Instanciando e testando as suas especialidades de Profissional
        Medico medico = new Medico("Dr. Vinícius Mendes", "clinica geral", "CRM-PB 12345", 250.00, "12345");
        Nutricionista nutri = new Nutricionista("Dra. Maria Clara", "nutricao", "CRN-PB 6789", 180.00, "6789");

        // Disparando o método abstrato implementado por você
        medico.registrarEspecifico();
        nutri.registrarEspecifico();
        System.out.println("--------------------------------------------");

        // Exibindo o resumo dos profissionais
        medico.exibirResumo();
        nutri.exibirResumo();
        System.out.println("--------------------------------------------");

        // 3. Simulando uma Consulta Agendada (Corrigido para usar o getter getCpf())
        Consulta consulta = new Consulta(paciente.getCpf(), medico.getNome(), "15/06/2026", "14:00", "inicial");
        System.out.println(consulta.exibirResumo());
        
        // Mudando o status para realizada no atendimento
        consulta.realizar();
        System.out.println("--------------------------------------------");

        // 4. Simulando o Atendimento com procedimentos adicionais
        int indiceDaConsulta = 0;
        Atendimento atendimento = new Atendimento(indiceDaConsulta, "Paciente relata cansaço e dores de cabeça.", "Enxaqueca leve");
        atendimento.adicionarProcedimento("Exame de Reflexo");
        atendimento.adicionarProcedimento("Aferição de Pressão");
        
        System.out.println("=== RESUMO DO ATENDIMENTO ===");
        System.out.println(atendimento.exibirResumo());
        System.out.println("--------------------------------------------");

        // 5. ETAPA 8: Processamento de pagamentos baseado em polimorfismo
        Pagamento pagamento = new PagamentoCartao(indiceDaConsulta, 2);
        pagamento.calcularAtendimento(medico, atendimento);
        
        System.out.println("=== RESUMO DO PAGAMENTO ===");
        System.out.println(pagamento.exibirResumo());
        System.out.println("--------------------------------------------");

        // 6. ETAPA 7: Consolidando tudo e gerando os relatórios na tela
        Consulta[] listaConsultas = { consulta };
        Atendimento[] listaAtendimentos = { atendimento };
        Pagamento[] listaPagamentos = { pagamento };
        double[] listaMultas = { 0.0 }; // Sem cancelamentos, sem multas

        // Gerando Relatório Geral de Atendimentos
        Relatorio.gerarRelatorio(listaConsultas, 1, listaAtendimentos, 1);
        
        // Gerando o Resumo Financeiro Consolidado da clínica
        Relatorio.gerarResumoFinanceiro(listaConsultas, 1, listaPagamentos, 1, listaMultas, 0);
    }
}
