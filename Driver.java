import java.io.File;
import java.io.IOException;

public class Driver {
	public static void main(String[] args) throws IOException {
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		double[] c1 = {6, 0, 0, 5};
		int[] e1 = {0, 1, 2, 3};
		Polynomial p1 = new Polynomial(c1, e1);
		double[] c2 = {0, -2, 0, 0, -9};
		int[] e2 = {0, 1, 2, 3, 4};
		Polynomial p2 = new Polynomial(c2, e2);
		Polynomial s = p1.add(p2);
		System.out.println("s(0.1)=" + s.evaluate(0.1));
		if (s.hasRoot(1))
			System.out.println("1 is a root of s");
		else
			System.out.println("1 is not a root of s");
		Polynomial p12 = p1.multiply(p2);
		for (int i = 0; i < 10; i++) {
			System.out.println("p1(" + i + ")*p2(" + i + ") = " + p12.evaluate(i) + " = " + p1.evaluate(i) * p2.evaluate(i));
		}
		System.out.println("p1*p2 " + (has_duplicates(p12) ? "has" : "does not have") + " duplicates");
		//Polynomial p3 = new Polynomial("5-3x2+7x8");
		//System.out.println("p3(1) = (" + p3 + ")(1) = " + p3.evaluate(1));
		System.out.println("Saving p3 to test.txt");
		p12.saveToFile("test.txt");
		Polynomial p4 = new Polynomial(new File("test.txt"));
		System.out.println(p12 + " = " + p4);
	}
    
	public static boolean has_duplicates(Polynomial p)
    {
        for (int i = 0; i < p.powers.length; i++) {
			for (int j = 0; j < i; j++) {
				if (p.powers[i] == p.powers[j]) {
					return true;
				}
			}
		}
		return false;
    }
    // BufferedReader input = new BufferedReader(new FileReader("file.txt"));
    //         String line = input.readLine();
    //     String[] parts = line.split("(?=[+-])");
    //     double[] non_zero_coff = new double[parts.length];
    //     int[] powers = new int[parts.length];
    //     int i = 0;
    //     for (String part : parts) {
    //         String[] comp = part.split("x");
    //         if (comp.length == 2) {
    //             non_zero_coff[i] = Double.parseDouble(comp[0]);
    //             powers[i] = Integer.parseInt(comp[1]);
    //         }
    //         if (comp.length == 1) {
    //             non_zero_coff[i] = Double.parseDouble(comp[0]);
    //         }
    //         i++;
    //     }
    //         System.out.println(Arrays.toString(non_zero_coff));
    //         System.out.println(Arrays.toString(powers));
    //         input.close();
    //     }
    //     catch (IOException e)
    //     {
    //         e.printStackTrace();
    //     }
}