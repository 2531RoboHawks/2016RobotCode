package org.usfirst.frc.team2531.robot;

import org.usfirst.frc.team2531.robot.commands.MoveElevator;
import org.usfirst.frc.team2531.robot.commands.RunIntake;
import org.usfirst.frc.team2531.robot.commands.Shoot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public static Joystick left;
	public static Joystick right;
	public static Joystick gamepad;

	public static JoystickButton intake;
	public static JoystickButton elevatorup;
	public static JoystickButton elevatordown;
	public static JoystickButton shoot;

	public OI() {
		left = new Joystick(1);
		right = new Joystick(0);
		gamepad = new Joystick(2);
		intake = new JoystickButton(gamepad, 5);
		elevatorup = new JoystickButton(gamepad, 6);
		elevatordown = new JoystickButton(gamepad, 1);
		shoot = new JoystickButton(gamepad, 2);
		intake.whileHeld(new RunIntake());
		elevatorup.whileHeld(new MoveElevator(true));
		elevatordown.whileHeld(new MoveElevator(false));
		shoot.whenPressed(new Shoot(1));

	}
}
