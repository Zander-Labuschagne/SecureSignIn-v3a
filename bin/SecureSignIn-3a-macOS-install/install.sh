#!/bin/bash
rm /Applications/SecureSignIn-*
mkdir /Applications/SecureSignIn
cp SecureSignIn-3a.jar /Applications/SecureSignIn/
touch /Applications/SecureSignIn/SecureSignIn-3a.sh
echo "java -jar /Applications/SecureSignIn/SecureSignIn-3a.jar" > /Applications/SecureSignIn/SecureSignIn-3a.sh
echo "alias ssi='/Applications/SecureSignIn/SecureSignIn-3a.sh'" >> ~/.bashrc
echo "alias ssi='/Applications/SecureSignIn/SecureSignIn-3a.sh'" >> ~/.zshrc
