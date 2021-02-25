package edu.uw.exemplar.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Comparator;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.function.DoubleSupplier;

public class LambdaDemo {

    public static void main(String[] args) {
    	DoubleSupplier sup = () -> Math.random();
        
        Employee[] employees = {
                new Employee("Gonzales", "Speedy", 40000),
                new Employee("Fudd", "Elmer", "J", 15000),
                new Employee("Duck", "Daffy", 20000),
                new Employee("Le Pew", "Pepe", 15000),
                new Employee("Pussycat", "Penelope", 20000),
                new Employee("Coyote", "Wile", "E", 30000),
                new Employee("Leghorn", "Foghorn", 25000),
                new Employee("Bunny", "Bugs", 100000),
                new Employee("Bunny", "Lola", 50000),
                new Employee("Pig", "Porky", 15000),
                new Employee("Pig", "Petunia", 15000)
        };

        for (Employee e : employees) {
            System.out.println(e);
        }
        
        System.out.printf("%nNatural order sort:%n");
        Arrays.sort(employees);
        for (Employee e : employees) {
            System.out.println(e);
        }
        
        Comparator<Employee> cmp;
        cmp = (e1, e2) -> e1.getEmployeeId() - e2.getEmployeeId();

        /*
         * Lamba expressions take the form:
         * (parameters) -> expression
         * For example:
         * (Employee e1, Employee e2) -> e1.getEmployeeId() - e2.getEmployeeId()
         * 
         * May be stored as a reference.
         * BiFunction<Employee,Employee,Integer> f = (Employee e1, Employee e2) -> e1.getEmployeeId() - e2.getEmployeeId();
         * 
         * May be a block of code including appropriate returns
         * BiFunction<Employee,Employee,Integer> f = (Employee e1, Employee e2) -> {
         *    int diff = 0;
         *    if (e1 != e2) {
         *        if ((diff = e1.getGivenName().compareTo(e2.getGivenName())) == 0)
         *        if ((diff = e1.getMiddleName().compareTo(e2.getMiddleName())) == 0)
         *        if ((diff = e1.getSurname().compareTo(e2.getSurname())) == 0)
         *        diff = Integer.compare(e1.getEmployeeId(), e2.getEmployeeId());
         *    }
         *    return diff;
         *  };
         * 
         * Parameter types may be ommited if they may be inferred from the context.
         * Return type is always inferred.
         * 
         * Any interface with a single abstract method is a "functional interface",
         * lambdas satisfy functional interfaces.
         */
        
        System.out.printf("%nEmployee id order sort (using lambda Integer.compare):%n");
        Arrays.sort(employees, (e1, e2)->Integer.compare(e1.getEmployeeId(), e2.getEmployeeId()));
        for (Employee e : employees) {
            System.out.println(e);
        }

        System.out.printf("%nGiven name order sort (using lambda block):%n");
        Arrays.sort(employees, (e1, e2)-> {
            int diff = 0;
            if (e1 != e2) {
                if ((diff = e1.getGivenName().compareTo(e2.getGivenName())) == 0)
                if ((diff = e1.getMiddleName().compareTo(e2.getMiddleName())) == 0)
                if ((diff = e1.getSurname().compareTo(e2.getSurname())) == 0)
                diff = Integer.compare(e1.getEmployeeId(), e2.getEmployeeId());
            }
            return diff;
        });
        for (Employee e : employees) {
            System.out.println(e);
        }

        /*
         * Method references allow you to use an existing method as a lambda.
         * The syntax has three variations:
         * 
         * object::instanceMethod
         * Class::instanceMethod  (first lambda parameter is the target object)
         * Class::staticMethod
         */

        // Using an object method reference, argument types are inferred
        class Jammin {
            int order(Employee e1, Employee e2) {
                return Integer.compare(e2.getEmployeeId(), e1.getEmployeeId());
            }
        }
        Jammin j = new Jammin();
        System.out.printf("%nReverse empoyee order sort (using object method reference):%n");
        Arrays.sort(employees, j::order);
        for (Employee e : employees) {
            System.out.println(e);
        }
        
        // Using a method reference, argument types are inferred
        System.out.printf("%nGiven name order sort (using instance method reference:%n");
        Arrays.sort(employees, Employee::givenCompareTo);
        for (Employee e : employees) {
            System.out.println(e);
        }

        // Using a method reference, argument types are inferred
        System.out.printf("%nSalary order sort (using class method reference:%n");
        Arrays.sort(employees, Employee::salaryCompare);
        for (Employee e : employees) {
            System.out.println(e);
        }


        // Comparator has default methods to facilitate composing a comparator
        System.out.printf("%nEmployee id order sort (using Comparator.comparing):%n");
        Arrays.sort(employees, Comparator.comparing(Employee::getEmployeeId));
        for (Employee e : employees) {
            System.out.println(e);
        }

        System.out.printf("%nGiven name order sort (using Comparator chaining methods):%n");
        Arrays.sort(employees, Comparator.comparing(Employee::getSurname)
                                         .reversed()
                                         .thenComparing(Employee::getGivenName)
                                         .thenComparing(Employee::getMiddleName)
                                         .thenComparing(Employee::getEmployeeId));
        for (Employee e : employees) {
            System.out.println(e);
        }

        
        /*
         * Stream, not I/O streams (java.util.stream.Stream), provides operations
         * for performing sequential aggregate operations (parallel operations
         * are also supported).
         */
        Stream<Employee> s;

        // Print all employees
        System.out.printf("%nAll employees (using Stream.forEach)%n");
        s = Stream.of(employees);
        s.forEach(System.out::println);
        
        System.out.printf("%nAll employees with salaries >= 30000%n");
        s = Stream.of(employees);
        s.filter(e -> e.getSalary() >= 30000)
         .forEach(System.out::println);
        
        System.out.printf("%nFirst employee with salary >= 30000%n");
        s = Stream.of(employees);
        Optional<Employee> x = s.filter(e -> e.getSalary() >= 30000)
                                .findFirst();
        System.out.println(x.isPresent() ? x.get().toString() : "None found.");

        System.out.printf("%nMaximum employee salary%n");
        s = Stream.of(employees);
        Integer max = s.map(Employee::getSalary)
                       .reduce(0, Integer::max);
        System.out.println(max);

        System.out.printf("%nMaximum employee salary%n");
        s = Stream.of(employees);
        OptionalInt optMax = s.mapToInt(Employee::getSalary)
                              .max();
        System.out.println(optMax.isPresent() ? Integer.toString(optMax.getAsInt()) : "None found.");


        // Determine if there are any names starting with 'L'
        s = Stream.of(employees);
        boolean anyStartWithL = s.anyMatch(e->e.getSurname().startsWith("L"));
        System.out.printf("%nThere are employee's whose name starts with 'L': %b%n", anyStartWithL);
        
        
        // Print all employees whose names start with 'L', using pipelining
        System.out.printf("%nEmployee's whose names start with 'L'%n");
        s = Stream.of(employees);
        s.filter(e->e.getSurname().startsWith("L"))
         .forEach(System.out::println);
        
        // Print all employees whose names start with 'L', using pipelining
        System.out.printf("%nCreate a new list with all the Employee's whose names start with 'L'%n");
        s = Stream.of(employees);
        List<Employee> elEmployees = s.filter(e->e.getSurname().startsWith("L"))
                                      .collect(Collectors.toList());

        // Print total of salaries, using pipelining
        s = Stream.of(employees);
        int total = s.mapToInt(e->e.getSalary())
                     .sum();
        System.out.printf("%nTotal salaries: %d%n", total);
    }
}
