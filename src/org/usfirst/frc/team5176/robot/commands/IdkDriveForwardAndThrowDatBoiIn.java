package org.usfirst.frc.team5176.robot.commands;

import org.usfirst.frc.team5176.misc.ArmPositions;
import org.usfirst.frc.team5176.robot.Robot;
import org.usfirst.frc.team5176.robot.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IdkDriveForwardAndThrowDatBoiIn extends Command {

    PIDController armPidBoi;
    PIDController drivePidBoi;
    double driveDistance;
	public static boolean shouldDo = false;
    public IdkDriveForwardAndThrowDatBoiIn(PIDController armLiftyDude, PIDController driveyBoi, double driveDist) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		armPidBoi = armLiftyDude;
    	drivePidBoi = driveyBoi;
    	driveDistance = driveDist;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//drivey boi
		RobotMap.frontRightEncoder.reset();
		drivePidBoi.setOutputRange(-.3, .3);
		drivePidBoi.setPID(.1, 0, 0);
		drivePidBoi.setSetpoint(Robot.inchesToUnits(driveDistance));
		drivePidBoi.setPercentTolerance(10);
		drivePidBoi.enable();
		
		/*
		//army boi
		armPidBoi.enable();
		armPidBoi.setSetpoint(ArmPositions.UP.getVal());
		
		shouldDo = true;
		
		//wait a couple secs the spinny bois
		new Thread(()->{
			if(shouldDo)
				try{Thread.sleep(5000);}catch(Exception e){}
			if(shouldDo)
				RobotMap.armSpinnyMotors.set(1);
			if(shouldDo)
				try{Thread.sleep(2000);}catch(Exception e){}
			RobotMap.armSpinnyMotors.set(0);
		}).start();
		*/
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return DriverStation.getInstance().isOperatorControl();
    }

    // Called once after isFinished returns true
    protected void end() {
    	drivePidBoi.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
