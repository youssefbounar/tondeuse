package com.mowitnow.tondeuse;

import com.mowitnow.tondeuse.domain.Position;
import com.mowitnow.tondeuse.service.TondeusesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

import static com.mowitnow.tondeuse.shared.helpers.Utils.*;

@SpringBootApplication
public class TondeuseApplication implements CommandLineRunner {
    @Autowired
    private TondeusesService tondeusesService;

    public static void main(String[] args) {
        SpringApplication.run(TondeuseApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


        Scanner scanner = openFile("fullfile.txt");
        // Open the input file


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
            // check if instructions exist
            checkIfInstructionsExist(instructions);

            // Create a MowerService object with the provided initial position, orientation, and lawn boundaries
            Position position = new Position(x, y, orientation, maxX, maxY);

            tondeusesService.setPosition(position);
            // Execute instructions for the current mower
            tondeusesService.executeInstructions(instructions);
            System.out.println(tondeusesService.getCurrentPosition());

        }

        // Close the scanner
        scanner.close();


    }
}
