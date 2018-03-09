/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5176.robot;

import org.usfirst.frc.team5176.misc.BNO055Fix;
import org.usfirst.frc.team5176.misc.BNO055Fix.AxisIGuess;
import org.usfirst.frc.team5176.misc.TalonSRXFix;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */

public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	public static TalonSRXFix frontLeftDank;
	public static TalonSRXFix frontRightDank;
	public static TalonSRXFix backLeftDank;
	public static TalonSRXFix backRightDank;
	
	public static VictorSP armTiltMotor;
	public static VictorSP armOpenMotor;
	public static VictorSP climbyBoi;
	public static VictorSP backRightVictor;
	
	public static SpeedControllerGroup frontLeftMotor;
	public static SpeedControllerGroup frontRightMotor;
	public static SpeedControllerGroup backLeftMotor;
	public static SpeedControllerGroup backRightMotor;
	
	public static MecanumDrive mecanumDrive;
	
	
	public static TalonSRXFix rightArmSpinny;
	public static TalonSRXFix leftArmSpinny;
	public static SpeedControllerGroup armSpinnyMotors;
	
	public static Encoder frontRightEncoder;
	public static BNO055Fix rotatyBoi;
	public static Potentiometer pot;
	//you were about to make a bno055fix object and make that pidrotation obj then hope for the best
	//look into making a new class so you dont have to have a seperate obj that is like all the motors, then two that are a side each blah blah T_T
	//ps i love you
	public static SpeedControllerGroup literallyAllTheMotors;
	
	public static SpeedControllerGroup literallyAllTheLeftMotors;
	public static SpeedControllerGroup literallyAllTheRightMotors;
	public static SpeedControllerGroup literallyAllTheMotorsButTheySpinThisTime;
	
	public static TalonSRXFix climbyBoii;
	
	public static void init()
	{
		frontLeftDank = new TalonSRXFix(5);//5
		frontRightDank = new TalonSRXFix(3);//3
		backLeftDank = new TalonSRXFix(2);//2//
		backRightDank = new TalonSRXFix(4);//4//
		
		armTiltMotor = new VictorSP(0);
		armOpenMotor = new VictorSP(1);
		climbyBoi = new VictorSP(2);
		backRightVictor = new VictorSP(3);
		
		//volitile
		//frontRightDank.setInverted(true);
		//backRightDank.setInverted(true);
		
		
		
		armTiltMotor.setInverted(true);
		
		//climbyBoii = new TalonSRXFix(8);//
		
		//frontLeftMotor = new SpeedControllerGroup((SpeedController)frontLeftDank, (SpeedController)frontLeftVictor);
		//frontRightMotor = new SpeedControllerGroup((SpeedController)frontRightDank, (SpeedController)frontRightVictor);
		//backLeftMotor = new SpeedControllerGroup((SpeedController)backLeftDank, (SpeedController)backLeftVictor);
		//backRightMotor = new SpeedControllerGroup((SpeedController)backRightDank, (SpeedController)backRightVictor);
		
		mecanumDrive = new MecanumDrive(frontLeftDank, backLeftDank, frontRightDank, backRightDank);
		
		rightArmSpinny = new TalonSRXFix(1);//right arm spinny
		leftArmSpinny = new TalonSRXFix(6);//left arm spinny//
		leftArmSpinny.setInverted(true);
		
		armSpinnyMotors = new SpeedControllerGroup(rightArmSpinny, leftArmSpinny);
		
		frontRightEncoder = new Encoder(0, 1);
		//rotatyBoi = new BNO055Fix(Port.kOnboard, (byte) 0x28, AxisIGuess.x);
		pot = new AnalogPotentiometer(0);//pin, full range, offset
		
		//frontRightDank.setInverted(true);
		//backRightDank.setInverted(true);
		
		literallyAllTheMotors = new SpeedControllerGroup(frontLeftDank, frontRightDank, backLeftDank, backRightDank);
		literallyAllTheMotors.setInverted(true);
		
		//literallyAllTheLeftMotors = new SpeedControllerGroup(frontLeftDank, backLeftDank);
		//literallyAllTheRightMotors = new SpeedControllerGroup(frontRightDank, backRightDank);
		//literallyAllTheRightMotors.setInverted(true);
		//literallyAllTheMotorsButTheySpinThisTime = new SpeedControllerGroup(literallyAllTheLeftMotors, literallyAllTheRightMotors);
	}
	
}
