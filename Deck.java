import java.util.Random;

public class Deck{
	public Card[] cards;
	public Random rand;
	public int cardsDealt;
	
	
	public Deck(){
		//create array for cards
		this.cards = new Card[52];
		this.rand = new Random();
		
		//fill array with cards
		fillSuit(0, "H");
		fillSuit(13, "S");
		fillSuit(26, "D");
		fillSuit(39, "C");
		
		//shuffle array
		shuffle();
		shuffle();
		shuffle();
		
		cardsDealt = 0;
	}
	
	public Card dealCard(){
		Card dealtCard = this.cards[cardsDealt];
		
		cardsDealt += 1;
		return dealtCard;
		
	}
	
	public void fillSuit(int x, String suit){
		int temp = 0;
		
		while (temp < 13){
			this.cards[temp + x] = new Card(temp + 2, suit);
			temp += 1;
		}
	}
	
	//Randomswaps every card in the deck
	public void shuffle(){
		int x = 0;
		
		while (x < 52){
			
			randomSwap(x);
			x += 1;
			
		}
	}
	
	
	//swaps the card at given position with another random card in the deck
	public void randomSwap(int pos1){
		int randNum = (rand.nextInt(52));
		
		Card temp = cards[pos1];
		cards[pos1] = cards[randNum];
		cards[randNum] = temp;
	}
	
	public void printDeck(){
		int x = 0;
		
		//goes through cards and prints them
		while (x < 52){
			System.out.println(this.cards[x].toString());
			x += 1;
		}
	}
	
	public static void main(String[] args){
		Deck test = new Deck();
		test.printDeck();
	}
}