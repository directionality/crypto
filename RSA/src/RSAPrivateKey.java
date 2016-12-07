import java.math.BigInteger;


public class RSAPrivateKey 
{
	/*
	RSAPrivateKey: 
	private ( 
			modulus: n, 
			publicExponent: e, 
			privateExponent: d, 
			prime1: p, 
			prime2: q, 
			exponent1: d mod (p-1), 
			exponent2: d mod (q-1), 
			coefficient: q-1 mod p
			)
			*/

	private BigInteger modulus = BigInteger.ZERO;
	private BigInteger publicExponent= BigInteger.ZERO;
	private BigInteger privateExponent= BigInteger.ZERO;
	private BigInteger prime1= BigInteger.ZERO;
	private BigInteger prime2= BigInteger.ZERO;
	private BigInteger exponent1= BigInteger.ZERO;
	private BigInteger exponent2= BigInteger.ZERO;
	private BigInteger coefficient= BigInteger.ZERO;
	
	public void SetModulus(BigInteger mod) 
	{
		this.modulus= mod;
	}
	public BigInteger GetModulus() 
	{
		return this.modulus; 
	}
	public void SetPublicExponent(BigInteger publicExp) 
	{
		this.publicExponent= publicExp;
	}
	public BigInteger GetPublicExponent() 
	{
		return this.publicExponent; 
	}
	public void SetPrivateExponent(BigInteger privateExp) 
	{
		this.privateExponent= privateExp;
	}
	public BigInteger GetPrivateExponent() 
	{
		return this.privateExponent; 
	}
	public void SetPrime1(BigInteger p1) 
	{
		this.prime1= p1;
	}
	public BigInteger GetPrime1() 
	{
		return this.prime1; 
	}
	public void SetPrime2(BigInteger p2) 
	{
		this.prime2= p2;
	}
	public BigInteger GetPrime2() 
	{
		return this.prime2; 
	}
	public void SetExponent1(BigInteger exp1) 
	{
		this.exponent1= exp1;
	}
	public BigInteger GetExponent1() 
	{
		return this.exponent1; 
	}
	public void SetExponent2(BigInteger exp2) 
	{
		this.exponent2= exp2;
	}
	public BigInteger GetExponent2() 
	{
		return this.exponent2; 
	}
	public void SetCoefficient(BigInteger coeff) 
	{
		this.coefficient= coeff;
	}
	public BigInteger GetCoefficient() 
	{
		return this.coefficient; 
	}
	
}
