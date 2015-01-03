package edu.ucsb.cs56.utilities.ldap;

import java.util.HashMap;

/** represents a single Row from a class roster downloaded from Egrades,
 i.e. information from Egrades for a single student 
*/

public class CsvRow extends HashMap<String,String> {

    public CsvRow(String fieldNames, String csvLine) {
	String [] keys = fieldNames.split(",");
	String [] values = csvLine.split(",");

	if (keys.length != values.length)
	    throw new IllegalArgumentException
		("fieldNames and csvLine must contain same number of fields");
	
	for (int i=0; i<keys.length; i++) {
	    this.put(keys[i],values[i]);
	}
    }

}
