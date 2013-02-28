package org.usfirst.frc0;

import edu.wpi.first.wpilibj.*;

public class Drive extends Iterative2013
{	
	public static void destroy()
	{
		// This may or may not work depending on SDK version. Use sparingly.
		wheels.destroy();
	}
	public static class wheels 
	{
		// Drivetrain Victors and encoders (250ct)
		static Victor leftV = new Victor(1);
		static Victor rightV = new Victor(2);
		static Encoder leftE = new Encoder(1,2);
		static Encoder rightE = new Encoder(3,4);
		
		static RobotDrive mainDrive = new RobotDrive(leftV, rightV);
		
		static void init(double sensitivity)
		{
			mainDrive.setSensitivity(sensitivity);
		}
		
		static void execute()
		{
			mainDrive.tankDrive(Control.leftStick.getY(), Control.rightStick.getY());
		}
		
		static void destroy()
		{
			mainDrive.free();
			leftE.free();
			rightE.free();
			leftV.free();
			rightV.free();
		}
	}
	
	public static class shooter 
	{
		// Shooter wheel and encoder
		static Victor rightRound = new Victor(4);
		static Encoder shootEnc = new Encoder(13,14); 
		
		static void init()
		{
			// Encoder used for rough metrics
			shootEnc.start();
			shootEnc.setDistancePerPulse((3.14*(8.0/12.0))/250.0);
		}
		
		static void execute()
		{
			if (Control.xButt.getRawButton(5) || Control.xButt.getRawButton(6) || Control.xButt.getRawButton(2))
			{
				if (Control.xButt.getRawButton(5))
				{
					rightRound.set(0.78);
				}
				if (Control.xButt.getRawButton(6))
				{
					rightRound.set(0.68);
				}
				if (Control.xButt.getRawButton(2))
				{
					rightRound.set(0.42);
				}
			}
			else
			{
				rightRound.set(0);
			}
		}
	}
}