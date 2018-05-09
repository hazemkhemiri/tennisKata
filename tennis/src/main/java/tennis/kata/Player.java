package tennis.kata;

public class Player {
	
	private Score score;

	public Player(Score score) {
		this.score = score;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Player:\t").append(score.toString());
		return builder.toString();
	}
	
	public boolean markOnePoint(TennisGame game) {
		return new ScoreManager(game).updateScore(this);
	}
	
	public void setScore(Score score) {
		this.score = score;
	}
	
	public Score getScore() {
		return score;
	}
	
}
