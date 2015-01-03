package edu.ucsb.cs56.utilities.ldap;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Test the CsvRow class
 * 
 * @author Phill Conrad
 * @see CsvRow
 */

public class CsvRowTest
{
    @Test public void testConstructor()
    {
        // the no arg constructor should give us zero for
        // both imaginary and real parts
        CsvRow r = new CsvRow("fname,lname,perm,major",
			      "Phill,Conrad,1234567,CMPSC");
	assertEquals("Phill",r.get("fname"));
	assertEquals("Conrad",r.get("lname"));
	assertEquals("1234567",r.get("perm"));
	assertEquals("CMPSC",r.get("major"));
    }
    
}
