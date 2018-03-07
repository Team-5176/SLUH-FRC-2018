package org.usfirst.frc.team5176.robot.commands;

import org.usfirst.frc.team5176.robot.Robot;
import org.usfirst.frc.team5176.robot.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArcadeDrive extends Command {
	
	private double joyX, joyY, twist, lastX, lastY, lastTwist;
	
	private Joystick joystick;
    public ArcadeDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	joystick = Robot.m_oi.getPilotJoystick();
		lastX = 0.0;
		lastY = 0.0;
		lastTwist = 0.0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {//legit just copied chris's code from 2017
    	//DriverStation.reportWarning("we are here", false);
    	joyX = joystick.getRawAxis(0);
		joyY = joystick.getRawAxis(1);
		twist = joystick.getRawAxis(4) / 2.0;
		
		if(Math.abs(joyX) < 0.10) {
	    	joyX = 0.0;
		}
    	
    	if(Math.abs(joyY) < 0.10) {
	    	joyY = 0.0;
    	}
    	
    	if(Math.abs(twist) < 0.10) {
	    	twist = 0.0;
    	}
    	
    	lastX = lastX + Robot.ACCELERATION * (joyX * 0.75 - lastX);
    	lastY = lastY + Robot.ACCELERATION * (joyY * 0.75 - lastY);
    	lastTwist = lastTwist + Robot.ACCELERATION * (twist * 0.75 - lastTwist);
    	
    	Robot.driveTrain.dankMemes(lastX, lastY, lastTwist);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
