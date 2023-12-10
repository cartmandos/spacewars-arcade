package spacewars.game;

/**
 * This class is an implementing class of SpaceShip class representing a basher type spaceship in the game.
 */
public class Basher extends SpaceShip{


    /** The value of acceleration for this type of spaceship.*/
    private static final boolean ACCELERATION_MODE = true;

    /** The range in which to turn shield on.*/
    private static final double SHIELD_DISTANCE = 0.19;

    /**
     * This is an implement method for doAction.
     * Does the actions of this type of ship for this round.
     * This is called once per round by the SpaceWars game driver.
     * The basher will always accelerate and move towards the closest ship, if is close enough to another ship it will
     * attempt to turn its shield on.
     *
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        this.shield = false;
        closestShip = game.getClosestShipTo(this);
        this.setClosestShipAttributes(closestShip);
        this.physics.move(ACCELERATION_MODE, (int)(Math.signum(closestShipAngle)));
        if (closestShipDistance <= SHIELD_DISTANCE)
            this.shieldOn();
        this.recharge();
    }
}
