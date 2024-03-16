package com.mowitnow.tondeuse;


import com.mowitnow.tondeuse.domain.Position;
import com.mowitnow.tondeuse.service.TondeusesService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Scanner;

import static com.mowitnow.tondeuse.shared.helpers.Utils.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class TondeuseApplicationTests {

    @Autowired
    private TondeusesService tondeusesService;

    /**
     * This test method reads mower positions and instructions from an input file and verifies that each  position matches the expected position after executing the instructions.
     * It opens the input file, reads the upper-right coordinates of the lawn, reads each mower's initial position, orientation, and instructions, creates a TondeusesService object for each mower, executes the instructions, and asserts that the final position matches the expected position.
     */
    @Test
    void tryInputfile() {

        // Open the input file
        Scanner scanner = openFile("firstfile.txt");

        // Read the upper-right coordinates of the lawn
        int maxX = checkIntValue(scanner);
        int maxY = checkIntValue(scanner);
        scanner.nextLine(); // Consume newline

        // Read mower positions and instructions from the file
        while (scanner.hasNextLine()) {
            // Read mower initial position and orientation
            int x = checkIntValue(scanner);
            int y = checkIntValue(scanner);
            char orientation = scanner.next().charAt(0);
            scanner.nextLine(); // Consume newline

            // Read mower instructions
            String instructions = scanner.nextLine();

            checkIfInstructionsExist(instructions);

            // Create a MowerService object with the provided initial position, orientation, and lawn boundaries
            Position position = new Position(x, y, orientation, maxX, maxY);

            tondeusesService.setPosition(position);
            // Execute instructions for the current mower
            tondeusesService.executeInstructions(instructions);
            System.out.println(tondeusesService.getCurrentPosition());

            // Assert that the final position matches the expected position
            Assert.assertEquals("1 3 N", tondeusesService.getCurrentPosition());
        }
        // Close the scanner
        scanner.close();

    }

    /**
     * This test method reads mower positions and instructions from a second input file and verifies that each mower's final position matches the expected position after executing the instructions.
     * It opens the second input file, reads the upper-right coordinates of the lawn, reads each mower's initial position, orientation, and instructions, creates a TondeusesService object for each mower, executes the instructions, and asserts that the final position matches the expected position.
     */
    @Test
    void trySecondefile() {

        // Open the second input file
        Scanner scanner = openFile("secondfile.txt");

        // Read the upper-right coordinates of the lawn
        int maxX = checkIntValue(scanner);
        int maxY = checkIntValue(scanner);
        scanner.nextLine(); // Consume newline

        // Read mower positions and instructions from the file
        while (scanner.hasNextLine()) {
            // Read mower initial position and orientation
            int x = checkIntValue(scanner);
            int y = checkIntValue(scanner);
            char orientation = scanner.next().charAt(0);
            scanner.nextLine(); // Consume newline

            // Read mower instructions
            String instructions = scanner.nextLine();
            System.out.println("instructions : " + instructions);

            // Create a TondeusesService object with the provided initial position, orientation, and lawn boundaries
            Position position = new Position(x, y, orientation, maxX, maxY);

            tondeusesService.setPosition(position);
//					TondeusesService mower = new TondeusesService(x, y, orientation, maxX, maxY);

            // Execute instructions for the current mower
            tondeusesService.executeInstructions(instructions);
            System.out.println(tondeusesService.getCurrentPosition());

            // Assert that the final position matches the expected position
            Assert.assertEquals("5 1 E", tondeusesService.getCurrentPosition());
        }
        // Close the scanner
        scanner.close();


    }

}
