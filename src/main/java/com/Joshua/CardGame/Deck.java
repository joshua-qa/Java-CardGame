package com.Joshua.CardGame;

import java.util.Collections;
import java.util.Stack;

/**
 * Created by Joshua on 2017-03-28.
 */
public class Deck {
    private Stack<Card> cards;

    public Deck() {
        this.cards = this.createCards();
        Collections.shuffle(this.cards);
    }

    private Stack<Card> createCards() {
        Stack<Card> cards = new Stack<>();

        for(Card.CardType cardType : Card.CardType.values()) {
            for(Card.CardNumber cardNumber : Card.CardNumber.values()) {
                if (cardType.equals(Card.CardType.SPADE) || cardType.equals(Card.CardType.CLUB)) {
                    Card card = new Card(cardNumber, cardType, Card.ColorType.BLACK, true);
                    cards.push(card);
                } else if (cardType.equals(Card.CardType.HEART) || cardType.equals(Card.CardType.DIAMOND)) {
                    Card card = new Card(cardNumber, cardType, Card.ColorType.RED, true);
                    cards.push(card);
                }
            }
        }

        return cards;
    }

    public Stack<Card> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(Card card : cards){
            sb.append(card.toString());
            sb.append("\n");
        }

        return sb.toString();
    }
}
