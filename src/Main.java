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
		
		for (int k = 0; k < a.length; k++) {
			i = k + 1;
			j = a.length - 1;
			pos = -1;
			numToSave = a[k];
			search = x - numToSave;
			
			while (i <= j && pos == -1) {
				m = (j+i)/2;
				if (a[m] == search && Math.abs(numToSave - a[m]) < difference) { // This is the binary implementation
					result = a[m];
					deff = numToSave;
					difference = Math.abs(numToSave - a[m]);
					pos = m;
				} else if (a[m] > search) {
					j = m - 1;
				} else {
					i = m + 1;
				}
			}
			
			
		}
		return "Peter should buy books whose prices are " + deff + " and " + result + ".\n";
	}
}
