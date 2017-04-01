import com.Joshua.CardGame.Card;
import com.Joshua.CardGame.Deck;
import com.Joshua.CardGame.Game;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.Stack;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by Joshua on 2017-03-30.
 */
public class GameTest {
    private Game game = new Game();
    private Deck deck = new Deck();

    @Before
    public void init() {
        game.init(deck);
    }

    @Test
    public void mainDeckTest() {
        assertThat(game.deckCardPop(), is(true));
        assertThat(deck.getCards().isEmpty(), is(true));
    }

    @Test
    public void toStringTest() {
        String deckString = deck.toString();
        assertNotNull(deckString);
    }
}