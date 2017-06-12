public class straightFlush extends handType{
	
	public straightFlush(int top){
		this.Rank = 1;
		this.Degree = new int[1];
		
		Degree = new int[1];
		this.Degree[0]= top;
		
		if (top != 0){
			this.real = true;
		}
	}
	
	public int ttest(){
		int t = test(this.Degree[0]);
		return t;
	}
	
	public boolean compareSame(handType other){
		return (this.Degree[0] > other.Degree[0]);
	}
	
	public String toString(){
		
		String degree = translateCardValue(this.Degree[0]);
		
		return (degree + "-high straight flush");
		
	}
}