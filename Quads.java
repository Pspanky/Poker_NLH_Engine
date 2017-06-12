public class Quads extends handType{
	public int QuadsCard;
	public int singleCard;
	
	
	public Quads(int Q, int s){
		this.Rank = 2;
		this.QuadsCard = Q;
		this.singleCard = s;
		
		int[] d = new int[]{Q,s};
		this.Degree = d;
		
		if (Q!=0){this.real = true;}
		
	}
	
	public boolean compareSame(handType otherHand){
		if (this.Degree[0] != otherHand.Degree[0]){
			return this.Degree[0] > otherHand.Degree[0];
		}
		
		else {return this.Degree[1] > otherHand.Degree[1];}
	}
	
	public int ttest(){
		int t = test(this.Degree[0]);
		return t;
	}
	
	public String toString(){
		
		String q = translateCardValue(this.Degree[0]);
		String s = translateCardValue(this.Degree[1]);
		
		return "Four of a kind " + q + "'s, with " + s + " kicker";
		
	}
}
	