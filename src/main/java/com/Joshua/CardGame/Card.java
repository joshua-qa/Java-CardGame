package com.Joshua.CardGame;

/**
 * Created by Joshua on 2017-03-28.
 */
public class Card {
    private CardNumber cardNumber;
    private CardType cardType;
    private ColorType colorType;
    private boolean visibleType;

    public Card(CardNumber cardNumber, CardType cardType, ColorType colorType, boolean visibleType) {
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.colorType = colorType;
        this.visibleType = visibleType;
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

    public boolean getVisibleType() {
        return visibleType;
    }

    public void setVisibleType(boolean visibleType) {
        this.visibleType = visibleType;
    }

    @Override
    public String toString() {
        if(visibleType) {
            return "[" +
                    cardType.mark +
                    cardNumber.cardname +
                    ']';
        } else {
            return "[ XX ]";
        }
    }

    public enum ColorType {
        RED("red"),
        BLACK("black");

        private String colorTypeValue;

        ColorType() {
        }

        ColorType(String colorTypeValue) {
            this.colorTypeValue = colorTypeValue;
        }

        public String getColorTypeValue() {
            return this.colorTypeValue;
        }
    }

    public enum CardNumber {
        ACE(" A", 1),
        TWO(" 2", 2),
        THREE(" 3", 3),
        FOUR(" 4", 4),
        FIVE(" 5", 5),
        SIX(" 6", 6),
        SEVEN(" 7", 7),
        EIGHT(" 8", 8),
        NINE(" 9", 9),
        TEN("10", 10),
        JACK(" J", 11),
        QUEEN(" Q", 12),
        KING(" K", 13);

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
        SPADE(0, "♠"),
        HEART(1, "♥"),
        DIAMOND(2, "◆"),
        CLUB(3, "♣");

        private int cardValue;
        private String mark;

        CardType() {
        }

        CardType(int cardValue, String mark) {
            this.cardValue = cardValue;
            this.mark = mark;
        }

        public int getCardValue() {
            return this.cardValue;
        }

        public String getMark() {
            return this.mark;
        }
    }
}
