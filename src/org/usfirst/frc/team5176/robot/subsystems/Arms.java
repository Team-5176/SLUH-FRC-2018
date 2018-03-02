package org.usfirst.frc.team5176.robot.subsystems;

import org.usfirst.frc.team5176.robot.RobotMap;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

/**
 *
 */
public class Arms extends Subsystem {

    private final SpeedController leftSpinMotor = RobotMap.testMota2;
    private final SpeedController rightSpinMotor = RobotMap.testMota1;
    
    private final SpeedController bothSpinnyBois = RobotMap.armSpinnyMotors;//use this
    private final SpeedController tiltArmMotor = RobotMap.armTiltMotor;
    private final SpeedController armOpenMotor = RobotMap.armOpenMotor;
    
	private final Potentiometer pot = RobotMap.pot;
	// Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void setSpinnyBois(double speed){
    	bothSpinnyBois.set(speed);
    }
    public void setTiltArmSpeed(double speed){
    	
    }
}

