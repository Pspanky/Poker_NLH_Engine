public class twoPair extends handType{

	public twoPair(int p1, int p2, int k){
		this.Rank = 7;
		this.Degree = new int[3];
		this.Degree[0] = p1;
		this.Degree[1] = p2;
		this.Degree[2] = k;
		
		if (p1!=0){
			this.real = true;
		}
	}
	
	public int ttest(){
		int t = test(this.Degree[0]);
		return t;
	}
	
	public boolean compareSame(handType otherHand){
		if (this.Degree[0] != otherHand.Degree[0]){
			return this.Degree[0] > otherHand.Degree[0];
		}
		
		if (this.Degree[1] != otherHand.Degree[1]){
			return this.Degree[1] > otherHand.Degree[1];
		}
		
		else return this.Degree[2] > otherHand.Degree[2];
	}
	
	public String toString(){
		return "Two pair, " + translateCardValue(Degree[0]) + "'s and " + translateCardValue(Degree[1]) + "'s";
	}
	
}