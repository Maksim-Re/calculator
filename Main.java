import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        try {
            String result = calc(input);
            System.out.println(result);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка " + e.getMessage());
        }
    }
    public static String calc(String input) {
        String[] parts = input.split(" ");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Неверный формат выражения");
        }

        int a = parseNumber(parts[0]);
        int b = parseNumber(parts[2]);

        checkRange(a);
        checkRange(b);

        int result;
        switch (parts[1]) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
            default:
                throw new IllegalArgumentException("Недопустимая операция");
        }
        return formatResult(result, isRoman(parts[0]) && isRoman(parts[2]));
    }
    private static int parseNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return parseRoman(input);
        }
    }
    private static int parseRoman(String input) {
        if (input.equals("I")) {
            return 1;
        } else if (input.equals("II")) {
            return 2;
        } else if (input.equals("III")) {
            return 3;
        } else if (input.equals("IV")) {
            return 4;
        } else if (input.equals("V")) {
            return 5;
        } else if (input.equals("VI")) {
            return 6;
        } else if (input.equals("VII")) {
            return 7;
        } else if (input.equals("VIII")) {
            return 8;
        } else if (input.equals("IX")) {
            return 9;
        } else if (input.equals("X")) {
            return 10;
        } else {
            throw new IllegalArgumentException("Недопустимое число: " + input);
        }
    }

    private static void checkRange(int number) {
        if (number < 1 || number > 10) {
            throw new IllegalArgumentException("Число не входит в допустимый диапазон: " + number);
        }
    }

    private static boolean isRoman(String input) {
        return input.matches("[IVX]+");
    }

    private static String formatResult(int number, boolean isRoman) {
        if (isRoman) {
            if (number <= 0) {
                throw new IllegalArgumentException("Отрицательное или нулевое римское число");
            }
            return toRoman(number);
        } else {
            return String.valueOf(number);
        }
    }

    private static String toRoman(int number) {
        String[] romanSymbols = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C"};
        int[] romanValues = {1, 4, 5, 9, 10, 40, 50, 90, 100};

        StringBuilder result = new StringBuilder();
        int i = romanValues.length - 1;

        while (number > 0) {
            if (number >= romanValues[i]) {
                result.append(romanSymbols[i]);
                number -= romanValues[i];
            } else {
                i--;
            }
        }

        return result.toString();
    }
}