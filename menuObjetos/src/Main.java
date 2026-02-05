import java.util.ArrayList;

public class Main {
    private static ArrayList<Aluno> listaAlunos = new ArrayList<>();
    private static ArrayList<Turma> listaTurmas = new ArrayList<>();
    public static void main(String[] args) {
     menuPrincipal();
    }
    public static void menuPrincipal(){
        System.out.println("==== Secretaria ====");
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


        }
    }

    // alunos
    private static void menuAlunos() {
        System.out.println("==== Alunos ====");
        System.out.println("1 - Listar Alunos.");
        System.out.println("2 - Cadastrar Alunos.");
        System.out.println("3 - Atualizar Alunos.");
        System.out.println("4 - Excluir Aluno.");
        System.out.println("5 - Voltar ao MENU.");
        String opcao = Leitura.dados("Digite a opção desejada!");

        switch (opcao){
            case "1":
                listarAlunos();
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
        }
    }


    private static void listarAlunos() {
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
        System.out.println("==== Turmas ====");
        System.out.println("1 - Listar Turmas.");
        System.out.println("2 - Cadastrar Turma.");
        System.out.println("3 - Atualizar Turma.");
        System.out.println("4 - Excluir Turma.");
        System.out.println("5 - Voltar ao MENU");
        String opcao = Leitura.dados("Digite a opção desejada!");

        switch (opcao){
            case "1":
                listarTurmas();
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
        }
    }

    private static void listarTurmas() {

        for (Turma t : listaTurmas){
            System.out.println(t);
        }
    }

    private static void CadastrarTurmas() {
    }

    private static void AtualizarTurmas() {
    }

    private static void ExcluirTurmas() {

    }








}