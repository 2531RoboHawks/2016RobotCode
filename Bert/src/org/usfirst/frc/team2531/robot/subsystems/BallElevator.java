package org.usfirst.frc.team2531.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BallElevator extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	CANTalon lift = new CANTalon(4);

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void setSpeed(double speed) {
		lift.set(speed);
	}
}
