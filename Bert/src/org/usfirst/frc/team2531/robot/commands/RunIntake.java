package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.Robot;
import org.usfirst.frc.team2531.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunIntake extends Command {

	public RunIntake() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.intake);
		requires(Robot.elevator);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		System.out.println("-> Intake");
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (RobotMap.elevatordown.get()) {
			Robot.elevator.setSpeed(0);
		} else {
			Robot.elevator.setSpeed(-1);
		}
		Robot.intake.setSpeed(1);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.intake.setSpeed(0.0);
		System.out.println("-! Intake");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
