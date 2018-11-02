package cryogen;

import java.io.Console; //Input from console
import java.util.LinkedList; //Linked lists

/**
 * @author Zander Labuschagne
 * E-Mail: <zander.labuschagne@protonmail.ch>
 * Application used to encrypt passwords to complex text for enhanced account security and reducing effort for user
 */

public class SecureSignIn
{
	/**
	 * Method to encrypt the password
	 * Based on Viginere's Cipher Algorithm, modified by Zander
	 *
	 * @param userPassword the password to be encrypted
	 * @param key          the key used to encrypt the password
	 * @return the encrypted password
	 */
	public static char[] encrypt(char[] userCipher, char[] k, int limit)
	{
		char[] systemCipher = new char[userCipher.length + 1];
		char[] finalCipher = new char[userCipher.length * 2 + 1];
		int keyIndex = 0;
		int i = 0;
		int ii = 0;
		int temp;
		int specCharCount = 0;
		int pos = 0;
		char[] specChars = new char[userCipher.length + 1];

		for (char t : userCipher) {
			if (t >= 65 && t <= 90) { //Encrypting Uppercase Characters
				temp = t - 65 + (k[keyIndex] - 65);
				if (temp < 0)
					temp += 26;
				if (temp <= 0)
					temp += 26;

				systemCipher[i++] = (char) (65 + (temp % 26));
				if (++keyIndex == k.length)
					keyIndex = 0;
			} else if (t >= 97 && t <= 122) { //Encrypting Lower Case Characters
				temp = t - 97 + (k[keyIndex] - 97);
				if (temp < 0)
					temp += 26;
				if (temp < 0)
					temp += 26;

				systemCipher[i++] = (char) (97 + (temp % 26));
				if (++keyIndex == k.length)
					keyIndex = 0;
			} else { //Encrypting Special Characters
				specChars[ii++] = (char) (pos + 65);
				specChars[ii++] = t;
				specCharCount++;
			}
			pos++;
		}
		i = 0;
		finalCipher[i++] = (char) (specCharCount == 0 ? 65 : (--specCharCount + 65));//Encrypting Amount of Special Characters in Password
		for (char t = specChars[0]; t != 0; i++, t = specChars[i - 1])//Encrypting Special Characters & Positions of Special Characters
			finalCipher[i] = t;
		ii = i;
		for (char t = systemCipher[0]; t != 0; i++, t = systemCipher[i - ii])//Encrypting Password
			finalCipher[i] = t;

		int ext = -1;
		if (i > 32) {
			while (true) {
				System.out.println("Warning: Password is longer than 32 characters");
				Console console = System.console();
				String input = console.readLine("Would you like to shorten the pasword to the 32 limit? [ y | n ]\n");
				if (input.toLowerCase().charAt(0) == 'y') {
					ext = 1;
					break;
				} else if (input.toLowerCase().charAt(0) == 'n')
					break;
			}
		}

		int length = 0;
		for (int x = 0; finalCipher[x] != '\0'; ++x)
			length++;
		char[] cipher = new char[length];
		for (int xi = 0; xi < cipher.length && xi < length; ++xi)
			cipher[xi] = finalCipher[xi];

		//Shuffle Password
		LinkedList<Character> evens = new LinkedList<>();
		LinkedList<Character>odds = new LinkedList<>();
		for (int iii = 0; iii < cipher.length; ++iii)
			if ((int) cipher[iii] % 2 == 0)
				evens.addLast(cipher[iii]);
			else
				odds.addFirst(cipher[iii]);
		int iv = 0;
		while (!evens.isEmpty() || !odds.isEmpty()) {
			if (!odds.isEmpty()) {
				cipher[iv++] = odds.getFirst();
				odds.removeFirst();
			}
			if (!evens.isEmpty()) {
				cipher[iv++] = evens.getFirst();
				evens.removeFirst();
			}
		}

		//encrypt special chars further
		for (int v = 0; v < cipher.length; ++v)
			if ((int) cipher[v] <= 47)
				cipher[v] += 10;
			else if ((int) cipher[v] > 47 && (int) cipher[v] < 64)
				cipher[v] -= 5;
			else if ((int) cipher[v] > 90 && (int) cipher[v] <= 96) {
				if (cipher.length % 2 == 0)
					cipher[v] += 2;
				else
					cipher[v] -= 2;
			}

		//Replacing unloved characters
		for (int vi = 0; vi < cipher.length; ++vi)
			if ((int) cipher[vi] == 34)
				cipher[vi] = 123;
			else if ((int) cipher[vi] == 38)
				cipher[vi] = 124;
			else if ((int) cipher[vi] == 60)
				cipher[vi] = 125;
			else if ((int) cipher[vi] == 62)
				cipher[vi] = 126;

		//Limitations
		if (ext == 1 || limit < 32)
		{
			char[] cipherLimited = new char[limit < cipher.length ? limit : cipher.length];
			for (int vii = 0; vii < cipher.length && vii < limit; ++vii)
				cipherLimited[vii] = cipher[vii];
			return cipherLimited;
		}

		return cipher;
		//TODO: Remove null characters
	}
}
