public class Main {

    public static void main(String[] args) {


        // Criando um paciente
        Paciente paciente = new Paciente(
                "Victor Manoel",
                "123.456.789-00",
                20,
                "83999999999",
                "Unimed"
        );


        // Criando dias de atendimento
        String[] dias = {"Segunda", "Quarta", "Sexta"};


        // Criando um profissional
        Profissional profissional = new Profissional(
                "Dr. Carlos",
                "clinica geral",
                "CRM12345",
                250.0,
                dias,
                3
        );


        // Testando os métodos herdados de Pessoa
        paciente.exibirResumo();

        System.out.println("----------------------");

        profissional.exibirResumo();

    }

}