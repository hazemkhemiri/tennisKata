package tennis.kata;

public class ScoreManager {

	private TennisGame game;
	private Player winner, loser;

	public ScoreManager(TennisGame game) {
		this.game = game;
	}

	public TennisGame getGame() {
		return game;
	}

	private void updateGameScore(Player player) {
		Score score = player.getScore();
		final boolean deuce = game != null && game.isDeuce();
		GameScoreValue newScore = score.getGameScore().increment(deuce);
		if (newScore == null) {
			throw new IllegalArgumentException("The player is already won the set");
		}
		score.setGameScore(newScore);
		if (game != null) {
			if (GameScoreValue.FOURTY.equals(game.getGuestPlayer().getScore().getGameScore())
					&& GameScoreValue.FOURTY.equals(game.getLocalPlayer().getScore().getGameScore())) {
				game.setDeuce(true);
			} else if (GameScoreValue.ADV.equals(game.getGuestPlayer().getScore().getGameScore())
					&& GameScoreValue.ADV.equals(game.getLocalPlayer().getScore().getGameScore())) {
				game.getLocalPlayer().getScore().setGameScore(GameScoreValue.DEUCE);
				game.getGuestPlayer().getScore().setGameScore(GameScoreValue.DEUCE);
			}
		}
	}

	private void updateSetScore(Player player) {
		Score score = player.getScore();
		if (GameScoreValue.WIN.equals(game.getGuestPlayer().getScore().getGameScore())) {
			winner = game.getGuestPlayer();
			loser = game.getLocalPlayer();
		} else {
			winner = game.getLocalPlayer();
			loser = game.getGuestPlayer();
		}
		winner.getScore().setGameScore(GameScoreValue.LOVE);
		winner.getScore().setSetScore(score.getSetScore() + 1);
		loser.getScore().setGameScore(GameScoreValue.LOVE);
		game.setDeuce(false);
	}

	private boolean updateMatchScore(Player player) {
		Score score = player.getScore();
		boolean matchWinner = false;
		boolean isSetWon;

		if (game.isTieBreak()) {
			isSetWon = winner.getScore().getSetScore() >= 7
					&& winner.getScore().getSetScore() > loser.getScore().getSetScore() + 1;
		} else {
			isSetWon = (winner.getScore().getSetScore() == 6 && loser.getScore().getSetScore() <= 4)
					|| winner.getScore().getSetScore() == 7;
		}
		if (isSetWon) {
			score.setMatchScore(score.getMatchScore() + 1);
			winner.getScore().setSetScore(0);
			loser.getScore().setSetScore(0);
			if (score.getMatchScore() == 2) {
				matchWinner = true;
			}
		}
		if (!game.isTieBreak() && game.getGuestPlayer().getScore().getMatchScore() == 1
				&& game.getLocalPlayer().getScore().getMatchScore() == 1) {
			game.setTieBreak(true);
		}
		return matchWinner;
	}

	public boolean updateScore(Player player) {
		boolean winner = false;
		updateGameScore(player);
		if (game != null && GameScoreValue.WIN.equals(player.getScore().getGameScore())) {
			updateSetScore(player);
			winner = updateMatchScore(player);
		}
		return winner;
	}

}
