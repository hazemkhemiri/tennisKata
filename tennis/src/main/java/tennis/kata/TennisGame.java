package tennis.kata;

public class TennisGame {
	private Player guestPlayer;
	private Player localPlayer;
	private boolean deuce;
	private boolean tieBreak;
	
	public TennisGame(String guestPlayerName, String localPlayerName) {
		setGuestPlayer(new Player(new Score(GameScoreValue.LOVE, 0)));
		setLocalPlayer(new Player(new Score(GameScoreValue.LOVE, 0)));
	}
	
	public TennisGame(Player guestPlayer, Player localPlayer) {
		setGuestPlayer(guestPlayer);
		setLocalPlayer(localPlayer);
	}

	public Player getGuestPlayer() {
		return guestPlayer;
	}

	public void setGuestPlayer(Player guestPlayer) {
		this.guestPlayer = guestPlayer;
	}

	public Player getLocalPlayer() {
		return localPlayer;
	}

	public void setLocalPlayer(Player localPlayer) {
		this.localPlayer = localPlayer;
	}
	
	public Boolean isDeuce() {
		return deuce;
	}
	
	public void setDeuce(Boolean deuce) {
		this.deuce = deuce;
	}

	public Boolean isTieBreak() {
		return tieBreak;
	}

	public void setTieBreak(Boolean tieBreak) {
		this.tieBreak = tieBreak;
	}
	
}
