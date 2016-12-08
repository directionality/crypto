import java.math.BigInteger;


public class EratosthenesSieve 
{

	public BigInteger suggestedPrime;
	public boolean isPossiblyPrime;
	
	public void SetSuggestedPrime(BigInteger suggPrime)
	{
		this.suggestedPrime = suggPrime;
	}
	public BigInteger GetSuggestedPrime()
	{
		return this.suggestedPrime;
	}
	public void SetIsPossiblyPrime(boolean flag)
	{
		this.isPossiblyPrime = flag;
	}
	public boolean GetIsPossiblyPrime()
	{
		return this.isPossiblyPrime;
	}
	
}
