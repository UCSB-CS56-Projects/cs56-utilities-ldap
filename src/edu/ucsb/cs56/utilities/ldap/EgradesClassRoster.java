package edu.ucsb.cs56.utilities.ldap;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/** represents a class roster downloaded from Egrades */

public class EgradesClassRoster {

    public final static String USAGE="java -cp build edu.ucsb.cs56.utilities.ldap.EgradesClassRoster infile.csv outfile.csv";

    public final static String EGRADES_HEADER="Enrl Cd,Perm #,Grade,Final Units,Student Last,Student First Middle,Quarter,Course ID,Section,Meeting Time(s) / Location(s),Email,ClassLevel,Major1,Major2,Date/Time";

    public final static java.nio.charset.Charset ENCODING = 
	java.nio.charset.StandardCharsets.UTF_8;

    public static List<String> readSmallTextFile(String aFileName) throws java.io.IOException {
	Path path = Paths.get(aFileName);
	return java.nio.file.Files.readAllLines(path, ENCODING);
    }
  
    public static void main (String [] args) throws java.io.IOException {
	if (args.length != 2) {
	    System.err.println("Usage: " + USAGE);
	    System.exit(1);
	}

	List<String> egrades = readSmallTextFile(args[0]);

	if (!egrades.get(0).equals(EGRADES_HEADER)) {
	    System.err.println("Error: first line of input file doesn't match expected headers");
	    System.err.println("Expecting:\n"+EGRADES_HEADER);
	    System.exit(2);
	}

	
	
    }

}
