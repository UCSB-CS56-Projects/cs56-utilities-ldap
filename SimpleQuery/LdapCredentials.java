import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Hashtable;

/**
   This is a simple class that can be used to get LDAP information
   from /etc/ldap.conf.  For the most part, it is a simple wrapper around
   a Hashtable<String,String> that only supports the
   containsKey and get methods.  A few other convenience methods.

   @author P. Conrad
   @version 2011/01/01

*/

public class LdapCredentials  {

    private Hashtable<String,String> ht; /* key value pairs from /etc/ldap.conf */
    private ArrayList<String> uris; /* list of uris */

    /** returns the value associated with this key in /etc/ldap.conf.  For example, if
	/etc/ldap.conf contains the line "bindpw myBigSecret", then
	get("bindpw") returns "myBigSecret".    Following the convention of /etc/ldap.conf
	comparisons are case-insensitive.
    */

    public String get(String key) {
	return ht.get(key.toUpperCase());
    }

    /** returns true if there if the key appears in /etc/ldap.conf
	For example, 
	/etc/ldap.conf contains the line "bindpw myBigSecret", then
	get("bindpw") returns true.  If bindpw doesn't appear as
	a key in ldap.conf, then get("bindpw") returns false.

	Following the convention of /etc/ldap.conf, comparisons
	are case-insensitive.
    */

    public boolean containsKey(String key) {
	return ht.containsKey((java.lang.Object) key.toUpperCase());
    }

    /** returns an ArrayList&lt;String&gt; of all values
	that appear on the url line in /etc/ldap.conf */

    public ArrayList<String> getURIs() {
	// return a copy of the object
	return new ArrayList<String>(uris);
    }
    
    /** Constructor turns filename of /etc/ldap.conf into an object
	we can query with get, getURIs and containsKey

       @param filename to open (if "", then /etc/ldap.conf is used)
     */
    public LdapCredentials(String filename) throws java.io.IOException {

	ht = new Hashtable<String,String>();
	uris = new ArrayList<String>();

	if (filename.trim().equals(""))
	    filename = "/etc/ldap.conf";
	
	// See Java Cookbook 2nd Edition, Recipe 10.6
	BufferedReader is = new BufferedReader(new FileReader(filename) ) ;

	// See http://www.roseindia.net/java/beginners/java-read-file-line-by-line.shtml

	String strLine;
	//Read File Line By Line
	while ((strLine = is.readLine()) != null)   {
	    processLine(strLine,ht,uris);
	}
	//Close the input stream
	is.close();
	
    }

    /** Process one line of an /etc/ldap.conf file, updating the Hashtable and/or ArrayList of uris.
	Making this a public static method allows us to do unit testing on it separately.
	The key will be converted to uppercase before being stored 

	@param oneLine one line of text in the format expected for /etc/ldap.conf
	@param ht a hashtable that will be updated with the values found on line
	@param uris will be updated only if this line start with uri; in that case, the uris found will be added to uris
     */
    public static void processLine(String oneLine, Hashtable<String,String> ht, ArrayList<String> uris)
    {
	// if the line is blank after being trimmed of whitespace, ignore it.
	if (oneLine.trim().equals(""))
	    return;

	// if the line starts with a hashmark, it is a commment, so ignore it
	if (oneLine.charAt(0)=='#')
	    return;
	
	// otherwise, separate oneLine into what comes before the first space,
	// and what comes after
	
	int indexOfFirstSpace = oneLine.indexOf(" ");
	String key = oneLine.substring(0,indexOfFirstSpace).trim().toUpperCase();
	String value = oneLine.substring(indexOfFirstSpace).trim();

	ht.put(key,value);

	if (key.equals("URI")) {
	    String [] values = value.split(" ");
	    for (String thisURI : values) {
		uris.add(thisURI.trim());
	    } // for
	} // if
	
    } // processLine method

    /** toString returns a string representation of the object--mainly 
	for debugging purposes */

    public String toString() {
	// automatically invokes the toString() methods of ht and uris
	return "{ ht="+ ht + ",uri="+ uris + " }";
    }
    
    /** A small test main that we can invoke just to see
	if our class is working 

	@param args arg[0] can be the name of a file to use in place of /etc/ldap.conf
    */

    public static void main(String [] args) throws java.io.IOException {
	
	String filename = "/etc/ldap.conf";
	if (args.length>=1)
	    filename = args[0];
	
	LdapCredentials ldc = new LdapCredentials(filename);
	System.out.println("ldc=" + ldc); // invokes ldc.toString()
	
	    
    }

} // class LdapCredentials