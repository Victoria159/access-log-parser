import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        System.out.println("Введите первое число и нажмите Enter:");
        int firstNumber = new Scanner(System.in).nextInt();
        System.out.println("Введите второе число и нажмите Enter:");
        int secondNumber = new Scanner(System.in).nextInt();
        int sum = firstNumber+secondNumber;
        System.out.println("Сумма равна:" +sum);
        int sub = firstNumber-secondNumber;
        System.out.println("Разность равна:" +sub);
        int mult = firstNumber*secondNumber;
        System.out.println("Произведение равно:" +mult);
        double quotient = (double) firstNumber/secondNumber;
        System.out.println("Частное равно:" +quotient);
    }
}