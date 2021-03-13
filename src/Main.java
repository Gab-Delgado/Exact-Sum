import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public Main() {
		
	}

	public static void main(String[] args) {
		Main ppal = new Main();
		try {
			ppal.executeProgram();
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void executeProgram() throws NumberFormatException, IOException {
		int n, m;
		ArrayList<int []> caseNumbers = new ArrayList<>();
		ArrayList<Integer> toFind = new ArrayList<>();
		boolean end = false;
		int cont = 0;
		
		while (!end) {
			String line = br.readLine();
			
			if (line != null && cont < 2) {
				if (!line.equals("")) {
					n = Integer.parseInt(line);
					int [] prices = new int[n];
					String [] stringPrices = br.readLine().split(" ");
					
					for (int i = 0; i < n; i++) {
						prices[i] = Integer.parseInt(stringPrices[i]);
					}
					
					m = Integer.parseInt(br.readLine());
					
					caseNumbers.add(prices);
					toFind.add(m);
					cont = 0;
				} else {
					cont++;
				}
				
			} else {
				end = true;
			}
		}
		
		if (caseNumbers.size() > 0) {
			String [] result = new String[caseNumbers.size()];
			for (int i = 0; i < caseNumbers.size(); i++) {
				Arrays.sort(caseNumbers.get(i));
				result[i] = solve(caseNumbers.get(i), toFind.get(i));
			}
			
			for (int i = 0; i < result.length; i++) {
				bw.write(result[i] + "\n");
				bw.flush();
			}
		}
	}
	
	/*public String solve (int [] arr, int m) {
		int bookOne = 0;
		int bookTwo = 0;
		int difference = (int) 10e6; // Because the difference will never be a number this big
		
		for (int i = 0; i < arr.length; i++) {
			boolean matches = false;
			for (int j = i + 1; j < arr.length && !matches; j++) {
				if (arr[i] + arr[j] == m && Math.abs(arr[i] - arr[j]) < difference) {
					bookOne = arr[i];
					bookTwo = arr[j];
					difference = Math.abs(bookOne - bookTwo);
					matches = true;
				}
			}
		}
		
		if (bookTwo < bookOne) {
			int mock = bookOne;
			bookOne = bookTwo;
			bookTwo = mock;
		}
		
		return "Peter should buy books whose prices are " + bookOne + " and " + bookTwo + ".\n";
		
	}*/
	
	public String solve(int [] a, int x) {
		int pos = -1;
		int i = 0;
		int j = a.length - 1;
		int result = 0;
		int m = 0;
		int difference = (int)10e7;
		int deff = 0;
		
		int numToSave = 0;
		int search = 0;
		
		//System.out.println("Valores: \n x: " + x);
		
		for (int k = 0; k < a.length; k++) {
			i = k + 1;
			j = a.length - 1;
			pos = -1;
			numToSave = a[k];
			//System.out.println("Numero guardado: " + numToSave);
			search = x - numToSave;
			//System.out.println("Se busca: " + search);
			
			// for a[i], E.g.   x = 100, a[0] = 78, search = 22;
			// We search for a number 22. If there is a number 22 then these are the two numbers
			// We can wrap it all in a for that goes through the array so then we do a[1] = 36
			// Therefore search = 64. If there is the number 64, these two are also a solution
			// We just need to find the one with the less difference between the two numbers.
			
			while (i <= j && pos == -1) {
				m = (j+i)/2;
				//System.out.println("Soy a[m]: " + a[m]);
				if (a[m] == search && Math.abs(numToSave - a[m]) < difference) {
					result = a[m];
					deff = numToSave;
					difference = Math.abs(numToSave - a[m]);
					//System.out.println("Entré!" + "\nEncontrado: " + result + " Deff: " + deff);
					pos = m;
				} else if (a[m] > search) {
					j = m - 1;
				} else {
					i = m + 1;
				}
			}
			
			
		}
		//System.out.println("Valor 1: " + deff + " Result: " + result);
		return "Peter should buy books whose prices are " + deff + " and " + result + ".\n";
	}
	
}
