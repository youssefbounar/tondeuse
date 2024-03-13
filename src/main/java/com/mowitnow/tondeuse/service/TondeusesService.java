package com.mowitnow.tondeuse.service;
import com.mowitnow.tondeuse.domain.Position;
import com.mowitnow.tondeuse.shared.constantes.Instruction;
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
        for (char instruction : instructions.toCharArray()) {
            switch (instruction) {
                case Instruction.G:
                    turnLeft();
                    break;
                case Instruction.D:
                    turnRight();
                    break;
                case Instruction.A:
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
            case Orientation.N:
                if (position.getY() < position.getMaxY()) position.setY(position.getY() + 1);
                break;
            case Orientation.E:
                if (position.getX() < position.getMaxX()) position.setX(position.getX() + 1);
                break;
            case Orientation.S:
                if (position.getY() > 0) position.setY(position.getY() - 1);
                break;
            case Orientation.W:
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
            case Orientation.N:
                position.setOrientation(Orientation.W);
                break;
            case Orientation.E:
                position.setOrientation(Orientation.N);
                break;
            case Orientation.S:
                position.setOrientation(Orientation.E);
                break;
            case Orientation.W:
                position.setOrientation(Orientation.S);
                break;
        }
    }

    /**
     * Turn the Tondeuse to the right.
     */
    @Override
    public void turnRight() {
        switch (position.getOrientation()) {
            case Orientation.N:
                position.setOrientation(Orientation.E);
                break;
            case Orientation.E:
                position.setOrientation(Orientation.S);
                break;
            case Orientation.S:
                position.setOrientation(Orientation.W);
                break;
            case Orientation.W:
                position.setOrientation(Orientation.N);
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
