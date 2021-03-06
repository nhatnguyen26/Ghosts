package ghost;

/**
 * Player class to represent player in the game.
 * @author BrainySmurf
 *
 */

public abstract class Player {
	
	public final static int FIRST_PLAYER = 0;
	public final static int SECOND_PLAYER = 1;
	
	private int turn;
	private String name;
	
	public Player(String name, int turn){
		this.name = name;
		this.turn = turn;
	}
	
	public int myTurn(){
		return this.turn;
	}
	
	public String getName(){
		return this.name;
	}
	
	public abstract char play(char lastPlayed);

}
