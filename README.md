# Secure-Sign-In v3a **Legacy**
This is a Java CLI application I have created in an attempt to improve my online account security. The _a_ in the version code states that this is the CLI(Command Line Interface) version where _b_ would be the GUI(Graphical User Interface). Find the new release(C++ CLI application) at https://gitlab.com/Zander-Labuschagne/SecureSignIn-v4a or https://github.com/Zander-Labuschagne/SecureSignIn-v4a

Benifits:
  - Remember one password for all sites, but all sites have different passwords.
  - Don't know the actual password which is entered in the password box on the website.
  - Provides a very strong, long and complex password.
  - No passwords are stored in file or database.
  - Easy to use.
  
Feel free to criticize or comment.
There are Android, iPhone, iPad and macOS applications available as well, however they are not always up to date and I have left some of them discontinued/incomplete. I work on these projects in my free time only so don't expect regular updates from me.

macOS(Swift) version: TBA

Android version: TBA

iOS(iPhone) version: TBA

iOS(iPad) version: TBA

E-Mail: ZANDER.LABUSCHAGNE@PROTONMAIL.CH

Copyright (C) 2017 Zander Labuschagne. This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License version 3 as published by the Free Software Foundation.

How to install:
  1. Oracle Java Runtime Environment is required to run the application, OpenJDK should work but was never tested.
  2. Either run the SecureSignIn-3a.jar file on any operating system(execute ``java -jar SecureSignIn-3a.jar`` in terminal) found in the ``bin`` directory, or run the ``install.sh`` file after extracting the zip file to install the application on Linux or MacOS systems with the following command: ``sudo sh install.sh`` which should add a ``ssi`` command that you can execute in a terminal session.  (Be sure to extract the appropraite file for your system, i.e. MacOS or Linux)
  3. On some Linux systems it's necessary to run ``sudo chmod +x install.sh`` before installation.
  
  (Feel free to create a Windows version for the installer as I will not make one myself)

How to use application:
1. Enter a password you will remember at the password prompt, preferably a strong and complicated password because this will influence the complexity of the resulting password.
2. Enter a key, such as google, facebook or whatever at the next prompt.
3. Choose between one of the two options below:
    * Enter ``c`` to copy the password to memory, caution this will only last for 8 seconds until the password is cleared from memory.
    * Enter ``v`` to view the password if you need to type it over manually.
4. Some websites have limitations on the length of the passwords... To use the compact variation of the password enter ``s``, or ``l`` otherwise.
5. To exit enter ``q``. The application should self terminate after the 8 seconds has elapsed or immediately if chosen to view the password, if not manually exit using ``CTRL + C``.