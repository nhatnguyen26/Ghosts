package ghost;

/**
 * Player class to represent player in the game.
 * @author BrainySmurf
 *
 */

public abstract class Player {
	
	final static int FIRST_PLAYER = 0;
	final static int SECOND_PLAYER = 1;
	
	private int turn;
	private String name;
	
	public Player(String name, int turn){
		this.name = name;
		this.turn = turn;
	}
	
	public int Turn(){
		return this.turn;
	}
	
	public String getName(){
		return this.name;
	}
	
	public abstract char play();

}
