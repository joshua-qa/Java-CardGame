import com.Joshua.CardGame.Card;
import com.Joshua.CardGame.Deck;
import org.junit.Before;
import org.junit.Test;

import java.util.Stack;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by Joshua on 2017-03-28.
 */
public class CardTest {
    private Deck deck;
    private Stack<Card> card;

    @Before
    public void init() {
        deck = new Deck();
        card = deck.getCards();
    }

    @Test
    public void getColorTypeTest() {
        String colorType = card.get(0).getColorType().toString();
        assertNotNull(colorType);
    }

    @Test
    public void getCardTypeTest() {
        String cardType = card.get(10).getCardType().toString();
        assertNotNull(cardType);
    }

    @Test
    public void getCardNumberTest() {
        int cardNumber = card.get(7).getCardNumber().getRank();
        assertNotNull(cardNumber);
    }

    @Test
    public void toStringTest() {
        String cardInfo = card.get(0).toString();
        assertNotNull(cardInfo);
    }
}
