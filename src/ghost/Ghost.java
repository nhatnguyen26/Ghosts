package ghost;

import java.util.ArrayList;

/**
 * Ghost class carry out the Ghost Game
 * @author BrainySmurf
 *
 */

public class Ghost {
	
	private Dictionary gameDict;
	public static final int NUM_OF_PLAYERS = 2;
	
	private LetterNode gameState;
	private StringBuffer strInPlay;
	private ArrayList<Player> players;
	
	public Ghost() {
		gameDict = new Dictionary();
		this.gameState = gameDict.getDict();
		this.players = new ArrayList<Player>();
		this.players.add(new CPUPlayer("1",0,gameDict));
		this.players.add(new CPUPlayer("2",1, gameDict));
	}
	
	public Ghost(String humanName) {
		gameDict = new Dictionary();
		this.gameState = gameDict.getDict();
		this.players = new ArrayList<Player>();
		this.players.add(new HumanPlayer(humanName,0));
		this.players.add(new CPUPlayer(1, gameDict));
	}
	
	public Ghost(String dictName, String humanName) {
		gameDict = new Dictionary(dictName);
		this.gameState = gameDict.getDict();
		this.players = new ArrayList<Player>();
		this.players.add(new HumanPlayer(humanName,0));
		this.players.add(new CPUPlayer(1, gameDict));
		
	}
	
	public Ghost(String dictName, int length, String humanName) {
		gameDict = new Dictionary(dictName,length);
		this.gameState = gameDict.getDict();
		this.players = new ArrayList<Player>();
		this.players.add(new HumanPlayer(humanName,0));
		this.players.add(new CPUPlayer(1, gameDict));
	}
	
	private Player nextPlayer(int turn) {
		return this.players.get((turn+1)%2);
	}
	
	private boolean isGhostFinished() {
		return this.gameState.isLeafNode();
	}
	
	public Player playGame(){
		this.strInPlay = new StringBuffer();
		Player currentPlayer = this.players.get(0);
		int turn;
		char charPlayed = this.gameState.getLetter();
		for (turn = 0; !this.isGhostFinished(); turn++) {
			charPlayed = currentPlayer.play(charPlayed);
			System.out.println(currentPlayer.getName() + " played " + charPlayed);
			this.strInPlay.append(charPlayed);
			System.out.println("Current play is: " + this.strInPlay.toString());
			currentPlayer = this.nextPlayer(turn);
			this.gameState = this.gameState.getChild(charPlayed);
			if (this.gameState == null)
				break;
		}
		return currentPlayer;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ghost game = new Ghost("Nhat");
		Player winner = game.playGame();
		System.out.println("Winner is: " + winner.getName());

	}

}
