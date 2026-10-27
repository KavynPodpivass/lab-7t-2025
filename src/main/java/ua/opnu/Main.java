package ua.opnu;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Consumer;
class Student {
    //Завдання 2
    private String name;
    private String group;
    private int[] marks;
    public Student(String name, String group, int[] marks){
        this.name = name;
        this.group = group;
        this.marks = marks;
    }
    public int[] getMarks(){
        return marks;
    }
    public String getName() {
        return name;
    }
    @Override
    public String toString(){
        return name;
    }
}

//Завдання 1
public class Main{
    public static int[] filter(int[] input, Predicate<Integer> p) {
        int[] result = new int[input.length];
        int counter = 0;
        for (int i : input) {
            if (p.test(i)) {
                result[counter] = i;
                counter++;
            }
        }
        return Arrays.copyOfRange(result, 0, counter);
    }
    //Завдання 2
    public static Student[] filter(Student[] input, Predicate<Student> p) {
        Student[] result = new Student[input.length];
        int counter = 0;
        for (Student s : input) {
            if (p.test(s)) {
                result[counter] = s;
                counter++;
            }
        }
        return Arrays.copyOfRange(result, 0, counter);
    }
    //Завдання 3
    public static int[] filter(int[] input, Predicate<Integer> p1, Predicate<Integer> p2) {
        int[] result = new int[input.length];
        int counter = 0;
        for (int i : input) {
            if (p1.test(i) && p2.test(i) ) {
                result[counter] = i;
                counter++;
            }
        }
        return Arrays.copyOfRange(result, 0, counter);
    };
    //Завдання 4
    public static void forEach(Student[] input, Consumer<Student> action) {
        for (Student s : input) {
            action.accept(s);
        }
    }
    //Завдання5
    public static void processIf(int[] input, Predicate<Integer> p, Consumer<Integer> action) {
        for (int i : input) {
            if(p.test(i)){
                action.accept(i);
            }
        }
    }
    //Завдання 6
    public static int[] processArray(int[] input, Function<Integer, Integer> function){
        int[] result = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            result[i] = function.apply(input[i]);
        }
        return result;
    }
    //Завдання 7
    public static String[] stringify(int[] input, Function<Integer, String> function){
        String[] result = new String[input.length];
        for (int i = 0; i < input.length; i++) {
            result[i] = function.apply(input[i]);
        }
        return result;
    }
    public static void main(String[] args) {
        //Завдання 1
        int[] test = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        Predicate<Integer> isPrime = n -> {
            if (n <= 1) {
                return false;
            }
            for (int i = 2; i < n; i++) {
                if (n % i == 0) {
                    return false;
                }
            }
            return true;
        };
        int[] res = filter(test, isPrime);
        System.out.println(Arrays.toString(res));
        //Завдання 2
        Predicate<Student> hasDebts = student -> {
            int[] marks = student.getMarks();
            for (int mark : marks) {
                if (mark < 60) {
                    return true;
                }
            }
            return false;
        };
        Student[] students = {
                new Student("Іван", "УП-251", new int[]{90, 80, 75}),
                new Student("Олег", "УП-251", new int[]{55, 95, 85}),
                new Student("Дар'я", "УП-251", new int[]{60, 65, 70}),
        };
        System.out.println("Cтуденти з заборгованостями" + Arrays.toString(filter(students, hasDebts)));
        //Завдання 3
        Predicate<Integer> isEvenNum = n -> n % 2 == 0;
        Predicate<Integer> isGreaterThanFive = n -> n > 5;
        int[] res3 = filter(test, isEvenNum, isGreaterThanFive);
        System.out.println("Парні та більше 5: " + Arrays.toString(res3));
        //Завдання 4
        Consumer<Student> printStudent = student -> System.out.println(student.getName());
        //Завдання 5
        forEach(students, printStudent);
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Consumer<Integer> printEven = n -> System.out.println("Парне число: " +n);
        processIf(test, isEven, printEven );
        //Завдання 6
        Function<Integer, Integer> powerOfTwo = n -> (int) Math.pow(2,n);
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] powers = processArray(numbers, powerOfTwo);
        System.out.println(Arrays.toString(powers));
        //Завдання 7
        String[] names = {"нуль", "один", "два", "три", "чотири", "п'ять", "шість", "сім", "вісім", "дев'ять" };
        Function<Integer, String> digitToSttring = n -> names[n];
        int[] digits = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        String[] words = stringify(digits, digitToSttring);
        System.out.println(Arrays.toString(words));
    }
}

