import java.util.Objects;
import java.util.Scanner;
import static java.lang.Math.*;

public class firstCalc {
    public static void main(String[] args) {
        float num1=0, num2= 0;
        System.out.println("Operacje do wyboru: +, -, *, ^, /, %, div -d, pierwiastek - p, log naturalny - n, log - l");

        Scanner in = new Scanner(System.in);
        char operator = in.next().charAt(0);

        //jezeli wybrany log naturalny potrzebujemy tylko 1 liczbe
        if(Objects.equals(operator, 'n')) {
            num1 = in.nextFloat();
        }
        else {
            num1 = in.nextFloat();
            num2 = in.nextFloat();
        }

        switch (operator) {
            case '+' -> //dodawanie
                    System.out.println(num1 + " + " + num2 + " = " + (num1 + num2));
            case '-' -> //odejmowanie
                    System.out.println(num1 + " - " + num2 + " = " + (num1 - num2));
            case '*' -> //mnozenie
                    System.out.println(num1 + " * " + num2 + " = " + num1 * num2);
            case '/' -> //dzielenie
                    System.out.println(num1 + " / " + num2 + " = " + num1 / num2);
            case '^' -> //potegowanie
                    System.out.println(num1 + " ^ " + num2 + " = " + pow(num1, num2));
            case '%' -> //mod
                    System.out.println(num1 + " % " + num2 + " = " + (int) (num1 % num2));
            case 'd' -> //czesc calkowita
                    System.out.println(num1 + " div " + num2 + " = " + floor(num1 / num2));
            case 'p' -> //pierwiastek z num1 o potedze num2
                    System.out.println("Pierwiastek z " + num1 + " stopnia " + num2 + ": " + pow(num1, 1.0 / num2));
            case 'n' -> // dla 2 liczb
                    System.out.println("Log(" + num1 + ") = " + log(num1));
            case 'l' -> //dla jednej liczby
                    System.out.println("Log(" + num1 + "," + num2 + ") = " + log(num1) / log(num2));
        }

    }
}