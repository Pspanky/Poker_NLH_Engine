public class onePair extends handType{
	
	public onePair(int pair, int k1, int k2, int k3){
		this.Rank = 8;
		this.Degree = new int[4];
		this.Degree[0] = pair;
		this.Degree[1] = k1;
		this.Degree[2] = k2;
		this.Degree[3] = k3;
		
		if (pair!=0){
			this.real = true;
		}
		
	}
	
	public boolean compareSame(handType otherHand){
		if (this.Degree[0] != otherHand.Degree[0]){
			return this.Degree[0] > otherHand.Degree[0];
		}
		
		if (this.Degree[1] != otherHand.Degree[1]){
			return this.Degree[1] > otherHand.Degree[1];
		}
		
		if (this.Degree[2] != otherHand.Degree[2]){
			return this.Degree[2] > otherHand.Degree[2];
		}
		
		else return this.Degree[3] > otherHand.Degree[3];
	}
	
	public int ttest(){
		int t = test(this.Degree[0]);
		return t;
	}
	
	public String toString(){
		String temp = translateCardValue(this.Degree[0]);
		return "Pair of " + temp + "s";
	}
	
}