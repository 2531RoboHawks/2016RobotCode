package org.usfirst.frc.team2531.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {

	CANTalon wheel = new CANTalon(2);

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		// setDefaultCommand(new RunShooter(1, false));
	}

	public void setSpeed(double speed) {
		wheel.set(speed);
	}
}
