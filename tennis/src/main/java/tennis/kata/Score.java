package tennis.kata;

public class Score {
	private GameScoreValue gameScore;
	private Integer setScore = 0;
	private Integer matchScore = 0;
	
	public Score(GameScoreValue gameScore, Integer setScore, Integer matchScore) {
		this.gameScore = gameScore;
		this.setScore = setScore;
		this.matchScore = matchScore;
	}

	public Score(GameScoreValue game, Integer set) {
		this(game, set, 0);
	}
	
	public Score(GameScoreValue scoreSet) {
		this(scoreSet, 0, 0);
	}

	public Integer getMatchScore() {
		return matchScore;
	}

	public void setMatchScore(Integer scoreMatch) {
		this.matchScore = scoreMatch;
	}

	public void setGameScore(GameScoreValue score) {
		this.gameScore = score;
	}

	public void setSetScore(Integer score) {
		this.setScore = score;
	}

	public GameScoreValue getGameScore() {
		return gameScore;
	}

	public Integer getSetScore() {
		return setScore;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(gameScore.toString()).append(" - ").append(setScore);
		builder.append(" - ").append(matchScore);
		
		return builder.toString();
	}

}
