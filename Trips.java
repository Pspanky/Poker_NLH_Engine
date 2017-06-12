public class Trips extends handType{
	
	public Trips(int trips, int k1, int k2){
		
		this.Rank =6;
		this.Degree = new int[3];
		this.Degree[0] = trips;
		this.Degree[1] = k1;
		this.Degree[2] = k2;
		
		if (trips!=0){
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
		
		else if (this.Degree[1] != otherHand.Degree[1]){
			return this.Degree[1] != otherHand.Degree[1];
		}
		
		else return this.Degree[2] != otherHand.Degree[2];
	}
	
	public String toString(){
		return "Three of a kind " + translateCardValue(this.Degree[0]) + "s, " + translateCardValue(this.Degree[1]) + ", " + translateCardValue(this.Degree[2]) + " kickers";
	}
	
}