public class Straight extends handType{
	
	public Straight(int top){
		this.Rank = 5;
		this.Degree = new int[1];
		this.Degree[0] = top;
		
		if (top!=0){
			this.real = true;
		}
	}
	
	public int ttest(){
		int t = test(this.Degree[0]);
		return t;
	}
	
	public boolean compareSame(handType otherHand){
		return this.Degree[0] > otherHand.Degree[0];
	}
	
	public String toString(){
		return "Straight to a " + translateCardValue(this.Degree[0]);
	}
	
}