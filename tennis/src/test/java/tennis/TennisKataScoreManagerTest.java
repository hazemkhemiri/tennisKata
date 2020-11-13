package tennis;

import org.junit.Test;

import tennis.kata.Player;
import tennis.kata.Score;
import tennis.kata.GameScoreValue;
import tennis.kata.TennisGame;

import static org.junit.Assert.*;

public class TennisKataScoreManagerTest {
//test game score	
	@Test
	public void should_return_15_when_get_0() {
		
		Player player = new Player(new Score(GameScoreValue.LOVE));
		
		player.markOnePoint(null);
		
		assertEquals(GameScoreValue.FIFTEEN, player.getScore().getGameScore());
		
	}
	
	@Test
	public void should_return_30_when_get_15() {
		
		Player player = new Player(new Score(GameScoreValue.FIFTEEN));
		
		player.markOnePoint(null);
				
		assertEquals(GameScoreValue.THIRTY, player.getScore().getGameScore());
		
	}
	
	@Test
	public void should_return_40_when_get_30() {
		
		Player player = new Player(new Score(GameScoreValue.THIRTY));
		
		boolean win = player.markOnePoint(null);
		
		assertFalse(win);
		assertEquals(GameScoreValue.FOURTY, player.getScore().getGameScore());
		
	}
	
	@Test
	public void should_return_win_when_get_40() {
		
		Player player = new Player(new Score(GameScoreValue.FOURTY));
		
		player.markOnePoint(null);
		
		assertEquals(GameScoreValue.WIN, player.getScore().getGameScore());
		
	}

	@Test
	public void should_return_null_when_increment_from_WIN () {

		Player player = new Player(new Score(GameScoreValue.WIN));

		player.markOnePoint(null);

		assertNull(player.getScore().getGameScore());

	}
	
	@Test
	public void should_activate_deuce_when_two_players_get_40() {
		Player player1 = new Player(new Score(GameScoreValue.THIRTY));
		Player player2 = new Player(new Score(GameScoreValue.FOURTY));
		TennisGame game = new TennisGame(player1, player2);
		
		player1.markOnePoint(game);
		
		assertTrue(game.isDeuce());
	}
	
	@Test
	public void should_return_ADV_when_get_40_and_deuce_is_Activated() {
		Player player1 = new Player(new Score(GameScoreValue.THIRTY));
		Player player2 = new Player(new Score(GameScoreValue.FOURTY));
		TennisGame game = new TennisGame(player1, player2);
		
		player1.markOnePoint(game);
		player1.markOnePoint(game);
		
		assertEquals(GameScoreValue.ADV, player1.getScore().getGameScore());
	}

// test set	score
	@Test
	public void should_Win_Game_when_get_ADV_and_deuce_is_activated() {
		Player player1 = new Player(new Score(GameScoreValue.THIRTY));
		Player player2 = new Player(new Score(GameScoreValue.FOURTY));
		TennisGame game = new TennisGame(player1, player2);
		
		player1.markOnePoint(game);
		player1.markOnePoint(game);
		player1.markOnePoint(game);
		
		assertEquals(GameScoreValue.LOVE, player1.getScore().getGameScore());
	}
	
	@Test
	public void should_win_game_when_get_40_when_deuce_is_not_activated() {
		Player player1 = new Player(new Score(GameScoreValue.THIRTY));
		Player player2 = new Player(new Score(GameScoreValue.FOURTY));
		TennisGame game = new TennisGame(player1, player2);
		
		player2.markOnePoint(game);
			
		assertEquals(GameScoreValue.LOVE, player2.getScore().getGameScore());
	}
	
	@Test
	public void should_get_deuce_when_player_lose_ADV() {
		Player player1 = new Player(new Score(GameScoreValue.THIRTY));
		Player player2 = new Player(new Score(GameScoreValue.FOURTY));
		TennisGame game = new TennisGame(player1, player2);
		
		player1.markOnePoint(game);
		player1.markOnePoint(game);
		player2.markOnePoint(game);
		
		assertEquals(GameScoreValue.DEUCE, player1.getScore().getGameScore());
	}
	
	@Test
	public void should_get_ADV_when_two_players_get_Deuce() {
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
	public void should_return_0_1_0_when_player_win_Group() {
		Player player2 = new Player(new Score(GameScoreValue.THIRTY, 0));
		Player player1 = new Player(new Score(GameScoreValue.FOURTY, 0));
		TennisGame game = new TennisGame(player2, player1);
		
		player1.markOnePoint(game);
		
		assertEquals("0 - 1 - 0", player1.getScore().toString());
		assertEquals("0 - 0 - 0", player2.getScore().toString());
	}
	
	@Test
	public void should_disactivate_deuce_when_player_win_game() {
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
	public void should_return_0_0_1_vs_0_0_0_when_player_win_seventh_game() {
		Player player1 = new Player(new Score(GameScoreValue.THIRTY, 5));
		Player player2 = new Player(new Score(GameScoreValue.FOURTY, 6));
		TennisGame game = new TennisGame(player1, player2);
		
		player2.markOnePoint(game);
		
		assertFalse(game.isDeuce());
		assertEquals("0 - 0 - 1", player2.getScore().toString());
		assertEquals("0 - 0 - 0", player1.getScore().toString());
	}
	
	@Test
	public void should_return_0_0_1_vs_0_0_0_when_player2_win_first_set() {
		Player player1 = new Player(new Score(GameScoreValue.THIRTY, 1));
		Player player2 = new Player(new Score(GameScoreValue.FOURTY, 5));
		TennisGame game = new TennisGame(player1, player2);
		
		player2.markOnePoint(game);
		
		assertFalse(game.isDeuce());
		assertEquals("0 - 0 - 1", player2.getScore().toString());
		assertEquals("0 - 0 - 0", player1.getScore().toString());
	}
	
	@Test
	public void should_player2_win_first_Match_Point_when_win_six_games() {
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
	public void should_player2_win_match_when_win_two_set_twice() {
		Player player1 = new Player(new Score(GameScoreValue.THIRTY, 1, 0));
		Player player2 = new Player(new Score(GameScoreValue.FOURTY, 6, 1));
		TennisGame game = new TennisGame(player1, player2);
		
		boolean win = player2.markOnePoint(game);
		
		assertEquals("0 - 0 - 2", player2.getScore().toString());
		assertEquals("0 - 0 - 0", player1.getScore().toString());
		assertTrue(win);
	}
	
	@Test
	public void should_player2_win_match_when_win_tie_break_in_seventh_set() {
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
	public void should_not_player2_be_winner_of_match_when_win_tie_break_in_sixth_set() {
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
	public void should_not_player2_be_winner_when_win_tie_break_in_Tenth_set() {
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
	public void should_player2_be_winner_when_win_tie_break_in_nineth_set() {
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
