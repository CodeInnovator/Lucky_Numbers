package lucky_stats.analyzer;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;





public class Parser {

	int[] distribution;
	double[] result;
	int counter;
	String saveTo ="Users/KB/JAVA_Development/";
	private static final int BUFFER_SIZE = 4096;
	private String date;

	public Parser() throws FileNotFoundException{
		distribution = new int[50];
		try {
			download();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		unzipfile();
		reader();
	}

	public void download() throws IOException{
		URL website = new URL("http://www.bclc.com/documents/DownloadableNumbers/CSV/LOTTOMAX.zip");
		ReadableByteChannel rbc = Channels.newChannel(website.openStream());
		FileOutputStream fos = new FileOutputStream("/Users/KB/JAVA_Developement/LOTTOMAX.zip");
		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
	}

	public void unzipfile(){
		List<String> fileList;
		String inputZip = "/Users/KB/JAVA_Developement/LOTTOMAX.zip";
		String outputDir = "/Users/KB/JAVA_Developement/";
		byte[] buffer = new byte[1024];

		ZipInputStream zis;
		try {
			zis = new ZipInputStream(new FileInputStream(inputZip));
			ZipEntry ze = zis.getNextEntry();

			while (ze != null) {
				String filePath = outputDir + File.separator + ze.getName();
				if (!ze.isDirectory()) {
					try {
						extractFile(zis, filePath);
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					File dir = new File(filePath);
					dir.mkdir();
				}
				zis.closeEntry();
				ze = zis.getNextEntry();
			}
			zis.close();

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
		byte[] bytesIn = new byte[BUFFER_SIZE];
		int read = 0;
		while ((read = zipIn.read(bytesIn)) != -1) {
			bos.write(bytesIn, 0, read);
		}
		bos.close();
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
					countDate(draws[3]);
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
	
	public void countDate(String update){
		this.date = update;
		
	}
	
	public String recentDate(){
		return date;
	}

}

