How to Run: We had some difficulty getting the shell script which had some arguments for the JVM for authentication to work after we added the project to a package so we decided to incorporate everything (including certification process) into the build.xml file.  The best way to run this application is to type:

ant run -Darg0=examplelist.txt

in terminal, where 'examplelist.txt' is any input file with each line of the format 'lastname, firstname'.  A file called 'outputListName.txt" should appear in the current directory of the format 'lastname, firstname, csilusername'.  If a query for a name returns more than one CSIL account, the csilusername will be listed as '???' (lastname, firstname, ???).  Likewise, if a name is not found the csilusername will be blank (lastname, firstname, ).

examplelist.txt and an outputListName.txt have been included to demonstrate the program's input and output.  Note that 'Mike Edwards' was included to show how a case where multiple csil usernames are returned is handled.

- Ryan McGinley & James Yang


