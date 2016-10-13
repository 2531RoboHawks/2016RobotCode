package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frclib.time.Delay;

/**
 *
 */
public class AutoSequence extends CommandGroup {
    
    public  AutoSequence() {
        // Add Commands here:
    	addSequential(new MoveForward(5000));
    	addSequential(new Delay(500));
    	addSequential(new TurnToHeading(180));
    	addSequential(new Delay(500));
    	addSequential(new MoveForward(5000));
        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
