import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

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
				result[i] = solve(caseNumbers.get(i), toFind.get(i));
			}
			
			for (int i = 0; i < result.length; i++) {
				bw.write(result[i] + "\n");
				bw.flush();
			}
		}
	}
	
	public String solve (int [] arr, int m) {
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
		
	}

}
