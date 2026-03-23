import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
    }

    // Task 1. Print Digits of a Number
    public static void task1(int n) {
        if (n < 10) {
            System.out.println(n);
            return;
        }
        task1(n / 10);
        System.out.println(n % 10);
    }

    // Task 2. Average of Elements
    public static double task2Sum(int[] arr, int n) {
        if (n <= 0) return 0;
        return arr[n - 1] + task2Sum(arr, n - 1);
    }

    // Task 3. Prime Number Check
    public static boolean task3(int n, int divisor) {
        if (n <= 2) return (n == 2);
        if (n % divisor == 0) return false;
        if (divisor * divisor > n) return true;
        return task3(n, divisor + 1);
    }

    // Task 4. Factorial
    public static int task4(int n) {
        if (n <= 1) return 1;
        return n * task4(n - 1);
    }

    // Task 5. Fibonacci Number
    public static int task5(int n) {
        if (n <= 1) return n;
        return task5(n - 1) + task5(n - 2);
    }

    // Task 6. Power Function
    public static int task6(int a, int n) {
        if (n == 0) return 1;
        return a * task6(a, n - 1);
    }

    // Task 7. Reverse Output
    public static void task7(Scanner sc, int n) {
        if (n == 0) return;
        int num = sc.nextInt();
        task7(sc, n - 1);
        System.out.print(num + " ");
    }

    // Task 8. Check Digits in String
    public static String task8(String s) {
        if (s.isEmpty()) return "Yes";
        if (!Character.isDigit(s.charAt(0))) return "No";
        return task8(s.substring(1));
    }

    // Task 9. Count Characters in a String
    public static int task9(String s) {
        if (s.isEmpty()) return 0;
        return 1 + task9(s.substring(1));
    }

    // Task 10. Greatest Common Divisor (GCD)
    public static int task10(int a, int b) {
        if (b == 0) return a;
        return task10(b, a % b);
    }
}