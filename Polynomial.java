import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;



public class Polynomial {
    
    // Fields - 
    double[] non_zero_coff;
    int[] powers;

    // Constructors 
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

    public Polynomial(File f) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader(f));
        String line = input.readLine();
        String[] parts = line.split("(?=[+-])");
        non_zero_coff = new double[parts.length];
        powers = new int[parts.length];
        int i = 0;
        for (String part : parts) {
            String[] comp = part.split("x");
            if (comp.length == 2) {
                non_zero_coff[i] = Double.parseDouble(comp[0]);
                powers[i] = Integer.parseInt(comp[1]);
            }
            if (comp.length == 1) {
                non_zero_coff[i] = Double.parseDouble(comp[0]);
            }
            i++;
        }
        input.close();
    }
    public Polynomial sortarray(Polynomial pol)
    {
        int n = pol.powers.length;

        for(int i = 0; i < n-1; i++)
        {
            for(int j = 0; j < n-i-1; j++)
            {
                if(pol.powers[j] > pol.powers[j+1])
                {
                    //Swap the elements in array 2
                    int temp = pol.powers[j];
                    pol.powers[j] = pol.powers[j+1];
                    pol.powers[j+1] = temp;

                    // Swap the corresponding elements in array 1
					double tempo = pol.non_zero_coff[j];
                    pol.non_zero_coff[j] = pol.non_zero_coff[j+1];
                    pol.non_zero_coff[j+1] = tempo;
                }
            }
        }
		return pol;
    }

    // Add function to add the polynomials
    public Polynomial add(Polynomial other)
    {
        other = sortarray(other);
		Polynomial main = new Polynomial(non_zero_coff, powers);
		main = sortarray(main);
		non_zero_coff = main.non_zero_coff;
		powers = main.powers;

        Set<Integer> uniquePowers = new HashSet<>();
        
        // Add the values from both the sets to the set. 
        for(int i = 0; i < Math.max(powers.length, other.powers.length); i++)
        {
            if(i<powers.length)
            {
                uniquePowers.add(powers[i]);
            }
            if(i<other.powers.length)
            {
                uniquePowers.add(other.powers[i]);
            }
        }
        int resultSize = uniquePowers.size(); // Size of the resulting polynomial

        double[] sum_coff = new double[resultSize];
        int[] sum_power = new int[resultSize];
        int index = 0;
        for(Integer power : uniquePowers)
        {
            sum_power[index++] = power;
        }
        Arrays.sort(sum_power);

        for(index = 0; index < sum_power.length ; index++)
        {
            int power = sum_power[index];
            for(int i = 0; i < powers.length; i++)
            {
                if(power==powers[i])
                {
                    sum_coff[index]+=non_zero_coff[i];
                }
            }
            for(int i = 0; i < other.powers.length; i++)
            {
                if(power==other.powers[i])
                {
                    sum_coff[index]+=other.non_zero_coff[i];
                }
            }
        }
        Polynomial p = new Polynomial(sum_coff, sum_power);
        return(p);
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

    public Polynomial multiply(Polynomial other)
    {
        other = sortarray(other);
        Polynomial main = new Polynomial(non_zero_coff, powers);
		main = sortarray(main);
		non_zero_coff = main.non_zero_coff;
		powers = main.powers;

        Set<Integer> uniquePowers = new HashSet<>();

		// Add all unique powers from the first polynomial
		for(int i = 0; i < powers.length; i++)
		{
			for(int j = 0; j < other.powers.length; j++)
			{
				uniquePowers.add(powers[i]+other.powers[j]);
			}
		}

		int resultSize = uniquePowers.size(); // Size of the resulting polynomial

		double[] mult_coff = new double[resultSize];
		int[] mult_pow = new int[resultSize];
        Polynomial mult = new Polynomial(mult_coff, mult_pow);

        for(int i = 0; i< powers.length; i++)
        {
            double[] mult_temp_coff = new double[other.powers.length];
            int[] mult_temp_pow = new int[other.powers.length];

            for(int j = 0; j < other.powers.length; j++)
            {
                mult_temp_coff[j] = non_zero_coff[i] * other.non_zero_coff[j];
                mult_temp_pow[j] = powers[i] + other.powers[j];
            }
            mult = mult.add(new Polynomial(mult_temp_coff, mult_temp_pow));
        }
        return mult;
    }


    // Function to save the file to the given file location name
    public void saveToFile(String file_name) throws IOException
    {
        Polynomial given = new Polynomial(non_zero_coff, powers);
        given = sortarray(given);
        non_zero_coff = given.non_zero_coff;
        powers = given.powers;
        System.out.println(Arrays.toString(non_zero_coff));
        System.out.println(Arrays.toString(powers));

        String a = "";
        if (powers[0] != 0) 
        {
            a += non_zero_coff[0] + "x" + powers[0];
        } else 
        {
            a += non_zero_coff[0];
        }
        int index = 1;

        while (index < powers.length) {
            if (non_zero_coff[index] >= 0) {
                a += "+" + non_zero_coff[index] + "x" + powers[index];
            } else {
                a += + non_zero_coff[index] + "x" + powers[index];
            }
            index++;
        }

        System.out.println(a);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file_name));
		writer.write(a);
		writer.close();
    }
}