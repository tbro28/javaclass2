package edu.uw.cp520.scg.domain;

import edu.uw.cp520.scg.util.PersonalName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * A consultant for the SCG, just has a PersonalName.
 *
 * @author Tim Brown
 */
public class Consultant implements Comparable<Consultant>, Serializable {

    /**
     * The personal details of the consultant.
     */
    private PersonalName name;

    private static Logger log = LoggerFactory.getLogger(Consultant.class);

    /**
     * Constructor.
     *
     * @param name to initially set when an instance is created.
     */
    public Consultant(PersonalName name) {
        this.name = name;
    }





    /**
     * Write out the serialization version of the class.
     *
     * @return the serialization proxy inner class object.
     */
    private Object writeReplace() {

        //log name info here.
        //log.info("here");

        return new SerializationProxy(this);


    }


    /**
     * Requires the read to use the proxy and not the class itself.
     *
     * @param objectInputStream
     * @throws InvalidObjectException
     */
    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Proxy required.");



    }


    /**
     * Private static nested class that represents the classes state (the proxy).
     */
    private static class SerializationProxy implements Serializable {
        private PersonalName serName;
        //private String x, y, z;

        SerializationProxy( final Consultant consultant) {
            serName = consultant.getName();

/*            x = serName.getLastName();
            y = serName.getFirstName();
            z = serName.getMiddleName();*/
        }

        /**
         * Returns the Consultant.
         *
         * @return return the consultant instance.
         */
        private Object readResolve() {

            //log name info here.
            log.info("Name: " + serName.toString());

            return new Consultant(serName);
            //return new Consultant(new PersonalName(x, y, z));
        }
    }




    /**
     * Returns the personal object.
     *
     * @return name.
     */
    public PersonalName getName() {
        return name;
    }

    /**
     * Create a string version of the object.
     *
     * @return a string object.
     */
    @Override
    public String toString() {
        return "Consultant{" +
                "name=" + name +
                '}';
    }

    /**
     * Implement the equals method.
     *
     * @param o
     * @return whether the two Consultant objects are equal or not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consultant that = (Consultant) o;
        return Objects.equals(name, that.name);
    }

    /**
     * Implement the hashcode.
     *
     * @return a hash of the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * The Consultant natural ordering is in ascending
     * ordered by the consultant's name.
     *
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure
     * {@code sgn(x.compareTo(y)) == -sgn(y.compareTo(x))}
     * for all {@code x} and {@code y}.  (This
     * implies that {@code x.compareTo(y)} must throw an exception iff
     * {@code y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code x.compareTo(y)==0}
     * implies that {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for
     * all {@code z}.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
     * <i>signum</i> function, which is defined to return one of {@code -1},
     * {@code 0}, or {@code 1} according to whether the value of
     * <i>expression</i> is negative, zero, or positive, respectively.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Consultant o) {
        int diff = 0;

        diff = this.name.compareTo(o.getName());

        return diff;
    }
}
