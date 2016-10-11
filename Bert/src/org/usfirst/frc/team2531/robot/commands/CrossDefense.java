package org.usfirst.frc.team2531.robot.commands;

import java.util.LinkedList;

import org.usfirst.frc.team2531.robot.Robot;
import org.usfirst.frc.team2531.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import frclib.pid.PID2;

/**
 *
 */
public class CrossDefense extends Command {

	PID2 head = new PID2(0, 0, 0, Robot.heading);

	boolean crossed = false;

	double flat = 0;
	double starttrigger = 5;
	double endtrigger = 2;

	boolean checkforflat = false;

	LinkedList<Double> plot = new LinkedList<Double>();

	public CrossDefense() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.drive);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		System.out.println("-> CrossDefence");
		flat = RobotMap.imu.getAngleZ();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.drive.ArcadeDrive(0.5, head.compute(RobotMap.imu.getAngleY()));
		plot.add(RobotMap.imu.getAccelY());
		if (plot.size() > 10) {
			plot.removeLast();
		}
		double total = 0;
		for (int i = 0; i < plot.size(); i++) {
			total += plot.get(i);
		}
		double average = total / plot.size();
		if (checkforflat) {
			if (average < flat + endtrigger && average > flat - endtrigger) {
				crossed = true;
			}
		} else {
			if (average < flat - starttrigger || average > flat + starttrigger) {
				checkforflat = true;
			}
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return crossed;
	}

	// Called once after isFinished returns true
	protected void end() {
		System.out.println("-! CrossDefence");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
