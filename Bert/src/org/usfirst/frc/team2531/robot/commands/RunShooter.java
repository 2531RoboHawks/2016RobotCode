package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.OI;
import org.usfirst.frc.team2531.robot.Robot;
import org.usfirst.frc.team2531.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RunShooter extends Command {

	boolean set = false;
	double pow;
	boolean done = false;

	public RunShooter() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.shooter);
	}

	public RunShooter(double power) {
		requires(Robot.shooter);
		pow = power;
		set = true;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		System.out.println("-> Shooter");
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (set) {
			Robot.shooter.setSpeed(-pow);
			done = true;
		} else {
			if (RobotMap.USE_GAMEPAD_ACTIONS) {
				Robot.shooter.setSpeed(-OI.gamepad.getRawAxis(3));
			} else {
				Robot.shooter.setSpeed(OI.right.getRawAxis(3) + 1);
			}
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return done;
	}

	// Called once after isFinished returns true
	protected void end() {
		if (!set) {
			Robot.shooter.setSpeed(0.0);
		}
		System.out.println("-! Shooter");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
