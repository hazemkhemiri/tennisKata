package tennis.kata;

public class Player {
	
	private String playerName;
	private Score score;

	public Player(String playerName, Score score) {
		this.playerName = playerName;
		this.score = score;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(playerName).append(":\t").append(score.toString());
		return builder.toString();
	}

	public boolean markOnePoint(TennisGame game) {
		final boolean deuce = game != null && game.isDeuce();
		GameScoreValue newScore = score.getScoreSet().increment(deuce);
		if (newScore == null) {
			throw new IllegalArgumentException("The player is already won the set");
		}
		score.setGameScore(newScore);
		boolean matchWinner = false;
		if (game != null) {
			if (GameScoreValue._40.equals(game.getGuestPlayer().getScore().getScoreSet())
					&& GameScoreValue._40.equals(game.getLocalPlayer().getScore().getScoreSet()) ) {
				game.setDeuce(true);
			} else if (GameScoreValue.ADV.equals(game.getGuestPlayer().getScore().getScoreSet())
					&& GameScoreValue.ADV.equals(game.getLocalPlayer().getScore().getScoreSet()) ) {
				game.getLocalPlayer().getScore().setGameScore(GameScoreValue.DEUCE);
				game.getGuestPlayer().getScore().setGameScore(GameScoreValue.DEUCE);
			} else if (newScore == GameScoreValue.WIN) {
				boolean isSetWon;
				Player winner, loser;
				if (GameScoreValue.WIN.equals(game.getGuestPlayer().getScore().getScoreSet())) {
					winner = game.getGuestPlayer();
					loser = game.getLocalPlayer();
				} else {
					winner = game.getLocalPlayer();
					loser = game.getGuestPlayer();
				}
				winner.getScore().setGameScore(GameScoreValue._0);
				winner.getScore().setSetScore(score.getScoreGame() + 1);
				loser.getScore().setGameScore(GameScoreValue._0);
				game.setDeuce(false);
				if (game.isTieBreak()) {
					isSetWon = winner.getScore().getScoreGame() >= 7 && winner.getScore().getScoreGame() > loser.getScore().getScoreGame() + 1;
				} else {
					isSetWon = (winner.getScore().getScoreGame() == 6 && loser.getScore().getScoreGame() <= 4) || winner.getScore().getScoreGame() == 7; 
				}
				if (isSetWon) {
					score.setMatchScore(score.getMatchScore() + 1);
					winner.getScore().setSetScore(0);
					loser.getScore().setSetScore(0);
					if (score.getMatchScore() == 2) {
						matchWinner = true;
					}
				}
				if (!game.isTieBreak() && game.getGuestPlayer().getScore().getMatchScore() == 1 && game.getLocalPlayer().getScore().getMatchScore() == 1) {
					game.setTieBreak(true);
				}
				
			}
		}
		return matchWinner;
	}
	
	public void setScore(Score score) {
		this.score = score;
	}
	
	public Score getScore() {
		return score;
	}
	

}
