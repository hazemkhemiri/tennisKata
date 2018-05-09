package tennis.kata;

public enum GameScoreValue {
	LOVE(0), FIFTEEN(15), THIRTY(30), FOURTY(40), DEUCE, ADV, WIN;
	
	private Integer value;
	
	private GameScoreValue(){}
	
	private GameScoreValue(Integer value) {
		this.value = value;
	}
	public GameScoreValue increment(boolean deuce) {
		
		switch (this) {
		case FOURTY:
			if (deuce) {
				return ADV;
			} else {
				return WIN;
			}
		case WIN:
			return null;

		default:
			return GameScoreValue.values()[ordinal() + 1];
		}
	}
	
	@Override
	public String toString() {
		
		return value == null ? "none" : value.toString();
	}

	public Integer getValue() {
		return value;
	}

}
