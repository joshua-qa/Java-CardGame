import com.Joshua.CardGame.Card;
import com.Joshua.CardGame.Deck;
import org.junit.Before;
import org.junit.Test;

import java.util.Stack;

import static org.hamcrest.CoreMatchers.is;
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
    public void cardCheckTest() {
        assertThat(card.get(0).getColorType(), is(Card.ColorType.BLACK));
        assertThat(card.get(35).getColorType(), is(Card.ColorType.RED));
    }
}
