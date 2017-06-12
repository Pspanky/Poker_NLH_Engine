public class Card implements Comparable<Card>{
	public int value;
	public String suit;
	
	public Card(int v, String s){
		this.value = v;
		this.suit = s;
	}
	
	public String getSuit(){
		if (this.suit == "S")return "spades";
		if (this.suit == "D")return "diamonds";
		if (this.suit == "H")return "hearts";
		if (this.suit == "C")return "clubs";
		else return "no suit";
	}
	
	public String convertValue(){
		if (this.value == 11){
			return "Jack";
		}
		
		else if (this.value == 12){
			return "Queen";
		}
		
		else if (this.value == 13){
			return "King";
		}
		
		else if (this.value == 14){
			return "Ace";
		}
		
		else return Integer.toString(this.value);
		
	}
	
	public String toString(){
		return this.convertValue() + " of " + getSuit();
	}
	
	public int compareTo(Card otherCard){
		int compareQuantity = otherCard.value;
		
		return this.value - compareQuantity;
	}
	
}