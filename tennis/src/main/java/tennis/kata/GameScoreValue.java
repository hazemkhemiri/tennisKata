package tennis.kata;

public enum GameScoreValue {
	_0, _15, _30, _40,DEUCE, ADV, WIN;
	
	public GameScoreValue increment(boolean deuce) {
		
		switch (this) {
		case _40:
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
		return name().replace("_", "");
	}
	
}
