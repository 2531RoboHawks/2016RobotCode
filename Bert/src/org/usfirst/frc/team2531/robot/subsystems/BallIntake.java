package org.usfirst.frc.team2531.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BallIntake extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	CANTalon intake = new CANTalon(1);

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void setSpeed(double speed) {
		intake.set(speed);
	}
}
