package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import frclib.time.Time;

/**
 *
 */
public class MoveForward extends Command {

	long moveTime;
	boolean finished;
	
    public MoveForward(long moveTime) {
    	requires(Robot.drive);
    	this.moveTime = moveTime;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("-> MoveForward");
    	finished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drive.TankDrive(1, 1);
    	Time.delayMiliseconds(moveTime);
    	finished = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("-! MoveForward");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
