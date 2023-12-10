package spacewars.game;

/**
 * This class is an implementing class of SpaceShip class representing an aggressive type spaceship in the game.
 */
public class Aggressive extends SpaceShip{


    /** The value of acceleration for this type of spaceship.*/
    private static final boolean ACCELERATION_MODE = true;

    /** The range in which the ship will attempt to fire.*/
    private static final double FIRING_RANGE = 0.21;

    /**
     * This is an implement method for doAction.
     * Does the actions of this type of ship for this round.
     * This is called once per round by the SpaceWars game driver.
     * The aggressive type will always accelerate and move towards the nearest ship,
     * if is within range will attempt to fire at the enemy's ship.
     *
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game){
        closestShip = game.getClosestShipTo(this);
        this.setClosestShipAttributes(closestShip);
        this.physics.move(ACCELERATION_MODE, (int)(Math.signum(closestShipAngle)));
        if (Math.abs(closestShipAngle) < FIRING_RANGE)
            this.fire(game);
        this.recharge();
    }
}
