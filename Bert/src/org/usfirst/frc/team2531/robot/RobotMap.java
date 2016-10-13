package org.usfirst.frc.team2531.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import frclib.sensors.ADIS16448;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// Control Configurations
	public static boolean USE_GAMEPAD_DRIVEING = false;
	// true if drive from gamepad

	public static boolean USE_GAMEPAD_ACTIONS = false;
	// true if control actions with gamepad

	public static boolean DEMO_MODE = false;
	// true enables "demo mode"
	// features: only
	// drives with gamepad and power is
	// limited to 50% with actions are
	// disabled

	// Sensors
	public static ADIS16448 imu = new ADIS16448();

	// Switches
	public static DigitalInput elevatorup = new DigitalInput(0);
	public static DigitalInput elevatordown = new DigitalInput(1);
}
