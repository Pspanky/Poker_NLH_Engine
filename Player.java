public class Player{
	public Card[] cards = {new Card(0, ""), new Card(0, "")};
	public String name;
	public int chips;
	public int numberOfCard = 0;
	
	public Player(String n, int c){
		this.name = n;
		this.chips = c;
	}
	
	public void getCard(Card card){
		cards[numberOfCard] = card;
		numberOfCard +=1;
	}
	
	public String printCards(){
		int x = 0;
		String cards = "";
		while (x < numberOfCard){
			cards += this.cards[x].toString();
			x += 1;
		}
		
		return cards;
	}
	
}