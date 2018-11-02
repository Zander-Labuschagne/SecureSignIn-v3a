#!/bin/bash

if [ -f /usr/bin/ssi ]; then
	rm /usr/bin/ssi
	echo "A CLI version of Secure Sign In detected and removed."
fi
mkdir /opt/Cryogen
mkdir /opt/Cryogen/SecureSignIn-3a
cp SecureSignIn-3a.jar /opt/Cryogen/SecureSignIn-3a/
touch /usr/bin/ssi
echo "java -jar /opt/Cryogen/SecureSignIn-3a/SecureSignIn-3a.jar" > /usr/bin/ssi
chmod +x /usr/bin/ssi
echo "Secure Sign In v3a successfully installed."
