#!/bin/env python

# Running this python script will dump the contents of the ldap directory to the screen
# so that you can see what attributes are in the ldap database.

# This script automatically reads from /etc/ldap.conf to get the 
# values of the binddn and bindpw

import os

def getLdapConfDict(filename):
    """ read contents from filename into dictionary and return it """
    thisDict = {}
    with open(filename) as f:
      content = f.readlines()
    for line in content:
        splitLine = line.split(None, 2)
        if (len(splitLine)==2):
            thisDict[splitLine[0]]=splitLine[1]
    return thisDict


confFile = getLdapConfDict("/etc/ldap.conf")

cmd = "ldapsearch -D " + confFile["binddn"] + " -w " + confFile["bindpw"] 
print(cmd);
os.system(cmd);
