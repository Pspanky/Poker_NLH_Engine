public class Flush extends handType{
	
	
	public Flush(int one, int two, int three, int four, int five){
		this.Rank = 4;
		this.Degree = new int[5];
		this.Degree[0] = one;
		this.Degree[1] = two;
		this.Degree[2] = three;
		this.Degree[3] = four;
		this.Degree[4] = five;
		
		if (one!=0){
			this.real = true;
		}
	}
	
	public boolean compareSame(handType otherHand){
		int x = 4;
		
		
		
		while (x != 0){
			if (this.Degree[x] != otherHand.Degree[x]){
				return this.Degree[x] > otherHand.Degree[x];
			}
			else x -= 1;
		}
		
		return false;
	}
	
	public int ttest(){
		int t = test(this.Degree[0]);
		return t;
	}
	
	public String toString(){
		return translateCardValue(this.Degree[4]) + " high flush";
	}

}