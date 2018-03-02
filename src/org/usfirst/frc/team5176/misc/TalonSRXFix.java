package org.usfirst.frc.team5176.misc;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SpeedController;

public class TalonSRXFix extends TalonSRX implements SpeedController {

	public TalonSRXFix(int deviceNumber) {
		super(deviceNumber);
	}

	@Override
	public void pidWrite(double output) {
		super.set(ControlMode.Position, output);
		DriverStation.reportError("You are using TalonSRXFix.pidWrite, please stop T_T", false);
	}

	@Override
	public void set(double speed) {
		super.set(ControlMode.PercentOutput, speed);
	}

	@Override
	public double get() {
		// TODO Auto-generated method stub
		return super.getMotorOutputPercent();
	}

	@Override
	public void disable() {
		// TODO Auto-generated method stub
		stopMotor();
	}

	@Override
	public void stopMotor() {
		// TODO Auto-generated method stub
		super.set(ControlMode.PercentOutput, 0);
	}
	
}
