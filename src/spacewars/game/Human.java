package spacewars.game;

/**
 * This class is an implementing class of SpaceShip class representing a human type spaceship in the game.
 */
public class Human extends SpaceShip{

    /**
     * This is an implement method for doAction.
     * Does the actions of this type of ship for this round.
     * This is called once per round by the SpaceWars game driver.
     * The type is controlled by the user:
     * Will turn and accelerate according to UP, LEFT, RIGHT buttons.
     * 'd' button will attempt to fire, 's' button will attempt to put shield on,
     * 'a' button will attempt to teleport.
     *
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game){
        this.shield = false; int turn = FORWARD; boolean accelerate = false;
        if (game.getGUI().isTeleportPressed()) this.teleport();
        if (game.getGUI().isLeftPressed()) turn = LEFT;
        if (game.getGUI().isRightPressed()) turn += RIGHT;
        if (game.getGUI().isUpPressed()) accelerate=true;
        this.physics.move(accelerate, turn);
        if (game.getGUI().isShieldsPressed()) this.shieldOn();
        if (game.getGUI().isShotPressed()) this.fire(game);
        this.recharge();
    }
}
