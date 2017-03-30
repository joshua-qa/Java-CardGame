package com.Joshua.CardGame;

/**
 * Created by Joshua on 2017-03-28.
 */
public class Card {
    private CardNumber cardNumber;
    private CardType cardType;
    private ColorType colorType;

    public Card(CardNumber cardNumber, CardType cardType, ColorType colorType) {
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.colorType = colorType;
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(CardNumber cardNumber) {
        this.cardNumber = cardNumber;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public ColorType getColorType() {
        return colorType;
    }

    public void setColorType(ColorType colorType) {
        this.colorType = colorType;
    }

    @Override
    public String toString() {
        return "[" +
                cardType.mark +
                cardNumber.cardname +
                ']';
    }

    public enum ColorType {
        RED("red"),
        BLACK("black");

        private String value;

        ColorType() {
        }

        ColorType(String value) {
            this.value = value;
        }
    }

    public enum CardNumber {
        ACE("A", 1),
        TWO("2", 2),
        THREE("3", 3),
        FOUR("4", 4),
        FIVE("5", 5),
        SIX("6", 6),
        SEVEN("7", 7),
        EIGHT("8", 8),
        NINE("9", 9),
        TEN("10", 10),
        JACK("J", 11),
        QUEEN("Q", 12),
        KING("K", 13);

        private String cardname;
        private int rank;

        CardNumber() {
        }

        CardNumber(String cardname, int rank) {
            this.cardname = cardname;
            this.rank = rank;
        }

        public int getRank() {
            return this.rank;
        }
    }

    public enum CardType {
        SPADE("spade", "♠"),
        HEART("heart", "♥"),
        DIAMOND("diamond", "◆"),
        CLUB("club", "♣");

        private String value;
        private String mark;

        CardType() {
        }

        CardType(String value, String mark) {
            this.value = value;
            this.mark = mark;
        }
    }
}
