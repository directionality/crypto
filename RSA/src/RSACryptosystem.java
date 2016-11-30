import java.math.BigInteger;
import java.util.Random;


public class RSACryptosystem {

	private BigInteger TWO = new BigInteger("2");
	private  BigInteger ONE = new BigInteger("1");
	private  BigInteger ZERO = new BigInteger("0");
	
	private BigInteger dP;
	private BigInteger dQ;
	private BigInteger qInv;
	private BigInteger saveP;
	private BigInteger saveQ;
	
	public BigInteger GeneratePrimeByBitSize(Random rand, int low, int high)
	{
		BigInteger max = TWO.pow(high);

		BigInteger min = TWO.pow(low);
		
		BigInteger random;
		
		for(;;)
		{
			random = randomNumber(rand, min, max);
		
			boolean result = MillerRabinPrimalityTest(rand, 10,random);
			
			if(result)
			{
				return random;
			}
		}
		
	}
	
	public BigInteger randomNumber(Random rand, BigInteger l, BigInteger u) {
	    
	    int maxBits = u.bitLength();
	    BigInteger range = u.subtract(l);
	    
	    if(range.compareTo(ZERO)<=0)
	    {
	    	return l;
	    }
	    else
	    {
		    BigInteger random; 
		    do
			{
				random =new BigInteger(maxBits, rand);
			}
		
		    while(random.compareTo(range)>0);
		    return random.add(l);
	    }
	}
	
	public BigInteger modularExponent(BigInteger a, BigInteger k, BigInteger n)
	{
		int t = k.bitLength();
		
		BigInteger b = ONE;
		if(k.equals(ZERO))
		{
			return b; 
		}
		BigInteger A = a;
		if(k.testBit(0))
		{
			b=a;
		}
		for(int i=1; i<=t; i++)
		{
			A = A.multiply(A).mod(n);
			if(k.testBit(i))
			{
				b=A.multiply(b).mod(n);
			}
			
		}
		return b;
	}
	
	public boolean MillerRabinPrimalityTest(Random rand, int k, BigInteger suggestPrime2)
	{
		if(suggestPrime2.compareTo(ONE)<=0)
		{
			return false; 
		}
		
		BigInteger r = suggestPrime2.subtract(ONE);
		int s = 0; 
		while(r.mod(TWO).equals(ZERO))
		{
			r = r.divide(TWO);
			s++;			
		}
				
		for(int i=0; i<k-1; i++)
		{
			
			//BigInteger randomTester = nextRandomBigInteger(suggestPrime2);
			BigInteger a = randomNumber(rand, TWO, suggestPrime2.subtract(TWO));
			BigInteger y = modularExponent(a,r, suggestPrime2);
			
			if(!y.equals(ONE)&(!y.equals(suggestPrime2.subtract(ONE))))
			{	
				int j = 1; 
				while(!y.equals(suggestPrime2.subtract(ONE))&(j<=s-1))
				{		
					y = y.multiply(y).mod(suggestPrime2);
					if(y.equals(ONE))
					{
							//System.out.println("compositeResult");
							//break;
						return false; 
					}
					j++;
				}
				if (!y.equals(suggestPrime2.subtract(ONE)))
				{
					return false; 
				}
			}
		}	
			
		return true;
		 
	}
	
	public BigInteger ComputeN(BigInteger p, BigInteger q)
	{	
		return p.multiply(q);
	}
	
	public BigInteger ComputeEulerTotient(Random rand, BigInteger p, BigInteger q)
	{
		p=p.subtract(ONE);
		q=q.subtract(ONE);
		return p.multiply(q);
	}
	
	public BigInteger FindE(Random rand, BigInteger phi)
 {
		BigInteger a = ZERO;

		for (;;) {
			a = randomNumber(rand, ONE, phi.subtract(ONE));
			if (MillerRabinPrimalityTest(rand, 10, a)) {
				if (!phi.mod(a).equals(ZERO)) {
					return a;
				}
			}
		}
	}
	
	public BigInteger FindD(BigInteger e, BigInteger phi)
	{
		BigInteger d = ZERO;
		BigInteger argument = phi;
		
		BigInteger x = ZERO;
		BigInteger y = ONE; 
		BigInteger u = ONE;
		BigInteger v = ZERO;
		
		while (!e.equals(ZERO))
		{
			BigInteger q = phi.divide(e);
			BigInteger r = phi.mod(e);
			BigInteger m = x.subtract(u.multiply(q));
			BigInteger n = y.subtract(v.multiply(q));
			phi = e;
			e = r;
			x = u;
			y = v;
			u = m;
			v = n;			
		}	
		
		if(!phi.equals(ONE))
		{
			System.out.println("modular inverse does not exist");
		}
		else
		{
			d = x.mod(argument);
		}
		
		return d; 
	}
	
	public BigInteger ComputeCipherText(BigInteger m, BigInteger e, BigInteger n)
	{
		BigInteger c = modularExponent(m, e, n);
		
		return c; 
	}
	
	public BigInteger RecoverPlainText(BigInteger c, BigInteger d, BigInteger n)
	{			
		BigInteger Message = modularExponent(c, d, n);
		
		return Message;
	}
	 
	public void PrepareRecoveryCRT(BigInteger d, BigInteger n, BigInteger p, BigInteger q)
	{ //dP = e-1 mod (p-1) = d mod (p-1) = 11787 mod 136 = 91
		dP = d.mod(p.subtract(ONE));
		//dQ = e-1 mod (q-1) = d mod (q-1) = 11787 mod 130 = 87
		dQ = d.mod(q.subtract(ONE));
		//qInv = q-1 mod p = 131-1 mod 137 = 114
		qInv = FindD(q, p); 
		
		saveP=p;
		saveQ=q;
		
	}
	public BigInteger RecoverPlainTextCRT(BigInteger c)
	{
		//m1 = cdP mod p = 836391 mod 137 = 102
		BigInteger m1 = modularExponent(c, dP, saveP);
		//m2 = cdQ mod q = 836387 mod 131 = 120
		BigInteger m2 = modularExponent(c, dQ, saveQ); 
		//h = qInv.(m1 - m2) mod p = 114.(102-120+137) mod 137 = 3 [we add in an extra p here to keep the sum positive] 
		BigInteger h = qInv.multiply(m1.subtract(m2)).mod(saveP);
		if(h.compareTo(ZERO)==-1)
		{
			h= h.add(saveP);
		}
		BigInteger m= m2.add(h.multiply(saveQ)).mod(saveP);
		 
		return m; 
	}
}
