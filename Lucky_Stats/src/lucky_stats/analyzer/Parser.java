package lucky_stats.analyzer;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Parser {
int[] distribution;
double[] result;
int counter;
	public Parser() throws FileNotFoundException{
		distribution = new int[50];
		reader();
		
	}
	
	public void reader() throws FileNotFoundException {
		String file = "/Users/KB/JAVA_Developement/LOTTOMAX.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		String n1;
		int nb1;
		
		try {
			 counter = 0;
			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
			        // use comma as separator
				
				String[] draws = line.split(cvsSplitBy);
				if(draws[0].contains("PRODUCT")){
					continue;
				} else{
					preAnalyze(draws[4]);
					preAnalyze(draws[5]);
					preAnalyze(draws[6]);
					preAnalyze(draws[7]);
					preAnalyze(draws[8]);
					preAnalyze(draws[9]);
					preAnalyze(draws[10]);
					
				}
				
			}
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	 
		
	  }
	
	private void preAnalyze(String numb) {
		String n;
		int nb1; 
		n = numb;
		nb1 = Integer.parseInt(n);
		counter++;
		distribution[nb1]++;
	//	System.out.println(nb1);
	
	}
	public double[] analyze(){
		double prob;
		double sumup;
		sumup = 0;
		result = new double[50];
		for(int i = 0; i<= 49; i++){
		double a =	(double) distribution[i];
		double b = (double) counter;
			prob =  a/b * 100;
			result[i] = prob;
		
		System.out.println(result[i]);
		}
		return result;
	}
	
	
}
		
