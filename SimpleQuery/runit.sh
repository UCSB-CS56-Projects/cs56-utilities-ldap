#!/bin/sh

# run the program with the special command line you need to get the certificates to work
# the $* copies all parameters passed to the shell script to the actual program.

export CLASSPATH=.
java -Djavax.net.ssl.keyStore=jssecacerts     -Djavax.net.ssl.keyStorePassword=changeit     -Djavax.net.ssl.trustStore=jssecacerts     -Djavax.net.ssl.trustStorePassword=changeit  SimpleQuery02 $*
