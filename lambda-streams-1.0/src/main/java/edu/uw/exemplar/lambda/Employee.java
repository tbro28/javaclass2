package edu.uw.exemplar.lambda;

public class Employee implements Comparable<Employee> {
    private static int nextEmployeeId = 1;
    private String givenName;
    private String middleName;
    private String surname;
    private int employeeId;
    private int salary;
    
    private static final int nextEmployeeId() {
        return nextEmployeeId++;
    }

    // Constructors
    public Employee(final String surname, final String givenName, final String middleName,
                    final int salary) {
        if (surname == null || givenName == null) {
            throw new IllegalArgumentException("Surname and given name are required.");
        }
        this.surname = surname;
        this.givenName = givenName ;
        this.middleName = middleName;
        employeeId = nextEmployeeId();
        this.salary = salary;
    }

    public Employee(final String surname, final String givenName, final int salary) {
        this(surname, givenName, "", salary);
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(final String givenName) {
        this.givenName = givenName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(final String middleName) {
        this.middleName = middleName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int compareTo(final Employee o) {
        int diff = 0;
        if (this != o) {
            if ((diff = surname.compareTo(o.surname)) == 0)
                if ((diff = givenName.compareTo(o.givenName)) == 0)
                    if ((diff = middleName.compareTo(o.middleName)) == 0)
                        if ((diff = Integer.compare(salary, o.salary)) == 0)
                            diff = Integer.compare(employeeId, o.employeeId);
        }

        return diff;
    }
    
    public int givenCompareTo(final Employee o) {
        int diff = 0;
        if (this != o) {
            if ((diff = givenName.compareTo(o.givenName)) == 0)
                if ((diff = surname.compareTo(o.surname)) == 0)
                    if ((diff = middleName.compareTo(o.middleName)) == 0)
                        if ((diff = Integer.compare(salary, o.salary)) == 0)
                            diff = Integer.compare(employeeId, o.employeeId);
        }

        return diff;
    }
    
    public static int salaryCompare(final Employee e1, final Employee e2) {
        int diff = 0;
        if (e1 != e2) {
            if ((diff = Integer.compare(e1.salary, e2.salary)) == 0)
            if ((diff = e1.surname.compareTo(e2.surname)) == 0)
            if ((diff = e1.givenName.compareTo(e2.givenName)) == 0)
            if ((diff = e1.middleName.compareTo(e2.middleName)) == 0)
            diff = Integer.compare(e1.employeeId, e2.employeeId);
        }

        return diff;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + employeeId;
        result = prime * result
                + ((givenName == null) ? 0 : givenName.hashCode());
        result = prime * result
                + ((middleName == null) ? 0 : middleName.hashCode());
        result = prime * result + salary;
        result = prime * result + ((surname == null) ? 0 : surname.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Employee other = (Employee) obj;
        if (employeeId != other.employeeId)
            return false;
        if (givenName == null) {
            if (other.givenName != null)
                return false;
        } else if (!givenName.equals(other.givenName))
            return false;
        if (middleName == null) {
            if (other.middleName != null)
                return false;
        } else if (!middleName.equals(other.middleName))
            return false;
        if (salary != other.salary)
            return false;
        if (surname == null) {
            if (other.surname != null)
                return false;
        } else if (!surname.equals(other.surname))
            return false;
        return true;
    }


    public String toString() {
        return String.format("%d: %s, %s %s, $%d", employeeId, surname, givenName,
                             (middleName == null) ? "" : middleName, salary);
    }
}
