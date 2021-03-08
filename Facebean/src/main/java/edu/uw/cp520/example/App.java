package edu.uw.cp520.example;

import java.beans.PropertyVetoException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws PropertyVetoException {


        System.out.println( "Hello World!" );

        FaceBean faceBean = new FaceBean();

        faceBean.setMouthWidth(110);
        System.out.println(faceBean.getMouthWidth());





    }
}
