package spacewars.game;

/**
 * This class is an implementing class of SpaceShip class representing a runner type spaceship in the game.
 */
public class Runner extends SpaceShip{


    /** The value of acceleration for this type of spaceship.*/
    private static final boolean ACCELERATION_MODE = true;

    /** Values that define when the spaceship feels threatened.*/
    private static final double THREATENED_DISTANCE = 0.25;
    private static final double THREATENED_ANGLE = 0.23;

    /**
     * This is an implement method for doAction.
     * Does the actions of this type of ship for this round.
     * This is called once per round by the SpaceWars game driver.
     * The runner is always accelerating and moves to avoid the closest ship,
     * and when feels threatened will attempt to teleport.
     *
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game){
        closestShip = game.getClosestShipTo(this);
        this.setClosestShipAttributes(closestShip);
        if (closestShipDistance < THREATENED_DISTANCE && Math.abs(closestShipAngle) < THREATENED_ANGLE)
            this.teleport();
        this.physics.move(ACCELERATION_MODE, (int)(Math.signum(closestShipAngle)*(-1)));
        this.recharge();
    }
}