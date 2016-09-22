package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.Robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveElevator extends Command {

	DigitalInput up = new DigitalInput(0);
	DigitalInput down = new DigitalInput(0);

	boolean done = false;
	boolean direction = true;

	public MoveElevator(boolean up) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.elevator);
		direction = up;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		System.out.println("-> MoveElevator");
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (direction) {
			Robot.elevator.setSpeed(0.5);
			if (!up.get()) {
				done = true;
			}
		} else {
			Robot.elevator.setSpeed(-0.5);
			if (!down.get()) {
				done = true;
			}
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		System.out.println("-! MoveElevator");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
