# Space Wars

```
##########################################################
#                                                        #
#  ____                        __        __              #
# / ___| _ __   __ _  ___ ___  \ \      / /_ _ _ __ ___  #
# \___ \| '_ \ / _` |/ __/ _ \  \ \ /\ / / _` | '__/ __| #
#  ___) | |_) | (_| | (_|  __/   \ V  V / (_| | |  \__ \ #
# |____/| .__/ \__,_|\___\___|    \_/\_/ \__,_|_|  |___/ #
#       |_|                                              #
#                                                        #
##########################################################
```

The classic arcade game - Space Wars!

## Getting Started

Clone the repository: git clone <https://github.com/cartmandos/asteroids-retro.git>

Run the game:

args Array of SpaceShips: a/b/d/h/r/s

```bash
java spacewars.game.SpaceWars /args/
```

Enjoy! 🚀

## Design Overview

Abstract Class: SpaceShip
In the design, the focal point is the SpaceShip class,
structured as an abstract class featuring an abstract method,
doAction. This design choice provides a foundation for extending
functionality while maintaining a structured approach.

### Extensibility

To enhance the game's features and functionality,
the design allows for straightforward extensions.
Decisions about whether a feature is shared across all spaceship
types or unique to a specific type can be easily made. Shared
features can be incorporated into the SpaceShip class, ensuring
inheritance across all types. For unique or strategy-specific features,
modifications can be made to the doAction method or additional methods
can be introduced for specific types. This design also facilitates the
seamless addition of new spaceship types, requiring only a corresponding
case in the SpaceShipFactory.

### Modularity

Emphasizing modularity, the design aims for simplicity and ease of maintenance.
Shared functionalities, such as energy checks, collision handling,
and attribute resets, are implemented modularly.
This approach allows for diverse spaceship behaviors,
enabling individual ships to update stats, adjust cooldown rounds, and more.

### Intuitiveness and Understandability

The design prioritizes intuitiveness by establishing common rules for different
spaceship types while emphasizing distinctions in their playing strategies.
By encapsulating non-shared methods and keeping the design minimal and straightforward,
the implementation becomes more intuitive and understandable.

### Considerations

- Data Members
  Thoughtful consideration went into deciding which data members belong to
  the SpaceShip class and which are specific to certain types.
  Basic features, like the closestShip object,
  are incorporated at the SpaceShip level to allow for potential
  future changes in action or the addition of new types.

- Access Modifiers
  Data members that are not essential for different types are designated as private,
  enhancing encapsulation. Those marked as protected are fundamental
  features critical for different types.

#### Specific Ship Behaviors

- Drunkard Ship
  The behavior of the drunkard ship is characterized by randomness,
  with continuous acceleration. Each round,
  actions are determined by a randomly generated number,
  influencing the occurrence of specific actions based on defined probability ranges.

- Special Ship
  The special ship embodies a hybrid approach,
  combining characteristics of aggressive, basher,
  and runner types. Its behavior involves defaulting to facing enemies and attempting to shoot them.
  However, when threatened, it dynamically adjusts strategies,
  prioritizing actions such as teleportation, shielding,
  or evading enemies based on energy considerations and proximity.

```

```
