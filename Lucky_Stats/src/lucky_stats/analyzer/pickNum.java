package lucky_stats.analyzer;

public class pickNum {
	int[] bestPick = new int[7];
	int counter = 0;
	
	public pickNum(double[] a){
		bestSix(a);
	}

	private void bestSix(double[] a) {
		double[] n1 = bestNumber(a);
		double[] n2 = bestNumber(n1);
		double[] n3 = bestNumber(n2);
		double[] n4 = bestNumber(n3);
		double[] n5 = bestNumber(n4);
		double[] n6 = bestNumber(n5);
		double[] n7 = bestNumber(n6);
	
		
		
		
	}
	private double[] bestNumber(double[] a){
		int c = a.length-1;
		double[] copy;
		double highest = 0;
		int bestNum = 0;
		copy = a;
		
		for (int i = 0; i <= c; i++){
			double r = copy[i];
			if(highest < r){
			bestNum = i;
			highest = r;
			copy[i] = 0.0;
			}
		}
		bestPick[counter] = bestNum;
		counter++;
		return copy;
	}
	
	public int[] getBestSix(){
		return bestPick;
	}
}
