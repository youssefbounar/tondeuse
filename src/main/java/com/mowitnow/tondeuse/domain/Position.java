package com.mowitnow.tondeuse.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
/**
 * Represents the position of an object.
 */
public class Position {

    /** The x-coordinate of the position. */
    private int x;

    /** The y-coordinate of the position. */
    private int y;

    /** The orientation of the object (N, E, S, W). */
    private char orientation;

    /** The maximum value of the x-coordinate. */
    private int maxX;

    /** The maximum value of the y-coordinate. */
    private int maxY;
}
