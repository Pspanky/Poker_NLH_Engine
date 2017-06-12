public class Hand{
	public Card c1;
	public Card c2;
	public Card[] cards = new Card[2];
	
	public Hand(Card c1, Card c2){
		this.c1 = c1;
		this.c2 = c2;
		this.cards[0] = c1;
		this.cards[1] = c2;
	}
	
}