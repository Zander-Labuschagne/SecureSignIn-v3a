#!/bin/bash

rm -r /opt/Cryogen/SecureSignIn
mkdir /opt/Cryogen
mkdir /opt/Cryogen/SecureSignIn
cp Main.class /opt/Cryogen/SecureSignIn/
cp SecureSignIn.class /opt/Cryogen/SecureSignIn/
touch /usr/bin/ssi
echo "#!/bin/bash" > /usr/bin/ssi
echo ("java /opt/Cryogen/SecureSignIn/Main.class " $@) > /usr/bin/ssi
chmod +x /usr/bin/ssi
