package org.usfirst.frc.team5176.misc;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class BNO055Fix extends BNO055 implements PIDSource{
	AxisIGuess axis;
	
	public BNO055Fix(I2C.Port port, byte address, AxisIGuess axiS) {
		super(port, address);
		this.axis = axiS;
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		//no im not doing this, what does that even mean
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {//TODO: verify axis's
		switch (axis) {
		case x:
			return this.getHeading();
		case y:
			return this.getVector()[1];
		case z:
			return this.getVector()[2];
		default:// \_('_')_/
			return this.getHeading();
		}
	}
	public void setRotationType(AxisIGuess axiS) {
		this.axis = axiS;
	}
	public enum AxisIGuess{x, y, z}
}
