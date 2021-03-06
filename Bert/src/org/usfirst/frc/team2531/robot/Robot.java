
package org.usfirst.frc.team2531.robot;

import org.usfirst.frc.team2531.robot.commands.AutoSequence;
import org.usfirst.frc.team2531.robot.subsystems.BallElevator;
import org.usfirst.frc.team2531.robot.subsystems.BallIntake;
import org.usfirst.frc.team2531.robot.subsystems.Drive;
import org.usfirst.frc.team2531.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frclib.vision.Camera;
import frclib.vision.VisionTracking;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	// subsystems
	public static Drive drive;
	public static BallElevator elevator;
	public static BallIntake intake;
	public static Shooter shooter;
	// variables
	public static int mode = 0;
	public static double heading;

	SendableChooser c = new SendableChooser();
	Command auto;
	VisionTracking cam = new VisionTracking(new Camera("cam0"));

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		System.out.println("-> Robot");
		drive = new Drive();
		elevator = new BallElevator();
		intake = new BallIntake();
		shooter = new Shooter();
		oi = new OI();
		RobotMap.imu.calibrate();
		RobotMap.imu.startLiveWindowMode();
		heading = RobotMap.imu.getRoll();
		// CameraServer.getInstance().startAutomaticCapture("cam0");
		cam.startCam();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit() {
		if (mode >= 2) {
			System.out.println("-! Autonomous");
		}
		if (mode == 1) {
			System.out.println("-! Teleop");
		}
		System.out.println("# Disabled");
		System.out.println("-> Disabled");
		mode = 0;
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
		cam.displayRawImage();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	public void autonomousInit() {
		System.out.println("# Autonomous");
		System.out.println("-> Autonomous");
		mode = 2;
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		auto = (Command) c.getSelected();
		if (auto != null) {
			auto.start();
		}

	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
		cam.displayRawImage();
	}

	public void teleopInit() {
		if (auto != null) {
			auto.cancel();
		}
		if (mode >= 2) {
			System.out.println("-! Autonomous");
		}
		System.out.println("# Teleop");
		System.out.println("-> Teleop");
		mode = 1;
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDashboard();
		cam.displayRawImage();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}

	public void updateSmartDashboard() {
		c.addDefault("None", null);
		c.addObject("AutoSequence", new AutoSequence());
		SmartDashboard.putData("Auto", c);
		SmartDashboard.putNumber("roll", RobotMap.imu.getPitch());
		SmartDashboard.putNumber("pitch", RobotMap.imu.getYaw());
		SmartDashboard.putNumber("yaw", RobotMap.imu.getRoll());
	}
}
