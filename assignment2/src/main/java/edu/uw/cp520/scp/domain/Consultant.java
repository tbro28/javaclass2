package edu.uw.cp520.scp.domain;

import edu.uw.cp520.scp.util.PersonalName;

/**
 *
 * A consultant for the SCG, just has a PersonalName.
 *
 * @author Tim Brown
 */
public class Consultant {

    /**
     * The personal details of the consultant.
     */
    private PersonalName name;

    /**
     * Constructor.
     *
     * @param name to initially set when an instance is created.
     */
    public Consultant(PersonalName name) {
        this.name = name;
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




}
