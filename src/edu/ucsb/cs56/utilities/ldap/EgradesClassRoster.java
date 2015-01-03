package edu.ucsb.cs56.utilities.ldap;

import java.nio.file.Path;
import java.nio.file.Paths;


/** represents a class roster downloaded from Egrades */

public class EgradesClassRoster {

    public final static java.nio.charset.Charset ENCODING = 
	java.nio.charset.StandardCharsets.UTF_8;

    public static java.util.List<String> readSmallTextFile(String aFileName) throws java.io.IOException {
	Path path = Paths.get(aFileName);
	return java.nio.file.Files.readAllLines(path, ENCODING);
    }
  
    

}
