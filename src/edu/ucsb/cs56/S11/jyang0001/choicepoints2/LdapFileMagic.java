package edu.ucsb.cs56.S11.jyang0001.choicepoints2;

import java.io.*;
import java.util.Hashtable;
import java.util.ArrayList;

/** Reads the last name and first name of each individual in a text document provided by the user (each line format: lastname, firstname) and parses the data for lookup using LDAP queries.

   @author Ryan McGinley 
   @author James Yang 
 */

public class LdapFileMagic {
		
    /**
       Reads the text file, modifies each line and stores that in a arraylist.
       @param inputFile The file which contains names to be parsed.  Each line must be of the format "lastname, firstname"
	@param origNameList An arraylist containing an no names at the point it is passed in.
	@return listFileReader An arraylist containing strings of the format "firstname*lastname" where * is a wildcard.
    */
    public static ArrayList<String> listFileReader(String inputFile, ArrayList<String> origNameList) {
		ArrayList<String> nameList = new ArrayList<String>();
		String curLine = "";

		try {
			FileInputStream fis = new FileInputStream(inputFile);
			DataInputStream in = new DataInputStream(fis);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
	
			while ((curLine = br.readLine()) != null) {
				origNameList.add(curLine);
				nameList.add(lineParser(curLine));
			}

			in.close();
		} catch (Exception e) {
		System.err.println("Error: " + e.getMessage());
		}
		return nameList;
	}
    /**
       Converts each line in the text file from 'lastname, firstname' to 'firstname*lastname'
       @param line The current line
       @return firstLastNames the formatted String.
     */
    public static String lineParser(String line) {
       	String[] tokens = line.split(", ");
       	String firstLastNames = tokens[1] + "*" + tokens[0];
       	return firstLastNames;
    }

    /**
       Writes the results from the LDAP queries to a new file
       @param origNameList an arraylist containing the original lines from the file
       @param csilUsernameList an arraylist containing the CSIL usernames for corresponding names
    */

    public static void listFileWriter(ArrayList<String> origNameList, ArrayList<String> csilUsernameList) {
	Writer writer = null;
 
        try {
            File file = new File("outputListName.txt");
	    String text = "";
	    file.createNewFile();
            writer = new BufferedWriter(new FileWriter(file));
	    for(int x = 0; x < origNameList.size(); x++){
		text = origNameList.get(x) + ", " + csilUsernameList.get(x) + "\n";
		writer.write(text);
	    }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
	}
    }

}
