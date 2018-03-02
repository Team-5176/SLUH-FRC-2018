package org.usfirst.frc.team5176.robot.subsystems;

import org.usfirst.frc.team5176.robot.RobotMap;
import org.usfirst.frc.team5176.robot.commands.ArcadeDrive;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

/**
 *
 */
public class DriveTrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private final MecanumDrive robotDrive = RobotMap.mecanumDrive;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ArcadeDrive());
    }
    
    public void dankMemes(double x, double y, double rotation) {//it is tradition
    	//DriverStation.reportWarning((robotDrive == null) + "", false);
    	robotDrive.driveCartesian(x, -y, rotation);
    }
    
    public void tankMemes(double left, double right) {
    	RobotMap.frontLeftMotor.set(left);
    	RobotMap.backLeftMotor.set(left);
    	RobotMap.frontRightMotor.set(right);
    	RobotMap.backLeftMotor.set(right);
    }
}

