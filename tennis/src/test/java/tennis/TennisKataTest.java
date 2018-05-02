package tennis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tennis.kata.Player;
import tennis.kata.Score;
import tennis.kata.GameScoreValue;
import tennis.kata.TennisGame;

public class TennisKataTest {
	
	private static final String ROGER = "Roger Federer";
	private static final String RAPHAEL = "Raphael Nadal";

	@Test
	public void shouldReturn15WhenGet0() {
		
		Player player = new Player(ROGER, new Score(GameScoreValue._0));
		
		player.markOnePoint(null);
		
		assertEquals(GameScoreValue._15, player.getScore().getScoreSet());
		
	}
	
	@Test
	public void shouldReturn30WhenGet15() {
		
		Player player = new Player(ROGER, new Score(GameScoreValue._15));
		
		player.markOnePoint(null);
				
		assertEquals(GameScoreValue._30, player.getScore().getScoreSet());
		
	}
	
	@Test
	public void shouldReturn40WhenGet30() {
		
		Player player = new Player(ROGER, new Score(GameScoreValue._30));
		
		boolean win = player.markOnePoint(null);
		
		assertFalse(win);
		assertEquals(GameScoreValue._40, player.getScore().getScoreSet());
		
	}
	
	@Test
	public void shouldReturnWinWhenGet40() {
		
		Player player = new Player(ROGER, new Score(GameScoreValue._40));
		
		player.markOnePoint(null);
		
		assertEquals(GameScoreValue.WIN, player.getScore().getScoreSet());
		
	}
	
	@Test
	public void shouldActivateDeuceWhenTwoPlayersGet40() {
		Player guestPlayer = new Player(ROGER, new Score(GameScoreValue._30));
		Player localPlayer = new Player(RAPHAEL, new Score(GameScoreValue._40));
		TennisGame game = new TennisGame(guestPlayer, localPlayer);
		
		guestPlayer.markOnePoint(game);
		
		assertTrue(game.isDeuce());
	}
	
	@Test
	public void shouldReturnAdvWhenGet40AndDeuceIsActivated() {
		Player guestPlayer = new Player(ROGER, new Score(GameScoreValue._30));
		Player localPlayer = new Player(RAPHAEL, new Score(GameScoreValue._40));
		TennisGame game = new TennisGame(guestPlayer, localPlayer);
		
		guestPlayer.markOnePoint(game);
		guestPlayer.markOnePoint(game);
		
		assertEquals(GameScoreValue.ADV, guestPlayer.getScore().getScoreSet());
	}
	
	@Test
	public void shouldReturnWinWhenGetAdvAndDeuceActivated() {
		Player guestPlayer = new Player(ROGER, new Score(GameScoreValue._30));
		Player localPlayer = new Player(RAPHAEL, new Score(GameScoreValue._40));
		TennisGame game = new TennisGame(guestPlayer, localPlayer);
		
		guestPlayer.markOnePoint(game);
		guestPlayer.markOnePoint(game);
		guestPlayer.markOnePoint(game);
		
		assertEquals(GameScoreValue._0, guestPlayer.getScore().getScoreSet());
	}
	
	@Test
	public void shouldReturnWinWhenGet40WhenDeuceDisactivated() {
		Player guestPlayer = new Player(ROGER, new Score(GameScoreValue._30));
		Player localPlayer = new Player(RAPHAEL, new Score(GameScoreValue._40));
		TennisGame game = new TennisGame(guestPlayer, localPlayer);
		
		localPlayer.markOnePoint(game);
			
		assertEquals(GameScoreValue._0, localPlayer.getScore().getScoreSet());
	}
	
	@Test
	public void shouldReturnDeuceWhenPlayerLoseAdv() {
		Player guestPlayer = new Player(ROGER, new Score(GameScoreValue._30));
		Player localPlayer = new Player(RAPHAEL, new Score(GameScoreValue._40));
		TennisGame game = new TennisGame(guestPlayer, localPlayer);
		
		guestPlayer.markOnePoint(game);
		guestPlayer.markOnePoint(game);
		localPlayer.markOnePoint(game);
		
		assertEquals(GameScoreValue.DEUCE, guestPlayer.getScore().getScoreSet());
	}
	
	@Test
	public void shouldReturnAdvWhenTwoPlayersGetDeuce() {
		Player guestPlayer = new Player(ROGER, new Score(GameScoreValue._30));
		Player localPlayer = new Player(RAPHAEL, new Score(GameScoreValue._40));
		TennisGame game = new TennisGame(guestPlayer, localPlayer);
		
		guestPlayer.markOnePoint(game);
		guestPlayer.markOnePoint(game);
		localPlayer.markOnePoint(game);
		localPlayer.markOnePoint(game);
		
		assertEquals(GameScoreValue.DEUCE, guestPlayer.getScore().getScoreSet());
		assertEquals(GameScoreValue.ADV, localPlayer.getScore().getScoreSet());
	}
	
	@Test
	public void shouldReturn1WhenplayerWinSetGroup() {
		Player guestPlayer = new Player(ROGER, new Score(GameScoreValue._30, 0));
		Player localPlayer = new Player(RAPHAEL, new Score(GameScoreValue._40, 0));
		TennisGame game = new TennisGame(guestPlayer, localPlayer);
		
		localPlayer.markOnePoint(game);
		
		assertEquals("0 - 1 - 0", localPlayer.getScore().toString());
		assertEquals("0 - 0 - 0", guestPlayer.getScore().toString());
	}
	
	@Test
	public void shouldDesactivateDeuceWhenPlayerWinGame() {
		Player guestPlayer = new Player(ROGER, new Score(GameScoreValue._30, 1));
		Player localPlayer = new Player(RAPHAEL, new Score(GameScoreValue._40, 1));
		TennisGame game = new TennisGame(guestPlayer, localPlayer);
		
		guestPlayer.markOnePoint(game);
		guestPlayer.markOnePoint(game);
		guestPlayer.markOnePoint(game);
		
		assertFalse(game.isDeuce());
		assertEquals("0 - 1 - 0", localPlayer.getScore().toString());
		assertEquals("0 - 2 - 0", guestPlayer.getScore().toString());
	}
	
	@Test
	public void shouldReturn0_0_1vs0_0_0WhenplayerWinAtTheSeventhGame() {
		Player guestPlayer = new Player(ROGER, new Score(GameScoreValue._30, 5));
		Player localPlayer = new Player(RAPHAEL, new Score(GameScoreValue._40, 6));
		TennisGame game = new TennisGame(guestPlayer, localPlayer);
		
		localPlayer.markOnePoint(game);
		
		assertFalse(game.isDeuce());
		assertEquals("0 - 0 - 1", localPlayer.getScore().toString());
		assertEquals("0 - 0 - 0", guestPlayer.getScore().toString());
	}
	
	@Test
	public void shouldReturn0_0_1vs0_0_0WhenLocalPlayerWinTheFirstSet() {
		Player guestPlayer = new Player(ROGER, new Score(GameScoreValue._30, 1));
		Player localPlayer = new Player(RAPHAEL, new Score(GameScoreValue._40, 5));
		TennisGame game = new TennisGame(guestPlayer, localPlayer);
		
		localPlayer.markOnePoint(game);
		
		assertFalse(game.isDeuce());
		assertEquals("0 - 0 - 1", localPlayer.getScore().toString());
		assertEquals("0 - 0 - 0", guestPlayer.getScore().toString());
	}
	
	@Test
	public void shouldLocalPlayerGetFirstMatchPoinWhenWinSixGame() {
		Player guestPlayer = new Player(ROGER, new Score(GameScoreValue._30, 1, 0));
		Player localPlayer = new Player(RAPHAEL, new Score(GameScoreValue._40, 6, 0));
		TennisGame game = new TennisGame(guestPlayer, localPlayer);
		
		boolean win = localPlayer.markOnePoint(game);
		
		assertEquals("0 - 0 - 1", localPlayer.getScore().toString());
		assertEquals("0 - 0 - 0", guestPlayer.getScore().toString());
		assertFalse(win);
	}
	
	@Test
	public void shouldActiveTieBreakWhenEachPlayerWinASet() {
		Player guestPlayer = new Player(ROGER, new Score(GameScoreValue._30, 1, 1));
		Player localPlayer = new Player(RAPHAEL, new Score(GameScoreValue._40, 6, 0));
		TennisGame game = new TennisGame(guestPlayer, localPlayer);
		
		boolean win = localPlayer.markOnePoint(game);
		
		assertEquals("0 - 0 - 1", localPlayer.getScore().toString());
		assertEquals("0 - 0 - 1", guestPlayer.getScore().toString());
		assertTrue(game.isTieBreak());
		assertFalse(win);
	}
	
	@Test
	public void shouldLocalPlayerNotWinMatchWhenWinTwoSetTwice() {
		Player guestPlayer = new Player(ROGER, new Score(GameScoreValue._30, 1, 0));
		Player localPlayer = new Player(RAPHAEL, new Score(GameScoreValue._40, 6, 1));
		TennisGame game = new TennisGame(guestPlayer, localPlayer);
		
		boolean win = localPlayer.markOnePoint(game);
		
		assertEquals("0 - 0 - 2", localPlayer.getScore().toString());
		assertEquals("0 - 0 - 0", guestPlayer.getScore().toString());
		assertTrue(win);
	}
	
	@Test
	public void shouldLocalPlayerBeWinnerMatchWhenWinTieBreakInSeventhSet() {
		Player guestPlayer = new Player(ROGER, new Score(GameScoreValue._30, 2, 1));
		Player localPlayer = new Player(RAPHAEL, new Score(GameScoreValue._40, 6, 1));
		TennisGame game = new TennisGame(guestPlayer, localPlayer);
		game.setTieBreak(true);
		
		boolean win = localPlayer.markOnePoint(game);
		
		assertEquals("0 - 0 - 2", localPlayer.getScore().toString());
		assertEquals("0 - 0 - 1", guestPlayer.getScore().toString());
		assertTrue(win);
	}
	
	@Test
	public void shouldNotLocalPlayerBeWinnerOfMatchWhenWinTieBreakInSixthSet() {
		Player guestPlayer = new Player(ROGER, new Score(GameScoreValue._30, 2, 1));
		Player localPlayer = new Player(RAPHAEL, new Score(GameScoreValue._40, 5, 1));
		TennisGame game = new TennisGame(guestPlayer, localPlayer);
		game.setTieBreak(true);
		
		boolean win = localPlayer.markOnePoint(game);
		
		assertEquals("0 - 6 - 1", localPlayer.getScore().toString());
		assertEquals("0 - 2 - 1", guestPlayer.getScore().toString());
		assertFalse(win);
	}
	
	@Test
	public void shouldNotLocalPlayerBeWinnerOfMatchWhenWinTieBreakInTenthSet() {
		Player guestPlayer = new Player(ROGER, new Score(GameScoreValue._30, 9, 1));
		Player localPlayer = new Player(RAPHAEL, new Score(GameScoreValue._40, 9, 1));
		TennisGame game = new TennisGame(guestPlayer, localPlayer);
		game.setTieBreak(true);
		
		boolean win = localPlayer.markOnePoint(game);
		
		assertEquals("0 - 10 - 1", localPlayer.getScore().toString());
		assertEquals("0 - 9 - 1", guestPlayer.getScore().toString());
		assertFalse(win);
	}
	
	@Test
	public void shouldLocalPlayerBeWinnerOfMatchWhenWinTieBreakInNinethSet() {
		Player guestPlayer = new Player(ROGER, new Score(GameScoreValue._30, 7, 1));
		Player localPlayer = new Player(RAPHAEL, new Score(GameScoreValue._40, 8, 1));
		TennisGame game = new TennisGame(guestPlayer, localPlayer);
		game.setTieBreak(true);
		
		boolean win = localPlayer.markOnePoint(game);
		
		assertEquals("0 - 0 - 2", localPlayer.getScore().toString());
		assertEquals("0 - 0 - 1", guestPlayer.getScore().toString());
		assertTrue(win);
	}
}

