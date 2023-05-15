import java.lang.Math;

public class Polynomial{
	double[] polynomial;
	public Polynomial()
	{
		polynomial = new double[1];
		polynomial[0] = 0;
	}
	
	public Polynomial(double arr[])
	{
		polynomial = arr;
	}
	
	
	public Polynomial add(Polynomial other)
	{
		double[] arr = new double[Math.max(polynomial.length, other.polynomial.length)];
		Polynomial p = new Polynomial(arr);
		for(int i=0; i < Math.max(other.polynomial.length, polynomial.length); i++)
		{
			if(i<polynomial.length && i < other.polynomial.length)
			{
				p.polynomial[i] = other.polynomial[i] + polynomial[i];
			}
			else if(i<polynomial.length && i >= other.polynomial.length)
			{
				p.polynomial[i] = polynomial[i];
			}
			else if(i>=polynomial.length && i < other.polynomial.length)
			{
				p.polynomial[i] = other.polynomial[i];
			}
		}
		return p;
	}
	
	public double evaluate(double x)
	{
		double val = 0;
		for(int i = 0; i < polynomial.length; i++)
		{
			val += polynomial[i]*Math.pow(x,i);
		}
		return val;
	}
	
	public boolean hasRoot(double x)
	{
		double val = evaluate(x);
		return (val == 0);
	}
}