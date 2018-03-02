package org.usfirst.frc.team5176.robot.commands;

import org.usfirst.frc.team5176.robot.Robot;
import org.usfirst.frc.team5176.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

/**
 * I spelt it wrong on purpose
 */
public class IdkDriveForwardAutonomouse extends Command {

	PIDOutput theBadThing;
	PIDController pid;
	public IdkDriveForwardAutonomouse() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	theBadThing = (double value) ->{
    		if(Robot.isAuto) {
    			Robot.driveTrain.tankMemes(value, value);
    		}
    	};
    	//pid = new PIDController(Kp, Ki, Kd, RobotMap.frontRightEncoder, theBadThing);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
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
