package cryogen;

import java.io.Console; //Input from console, espesially password input
import java.awt.Toolkit; //Copy function
import java.awt.datatransfer.Clipboard; //Copy function
import java.awt.datatransfer.StringSelection; //Copy function

/**
 * @author Zander Labuschagne
 * E-Mail: <zander.labuschagne@protonmail.ch>
 * Main class for the Secure Sign In v3 CLI application
**/

public class Main
{
	private static char option1 = '.';
	private static char option2 = '.';
	
	private static char[] prompt(String prompt)
	{
		Console console = System.console();
		
		return console.readPassword(prompt + ": ");
	}
	
	private static void argParsePanic(String arg)
	{
		System.out.println("Argument \"" + arg + "\" is not defined in Secure Sign In v3.x.x");
		System.out.println("Try 'ssi --help' for more information.");
		System.exit(-1);
	}
	
	private static void applicationPanic()
	{
		System.out.println("Terminating application due to user disobedience.");
		System.out.println("Try 'ssi --help' for more information.");
		System.exit(-1);
	}
	
	private static void printHelp()
	{
		System.out.println("Secure Sign In v3.0.0a ( https://gitlab.com/Zander-Labuschagne/SecureSignIn-v3a )");
		
		System.out.println("");
		System.out.println("Usage: ssi [OPTIONS]...");
		System.out.println("\t-l, --long\t\tDefault long version of the password");
		System.out.println("\t-s, --short\t\tShort version of the password");
		System.out.println("\t-c, --copy\t\tCopies the password to memory for 8 seconds");
		System.out.println("\t-v, --verbose\t\tDisplays the password on the console screen if needed to type over manually");
		System.out.println("\t\t\t\t(not safe -- only use when absolutely necessary)");
		System.out.println("\t-h, --help\t\tDisplays this help menu");
		
		System.out.println("");
		System.out.println("EXAMPLES:");
		System.out.println("\tssi -lc");
		System.out.println("\tssi -cl");
		System.out.println("\tssi -lv");
		System.out.println("\tssi -l -c");
		System.out.println("\tssi -v -l");
		System.out.println("ssi -c");
		System.out.println("ssi --short --copy");
	}
	
	private static void parseArgs(String[] args)
	{
		for (short arg = 0; arg < args.length; ++arg) {
			if (args[arg].length() < 2)
				argParsePanic(args[arg]);
			if (args[arg].length() > 2 && args[arg].charAt(0) == '-' && args[arg].charAt(1) == '-') {
				if (args[arg].equals("--long"))
					option1 = 'l';
				else if (args[arg].equals("--short"))
					option1 = 's';
				else if (args[arg].equals("--copy"))
					option2 = 'c';
				else if (args[arg].equals("--verbose"))
					option2 = 'v';
				else if (args[arg].equals("--help"))
					printHelp();
				else
					argParsePanic(args[arg]);
					
			} else if (args[arg].length() >= 2 && args[arg].charAt(0) == '-') {
				for (short i = 1; i < args[arg].length(); ++i) {
					switch (args[arg].charAt(i)) {
						case 'l':
							option1 = 'l';
							break;
						case 's':
							option1 = 's';
							break;
						case 'c':
							option2 = 'c';
							break;
						case 'v':
							option2 = 'v';
							break;
						case 'h':
							printHelp();
						default:
							argParsePanic(args[arg]);
					}
				}
			} else 
				argParsePanic(args[arg]);
		}
	}
	
	private static void copyCipher(char[] cipher)
	{
		Thread deleteCipher = new Thread() {
			public void run() {
				try {
					Thread.sleep(8000);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(new StringSelection(""), null);
				}
				catch(InterruptedException iex) {
					System.out.println(iex.getMessage());
				}
			}
		};
		StringSelection stringSelection = new StringSelection(new String(cipher));
		Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
		clpbrd.setContents(stringSelection, null);
		deleteCipher.start();
	}
	
	private static void printCipher(char[] cipher)
	{
		for (short ii = 0; cipher[ii] != '\0'; ++ii)
			System.out.print(cipher[ii]);
	}
	
	public static void main(String[] args)
	{
		try
		{
			Console console = System.console();
			
			char[] plain;
			while (true) {
				plain = prompt("Password");
				if (new String(plain).length() < 3)
					System.out.println("Please Enter a Password that contains at least 3 characters");
				else
					break;
			}
			
			char[] k;
			while (true) {
				k = prompt("Key");
				if (new String(k).length() < 3)
					System.out.println("Please Enter a Key that contains at least 3 characters");
				else
					break;
			}
			
			parseArgs(args);
			
			if (option1 == '.') {
				System.out.println("Enter 's' to use the shortened version of the password.");
				System.out.println("Enter 'l' to use the long version of the password. (Default)");
				System.out.println("Enter 'q' to exit the application.");
				option1 = console.readLine().charAt(0);
			}
			
			char[] cipher = null;
			switch (option1) {
				case 's':
					cipher = SecureSignIn.encrypt(plain, k, 12);
					break;
				case 'l':
					cipher = SecureSignIn.encrypt(plain, k, 32);
					break;
				case 'q':
					System.exit(0);
				default:
					applicationPanic();
			}
			
			if(cipher == null)
				throw new Exception("Error Occurred During Encryption");
			
			if (option2 == '.') {
				System.out.println("Enter 'c' to copy the password and exit the application.");
				System.out.println("Enter 'v' to view/display the password on screen and exit the application.");
				System.out.println("Enter 'q' to exit the application.");
				option2 = console.readLine().charAt(0);
			}
			
			switch (option2) {
				case 'c':
					copyCipher(cipher);
					System.exit(0);
				case 'v':
					printCipher(cipher);
					System.exit(0);
				default:
					applicationPanic();
			}
		} catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
}