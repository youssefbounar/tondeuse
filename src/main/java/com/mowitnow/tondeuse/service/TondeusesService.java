package com.mowitnow.tondeuse.service;
import com.mowitnow.tondeuse.domain.Position;
import com.mowitnow.tondeuse.shared.enums.Instructions;
import com.mowitnow.tondeuse.shared.constantes.Orientation;
import org.springframework.stereotype.Service;



/**
 * A service class for controlling Tondeuses .
 */
@Service
public class TondeusesService implements ItondeusesService, Imovable {

    private Position position;

    /**
     * Set the initial position of the Tondeuse.
     *
     * @param position The initial position of the Tondeuse.
     */
    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Execute instructions to control the Tondeuse.
     *
     * @param instructions A string containing instructions (G, D, A).
     */
    @Override
    public void executeInstructions(String instructions) {
//        Enum
        for (char instruction : instructions.toCharArray()) {
            switch (Instructions.valueOf(String.valueOf(instruction))) {
                case G:
//                case Instruction.G:
                    turnLeft();
                    break;
                case D:
                    turnRight();
                    break;
                case A:
                    moveForward();
                    break;
            }
        }
    }

    /**
     * Move the Tondeuse forward based on its orientation.
     */
    @Override
    public void moveForward() {
        switch (position.getOrientation()) {
            case Orientation.NORD:
                if (position.getY() < position.getMaxY()) position.setY(position.getY() + 1);
                break;
            case Orientation.EST:
                if (position.getX() < position.getMaxX()) position.setX(position.getX() + 1);
                break;
            case Orientation.SUD:
                if (position.getY() > 0) position.setY(position.getY() - 1);
                break;
            case Orientation.WEST:
                if (position.getX() > 0) position.setX(position.getX() - 1);
                break;
        }
    }

    /**
     * Turn the Tondeuse to the left.
     */
    @Override
    public void turnLeft() {
        switch (position.getOrientation()) {
            case Orientation.NORD:
                position.setOrientation(Orientation.WEST);
                break;
            case Orientation.EST:
                position.setOrientation(Orientation.NORD);
                break;
            case Orientation.SUD:
                position.setOrientation(Orientation.EST);
                break;
            case Orientation.WEST:
                position.setOrientation(Orientation.SUD);
                break;
        }
    }

    /**
     * Turn the Tondeuse to the right.
     */
    @Override
    public void turnRight() {
        switch (position.getOrientation()) {
            case Orientation.NORD:
                position.setOrientation(Orientation.EST);
                break;
            case Orientation.EST:
                position.setOrientation(Orientation.SUD);
                break;
            case Orientation.SUD:
                position.setOrientation(Orientation.WEST);
                break;
            case Orientation.WEST:
                position.setOrientation(Orientation.NORD);
                break;
        }
    }

    /**
     * Get the current position of the Tondeuse.
     *
     * @return A string representation of the Tondeuse's position.
     */
    @Override
    public String getCurrentPosition() {
        return position.getX() + " " + position.getY() + " " + position.getOrientation();
    }
}
