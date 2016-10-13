package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frclib.time.Delay;

/**
 *
 */
public class Shoot extends CommandGroup {

	public Shoot(double power) {
		addSequential(new RunShooter(power, true));
		addSequential(new Delay(5000));
		addSequential(new MoveElevator(true));
		addSequential(new MoveElevator(false));
		addSequential(new RunShooter(0, true));
	}
}
