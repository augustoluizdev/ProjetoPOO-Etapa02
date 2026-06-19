import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    static ArrayList<Paciente> pacientes            = new ArrayList<Paciente>();
    static ArrayList<Profissional> profissionais    = new ArrayList<Profissional>();
    static ArrayList<Consulta> consultas            = new ArrayList<Consulta>();
    static ArrayList<Atendimento> atendimentos      = new ArrayList<Atendimento>();
    static ArrayList<Pagamento> pagamentos          = new ArrayList<Pagamento>();
    static ArrayList<Double> multas                 = new ArrayList<Double>();
    static ArrayList<Pessoa> pessoas                = new ArrayList<Pessoa>();

    // HashMap<String, Paciente>: busca por chave (CPF); mais eficiente que percorrer lista
    static HashMap<String, Paciente> pacientesPorCpf          = new HashMap<String, Paciente>();
    // HashMap<String, Profissional>: busca por chave (nome); mais eficiente que percorrer lista
    static HashMap<String, Profissional> profissionaisPorNome = new HashMap<String, Profissional>();
    // HashSet<String>: apenas verificacao de existencia; nao precisa de ordem
    static HashSet<String> cpfsCadastrados                    = new HashSet<String>();

    static ClinicaServico clinicaServico = new ClinicaServico();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== SISTEMA VIDA PLENA ===");

        int opcao = -1;
        do {
            exibirMenuPrincipal();
            // Etapa 12: try/catch em toda leitura de numero do usuario
            try {
                opcao = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Opcao invalida. Digite um numero.");
                opcao = -1;
            }

            switch (opcao) {
                case 1: menuPacientes(); break;
                case 2: menuProfissionais(); break;
                case 3: menuConsultas(); break;
                case 4: menuAtendimentos(); break;
                case 5: menuPagamentos(); break;
                case 6: menuRelatorios(); break;
                case 0: System.out.println("Encerrando. Ate logo!"); break;
                default:
                    if (opcao != -1) System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    // ===================== MENU PRINCIPAL =====================

    static void exibirMenuPrincipal() {
        System.out.println("\n--- MENU PRINCIPAL ---");
        System.out.println("1 - Pacientes");
        System.out.println("2 - Profissionais");
        System.out.println("3 - Consultas");
        System.out.println("4 - Atendimentos");
        System.out.println("5 - Pagamentos");
        System.out.println("6 - Relatorios");
        System.out.println("0 - Sair");
        System.out.print("Opcao: ");
    }

    // ===================== PACIENTES =====================

    static void menuPacientes() {
        System.out.println("\n--- PACIENTES ---");
        System.out.println("1 - Cadastrar paciente (minimo)");
        System.out.println("2 - Cadastrar paciente (completo)");
        System.out.println("3 - Desativar paciente");
        System.out.println("4 - Buscar por CPF");
        System.out.println("5 - Listar todos");
        System.out.print("Opcao: ");

        int op = lerInteiro();
        switch (op) {
            case 1: cadastrarPacienteMinimo(); break;
            case 2: cadastrarPacienteCompleto(); break;
            case 3: desativarPaciente(); break;
            case 4: buscarPaciente(); break;
            case 5: listarPacientes(); break;
            default: System.out.println("Opcao invalida.");
        }
    }

    static void cadastrarPacienteMinimo() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine().trim();

        if (!cpfsCadastrados.add(cpf)) {
            System.out.println("Erro: CPF ja cadastrado.");
            return;
        }

        Paciente paciente = new Paciente(nome, cpf);
        pacientes.add(paciente);
        pacientesPorCpf.put(cpf, paciente);
        pessoas.add(paciente);
        System.out.println("Paciente cadastrado com sucesso!");
    }

    static void cadastrarPacienteCompleto() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine().trim();

        if (!cpfsCadastrados.add(cpf)) {
            System.out.println("Erro: CPF ja cadastrado.");
            return;
        }

        // Etapa 12: try/catch para idade + finally com log
        int idade = 0;
        boolean idadeValida = false;
        while (!idadeValida) {
            System.out.print("Idade: ");
            try {
                idade = Integer.parseInt(scanner.nextLine().trim());
                if (idade < 0) {
                    System.out.println("Idade nao pode ser negativa.");
                } else {
                    idadeValida = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Idade invalida. Digite apenas numeros.");
            } finally {
                if (!idadeValida) {
                    System.out.println("[LOG] Entrada de idade invalida registrada.");
                }
            }
        }

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine().trim();
        System.out.print("Convenio (Enter para pular): ");
        String convenio = scanner.nextLine().trim();

        Paciente paciente = new Paciente(nome, cpf, idade, telefone, convenio);
        pacientes.add(paciente);
        pacientesPorCpf.put(cpf, paciente);
        pessoas.add(paciente);
        System.out.println("Paciente cadastrado com sucesso!");
    }

    static void desativarPaciente() {
        System.out.print("CPF: ");
        String cpf = scanner.nextLine().trim();
        try {
            Paciente p = clinicaServico.buscarPacientePorCpf(pacientesPorCpf, cpf);
            p.desativar();
            System.out.println("Paciente desativado.");
        } catch (PacienteNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    static void buscarPaciente() {
        System.out.print("CPF: ");
        String cpf = scanner.nextLine().trim();
        try {
            Paciente p = clinicaServico.buscarPacientePorCpf(pacientesPorCpf, cpf);
            p.exibirResumo();
        } catch (PacienteNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    static void listarPacientes() {
        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
            return;
        }
        for (Paciente p : pacientes) {
            p.exibirResumo();
        }
    }

    // ===================== PROFISSIONAIS =====================

    static void menuProfissionais() {
        System.out.println("\n--- PROFISSIONAIS ---");
        System.out.println("1 - Cadastrar fisioterapeuta");
        System.out.println("2 - Cadastrar psicologo");
        System.out.println("3 - Cadastrar nutricionista");
        System.out.println("4 - Cadastrar clinico geral");
        System.out.println("5 - Buscar por nome");
        System.out.println("6 - Listar todos");
        System.out.print("Opcao: ");

        int op = lerInteiro();
        switch (op) {
            case 1: cadastrarFisioterapeuta(); break;
            case 2: cadastrarPsicologo(); break;
            case 3: cadastrarNutricionista(); break;
            case 4: cadastrarClinicoGeral(); break;
            case 5: buscarProfissional(); break;
            case 6: listarProfissionais(); break;
            default: System.out.println("Opcao invalida.");
        }
    }

    static void cadastrarFisioterapeuta() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();
        System.out.print("Registro: ");
        String registro = scanner.nextLine().trim();
        double valor = lerDouble("Valor da consulta: ");
        System.out.print("Sessoes previstas: ");
        int sessoes = lerInteiro();

        Fisioterapeuta f = new Fisioterapeuta(nome, registro, valor, sessoes);
        adicionarDias(f);
        profissionais.add(f);
        profissionaisPorNome.put(f.getNome(), f);
        pessoas.add(f);
        System.out.println("Fisioterapeuta cadastrado!");
        f.exibirResumo();
    }

    static void cadastrarPsicologo() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();
        System.out.print("Registro: ");
        String registro = scanner.nextLine().trim();
        double valor = lerDouble("Valor da consulta: ");
        System.out.print("Abordagem terapeutica: ");
        String abordagem = scanner.nextLine().trim();

        Psicologo p = new Psicologo(nome, registro, valor, abordagem);
        adicionarDias(p);
        profissionais.add(p);
        profissionaisPorNome.put(p.getNome(), p);
        pessoas.add(p);
        System.out.println("Psicologo cadastrado!");
        p.exibirResumo();
    }

    static void cadastrarNutricionista() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();
        System.out.print("Registro: ");
        String registro = scanner.nextLine().trim();
        double valor = lerDouble("Valor da consulta: ");
        System.out.print("Plano alimentar: ");
        String plano = scanner.nextLine().trim();

        Nutricionista n = new Nutricionista(nome, registro, valor, plano);
        adicionarDias(n);
        profissionais.add(n);
        profissionaisPorNome.put(n.getNome(), n);
        pessoas.add(n);
        System.out.println("Nutricionista cadastrado!");
        n.exibirResumo();
    }

    static void cadastrarClinicoGeral() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();
        System.out.print("Registro: ");
        String registro = scanner.nextLine().trim();
        double valor = lerDouble("Valor da consulta: ");
        System.out.print("Encaminhamento padrao: ");
        String enc = scanner.nextLine().trim();

        ClinicoGeral c = new ClinicoGeral(nome, registro, valor, enc);
        adicionarDias(c);
        profissionais.add(c);
        profissionaisPorNome.put(c.getNome(), c);
        pessoas.add(c);
        System.out.println("Clinico Geral cadastrado!");
        c.exibirResumo();
    }

    static void adicionarDias(Profissional p) {
        System.out.println("Digite os dias de atendimento (ex: segunda). Digite 'fim' para encerrar.");
        while (true) {
            String dia = scanner.nextLine().trim().toLowerCase();
            if (dia.equals("fim")) break;
            if (!dia.isEmpty()) p.diasDisponiveis.add(dia);
        }
    }

    static void buscarProfissional() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();
        try {
            Profissional p = clinicaServico.buscarProfissionalPorNome(profissionaisPorNome, nome);
            p.exibirResumo();
        } catch (ProfissionalNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    static void listarProfissionais() {
        if (profissionais.isEmpty()) {
            System.out.println("Nenhum profissional cadastrado.");
            return;
        }
        for (Profissional p : profissionais) {
            p.exibirResumo();
        }
    }

    // ===================== CONSULTAS =====================

    static void menuConsultas() {
        System.out.println("\n--- CONSULTAS ---");
        System.out.println("1 - Agendar");
        System.out.println("2 - Cancelar");
        System.out.println("3 - Listar");
        System.out.print("Opcao: ");

        int op = lerInteiro();
        switch (op) {
            case 1: agendarConsulta(); break;
            case 2: cancelarConsulta(); break;
            case 3: listarConsultas(); break;
            default: System.out.println("Opcao invalida.");
        }
    }

    static void agendarConsulta() {
        System.out.print("CPF do paciente: ");
        String cpf = scanner.nextLine().trim();
        System.out.print("Nome do profissional: ");
        String nomeProfissional = scanner.nextLine().trim();
        System.out.print("Data (DD/MM/AAAA): ");
        String data = scanner.nextLine().trim();
        System.out.print("Horario (HH:MM): ");
        String horario = scanner.nextLine().trim();
        System.out.print("Tipo (inicial/retorno): ");
        String tipo = scanner.nextLine().trim();
        System.out.print("Dia da semana (ex: segunda): ");
        String dia = scanner.nextLine().trim();

        try {
            Consulta consulta = clinicaServico.agendarConsulta(
                    pacientesPorCpf, profissionaisPorNome, consultas,
                    cpf, nomeProfissional, data, horario, tipo, dia
            );
            System.out.println("Consulta agendada!");
            System.out.println(consulta.exibirResumo());
        } catch (PacienteNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (ProfissionalNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (PacienteInativoException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (HorarioIndisponivelException e) {
            System.out.println("Horario indisponivel: " + e.getMessage());
        } finally {
            // Etapa 12: finally com proposito real
            System.out.println("[LOG] --- Operacao de agendamento finalizada ---");
        }
    }

    static void cancelarConsulta() {
        System.out.print("CPF do paciente: ");
        String cpf = scanner.nextLine().trim();
        System.out.print("Data (DD/MM/AAAA): ");
        String data = scanner.nextLine().trim();
        System.out.print("Horario (HH:MM): ");
        String horario = scanner.nextLine().trim();

        try {
            Consulta consulta = clinicaServico.buscarConsulta(consultas, cpf, data, horario);
            clinicaServico.cancelarConsulta(consulta);
            System.out.println("Consulta cancelada.");
        } catch (ConsultaNaoEncontradaException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (OperacaoInvalidaException e) {
            System.out.println("Operacao invalida: " + e.getMessage());
        }
    }

    static void listarConsultas() {
        if (consultas.isEmpty()) {
            System.out.println("Nenhuma consulta registrada.");
            return;
        }
        for (int i = 0; i < consultas.size(); i++) {
            System.out.println("[" + i + "] " + consultas.get(i).exibirResumo());
        }
    }

    // ===================== ATENDIMENTOS =====================

    static void menuAtendimentos() {
        System.out.println("\n--- ATENDIMENTOS ---");
        listarConsultas();
        System.out.print("Indice da consulta: ");
        int indice = lerInteiro();
        System.out.print("Observacoes: ");
        String obs = scanner.nextLine().trim();
        System.out.print("Diagnostico: ");
        String diag = scanner.nextLine().trim();

        // Etapa 12: finally no atendimento
        try {
            if (indice < 0 || indice >= consultas.size()) {
                throw new OperacaoInvalidaException("Indice de consulta invalido.");
            }
            Consulta consulta = consultas.get(indice);
            if (!consulta.status.equals("agendada")) {
                throw new OperacaoInvalidaException(
                        "Apenas consultas agendadas podem receber atendimento. Status: "
                        + consulta.status
                );
            }
            consulta.realizar();
            Atendimento atendimento = new Atendimento(indice, obs, diag);
            atendimentos.add(atendimento);
            System.out.println("Atendimento registrado!");
        } catch (OperacaoInvalidaException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            // Etapa 12: finally com proposito real
            System.out.println("[LOG] --- Operacao de atendimento finalizada ---");
        }
    }

    // ===================== PAGAMENTOS =====================

    static void menuPagamentos() {
        System.out.println("\n--- PAGAMENTOS ---");
        listarConsultas();
        System.out.print("Indice da consulta: ");
        int indice = lerInteiro();
        double valor = lerDouble("Valor base (R$): ");

        System.out.println("Forma de pagamento:");
        System.out.println("1 - Dinheiro/Pix (5% desconto)");
        System.out.println("2 - Cartao (ate 6x)");
        System.out.println("3 - Convenio");
        System.out.print("Opcao: ");
        int forma = lerInteiro();

        // Etapa 12: finally no pagamento
        try {
            Pagamento pagamento = null;

            if (forma == 1) {
                pagamento = clinicaServico.registrarPagamento(indice, valor, "pix", 1);
            } else if (forma == 2) {
                System.out.print("Numero de parcelas (1 a 6): ");
                int parcelas = lerInteiro();
                pagamento = clinicaServico.registrarPagamento(indice, valor, "cartao", parcelas);
            } else if (forma == 3) {
                System.out.print("Nome do convenio (SaudePlus/VidaMais/BemEstar): ");
                String convenio = scanner.nextLine().trim();
                pagamento = clinicaServico.registrarPagamento(indice, valor, "convenio", 1);
                // ajusta o convenio no objeto criado
                if (pagamento instanceof PagamentoConvenio) {
                    System.out.println("Convenio: " + ((PagamentoConvenio) pagamento).getNomeConvenio());
                }
            } else {
                throw new PagamentoInvalidoException("Forma de pagamento invalida.");
            }

            pagamentos.add(pagamento);
            multas.add(0.0);
            System.out.println("Pagamento registrado!");
            System.out.println(pagamento.exibirResumo());

        } catch (PagamentoInvalidoException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            // Etapa 12: finally com proposito real
            System.out.println("[LOG] --- Operacao de pagamento finalizada ---");
        }
    }

    // ===================== RELATORIOS =====================

    static void menuRelatorios() {
        System.out.println("\n--- RELATORIOS ---");
        System.out.println("1 - Relatorio geral");
        System.out.println("2 - Resumo financeiro");
        System.out.println("3 - Relatorio unificado de pessoas (ligacao dinamica)");
        System.out.println("4 - Relatorio de pagamentos (ligacao dinamica)");
        System.out.print("Opcao: ");

        int op = lerInteiro();
        switch (op) {
            case 1:
                Relatorio.gerarRelatorio(consultas, atendimentos);
                break;
            case 2:
                Relatorio.gerarResumoFinanceiro(consultas, pagamentos, multas);
                break;
            case 3:
                // Etapa 14: List<Pessoa> + ligacao dinamica
                relatorioPessoas();
                break;
            case 4:
                // Etapa 14: List<Pagamento> + ligacao dinamica
                relatorioPagamentos();
                break;
            default:
                System.out.println("Opcao invalida.");
        }
    }

    // Etapa 14: percorre List<Pessoa> e chama exibirResumo() via ligacao dinamica
    // LIGACAO DINAMICA: o metodo executado depende do tipo REAL do objeto, nao do tipo da referencia
    static void relatorioPessoas() {
        System.out.println("\n=== RELATORIO UNIFICADO DE CADASTROS ===");
        int totalPacientes = 0;
        int totalProfissionais = 0;

        for (Pessoa pessoa : pessoas) {
            // LIGACAO DINAMICA: exibirResumo() do tipo real (Paciente, Fisioterapeuta, etc)
            pessoa.exibirResumo();

            // DYNAMIC CASTING: instanceof para identificar tipo real
            if (pessoa instanceof Paciente) {
                Paciente pac = (Paciente) pessoa;
                System.out.println("  Convenio: " + pac.getConvenioNome()
                        + " | Ativo: " + pac.isAtivo());
                totalPacientes++;
            } else if (pessoa instanceof Profissional) {
                Profissional prof = (Profissional) pessoa;
                System.out.println("  Especialidade: " + prof.getEspecialidade());
                totalProfissionais++;
            }
            System.out.println("---");
        }

        System.out.println("Total de pacientes: " + totalPacientes);
        System.out.println("Total de profissionais: " + totalProfissionais);
    }

    // Etapa 14: percorre List<Pagamento> e chama calcularValorFinal() via ligacao dinamica
    // LIGACAO DINAMICA: o metodo executado depende do tipo REAL do objeto, nao do tipo da referencia
    static void relatorioPagamentos() {
        System.out.println("\n=== RELATORIO DE PAGAMENTOS ===");
        if (pagamentos.isEmpty()) {
            System.out.println("Nenhum pagamento registrado.");
            return;
        }
        double total = 0;
        for (Pagamento pagamento : pagamentos) {
            // LIGACAO DINAMICA: calcularValorFinal() do tipo real
            System.out.println(pagamento.exibirResumo());
            total += pagamento.calcularValorFinal();
        }
        System.out.printf("Total arrecadado: R$%.2f%n", total);
    }

    // ===================== HELPERS DE LEITURA SEGURA =====================

    // Etapa 12: leitura segura de inteiro — nunca encerra a aplicacao
    static int lerInteiro() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Valor invalido. Digite um numero inteiro: ");
            }
        }
    }

    // Etapa 12: leitura segura de double — nunca encerra a aplicacao
    static double lerDouble(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Valor invalido. Use apenas numeros (ex: 150.00).");
            }
        }
    }
}