
You may have to:

(1) compile InstallCert.java to InstallCert.class

(2) run java InstallCert ldap1.engr.ucsb.edu

(3) ./runit.sh uid=pconrad cn

That will give you "real name" given a userid.

Or to look up a userid from a real name:

java -Djavax.net.ssl.keyStore=jssecacerts     -Djavax.net.ssl.keyStorePassword=changeit     -Djavax.netore=jssecacerts     -Djavax.net.ssl.trustStorePassword=changeit  SimpleQuery02 "cn=Phillip Conrad" uid
dn=uid=pconrad,ou=faculty,ou=coms,ou=people,dc=engr,dc=ucsb,dc=edu

