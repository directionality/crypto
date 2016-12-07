
public class RsaAsn 
{

	public Encoder EncodePrivateKey(RSAPrivateKey k)
	{
		
		Encoder enc = new Encoder((byte)3).initSequence();
		enc.addToSequence(new Encoder(k.GetModulus()));
		enc.addToSequence(new Encoder(k.GetPublicExponent()));
		enc.addToSequence(new Encoder(k.GetPrivateExponent()));
		enc.addToSequence(new Encoder(k.GetPrime1()));
		enc.addToSequence(new Encoder(k.GetPrime2()));
		enc.addToSequence(new Encoder(k.GetExponent1()));
		enc.addToSequence(new Encoder(k.GetExponent2()));
		enc.addToSequence(new Encoder(k.GetCoefficient()));
		
		System.out.println("Encoded: " + enc);
		//my_seq.print();
		
		return enc;
	}
	
	public Encoder EncodePublicKey(RSAPublicKey k)
	{
		
		Encoder enc = new Encoder((byte)3).initSequence();
		enc.addToSequence(new Encoder(k.GetModulus()));
		enc.addToSequence(new Encoder(k.GetPublicExponent()));
		
		System.out.println("Encoded: " + enc);
		//my_seq.print();
		
		return enc;
	}
	
	public Decoder DecodeKey(byte[] data)
	{
		Decoder dec = new Decoder(data);
		try {
			dec=dec.getContent();
		} catch (ASN1DecoderFail e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dec;
	}
	
}
