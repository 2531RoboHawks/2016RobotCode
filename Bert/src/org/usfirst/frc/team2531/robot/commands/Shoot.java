package org.usfirst.frc.team2531.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frclib.time.Delay;

/**
 *
 */
public class Shoot extends CommandGroup {

	public Shoot(double power) {
		addSequential(new RunShooter(power));
		addSequential(new Delay(2000));
		addSequential(new MoveElevator(true),1000);
		addSequential(new MoveElevator(false),1000);
		addSequential(new RunShooter(0));
	}
}
