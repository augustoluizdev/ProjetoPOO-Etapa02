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


        // Testando a especialização de Pessoa
        paciente.exibirResumo();


    }

}