#!/bin/bash

mkdir /Applications/SecureSignIn
cp Main.class /Applications/SecureSignIn/
cp SecureSignIn.class /Applications/SecureSignIn/
echo "alias ssi='java /Applications/SecureSignIn/Main.class'" >> ~/.bashrc
echo "alias ssi='java /Applications/SecureSignIn/Main.class'" >> ~/.zshrc
