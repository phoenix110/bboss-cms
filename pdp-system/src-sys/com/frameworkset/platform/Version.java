package com.frameworkset.platform;

import java.util.Date;

/**
 * 
 * @author biaoping.yin[yin-bp@163.com]
 * @version Revision: 1.0.0 
 * @date Jan 15, 2009 10:03:42 AM 
 * @since 1.0.0
 */
public class Version {
	public static final String description="SYS V3.1";
    public static final short version=100;
    public static final Date releaseDate = new Date("2009/11/04");
    

    /**
     * Prints the value of the description and cvs fields to System.out.
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("\nVersion: \t" + description + "\nDate:\t" + releaseDate);
       
        
    }

    /**
     * Returns the catenation of the description and cvs fields.
     * @return String with description
     */
    public static String printDescription() {
        return  description ;
    }

    /**
     * Returns the version field as a String.
     * @return String with version
     */
    public static String printVersion() {
        return Short.toString(version);
    }

    /**
     * Compares the specified version number against the current version number.
     * @param v short
     * @return Result of == operator.
     */
    public static boolean compareTo(short v) {
        return version == v;
    }

}
