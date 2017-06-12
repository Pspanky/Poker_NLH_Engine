import java.util.Arrays;

public class Game{
	public Player[] Players = new Player[9];
	public Card[] Board = {null, null, null, null, null};
	public Deck deck = new Deck();
	public int boardCardsDealt = 0;
	
	//Print all cards on board
	public String printBoard(){
		int x = 0;
		String board = "";
		
		while (Board[x] != null){
			board += Board[x].toString() + " ";
			x += 1;
		}
		
		return board;
	}
	
	//Give each player two cards from the deck
	public void dealPlayerCards(){
		int x = 0;
		int y = 0;
		while (x < 2){
			while (y < 2){
				Players[y].getCard(deck.dealCard());
				y += 1;
			}
			x += 1;
			y = 0;
		}
	}
	
	public Card[] copyCards(Card[] cards){
		Card[] copiedcards = new Card[cards.length];
		int temp = 0;
		
		while(temp < cards.length){
			Card tempCard = new Card(cards[temp].value, cards[temp].suit);
			copiedcards[temp] = tempCard;
			temp +=1 ;
		}
		
		return copiedcards;
	}
	
	public void dealBoard(){
		
		//Deal flop
		if (boardCardsDealt == 0){
			while (boardCardsDealt < 3){
				
				this.Board[boardCardsDealt] = deck.dealCard();
				boardCardsDealt += 1;
			}
		}
		
		//Deal turn or river
		else if ((boardCardsDealt == 3) || (boardCardsDealt == 4)){
			this.Board[boardCardsDealt] = deck.dealCard();
			boardCardsDealt += 1;
		}
		
		//Board is already dealt
		else {System.out.println("Board is full");}
	}
	
	//delete river card
	public void resetRiver(){
		if(this.Board[4] != null){
			this.Board[4] = new Card(0,"N");
			boardCardsDealt -=1;
		}
		
		
		else {System.out.println("No River Card!");}
	}
	
	public String arrayprint(Card[] cards){
		String allcards = "";
		int x =0;
		while(x<cards.length){
			allcards += cards[x].toString();
			x+=1;
		}
		
		return allcards;
	}
	
	public float[] calculateOddsTurn(){
		
		float p1wins = 0;
		float p2wins = 0;
		int calculations = 0;
		
		Card[] p1cards = new Card[7];
		Card[] p2cards = new Card[7];
		
		p1cards[0] = this.Players[0].cards[0];
		p1cards[1] = this.Players[0].cards[1];
		
		p2cards[0] = this.Players[1].cards[0];
		p2cards[1] = this.Players[1].cards[1];
		
		int x = 0;
		
		while(x < 4){
			p1cards[x+2] = this.Board[x];
			p2cards[x+2] = this.Board[x];
			x += 1;
		}
		
		while(this.deck.cardsDealt < 52){
			
			this.dealBoard();
			p1cards[6] = this.Board[4];
			p2cards[6] = this.Board[4];
			
			handType p1 = findHandType(p1cards);
			handType p2 = findHandType(p2cards);
			
			//If they're not tied, then one hand is better
			if(p1.isBetter(p2)){
					p1wins +=1;
			}
				
			else{p2wins +=1;}
			
			calculations += 1;
			
			this.resetRiver();
		}
		
		System.out.println("Player 1 wins:" + p1wins);
		System.out.println("Player 2 wins:" + p2wins);
		
		float[] odds = new float[2];
		odds[0] = (p1wins)/(calculations);
		odds[1] = p2wins/calculations;
		
		return odds;
		
	}
	
	//Returns an array of player + board cards (for calculating handtype of players)
	public Card[] getCards(Player player){
		
		//New array for holding the cards
		Card[] allCards = new Card[7];
		
		//Get cards from player hand
		allCards[0] = player.cards[0];
		allCards[1] = player.cards[1];
		
		int x = 2;
		
		//Copy cards from board
		while (x < 7){
			
			allCards[x] = Board[x];
			x += 1;
			
		}
		
		
		
		//Return player + board cards
		return allCards;
	}
	
	public straightFlush sfCheck(Card[] playerCards){
		
		//initialize straightFlush to be returned
		straightFlush checkedHand = new straightFlush(0);
		
		
		int index = 0;
		
		//a straight can't begin from beyond index 3
		while (index < 3){
			
			//Initialize the suit we're  checking for
			String suit = playerCards[index].suit;
			
			//start iterating from our index
			int temp = index;
			int length = 1;
			
			while ((temp < 6) && (length <5)){
				
				//if a card we're checking has the same value, we skip it 
				if (playerCards[temp + 1].value == playerCards[temp].value){
					temp += 1;
				}
				
				else {
					if ((playerCards[temp + 1].value == (playerCards[temp].value + 1)) && playerCards[temp + 1].suit == suit){
						length +=1;
						temp +=1;
					}
					
					//if the next card is not consecutive and has same suit, we exit
					else temp = 7;
						
				}
				
				if ((length == 5) && (playerCards[temp].value > checkedHand.Degree[0])){checkedHand = new straightFlush(playerCards[temp].value);}
			}
			
			index += 1;
			
		}
		
		return checkedHand;
		
	} 
	
	public Quads qCheck(Card[] playerCards){
		Quads hand = new Quads(0,0);
		int index = 0;
		int length = 0;
		int value;
		
		while (index < 4){
			
			//Up until index 3, we check if there are 4 of a kind
			value = playerCards[index].value;
			length = 0;
			
			while ((playerCards[index].value == value) && (length < 3)){
				
				if (playerCards[index + 1].value == value){
					length +=1;
					index +=1;
					
					if (length == 3){
						hand = new Quads(playerCards[index].value, 0);
					}
				}
				
				else index += 1;
				 
			}
		}
		
		if (hand.Degree[0] != 0){
			
			if (playerCards[6].value != hand.Degree[0]) hand.Degree[1] = playerCards[6].value;
			else hand.Degree[1] = playerCards[2].value;
			
		}
		
		return hand;
	}
		
	public int findTripsValue(Card[] playerCards){
			
			int tripsValue = 0;
			int index = 0;
			
			while (index < 5){
				
				int temp = index;
				int tempVal = playerCards[temp].value;
				int length = 1;
				
				while (temp < 6){
					
					temp +=1;
					
					if (playerCards[temp].value == tempVal){
						length +=1;
					}
					
					if (length == 3){
						tripsValue = tempVal;
					}
				}
				
				index +=1;
			}
			
			return tripsValue;
		}
	
	
	
	public FullHouse fhCheck(Card[] playerCards){
		int tripValue = findTripsValue(playerCards);
		int pairValue = 0;
		
		if (tripValue != 0){
			pairValue = findPairV(playerCards, tripValue);
		}
		
		if ((tripValue != 0) && (pairValue != 0)){
			return new FullHouse(tripValue, pairValue);
		}
		
		else return new FullHouse(0,0);
	}
	
	
	public Flush flushCheck(Card[] playerCards){
		
		//initialize straightFlush to be returned
		Flush checkedHand = new Flush(0,0,0,0,0);
		
		int index = 0;
		
		//a straight can't begin from beyond index 3
		while (index < 3){
			
			//Initialize the suit we're  checking for
			String tempSuit = playerCards[index].suit;
			
			
			//start iterating from our index
			int temp = index;
			int[] tempArray = new int[]{playerCards[index].value,0,0,0,0};
			int length = 1;
			
			while ((temp < 6) && (length <5)){
				temp +=1;
				//if a card we're checking has the same value, we skip it 
				if (playerCards[temp].suit == tempSuit){
					tempArray[length] = playerCards[temp].value;
					length +=1;
				}
						
				
				if (length == 5) checkedHand = new Flush(tempArray[0],tempArray[1],tempArray[2],tempArray[3],tempArray[4]);
			}
			
			index += 1;
		}
		
		return checkedHand;
	} 
	
	public Straight straightCheck(Card[] playerCards){
		
		//initialize straightFlush to be returned
		Straight checkedHand = new Straight(0);
		
		
		int index = 0;
		
		//a straight can't begin from beyond index 3
		while (index < 3){
			
			//start iterating from our index
			int temp = index;
			int length = 0;
			
			while ((temp < 6) && (length <4)){
				
				//if a card we're checking has the same value, we skip it 
				if (playerCards[temp + 1].value == playerCards[temp].value){
					temp += 1;
				}
				
				else {
					if (playerCards[temp + 1].value == playerCards[temp].value + 1){
						length +=1;
						temp +=1;
					}
					
					//if the next card is not consecutive and has same suit, we exit
					else temp = 7;
						
				}
				
				if ((length == 4) && (playerCards[temp].value > checkedHand.Degree[0]))checkedHand = new Straight(playerCards[temp].value);
			}
			
			index += 1;
			
		}
		
		return checkedHand;
	} 
	
	public Trips tripsCheck(Card[] playerCards){
		
		Trips checkedHand = new Trips(0,0,0);
		
		int tripsValue = findTripsValue(playerCards);
		
		if (tripsValue != 0){
			
			checkedHand = new  Trips(tripsValue,0,0);
			
			int index = 0;
			int temp1 = 0;
			int temp2 = 0;
			
			while (index < 7){
				
				if ((playerCards[index].value != tripsValue)&& (playerCards[index].value> temp1)){
					
					temp1 = playerCards[index].value;
				}

				index +=1;
			}
			
			index = 0;
			
			while (index < 7){
				
				if (((playerCards[index].value != tripsValue)&&(playerCards[index].value!=temp1))&& (playerCards[index].value> temp2)){
					
					temp2 = playerCards[index].value;
				}

				index +=1;
			}
			
			checkedHand = new Trips(tripsValue, temp1, temp2);
		}
		
		return checkedHand;
		
	}


	public twoPair twoPairCheck(Card[] playerCards){
		twoPair checkedHand = new twoPair(0,0,0);
		
		int highPair = findPairV(playerCards);
		int lowPair = 0;
		
		if (highPair != 0){
			lowPair = findPairV(playerCards, highPair);
		}
		
		//if we have two pair, we find the kicker
		if ((highPair!=0) && (lowPair != 0)){
			
			int temp = 6;
			
			while ((playerCards[temp].value == highPair) || (playerCards[temp].value == lowPair)){
				temp -=1 ;
			}
			
			return new twoPair(highPair, lowPair, playerCards[temp].value);
		}
		
		else return checkedHand;
	}
	
	public Highcard highCheck(Card[] playercards){
		return new Highcard(playercards[6].value,playercards[5].value,playercards[4].value,playercards[3].value,playercards[2].value);
	}
	
	public int findPairV(Card[] playerCards, int tripsV){
		int pairValue = 0;
		int index = 0;
		
		while (index < 6){
			
			int value = playerCards[index].value;
			int temp = index;
			
			while (temp < 6){
				
				temp +=1;
				
				if (playerCards[temp].value == value){
					
					if (((value) > pairValue) && (value != tripsV)){
						pairValue = value;
					}
					
				}
			}
			
			index +=1;
		}
		
		return pairValue;
	}
	
	public int findPairV(Card[] playercards){
		return findPairV(playercards, 0);
	}
	
	public onePair onePairCheck(Card[] playerCards, int tripsV){
		//initialize hand to return
		onePair checkedHand = new onePair(0,0,0,0);
		
		//Check for pair
		int pairValue = findPairV(playerCards);
		
		
		//if pair exists, fill in remaining cards
		if (pairValue != 0){
			checkedHand = new onePair(pairValue,0,0,0);
			
			int temp = 1;
			int index = 6;
			
			//starting from the bottom, fetch the highest values
			while (temp < 4){
				
				
				if (playerCards[index].value != tripsV){
	
					checkedHand.Degree[temp] = playerCards[index].value;
					index -=1;
					temp +=1;
				}
				
				else index-=1;
			}
			
			return checkedHand;
			
		}
		else return checkedHand;
	}
	
	
	
	public handType findHandType(Card[] playerscards){
		
		Card[] playercards = this.copyCards(playerscards);
		
		Arrays.sort(playercards);
		
		handType playerHandType = new straightFlush(0);
		
		playerHandType = sfCheck(playercards);
		if (playerHandType.real != false) {return playerHandType;}
		
		playerHandType = qCheck(playercards);
		if (playerHandType.real != false) return playerHandType;
		
		playerHandType = fhCheck(playercards);
		if (playerHandType.real != false) return playerHandType;
		
		playerHandType = flushCheck(playercards);
		if (playerHandType.real != false) return playerHandType;
		
		playerHandType = straightCheck(playercards);
		if (playerHandType.real != false) return playerHandType;
		
		playerHandType = tripsCheck(playercards);
		if (playerHandType.real != false) return playerHandType;
		
		playerHandType = twoPairCheck(playercards);
		if (playerHandType.real != false) return playerHandType;
		
		playerHandType = onePairCheck(playercards,0);
		if (playerHandType.real != false) return playerHandType;
		
		return highCheck(playercards);
	}
	
	public Game(){
	}
	
	public static void main(String[] args){
		Game testGame = new Game();
		testGame.Players[0] = new Player("Paul", 10000);
		testGame.Players[1] = new Player("Amanda", 10000);
		testGame.dealPlayerCards();
		testGame.dealBoard();
		testGame.dealBoard();
		System.out.println(testGame.printBoard());
		
		System.out.println(testGame.Players[0].printCards());
		System.out.println(testGame.Players[1].printCards());
		
		float[] x = testGame.calculateOddsTurn();
		System.out.println("Player 1 equity on turn:" + x[0]);
		System.out.println("Player 2 equity on turn:" + x[1]);

		
	}
}