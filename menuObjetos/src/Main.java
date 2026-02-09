import java.util.ArrayList;

public class Main {

    private static ArrayList<Aluno> listaAlunos = new ArrayList<>();
    private static ArrayList<Turma> listaTurmas = new ArrayList<>();
    public static void main(String[] args) {
     menuPrincipal();
    }


    public static void menuPrincipal(){
        System.out.println("\n==== Secretaria ====");
        System.out.println("1 - Alunos.");
        System.out.println("2 - Turmas.");
        System.out.println("3 - Sair.");
        String opcao = Leitura.dados("Digite a opção desejada!");

        switch (opcao){
            case "1":
                menuAlunos();
                break;
            case "2":
                menuTurma();
                break;
            case "3":
                System.out.println("Ate a proxima...");
                System.exit(0);
            default:
                System.out.println("Opção invalida! Tente novamente!");
                menuPrincipal();
        }
    }

    // alunos
    private static void menuAlunos() {
        System.out.println("\n==== Alunos ====");
        System.out.println("1 - Listar Alunos.");
        System.out.println("2 - Cadastrar Alunos.");
        System.out.println("3 - Atualizar Alunos.");
        System.out.println("4 - Excluir Aluno.");
        System.out.println("5 - Voltar ao MENU.");
        String opcao = Leitura.dados("Digite a opção desejada!");

        switch (opcao){
            case "1":
                listarAlunos();
                menuAlunos();
                break;
            case "2":
                CadastrarAluno();
                break;
            case "3":
                AtualizarAluno();
                break;
            case "4":
                ExcluirAluno();
                break;
            case "5":
                menuPrincipal();
                break;
            default:
                System.out.println("Opção invalida! Tente novamente!");
                menuAlunos();
        }
    }


    private static void listarAlunos() {
        if(listaAlunos.isEmpty()){
            System.out.println("Não há alunos cadastrados.");
            return;
        }
        for (Aluno a : listaAlunos){
            System.out.println(a);
        }
    }

    private static void CadastrarAluno() {
    }

    private static void AtualizarAluno() {
    }

    private static void ExcluirAluno() {
    }

    // turma
    private static void menuTurma() {
        System.out.println("\n==== Turmas ====");
        System.out.println("1 - Listar Turmas.");
        System.out.println("2 - Cadastrar Turma.");
        System.out.println("3 - Atualizar Turma.");
        System.out.println("4 - Excluir Turma.");
        System.out.println("5 - Voltar ao MENU");
        String opcao = Leitura.dados("Digite a opção desejada!");

        switch (opcao){
            case "1":
                listarTurmas();
                menuTurma();
                break;
            case "2":
                CadastrarTurmas();
                break;
            case "3":
                AtualizarTurmas();
                break;
            case "4":
                ExcluirTurmas();
                break;
            case "5":
                menuPrincipal();
                break;
            default:
                System.out.println("Opção invalida! Tente novamente!");
                menuTurma();
        }
    }

    private static void listarTurmas() {

        if (listaTurmas.isEmpty()) {
            System.out.println("Não há turmas cadastradas.");
            return;
        }
        for (Turma t : listaTurmas){
            System.out.println(t);
        }
    }

    private static void CadastrarTurmas() {
        Periodo periodo = ValidarPeriodo();
        String curso = Leitura.dados("Digite o curso:");
        while(!isCharacther(curso)) {
            System.out.println("nome de curso invalido, utilize apenas letras!");
            curso = Leitura.dados("Digite o curso:");
        }

        String sigla = Leitura.dados("Digite a sigla:");
        boolean repetido = true;
        while (sigla.isBlank() || !repetido) {
            System.out.println("sigla invalida!");
            sigla = Leitura.dados("Digite a sigla:");
            sigla = sigla.toUpperCase();

            for(Turma t: listaTurmas){
                if(t.getSigla().equals(sigla)) {
                    System.out.println("Turma já cadastrada");
                    repetido = true;
                }
            }
            repetido = false;
        }

        Turma turma = new Turma(curso, sigla, periodo);
        listaTurmas.add(turma);
       // menuTurma();
    }

    private static boolean isCharacther(String curso) {
        String textoSemNumero = curso.replaceAll("\\d", " ");
        return  !curso.isBlank() && curso.equals((textoSemNumero));
    }

    private static Periodo ValidarPeriodo(){
        Periodo periodo;
        String opcaoPeriodo = Leitura.dados("""
                Digite o numero do periodo escolhido:
                1 - Matutino
                2 - Vespertino
                3 - Noturno
                4 - Integral""");

        switch (opcaoPeriodo){
            case "1":
                return Periodo.MATUTINO;

            case "2":
                return Periodo.VESPERTINO;

            case "3":
                return Periodo.NOTURNO;

            case "4":
                return Periodo.INTEGRAL;

            default:
                System.out.println("Opção invalida, tente novamente");
                return ValidarPeriodo();
        }

    }

    private static void AtualizarTurmas() {
    }

    private static void ExcluirTurmas() {

    }








}