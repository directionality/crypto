import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class MyApp {

	public static void main(String[] args) {		
		Random rand = new Random(4);
		
		RSACryptosystem rsa = new RSACryptosystem();
		
		BigInteger p = rsa.GeneratePrimeByBitSize(rand, 800, 801);
		BigInteger q = rsa.GeneratePrimeByBitSize(rand, 800, 801);
		
		BigInteger n = p.multiply(q);
		
		BigInteger phi = rsa.ComputeEulerTotient(rand, p, q);
		BigInteger Message = new BigInteger("1978");
		
		BigInteger TWO = new BigInteger("2");
		BigInteger max = TWO.pow(500);
		BigInteger min = TWO.pow(501);
		Message=rsa.randomNumber(rand,min , max);
		
		System.out.println("Message: "+Message);
		BigInteger eNext = rsa.FindE(rand, phi);
		System.out.println("e: "+eNext);
		BigInteger d = rsa.FindD(eNext, phi);
		System.out.println("d: "+d);
		
		BigInteger CipherText = rsa.ComputeCipherText(Message, eNext, n);
		System.out.println("CipherText: " + CipherText);
		
		long startTime,estimatedTime;
		
		startTime = System.nanoTime();    
		BigInteger PlainText = rsa.RecoverPlainText(CipherText, d, n);
		System.out.println("PlainText: " + PlainText);
		estimatedTime = System.nanoTime() - startTime;
		System.out.println("Time: " + estimatedTime);
		
		
		
		startTime = System.nanoTime();    
		rsa.PrepareRecoveryCRT(d, n, p, q);
		BigInteger PlainText2 = rsa.RecoverPlainTextCRT(CipherText);
		System.out.println("PlainText2: " + PlainText2);
		// ... the code being measured ...    
		estimatedTime = System.nanoTime() - startTime;
		System.out.println("Time: " + estimatedTime);
}
	
}
