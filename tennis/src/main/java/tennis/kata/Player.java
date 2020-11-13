package tennis.kata;

public class Player {
	
	private Score score;

	public Player(Score score) {
		this.score = score;
	}
	
	public boolean markOnePoint(TennisGame game) {
		return new ScoreManager(game).updateScore(this);
	}

	public Score getScore() {
		return score;
	}
	
}
