import java.math.BigInteger;


public class RSAPublicKey 
{
	/*
	RSAPublicKey: public (
		modulus: n, 
		publicExponent: e
		)
		*/

	private BigInteger modulus = BigInteger.ZERO;
	private BigInteger publicExponent= BigInteger.ZERO;
	
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
	
}
