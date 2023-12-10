package spacewars.game;

/**
 * This class is an implementing class of SpaceShip class representing a drunkard type spaceship in the game.
 */
public class Drunkard extends SpaceShip{


    /** The value of acceleration for this type of spaceship.*/
    private static final boolean ACCELERATION_MODE = true;

    /** The max (exclusive) value for the random number from zero to this max.*/
    private static final int EXCLUSIVE_MAX = 1001;

    /** Values defining the turn direction bounds of the ship.*/
    private static final int RIGHT_DIRECTION_MAX = 150;
    private static final int LEFT_DIRECTION_MIN = 700;

    /** Values defining if to attempt action.*/
    private static final int TELEPORT = 0;
    private static final int SHIELD = 50;
    private static final int FIRE = 925;

    /**
     * This is an implement method for doAction.
     * Does the actions of this type of ship for this round.
     * This is called once per round by the SpaceWars game driver.
     * The drunkard had too much to drink and is pressing buttons in the ship randomly.
     *
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        this.shield = false; int turn = FORWARD;
        int randomZeroToThousand = (int) (Math.random() * EXCLUSIVE_MAX);
        if (randomZeroToThousand == TELEPORT) this.teleport();
        if (randomZeroToThousand < RIGHT_DIRECTION_MAX) turn = RIGHT;
        else if (randomZeroToThousand > LEFT_DIRECTION_MIN) turn = LEFT;
        this.physics.move(ACCELERATION_MODE, turn);
        if (randomZeroToThousand < SHIELD) this.shieldOn();
        if (randomZeroToThousand > FIRE) this.fire(game);
        this.recharge();
    }
}
