import java.util.ArrayList;
import java.util.Scanner;

public class SumOfIntegers {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter numbers separated by spaces: ");
        String input = scanner.nextLine();

        String[] numberStrings = input.split("\\s+");

        for (String numStr : numberStrings) {
            try {
                Integer number = Integer.parseInt(numStr);
                numbers.add(number);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format: " + numStr);
                return;
            }
        }

        int sum = 0;
        for (Integer num : numbers) {
            sum += num;
        }

        System.out.println("Sum of numbers: " + sum);
    }
}
