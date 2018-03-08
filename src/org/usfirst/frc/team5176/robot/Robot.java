/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//aka CHAIN GANG

package org.usfirst.frc.team5176.robot;

import org.usfirst.frc.team5176.misc.ArmPositions;
import org.usfirst.frc.team5176.robot.commands.ExampleCommand;
import org.usfirst.frc.team5176.robot.commands.IdkDriveForwardAndThrowDatBoiIn;
import org.usfirst.frc.team5176.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5176.robot.subsystems.ExampleSubsystem;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static final ExampleSubsystem kExampleSubsystem
			= new ExampleSubsystem();
	public static final double ACCELERATION = 0.3;
	public static OI m_oi;
	
	public static DriveTrain driveTrain;
	
	public static boolean isAuto = false;
	
	public static PIDController driveForwardPid;
	public static PIDController rotatePid;
	public static PIDController armsPidLift;
	 
	
	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();
	
	//testing
	public static double pot_min;
	public static double pot_max;
	public static double pot_val;
	public static double[] pot_array;
	
	public static UsbCamera camera;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		RobotMap.init();
		m_oi = new OI();
		m_chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", m_chooser);
		
		driveTrain = new DriveTrain();
		driveForwardPid = new PIDController(.1, 0, 0, RobotMap.frontRightEncoder, RobotMap.literallyAllTheMotors);
		driveForwardPid.setAbsoluteTolerance(50);
		driveForwardPid.disable();
		//rotatePid = new PIDController(1, 0, 0, RobotMap.rotatyBoi, );
		
		armsPidLift = new PIDController(.1, 0, 0, RobotMap.pot, RobotMap.armTiltMotor);
		//armsPidLift.disable();
		//setup stuff
		pot_val = RobotMap.pot.get() * 100;
		pot_min = pot_val;
		pot_max = pot_val;
		
		pot_array = new double[10];
		for(int x = 0; x < 10; x++)
			pot_array[x] = pot_val;
		
		camera = CameraServer.getInstance().startAutomaticCapture(0);
        camera.setResolution(720, 480);
        
        //auto sauce
		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		RobotMap.armSpinnyMotors.set(0);
		driveForwardPid.disable();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		//m_autonomousCommand = m_chooser.getSelected();//if i feel like it
		m_autonomousCommand = new IdkDriveForwardAndThrowDatBoiIn(armsPidLift, driveForwardPid, 64);//TODO: PLEASE CHANGE THE DISTANCE OH JESUS
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
		/*
		//drivey boi
		RobotMap.frontRightEncoder.reset();
		driveForwardPid.setOutputRange(-.3, .3);
		driveForwardPid.setPID(.1, 0, 0);
		driveForwardPid.setSetpoint(inchesToUnits(64));
		driveForwardPid.setPercentTolerance(10);
		driveForwardPid.enable();
		
		//army boi
		armsPidLift.enable();
		armsPidLift.setSetpoint(ArmPositions.UP.getVal());
		*/
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Encoder Value", RobotMap.frontRightEncoder.getDistance());
		SmartDashboard.updateValues();
		
		
		
		//pid.setPID(.1, 0, 0);//;SmartDashboard.getNumber("penis", .1), SmartDashboard.getNumber("ienis", 0), SmartDashboard.getNumber("denis", 0));
		//pid.setSetpoint(0);//SmartDashboard.getNumber("Setpoint", 0));
		//Good job;
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
		driveForwardPid.disable();
		IdkDriveForwardAndThrowDatBoiIn.shouldDo = false;
		
		//arms pid
		armsPidLift.setOutputRange(-.4, .4);
		armsPidLift.setPID(1.6, 0, 0);//1.4
		armsPidLift.setPercentTolerance(.05);
		armsPidLift.setSetpoint(0);
		armsPidLift.enable();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		//RobotMap.armSpinnyMotors.set(-OI.coJoystick.getRawAxis(5));//right stick y axis
		//RobotMap.leftArmSpinny.set(-OI.coJoystick.getRawAxis(1));
		//RobotMap.rightArmSpinny.set(-OI.coJoystick.getRawAxis(5));
		double spinnyVal = 0;
		if(OI.coJoystick.getRawAxis(3) > .5) {
			//RobotMap.armSpinnyMotors.set(-.5);
			spinnyVal = -.5;
		}
		if(OI.coJoystick.getRawButton(6)){
			//RobotMap.armSpinnyMotors.set(1);
			spinnyVal = 1;
		}
		if(OI.coJoystick.getRawAxis(3) < .5 && !OI.coJoystick.getRawButton(6)) {
			
			spinnyVal = 0;
		}
		RobotMap.armSpinnyMotors.set(spinnyVal);
		
		
		
		//arm up down bois
		if(OI.coJoystick.getRawButton(1)){
			armsPidLift.setSetpoint(ArmPositions.DOWN.getVal());
		}
		if(OI.coJoystick.getRawButton(4)){
			armsPidLift.setSetpoint(ArmPositions.UP.getVal());
		}
		
	}
	@Override
	public void testInit(){//.7 down .3 up//.53 up .85 down//bro i dont even know anymore
		armsPidLift.setOutputRange(-.4, .4);
		armsPidLift.setPID(2.2, 0, 0);//1.8
		armsPidLift.setPercentTolerance(.05);
		armsPidLift.setSetpoint(ArmPositions.DOWN.getVal());//.2//.25
		armsPidLift.enable();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		
		//RobotMap.armSpinnyMotors.set(OI.pilotJoystick.getRawAxis(5));//right stick y axis
		//RobotMap.armTiltMotor.set(-OI.pilotJoystick.getRawAxis(1)/2);//left stick y axis
		//RobotMap.armOpenMotor.set(OI.pilotJoystick.getRawAxis(4)/2);//right stick x axis
		
		//RobotMap.climbyBoi.set(OI.pilotJoystick.getRawAxis(1));
		/*
		//store it current pot value, because logic would dictate that reading a value from memory is faster than calling a method (i hope)
		pot_val = RobotMap.pot.get() * 100;//also *100 to put into percents
		
		//shift array and set first value to current value
		for(int x = 0; x < 9; x++)
			pot_array[x+1] = pot_array[x];
		pot_array[0] = pot_val;
		
		//calculate average from last 10
		
		double sum = 0;
		for(int x = 0; x < 10; x++)
			sum += pot_array[x];
		sum /= 10;
		
		
		if(pot_val < pot_min)
			pot_min = pot_val;
		if(pot_val > pot_max)
			pot_max = pot_val;
		*/
		//DriverStation.reportWarning(pot_min + " < " + pot_val + " < " + pot_max + "\n" + sum, false);
		//DriverStation.reportWarning(sum + " calculated in:" + time, false);
		//RobotMap.literallyAllTheMotorsButTheySpinThisTime.set(OI.pilotJoystick.getRawAxis(5)/2);
		//DriverStation.reportWarning("get: " + RobotMap.pot.get() + " pidget: " + RobotMap.pot.pidGet() + " setpoint: " + armsPidLift.getSetpoint() + " error: " + armsPidLift.getError() + " onTarget: " + armsPidLift.onTarget(), false);
		
		/*
		if(OI.coJoystick.getRawButton(1)){
			//RobotMap.testMota.set(.3);
			armsPidLift.setSetpoint(0);
		}
		if(OI.coJoystick.getRawButton(4)){
			//RobotMap.testMota.set(.3);
			armsPidLift.setSetpoint(.25);
		}
		*/
		//DriverStation.reportWarning(, printTrace);
	}
	public boolean isInAutonomous() {
		return isAutonomous();
	}
	public static double inchesToUnits(double inches){
		return (inches * 673.68421052631578947368421052632) / 60;//100/9.5
	}
}
