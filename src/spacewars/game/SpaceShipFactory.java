package spacewars.game;

/**
 * This class is a factory class which creates the SpaceShips array for the gameplay.
 * According to the given arguments it will create the corresponding type of spaceship.
 */
public class SpaceShipFactory {

    /**
     * This method creates an array of spaceships
     * according to the type in the args array that was given.
     *
     * @param args arguments for the type of spaceship to create.
     * @return the array of spaceships created.
     */
    public static SpaceShip[] createSpaceShips(String[] args) {
        SpaceShip[] spaceShips = new SpaceShip[args.length];
        for (int i=0; i<spaceShips.length; i++){
            switch (args[i]){
                // Human
                case "h":
                    spaceShips[i] = new Human();
                    break;
                // Runner
                case "r":
                    spaceShips[i] = new Runner();
                    break;
                // Basher
                case "b":
                    spaceShips[i] = new Basher();
                    break;
                // Aggressive
                case "a":
                    spaceShips[i] = new Aggressive();
                    break;
                // Drunkard
                case "d":
                    spaceShips[i] = new Drunkard();
                    break;
                // Special
                case "s":
                    spaceShips[i] = new Special();
                    break;
            }
        }
        return spaceShips;
    }
}
