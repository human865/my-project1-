import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Мое уравнение: x^3 - x - 2 = 0");

        Scanner scanner = new Scanner(System.in);

        double a = readDouble(scanner, "Введите a [по умолчанию 1]: ", 1);
        double b = readDouble(scanner, "Введите b [по умолчанию 2]: ", 2);
        double c = readDouble(scanner, "Введите ε [по умолчанию 0.0001]: ", 0.0001);

        double fLeft = Math.pow(a, 3) - a - 2;
        double fRight = Math.pow(b, 3) - b - 2;

        System.out.println("На левой границе " + a + " функция равна " + fLeft);
        System.out.println("На правой границе " + b + " функция равна " + fRight);

        if (fLeft * fRight > 0) {
            System.out.println("Ошибка. На концах должны быть плюс и минус!");
            scanner.close();
            return;
        } else {
            System.out.println("Всё хорошо, можно начинать делить отрезок пополам!");
        }

        int stepCounter = 0;

        while ((b - a) / 2 > c) {
            double middle = (a + b) / 2;
            double fMiddle = Math.pow(middle, 3) - middle - 2;

            if (fLeft * fMiddle < 0) {
                b = middle;
                fRight = fMiddle;
            } else {
                a = middle;
                fLeft = fMiddle;
            }

            stepCounter++;
        }

        double finalRoot = (a + b) / 2;

        System.out.println("\nКорень найден!");
        System.out.println("Приблизительный корень = " + finalRoot);

        double check = Math.pow(finalRoot, 3) - finalRoot - 2;

        System.out.println("Если подставить этот корень в уравнение, получится почти ноль: " + check);
        System.out.println("Сделано ровно " + stepCounter + " шагов, чтобы найти ответ");

        scanner.close();
    }

    static double readDouble(Scanner scanner, String prompt, double defaultValue) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            return defaultValue;
        }

        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Это не число! Использую значение по умолчанию: " + defaultValue);
            return defaultValue;
        }
    }
}