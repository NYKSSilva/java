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
//        String nome = Leitura.dados("Digite seu nome:");
//        String curso = Leitura.dados("Digite o curso:");
//        while(!isCharacther(curso)) {
//            System.out.println("nome de curso invalido, utilize apenas letras!");
//            curso = Leitura.dados("Digite o curso:");
//        }
//
//        String sigla = Leitura.dados("Digite a sigla:");
//        while(!validarSigla(sigla)){
//            System.out.println("Sigla invalida. Precisa ser texto e não repetida");
//            sigla = Leitura.dados("Digite a sigla:");
//        }
//        Aluno aluno = new Aluno(nome, nascimento, turma);
//        listaAlunos.add(aluno);
//        menuAlunos();
//    }
//
//    }
//
//    private static void validarTurma() {
//        if (isVazio(listaTurmas))
//
//            for (Turma t : listaTurmas){
//                if(t.isAtivo())
//                    System.out.println(t);
//            }
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
                menuTurma();
                break;
            case "3":
                AtualizarTurmas();
                menuTurma();
                break;
            case "4":
                ExcluirTurmas();
                menuTurma();
                break;
            case "5":
                menuPrincipal();
                menuTurma();
                break;
            default:
                System.out.println("Opção invalida! Tente novamente!");
                menuTurma();
        }
    }
//LISTAR
    private static void listarTurmas() {

        if (isVazio(listaTurmas)) {
            System.out.println("Não há turmas cadastradas.");
            return;
        }
        for (Turma t : listaTurmas){
            if(t.isAtivo())
            System.out.println(t);
        }
    }

//CADASTRAR
    private static void CadastrarTurmas() {
        Periodo periodo = ValidarPeriodo();
        String curso = Leitura.dados("Digite o curso:");
        while(!isCharacther(curso)) {
            System.out.println("nome de curso invalido, utilize apenas letras!");
            curso = Leitura.dados("Digite o curso:");
        }

        String sigla = Leitura.dados("Digite a sigla:");
        while(!validarSigla(sigla)){
            System.out.println("Sigla invalida. Precisa ser texto e não repetida");
            sigla = Leitura.dados("Digite a sigla:");
    }
        Turma turma = new Turma(curso, sigla, periodo);
        listaTurmas.add(turma);
        menuTurma();
    }

    private static boolean validarSigla(String sigla) {
        if (sigla.isBlank()) return false;

        for (Turma turma : listaTurmas) {
            if (turma.getSigla().equals(sigla)){
                return false;
            }
        }

        return true;
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

    //ATUALIZAR
    private static void AtualizarTurmas() {
        if (isVazio(listaTurmas)) {
            System.out.println("Não há turmas cadastradas.");
            return;
        }
        listarTurmaSigla();
        int idAtualizar = validaIDTurma();
//%s {String}, %d {int decimal}, %f {numeros float}
//para juntar uma variavel com um texto em um sout {printf} conseguimos usar o %{e o tipo da variavel como %s para strings}
//para juntar texto e uma variavel no println usamos +variavel+ {"blabla"+variavel+"blabla"}
      Periodo periodo =  listaTurmas.get(idAtualizar).getPeriodo();
      System.out.printf("O periodo atual é: %s ", periodo );

     atualizarPeriodo(idAtualizar);
    }

    private static void atualizarPeriodo(int idAtualizar) {
        boolean rodarNovamente = true;
        while(rodarNovamente) {
            String opcaoPeriodo = Leitura.dados("\nDeseja modificar o periodo? (s/n):").toUpperCase();
            switch (opcaoPeriodo) {
                case "S":
                    //Periodo periodo = ValidarPeriodo();
                    System.out.printf("O novo periodo é: %s", listaTurmas.get(idAtualizar).setPeriodo(ValidarPeriodo());

                    rodarNovamente = false;
                    break;
                case "N":
                    rodarNovamente = false;
                    break;
                default:
                    System.out.println("Opção invalida, Escolha S para Sim ou N para Não");
                    continue;
            }
            break;
        }
    }

    private static int validaIDTurma() {
        String opcao = Leitura.dados("\nDigite o numero da turma que desejada:");
        int opcaoValida = -1;
        int opcaoUsuario = -1;
        while (opcaoValida ==-1){
            opcaoUsuario = validarOpcaoTurmas(opcao);
            if(opcaoUsuario == -1){
                System.out.println("Opção inválida! Digite novamente");
                opcao = Leitura.dados("Digite o numero da turma que desejada:");
            } else{
                opcaoValida = opcaoUsuario;
            }
        }
        return opcaoValida;
    }

    //EXCLUIR
    private static void ExcluirTurmas() {
        if (isVazio(listaTurmas)) {
            System.out.println("Não há turmas cadastradas.");
            return;
        }
        listarTurmaSigla();
       int idExcluir = validaIDTurma();
        confirmaExclusao();

        if (confirmaExclusao()) {
            //listaTurmas.remove(idExcluir);
            listaTurmas.get(idExcluir).setAtivo(false);
            System.out.println("Turma excluida com sucesso!");
        }else {
            System.out.println("Operação cancelada!");
        }
    }

    private static boolean isVazio(ArrayList<Turma> listaTurmas) {
        if (listaTurmas.isEmpty()) return true;

        for (Turma turma: listaTurmas){
            if (turma.isAtivo()) return false;
        }
        return true;
    }

    private static Boolean confirmaExclusao() {
        String confirma = Leitura.dados("Você tem certeza? (S/N): ").toUpperCase();

        while (true) {
            switch (confirma) {
                case "S":
                    return true;
                case "N":
                    return false;
                default:
                    System.out.println("Opcao invalida. digite S para sim ou N para não.");
                    break;
            }
        }
    }

    private static int validarOpcaoTurmas(String opcao) {
        if (opcao.isBlank()) return -1;
        int opcaoNumero = -1;
        try {
           opcaoNumero = Integer.parseInt(opcao);

        }catch (NumberFormatException e){
            return -1;
        }

        int indiceLista = opcaoNumero-1;
        return indiceLista>=0 && listaTurmas.size() > indiceLista? indiceLista: -1;
    }

    private static void listarTurmaSigla() {
        System.out.println("\n==== Lista de turmas ====");
        for (int i=0;i<listaTurmas.size(); i++){
            if (listaTurmas.get(i).isAtivo())
            System.out.printf("\n%d - %s",i+1, listaTurmas.get(i).getSigla());
        }
    }


}
