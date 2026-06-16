import java.util.ArrayList;

public class Relatorio {

    public static void gerarRelatorio(ArrayList<Consulta> consultas,
                                      ArrayList<Atendimento> atendimentos) {
        System.out.println("\n=== RELATORIO GERAL ===");
        for (int i = 0; i < consultas.size(); i++) {
            Consulta consulta = consultas.get(i);
            System.out.println(consulta.exibirResumo());
       
            String diag = buscarDiagnostico(i, atendimentos);
            if (!diag.equals("")) {
                System.out.println("  Diagnostico: " + diag);
            }
            System.out.println("---");
        }
    }

    public static void gerarRelatorio(ArrayList<Consulta> consultas,
                                      ArrayList<Atendimento> atendimentos,
                                      String nomeProfissional) {
        System.out.println("\n=== RELATORIO - " + nomeProfissional + " ===");
        boolean achou = false;
        for (int i = 0; i < consultas.size(); i++) {
            Consulta consulta = consultas.get(i);
            if (consulta.nomeProfissional.equals(nomeProfissional)) {
                System.out.println(consulta.exibirResumo());
                String diag = buscarDiagnostico(i, atendimentos);
                if (!diag.equals("")) {
                    System.out.println("  Diagnostico: " + diag);
                }
                System.out.println("---");
                achou = true;
            }
        }
        if (!achou) {
            System.out.println("Nenhuma consulta encontrada para esse profissional.");
        }
    }

    public static void gerarRelatorio(ArrayList<Consulta> consultas,
                                      ArrayList<Atendimento> atendimentos,
                                      String dataInicio, String dataFim) {
        System.out.println("\n=== RELATORIO - " + dataInicio + " a " + dataFim + " ===");
        for (int i = 0; i < consultas.size(); i++) {
            Consulta consulta = consultas.get(i);
            if (estaNoIntervalo(consulta.data, dataInicio, dataFim)) {
                System.out.println(consulta.exibirResumo());
                String diag = buscarDiagnostico(i, atendimentos);
                if (!diag.equals("")) {
                    System.out.println("  Diagnostico: " + diag);
                }
                System.out.println("---");
            }
        }
    }

    public static void gerarResumoFinanceiro(ArrayList<Consulta> consultas,
                                             ArrayList<Pagamento> pagamentos,
                                             ArrayList<Double> multas) {
        int realizadas = 0;
        int canceladas = 0;
        double totalFaturado = 0;
        double totalEmMultas = 0;

        for (Consulta consulta : consultas) {
            if (consulta.status.equals("realizada")) realizadas++;
            if (consulta.status.equals("cancelada")) canceladas++;
        }

        for (Pagamento pagamento : pagamentos) {
            totalFaturado = totalFaturado + pagamento.valorFinal;
        }

        for (Double multa : multas) {
            totalEmMultas = totalEmMultas + multa;
        }

        System.out.println("\n=== RESUMO FINANCEIRO ===");
        System.out.println("Atendimentos realizados: " + realizadas);
        System.out.println("Total faturado: R$" + Math.round(totalFaturado * 100.0) / 100.0);
        System.out.println("Cancelamentos: " + canceladas);
        System.out.println("Total em multas: R$" + Math.round(totalEmMultas * 100.0) / 100.0);
    }

    public static String buscarDiagnostico(int indiceConsulta, ArrayList<Atendimento> atendimentos) {
        for (Atendimento atendimento : atendimentos) {
            if (atendimento.indiceConsulta == indiceConsulta) {
                return atendimento.diagnostico;
            }
        }
        return "";
    }

    public static boolean estaNoIntervalo(String data, String inicio, String fim) {
        int valorData = converterDataParaNumero(data);
        int valorInicio = converterDataParaNumero(inicio);
        int valorFim = converterDataParaNumero(fim);
        return valorData >= valorInicio && valorData <= valorFim;
    }

    private static int converterDataParaNumero(String data) {
        int dia = Integer.parseInt(data.substring(0, 2));
        int mes = Integer.parseInt(data.substring(3, 5));
        int ano = Integer.parseInt(data.substring(6, 10));
        return ano * 10000 + mes * 100 + dia;
    }
}
