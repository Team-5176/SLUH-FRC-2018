package org.usfirst.frc.team5176.misc;

public enum ArmPositions {
	UP(.25),
	//MEDIUM()
	DOWN(0),
	REALLY_UP(.3);//untested
	
	private final double position;
	private ArmPositions(double pos) {
		this.position = pos;
	}
	public double getVal() {
		return position;
	}
}
