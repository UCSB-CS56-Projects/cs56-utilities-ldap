package edu.ucsb.cs56.S11.jyang0001.choicepoints2;

import javax.naming.*;
import javax.naming.ldap.*;
import javax.naming.directory.*;
import java.util.Hashtable;
import java.util.ArrayList;

/**
   This code is based on the SimpleQuery class, which originally
   comes from a programming named Adam Smith, and was posted at the link shown below.

   I've added the ability to authenticate using the username/password found in /etc/ldap.conf

   The parsing of /etc/ldap.conf is my own (that comes from the separate class LdapCredentials).

   Some of the authentication code comes from a post from a programmer named Rene Moser who blogs at renemoser.net
   (see second link below.)

   @author Adam Smith (original author)
   @author Phill Conrad (adapted for UCSB College of Engineering LDAP)
   @author Ryan McGinley (Modified To Automate Lookups)
   @author James Yang (Modified To Automate Lookups)
   @see <a href="http://www.stonemind.net/blog/2008/01/23/a-simple-ldap-query-program-in-java/">http://www.stonemind.net/blog/2008/01/23/a-simple-ldap-query-program-in-java/</a>
   @see <a href="http://www.renemoser.net/2008/01/ldap-authentication-using-java/">http://www.renemoser.net/2008/01/ldap-authentication-using-java/</a>
 */

public class SimpleQuery02 {

    public SimpleQuery02() {}

    public static void main(String[] args) {

        if (args.length != 1) {
	    System.out.println("Usage: java SimpleQuery02 filename.txt");
	    return;
	}
	
	ArrayList<String> origNameList = new ArrayList<String>();
	ArrayList<String> nameList = LdapFileMagic.listFileReader(args[0], origNameList);
	ArrayList<String> csilUsernameList = new ArrayList<String>();

        StringBuffer output = new StringBuffer();
	String attribute = "uid";

	for (int i=0; i<nameList.size(); i++) {
	    String query = "cn=" + nameList.get(i);
	    csilUsernameList.add(getLdapStuff(query, output));
	}
	LdapFileMagic.listFileWriter(origNameList, csilUsernameList);

    }		
    /**
       Converted loose code from main to method which can be called multiple times to query data from the LDAP server
       @param query The name of the user we are searching for
       @param output This is a StringBuffer we will reuse.
       @return answer This is the CSIL username that results from the LDAP query 
     */
    public static String getLdapStuff(String query, StringBuffer output) {
	String answer = "";
        try {
	    int x = 0;
	    LdapCredentials ldc = new LdapCredentials("/etc/ldap.conf");

            String url = ldc.getURIs().get(0);
            Hashtable<String,String> env = new Hashtable<String,String>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, url);

	    env.put(Context.SECURITY_AUTHENTICATION, "simple");
	    env.put(Context.SECURITY_PRINCIPAL, ldc.get("BINDDN"));
	    env.put(Context.SECURITY_CREDENTIALS, ldc.get("BINDPW"));

            DirContext context = new InitialDirContext(env);

            SearchControls ctrl = new SearchControls();
            ctrl.setSearchScope(SearchControls.SUBTREE_SCOPE);
            NamingEnumeration enumeration = context.search(ldc.get("BASE"), query, ctrl);
            while (enumeration.hasMore()) {
		SearchResult result = (SearchResult) enumeration.next();

                Attributes attribs = result.getAttributes();
		BasicAttribute ba = (BasicAttribute) attribs.get("uid");

		if (ba!=null) {
		    NamingEnumeration values = ba.getAll();
		    while (values.hasMore()) {
				if (output.length() > 0) {
				    output.append("\n");
				}
				if (x > 0) {
				    answer = "???";
				    values.next();
				}
				else {
			       	    answer=values.next().toString();
				    output.append(answer);
				}
				    x++;
				    
				    
		    } // while
		} // ba is not null
		
            } // while enumeration has more


        } catch (Exception e) {
            e.printStackTrace();
        }
	return answer;
	}
}
