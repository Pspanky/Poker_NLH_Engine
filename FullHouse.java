public class FullHouse extends handType{
	
	public FullHouse(int trips, int pair){
		this.Rank = 3;
		this.Degree = new int[2];
		this.Degree[0] = trips;
		this.Degree[1] = pair;
		
		if (trips!=0){
			this.real = true;
		}
	}
	
	public int ttest(){
		int t = test(this.Degree[0]);
		return t;
	}
	
	public boolean compareSame(handType otherhand){
		if (this.Degree[0] != otherhand.Degree[0]){
			return this.Degree[0] > otherhand.Degree[0];
		}
		
		else return this.Degree[1] > otherhand.Degree[1];
	}
	
	public String toString(){
		return translateCardValue(this.Degree[0]) + "s full of " + translateCardValue(this.Degree[1]) + "s";
	}
	
}