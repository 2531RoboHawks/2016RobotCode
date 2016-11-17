package org.usfirst.frc.team2531.robot;

import org.usfirst.frc.team2531.robot.commands.MoveElevator;
import org.usfirst.frc.team2531.robot.commands.RunIntake;
import org.usfirst.frc.team2531.robot.commands.RunShooter;

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

	public static JoystickButton intakein;
	public static JoystickButton intakeout;
	public static JoystickButton elevatorup;
	public static JoystickButton elevatordown;
	public static JoystickButton shoot;
	public static JoystickButton autoshoot;

	public OI() {
		left = new Joystick(1);
		right = new Joystick(0);
		gamepad = new Joystick(2);
		if (RobotMap.USE_GAMEPAD_ACTIONS) {
			//autoshoot = new JoystickButton(gamepad, 3);
			intakein = new JoystickButton(gamepad, 6);
			intakeout = new JoystickButton(gamepad, 8);
			elevatorup = new JoystickButton(gamepad, 4);
			elevatordown = new JoystickButton(gamepad, 2);
			shoot = new JoystickButton(gamepad, 5);
		} else {
			//autoshoot = new JoystickButton(right, 4);
			intakein = new JoystickButton(left, 1);
			intakeout = new JoystickButton(left, 2);
			elevatorup = new JoystickButton(right, 3);
			elevatordown = new JoystickButton(right, 2);
			shoot = new JoystickButton(right, 1);
		}
		// autoshoot.whileHeld(new AutoShoot(1));
		intakein.whileHeld(new RunIntake(0.5));
		intakeout.whileHeld(new RunIntake(-0.5));
		elevatorup.whileHeld(new MoveElevator(true));
		elevatordown.whileHeld(new MoveElevator(false));
		shoot.whileHeld(new RunShooter(1, false));
	}
}
