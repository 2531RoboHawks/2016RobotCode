package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.Robot;
import org.usfirst.frc.team2531.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import frclib.pid.PID;

public class TurnToHeading extends Command {

	PID pid;

	public TurnToHeading(double headingchange) {
		Robot.heading += headingchange;
		pid = new PID(5, 2, 0, Robot.heading);
	}

	@Override
	protected void initialize() {
		System.out.println("-> TurnToHeading");
		pid.setOnTargetOffset(1);
	}

	@Override
	protected void execute() {
		double output = pid.compute(RobotMap.imu.getRoll());
		Robot.drive.TankDrive(-output, output);
	}

	@Override
	protected boolean isFinished() {
		return pid.onTarget();
	}

	@Override
	protected void end() {
		System.out.println("-! TurnToHeading");
	}

	@Override
	protected void interrupted() {
		end();
	}
}
