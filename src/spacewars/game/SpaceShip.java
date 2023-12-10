package spacewars.game;

import spacewars.physics.*;
import spacewars.ui.*;

import java.awt.Image;

/**
 * This class is an abstract class representing a spaceship in the game.
 * All spaceships in the game inherit these methods and data fields.
 */
public abstract class SpaceShip{

    /** The values for turning directions of the spaceship.*/
    protected static final int FORWARD = 0;
    protected static final int LEFT = 1;
    protected static final int RIGHT = -1;

    /** The Initialization values of spaceship settings.*/
    private static final int INITIAL_HEALTH = 25;
    private static final int INITIAL_ENERGY = 200;
    private static final int MAX_ENERGY = 250;

    /** The values of points & costs for each action made by the spaceship.*/
    private static final int NO_POINTS = 0;
    private static final int BASHING_POINTS = 20;
    private static final int BASHING_COST = 15;
    private static final int MAX_ENERGY_HIT_COST = 5;
    private static final int CURRENT_ENERGY_HIT_COST = 10;
    private static final int SHIELD_SHOT_COST = 2;
    private static final int SHIELD_COST_PER_ROUND = 3;
    private static final int TELEPORT_COST = 100;
    private static final int FIRE_COST = 15;

    /** The number of rounds to cool down between each fire.*/
    private static final int COOLDOWN_ROUNDS = 7;

    /** The health value of a dead ship.*/
    private static final int DEAD = 0;

    /** The state of the spaceship's shield: on/off.*/
    protected boolean shield;

    /** Attributes for the closest ship in the game.*/
    protected SpaceShip closestShip;
    protected double closestShipDistance;
    protected double closestShipAngle;

    /** Spaceship's data members.*/
    protected SpaceShipPhysics physics;
    protected int health;
    protected int currentEnergy;
    private int maxEnergy;
    private int fireCooldownRoundsLeft;

    /**
     * The default constructor of the class.
     * This constructor creates a new spaceship according to setSpaceShip() method.
     */
    public SpaceShip(){setSpaceShip(); }

    /**
     * This method sets ALL data members according to their initialization values,
     * Creates a new SpaceShipPhysics object and resets the counter of cool down rounds for firing ability.
     */
    private void setSpaceShip(){
        this.physics = new SpaceShipPhysics();
        this.health = INITIAL_HEALTH;
        this.currentEnergy = INITIAL_ENERGY;
        this.maxEnergy = MAX_ENERGY;
        this.fireCooldownRoundsLeft = 0;
    }

    /**
     * Does the actions of this ship for this round. 
     * This is called once per round by the SpaceWars game driver.
     * 
     * @param game the game object to which this ship belongs.
     */
    public abstract void doAction(SpaceWars game);

    /**
     * This method is called every time a collision with this ship occurs.
     */
    public void collidedWithAnotherShip(){
        gotHit(BASHING_COST, BASHING_POINTS);
    }

    /** 
     * This method is called whenever a ship has died. It resets the ship's 
     * attributes, and starts it at a new random position.
     */
    public void reset(){setSpaceShip(); }

    /**
     * Checks if this ship is dead.
     * 
     * @return true if the ship is dead. false otherwise.
     */
    public boolean isDead() {return this.health == DEAD ; }

    /**
     * Gets the physics object that controls this ship.
     * 
     * @return the physics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics() {
        return this.physics;
    }

    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets shot at (with or without a shield).
     */
    public void gotShot() {
        gotHit(SHIELD_SHOT_COST, NO_POINTS);
    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     * 
     * @return the image of this ship.
     */
    public Image getImage(){
        if (this.shield)
            return GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
        return GameGUI.ENEMY_SPACESHIP_IMAGE;
    }

    /**
     * Attempts to fire a shot.
     * 
     * @param game the game object.
     */
    public void fire(SpaceWars game) {
       if (this.fireCooldownRoundsLeft == 0 && isEnoughEnergy(FIRE_COST)){
           this.currentEnergy -= FIRE_COST;
           game.addShot(this.physics);
           this.fireCooldownRoundsLeft = COOLDOWN_ROUNDS;
       }
    }

    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn() {
        if (isEnoughEnergy(SHIELD_COST_PER_ROUND)) {
            this.currentEnergy -= SHIELD_COST_PER_ROUND;
            this.shield = true;
        }
    }

    /**
     * Attempts to teleport.
     */
    public void teleport() {
        if (isEnoughEnergy(TELEPORT_COST)){
            this.currentEnergy -= TELEPORT_COST;
            this.physics = new SpaceShipPhysics();
        }
    }

    /**
     * This method handles a spaceShip being hit by shot/colliding with another spaceship.
     * When a ship's hit with shield on and it has enough energy for the cost of it,
     * it will decrease the current energy and increase points (bashing mode) to max energy accordingly.
     * Else, If shield is off OR there's not enough energy, it will decrease health by 1,
     * and will decrease current energy and max energy according to this scenario (only if there's enough energy).
     *
     * @param cost the cost of the hit when shields's on.
     * @param points points to increase maxEnergy in case of bashing.
     */
    private void gotHit(int cost, int points){
        if (this.shield && isEnoughEnergy(cost)) {
            this.currentEnergy -= cost;
            this.maxEnergy += points;
        }else {
            if (isEnoughEnergy(CURRENT_ENERGY_HIT_COST) &&
                    maxEnergy >= MAX_ENERGY_HIT_COST) {
                this.currentEnergy -= CURRENT_ENERGY_HIT_COST;
                this.maxEnergy -= MAX_ENERGY_HIT_COST;
            }
            this.health--;
        }
    }

    /**
     * This method checks if the spaceship has enough energy to take the cost.
     *
     * @param cost the cost to check against.
     */
    private boolean isEnoughEnergy(int cost){
        return this.currentEnergy >= cost;
    }

    /**
     * This method is called once by doAction() and is recharging the spaceship each round.
     * It will regenerate energy and will update its stats.
     */
    protected void recharge(){
        regenerate();
        adjustCurrentEnergy();
        updateCooldownRounds();
    }

    /**
     * This method updates the stats of the spaceship's energy.
     * If current energy is above max energy it will reset current energy to its max energy.
     */
    private void adjustCurrentEnergy(){
        if (this.currentEnergy > this.maxEnergy) this.currentEnergy = this.maxEnergy;
    }

    /**
     * This method updates cool down rounds left to fire at the end of each round.
     */
    private void updateCooldownRounds(){
        if (this.fireCooldownRoundsLeft > 0) this.fireCooldownRoundsLeft--;
    }


    /**
     * This method is called by doAction() and regenerates the spaceship's energy
     * according to a given formula.
     */
    private void regenerate(){
        this.currentEnergy += Math.floor(2 * (physics.getVelocity()/physics.getMaxVelocity())) + 1;
    }

    /**
     * This method updates this ship's attributes regarding the closest ship's
     * distance and angle in relation to this ship.
     *
     * @param other SpaceShip obj. of the closest ship.
     */
    protected void setClosestShipAttributes(SpaceShip other){
        closestShipDistance = this.physics.distanceFrom(other.physics);
        closestShipAngle = this.physics.angleTo(other.physics);
    }
}
