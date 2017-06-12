public abstract class handType{ 
	public int Rank;
	public int[] Degree;
	public boolean real = false;
	
	public boolean isBetter(handType otherHand){
		
		//if they're same handType, check which has higher degree
		if (this.Rank == otherHand.Rank){
			return this.compareSame(otherHand);
		}
		
		//Else return better hand
		else return (this.Rank < otherHand.Rank);
	}
	
	
	//compares same subclass of handType
	public abstract boolean compareSame(handType otherHand);

	
	public int test(int x){
		return x;
	}
	
	public abstract int ttest();
	
	public boolean isSame(handType otherHand){
		
		if(this.Rank != otherHand.Rank){
			return false;
		}
		
		int degrees = otherHand.Degree.length;
		int x = 0;
		
		while(x < degrees){
			
				//if any of the degrees differ, they're not the same
				if (this.Degree[x] != otherHand.Degree[x]) {return false;}
				x+=1;
		}
		
		
		//Else it's true
		return true;
		
		}
	
	public String translateCardValue(int value){
		
		if (value == 11){
			return "Jack";
		}
		
		if (value == 12){
			return "Queen";
		}
		
		if (value == 13){
			return "King";
		}
		
		if (value == 14){
			return "Ace";
		}
		
		else return Integer.toString(value);
		
	}
	
}