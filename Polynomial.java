import java.lang.Math;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class Polynomial{
	double[] non_zero_coff;
	int[] powers;
	public Polynomial()
	{
		non_zero_coff = new double[1];
		non_zero_coff[0] = 0;

		powers = new int[1];
		powers[0] = 0;
	}
	
	public Polynomial(double coff[], int power[])
	{
		non_zero_coff = coff;
		powers = power;
	}

	public Polynomial(File f) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(f));
		String line = reader.readLine();
		String[] parts = line.split("[\\+\\-]");
		double[] non_zero_coff = new double[parts.length];
		int[] powers = new int[parts.length];
		int i = 0;
		for(String part:parts)
		{
			String[] comp=part.split("x");
			if(comp.length == 2)
			{
				non_zero_coff[i]=Double.parseDouble(comp[0]);
				powers[i]=Integer.parseInt(comp[1]);
			}
			if(comp.length == 1)
			{
				non_zero_coff[i]=Double.parseDouble(comp[0]);
			}
		}
	}
	
	public Polynomial add(Polynomial other)
	{
		int len = 0;
		int highest_power = 0;
		for(int i = 0, inner_index=0, outer_index=0; i < Math.max(powers.length, other.powers.length); i++)
		{
			if(i==powers[inner_index] && i==other.powers[outer_index])
			{
				len++;
				inner_index++;
				outer_index++;
				if (i>highest_power)
				{
					highest_power=i;
				}
			}
			else if(i==powers[inner_index])
			{
				len++;
				inner_index++;
				if (i>highest_power)
				{
					highest_power=i;
				}
			}
			else if(i==other.powers[outer_index])
			{
				len++;
				outer_index++;
				if (i>highest_power)
				{
					highest_power=i;
				}
			}
		}

		double[] sum_coff = new double[len];
		int[] sum_power = new int[len];

		int mains_power = 0;
		int inner_index = 0;
		int outer_index = 0;
		int sum_index = 0;
		while(mains_power <= highest_power)
		{

			if((mains_power == powers[inner_index]) && (mains_power == other.powers[outer_index]))
			{
				sum_coff[sum_index] = non_zero_coff[inner_index] + other.non_zero_coff[outer_index];
				sum_power[sum_index] = powers[inner_index];
				sum_index++;
				inner_index++;
				outer_index++;
			}
			else if(mains_power == powers[inner_index])
			{
				sum_coff[sum_index] = non_zero_coff[inner_index];
				sum_power[sum_index] = powers[inner_index];
				sum_index++;
				inner_index++;
			}
			else if(mains_power == other.powers[outer_index])
			{
				sum_coff[sum_index] = other.non_zero_coff[outer_index];
				sum_power[sum_index] = other.powers[outer_index];
				sum_index++;
				outer_index++;
			}
			mains_power++;
		}

		Polynomial p = new Polynomial(sum_coff, sum_power);
		return p;
	}
	
	public double evaluate(double x)
	{
		double val = 0;
		for(int i = 0; i < powers.length; i++)
		{
			val += non_zero_coff[i]*Math.pow(x, powers[i]);
		}
		return val;
	}
	
	public boolean hasRoot(double x)
	{
		double val = evaluate(x);
		return (val == 0);
	}

	// public Polynomial multiply(Polynomial other)
	// {
	// 	true;
	// }

	public void saveToFile(String file_name)
	{
		String a = "";
		
	}


}