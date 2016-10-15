package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveForward extends Command {

	double pow;
	long end;
	boolean finished;

	public MoveForward(long moveTime, double pow) {
		requires(Robot.drive);
		this.pow = pow;
		this.end = System.currentTimeMillis() + moveTime;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		System.out.println("-> MoveForward");
		finished = false;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (System.currentTimeMillis() > end) {
			finished = true;
		}
		Robot.drive.TankDrive(-pow, -pow);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return finished;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drive.TankDrive(0, 0);
		System.out.println("-! MoveForward");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
