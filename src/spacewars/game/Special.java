package spacewars.game;

/**
 * This class is an implementing class of SpaceShip class representing a special type spaceship in the game.
 */
public class Special extends SpaceShip{


    /** The value of acceleration for this type of spaceship.*/
    private static final boolean ACCELERATION_MODE = true;

    /** Values that define when the spaceship feels threatened.*/
    private static final double THREATENED_DISTANCE = 0.15;
    private static final double THREATENED_ANGLE = 0.12;

    /** Values that defines which direction in relation to closest ship.*/
    private static final int FACE_ENEMY = 1;
    private static final int AVOID_ENEMY = -1;

    /** Value that defines if the spaceship is energized enough to teleport.*/
    private static final int ENERGIZED = 120;

    /** Value that defines if the spaceship's health is within danger zone.*/
    private static final int DANGER_ZONE = 3;

    /** The range in which to turn shield on.*/
    private static final double SHIELD_DISTANCE = 0.15;

    /** The range in which the ship will attempt to fire.*/
    private static final double FIRING_RANGE = 0.20;

    /**
     * This is an implement method for doAction.
     * Does the actions of this type of ship for this round.
     * This is called once per round by the SpaceWars game driver.
     * The special type spaceship is always accelerating and will attempt to
     * face enemies and fire at them when in range.
     * If it feels threatened and is energized enough it will teleport.
     * else if it's threatened or its health is within danger zone it will
     * attempt to avoid nearest spaceships.
     *
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        this.shield = false; int direction = FACE_ENEMY;
        closestShip = game.getClosestShipTo(this);
        this.setClosestShipAttributes(closestShip);
        if (closestShipDistance < THREATENED_DISTANCE && Math.abs(closestShipAngle) < THREATENED_ANGLE) {
            if (this.currentEnergy > ENERGIZED) this.teleport();
            else direction = AVOID_ENEMY;
        }
        if (this.health < DANGER_ZONE) direction = AVOID_ENEMY;
        this.physics.move(ACCELERATION_MODE, (int) (Math.signum(closestShipAngle))* (direction));
        if (closestShipDistance <= SHIELD_DISTANCE) this.shieldOn();
        if (Math.abs(closestShipAngle) < FIRING_RANGE) this.fire(game);
        this.recharge();
    }
}
