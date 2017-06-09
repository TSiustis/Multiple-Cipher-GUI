package rsa;
/*
 * Security RSA Assignment
 * Author: Corbin Becker
 * Student Number : C11431862
 */
import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {
	
private BigInteger e, n, d, p , q, euler, encryptedint, decryptedint;
private String encryptedtext, decryptedtext;

public RSA(String mytext) {
	
	//2 prime numbers
	SecureRandom rand = new SecureRandom();
	p = BigInteger.probablePrime(512, rand);
	q = BigInteger.probablePrime(512, rand);
	
	//n = p*q
	n = p.multiply(q);
	
	//qotient
	euler = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
	
	
	e = BigInteger.probablePrime(512/2, rand);  
	
	while(euler.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(euler) < 0){
		e.add(BigInteger.ONE);
	}
		
	BigInteger plainint = toBigInteger(mytext);
	
	encryptedint = encrypt(plainint);
	
	encryptedtext = encryptedint.toString();

	//cheia privata
	d = e.modInverse(euler);
	
	BigInteger plain = decrypt(encryptedint);
	
	decryptedtext = fromBigInteger(plain);
	
	decryptedint = toBigInteger(decryptedtext);
	
    }


public BigInteger decrypt(BigInteger encryptedint)
{
	return encryptedint.modPow(d, n);
}


public BigInteger encrypt(BigInteger plainint)
{
	return plainint.modPow(e, n);
}


public static BigInteger toBigInteger(String mystring)
{
    return new BigInteger(mystring.getBytes());
}


public static String fromBigInteger(BigInteger bar)
{
    return new String(bar.toByteArray());
}

//getters and setters
public String getp()
{
	String myString = "" + p;
	return myString;
}

public String getq()
{
	String myString = "" + q;
	return myString;
}

public String getn()
{
	String myString = "" + n;
	return myString;
}

public String getTot()
{
	String myString = "" + euler;
	return myString;
}


public String gete()
{
	String myString = "" + e;
	return myString;
}

public String getEncryptedInt()
{
	String myString = "" + encryptedint;
	return myString;
}

public String getEncryptedText() {
	String myString = "" + encryptedtext;
	return myString;
}

public String getDecryptedInt() {
	String myString = "" + decryptedint;
	return myString;
}

public String getDecryptedText() {
	String myString = "" + decryptedtext;
	return myString;
}

}
