import java.util.Scanner;

public class Leitura {
    public static String dados(String message){
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextLine();
    }
}
