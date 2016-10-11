package org.usfirst.frc.team2531.robot;

import org.usfirst.frc.team2531.robot.commands.MoveElevator;
import org.usfirst.frc.team2531.robot.commands.RunIntake;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());

	public static Joystick left;
	public static Joystick right;
	public static Joystick gamepad;

	public static JoystickButton intake;
	public static JoystickButton elevatorup;
	public static JoystickButton elevatordown;

	public OI() {
		left = new Joystick(1);
		right = new Joystick(0);
		gamepad = new Joystick(2);
		intake = new JoystickButton(gamepad, 5);
		elevatorup = new JoystickButton(gamepad, 6);
		elevatordown = new JoystickButton(gamepad, 1);
		intake.whileHeld(new RunIntake());
		elevatorup.whileHeld(new MoveElevator(true));
		elevatordown.whileHeld(new MoveElevator(false));
	}
}
