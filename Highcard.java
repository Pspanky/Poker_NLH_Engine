public class Highcard extends handType{
	
	public Highcard(int a, int b, int c, int d, int e){
		this.Rank = 9;
		this.Degree = new int[5];
		this.Degree[0] = a;
		this.Degree[1] = b;
		this.Degree[2] = c;
		this.Degree[3] = d;
		this.Degree[4] = e;
		
		if (a!=0){
			this.real = true;
		}
		
	}
	
	public boolean compareSame(handType otherhand){
		int x = 0;
		
		while (x < 5){
			if (this.Degree[x] != otherhand.Degree[x]){
				return this.Degree[x] > otherhand.Degree[x];
			}
			
			x += 1;
		}
		return false;
	
	}
	
	public int ttest(){
		int t = test(this.Degree[0]);
		return t;
	}

	
	public String toString(){
		return translateCardValue(this.Degree[0]) + "high";
	}
	
	
}