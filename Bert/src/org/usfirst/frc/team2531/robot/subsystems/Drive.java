package org.usfirst.frc.team2531.robot.subsystems;

import org.usfirst.frc.team2531.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drive extends Subsystem {

	private RobotDrive drive = new RobotDrive(0, 1, 2, 3);

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(Robot.tankdrive);
	}

	public void TankDrive(Joystick left, Joystick right) {
		drive.tankDrive(left, right);
	}

	public void TankDrive(double left, double right) {
		drive.tankDrive(left, right);
	}

	public void ArcadeDrive(Joystick joystick) {
		drive.arcadeDrive(joystick);
	}

	public void ArcadeDrive(double forward, double steering) {
		drive.arcadeDrive(forward, steering);
	}
}
