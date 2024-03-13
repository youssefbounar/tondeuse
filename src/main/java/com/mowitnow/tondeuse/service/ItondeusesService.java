package com.mowitnow.tondeuse.service;

import com.mowitnow.tondeuse.domain.Position;

/**
 * This interface defines operations for controlling Tondeuses (lawnmowers).
 */
public interface ItondeusesService {

    /**
     * Set the position of the Tondeuse.
     *
     * @param position The position to set.
     */
    void setPosition(Position position);

    /**
     * Execute instructions to control the Tondeuse.
     *
     * @param instructions A string containing instructions (G, D, A).
     */
    void executeInstructions(String instructions);

    /**
     * Get the current position of the Tondeuse.
     *
     * @return A string representation of the current position.
     */
    String getCurrentPosition();
}
