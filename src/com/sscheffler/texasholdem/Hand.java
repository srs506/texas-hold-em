package com.sscheffler.texasholdem;

import java.util.ArrayList;
import java.util.Collections;

public class Hand implements Comparable<Hand>
{
	private ArrayList<Card> cards;
	private int[]			numberOfSuits; //0 Clubs; 1 Diamonds; 2 Hearts; 3 Diamonds
	private int				rank; 	//0 High card; 1 One Pair; 2 Two Pair; 3 Three of a Kind; 4 Straight; 5 Flush 
									//6 Full House; 7 Four of a Kind; 8 Straight Flush
	public static String[]	ranks = {"High Card", "One Pair", "Two Pair", "Three of a Kind", "Straight", "Flush",
										"Full House", "Four of a Kind", "Straight Flush"};
	public Card				highestCard;

	public Hand()
	{
		this.cards 				= new ArrayList<Card>(7);
		this.numberOfSuits		= new int[4];
		this.rank				= 0;
	}
	
	public Hand(ArrayList<Card> initialCards)
	{
		this.cards = new ArrayList<Card>(7);
		this.numberOfSuits = new int[4];
		
		this.addCards(initialCards);
	}
	
	public void addCards(ArrayList<Card> cardsToAdd){ this.cards.addAll(cardsToAdd); }
	
	public void removeCards(ArrayList<Card> cardsToRemove){ this.cards.removeAll(cardsToRemove); }
	
	public void sortLowestToHighest(){ Collections.sort(this.cards); }
	
	public void sortHighestToLowest(){ Collections.sort(this.cards, Collections.reverseOrder()); }
	
	public int getHighestNumberOfSuits()
	{
		int highest = 0;
		for(int i = 0; i < this.numberOfSuits.length; i++)
		{
			if(this.numberOfSuits[i] > highest)
			{
				highest = this.numberOfSuits[i];
			}
		}
		return highest;
	}
	
	public int getSuitWithHighestAmountOfCards()
	{
		int highestNum = 0;
		int suitNum = 0;
		for(int i = 0; i < this.numberOfSuits.length; i++)
		{
			if(this.numberOfSuits[i] > highestNum)
			{
				suitNum = i;
			}
		}
		return suitNum;
	}
	
	public ArrayList<Card> getCards(){ return cloneList(this.cards); }
	
	public void setCards(ArrayList<Card> newCards)
	{
		this.cards.clear();
		this.cards.addAll(newCards);
	}
	
	public Card getHighestCard(){ return this.highestCard; }
	
	public void setHighestCard(Card highestCard){ this.highestCard = highestCard; }
	
	public int getRank(){ return this.rank; }
	
	/**
	 * Sets the new rank of the Hand, only if it is higher than the previous rank.
	 * @param newRank The new rank to set
	 */
	public void setRank(int newRank)
	{
		if(newRank > this.rank)
		{
			this.rank = newRank;
		}
	}
	
	private static ArrayList<Card> cloneList(ArrayList<Card> listToClone) 
	{
	    ArrayList<Card> clone = new ArrayList<Card>(listToClone.size());
	    clone.addAll(listToClone); 
	    return clone;
	}

	@Override
	public int compareTo(Hand otherHand)
	{
		if(this.rank > otherHand.getRank())
		{
			return 1;
		}
		if(this.rank == otherHand.getRank())
		{
			if(this.getHighestCard().getValue() > otherHand.getHighestCard().getValue())
			{
				return 1;
			}
			if(this.getHighestCard().getValue() < otherHand.getHighestCard().getValue())
			{
				return -1;
			}
		}
		if(this.rank < otherHand.getRank())
		{
			return -1;
		}
		return 0;
	}
}
