package tennis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tennis.kata.Player;
import tennis.kata.Score;
import tennis.kata.GameScoreValue;
import tennis.kata.TennisGame;

public class TennisKataScoreManagerTest {
//test game score	
	@Test
	public void shouldReturn15WhenGet0() {
		
		Player player = new Player(new Score(GameScoreValue.LOVE));
		
		player.markOnePoint(null);
		
		assertEquals(GameScoreValue.FIFTEEN, player.getScore().getGameScore());
		
	}
	
	@Test
	public void shouldReturn30WhenGet15() {
		
		Player player = new Player(new Score(GameScoreValue.FIFTEEN));
		
		player.markOnePoint(null);
				
		assertEquals(GameScoreValue.THIRTY, player.getScore().getGameScore());
		
	}
	
	@Test
	public void shouldReturn40WhenGet30() {
		
		Player player = new Player(new Score(GameScoreValue.THIRTY));
		
		boolean win = player.markOnePoint(null);
		
		assertFalse(win);
		assertEquals(GameScoreValue.FOURTY, player.getScore().getGameScore());
		
	}
	
	@Test
	public void shouldReturnWinWhenGet40() {
		
		Player player = new Player(new Score(GameScoreValue.FOURTY));
		
		player.markOnePoint(null);
		
		assertEquals(GameScoreValue.WIN, player.getScore().getGameScore());
		
	}
	
	@Test
	public void shouldActivateDeuceWhenTwoPlayersGet40() {
		Player player1 = new Player(new Score(GameScoreValue.THIRTY));
		Player player2 = new Player(new Score(GameScoreValue.FOURTY));
		TennisGame game = new TennisGame(player1, player2);
		
		player1.markOnePoint(game);
		
		assertTrue(game.isDeuce());
	}
	
	@Test
	public void shouldReturnAdvWhenGet40AndDeuceIsActivated() {
		Player player1 = new Player(new Score(GameScoreValue.THIRTY));
		Player player2 = new Player(new Score(GameScoreValue.FOURTY));
		TennisGame game = new TennisGame(player1, player2);
		
		player1.markOnePoint(game);
		player1.markOnePoint(game);
		
		assertEquals(GameScoreValue.ADV, player1.getScore().getGameScore());
	}

// test set	score
	@Test
	public void shouldeWinGameWhenGetAdvAndDeuceActivated() {
		Player player1 = new Player(new Score(GameScoreValue.THIRTY));
		Player player2 = new Player(new Score(GameScoreValue.FOURTY));
		TennisGame game = new TennisGame(player1, player2);
		
		player1.markOnePoint(game);
		player1.markOnePoint(game);
		player1.markOnePoint(game);
		
		assertEquals(GameScoreValue.LOVE, player1.getScore().getGameScore());
	}
	
	@Test
	public void shouldReturnWinWhenGet40WhenDeuceDisactivated() {
		Player player1 = new Player(new Score(GameScoreValue.THIRTY));
		Player player2 = new Player(new Score(GameScoreValue.FOURTY));
		TennisGame game = new TennisGame(player1, player2);
		
		player2.markOnePoint(game);
			
		assertEquals(GameScoreValue.LOVE, player2.getScore().getGameScore());
	}
	
	@Test
	public void shouldReturnDeuceWhenPlayerLoseAdv() {
		Player player1 = new Player(new Score(GameScoreValue.THIRTY));
		Player player2 = new Player(new Score(GameScoreValue.FOURTY));
		TennisGame game = new TennisGame(player1, player2);
		
		player1.markOnePoint(game);
		player1.markOnePoint(game);
		player2.markOnePoint(game);
		
		assertEquals(GameScoreValue.DEUCE, player1.getScore().getGameScore());
	}
	
	@Test
	public void shouldReturnAdvWhenTwoPlayersGetDeuce() {
		Player player1 = new Player(new Score(GameScoreValue.THIRTY));
		Player player2 = new Player(new Score(GameScoreValue.FOURTY));
		TennisGame game = new TennisGame(player1, player2);
		
		player1.markOnePoint(game);
		player1.markOnePoint(game);
		player2.markOnePoint(game);
		player2.markOnePoint(game);
		
		assertEquals(GameScoreValue.DEUCE, player1.getScore().getGameScore());
		assertEquals(GameScoreValue.ADV, player2.getScore().getGameScore());
	}
	
	@Test
	public void shouldReturn0_1_0WhenPlayerWinSetGroup() {
		Player player2 = new Player(new Score(GameScoreValue.THIRTY, 0));
		Player player1 = new Player(new Score(GameScoreValue.FOURTY, 0));
		TennisGame game = new TennisGame(player2, player1);
		
		player1.markOnePoint(game);
		
		assertEquals("0 - 1 - 0", player1.getScore().toString());
		assertEquals("0 - 0 - 0", player2.getScore().toString());
	}
	
	@Test
	public void shouldDesactivateDeuceWhenPlayerWinGame() {
		Player player1 = new Player(new Score(GameScoreValue.THIRTY, 1));
		Player player2 = new Player(new Score(GameScoreValue.FOURTY, 1));
		TennisGame game = new TennisGame(player1, player2);
		
		player1.markOnePoint(game);
		player1.markOnePoint(game);
		player1.markOnePoint(game);
		
		assertFalse(game.isDeuce());
		assertEquals("0 - 1 - 0", player2.getScore().toString());
		assertEquals("0 - 2 - 0", player1.getScore().toString());
	}

//test match score	
	@Test
	public void shouldReturn0_0_1vs0_0_0WhenplayerWinAtTheSeventhGame() {
		Player player1 = new Player(new Score(GameScoreValue.THIRTY, 5));
		Player player2 = new Player(new Score(GameScoreValue.FOURTY, 6));
		TennisGame game = new TennisGame(player1, player2);
		
		player2.markOnePoint(game);
		
		assertFalse(game.isDeuce());
		assertEquals("0 - 0 - 1", player2.getScore().toString());
		assertEquals("0 - 0 - 0", player1.getScore().toString());
	}
	
	@Test
	public void shouldReturn0_0_1vs0_0_0Whenplayer2WinTheFirstSet() {
		Player player1 = new Player(new Score(GameScoreValue.THIRTY, 1));
		Player player2 = new Player(new Score(GameScoreValue.FOURTY, 5));
		TennisGame game = new TennisGame(player1, player2);
		
		player2.markOnePoint(game);
		
		assertFalse(game.isDeuce());
		assertEquals("0 - 0 - 1", player2.getScore().toString());
		assertEquals("0 - 0 - 0", player1.getScore().toString());
	}
	
	@Test
	public void shouldPlayer2GetFirstMatchPoinWhenWinSixGame() {
		Player player1 = new Player(new Score(GameScoreValue.THIRTY, 1, 0));
		Player player2 = new Player(new Score(GameScoreValue.FOURTY, 6, 0));
		TennisGame game = new TennisGame(player1, player2);
		
		boolean win = player2.markOnePoint(game);
		
		assertEquals("0 - 0 - 1", player2.getScore().toString());
		assertEquals("0 - 0 - 0", player1.getScore().toString());
		assertFalse(win);
	}
	
	@Test
	public void shouldActiveTieBreakWhenEachPlayerWinASet() {
		Player player1 = new Player(new Score(GameScoreValue.THIRTY, 1, 1));
		Player player2 = new Player(new Score(GameScoreValue.FOURTY, 6, 0));
		TennisGame game = new TennisGame(player1, player2);
		
		boolean win = player2.markOnePoint(game);
		
		assertEquals("0 - 0 - 1", player2.getScore().toString());
		assertEquals("0 - 0 - 1", player1.getScore().toString());
		assertTrue(game.isTieBreak());
		assertFalse(win);
	}
	
	@Test
	public void shouldPlayer2NotWinMatchWhenWinTwoSetTwice() {
		Player player1 = new Player(new Score(GameScoreValue.THIRTY, 1, 0));
		Player player2 = new Player(new Score(GameScoreValue.FOURTY, 6, 1));
		TennisGame game = new TennisGame(player1, player2);
		
		boolean win = player2.markOnePoint(game);
		
		assertEquals("0 - 0 - 2", player2.getScore().toString());
		assertEquals("0 - 0 - 0", player1.getScore().toString());
		assertTrue(win);
	}
	
	@Test
	public void shouldPlayer2BeWinnerMatchWhenWinTieBreakInSeventhSet() {
		Player player1 = new Player(new Score(GameScoreValue.THIRTY, 2, 1));
		Player player2 = new Player(new Score(GameScoreValue.FOURTY, 6, 1));
		TennisGame game = new TennisGame(player1, player2);
		game.setTieBreak(true);
		
		boolean win = player2.markOnePoint(game);
		
		assertEquals("0 - 0 - 2", player2.getScore().toString());
		assertEquals("0 - 0 - 1", player1.getScore().toString());
		assertTrue(win);
	}
	
	@Test
	public void shouldNotplayer2BeWinnerOfMatchWhenWinTieBreakInSixthSet() {
		Player player1 = new Player(new Score(GameScoreValue.THIRTY, 2, 1));
		Player player2 = new Player(new Score(GameScoreValue.FOURTY, 5, 1));
		TennisGame game = new TennisGame(player1, player2);
		game.setTieBreak(true);
		
		boolean win = player2.markOnePoint(game);
		
		assertEquals("0 - 6 - 1", player2.getScore().toString());
		assertEquals("0 - 2 - 1", player1.getScore().toString());
		assertFalse(win);
	}
	
	@Test
	public void shouldNotplayer2BeWinnerOfMatchWhenWinTieBreakInTenthSet() {
		Player player1 = new Player(new Score(GameScoreValue.THIRTY, 9, 1));
		Player player2 = new Player(new Score(GameScoreValue.FOURTY, 9, 1));
		TennisGame game = new TennisGame(player1, player2);
		game.setTieBreak(true);
		
		boolean win = player2.markOnePoint(game);
		
		assertEquals("0 - 10 - 1", player2.getScore().toString());
		assertEquals("0 - 9 - 1", player1.getScore().toString());
		assertFalse(win);
	}
	
	@Test
	public void shouldPlayer2BeWinnerOfMatchWhenWinTieBreakInNinethSet() {
		Player player1 = new Player(new Score(GameScoreValue.THIRTY, 7, 1));
		Player player2 = new Player(new Score(GameScoreValue.FOURTY, 8, 1));
		TennisGame game = new TennisGame(player1, player2);
		game.setTieBreak(true);
		
		boolean win = player2.markOnePoint(game);
		
		assertEquals("0 - 0 - 2", player2.getScore().toString());
		assertEquals("0 - 0 - 1", player1.getScore().toString());
		assertTrue(win);
	}
}

