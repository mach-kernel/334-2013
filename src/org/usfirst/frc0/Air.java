package org.usfirst.frc0;

import edu.wpi.first.wpilibj.*;

public class Air extends Iterative2013 
{
	// Pressure sensor is on 7 of Digital IO
	// Compressor is on 1 of Relay
	static Compressor airComp = new Compressor(7,1);
	
	public static void reset()
	{
		elevator.reset();
		shooter.reset();
	}
	
	public static void pressurize()
	{
		if (!airComp.getPressureSwitchValue())
		{
			airComp.start();
		}
		else 
		{
			airComp.stop();
		}
	}
	
	public static void destroy()
	{
		airComp.stop();
		airComp.free();
		
		elevator.destroy();
		shooter.destroy();
		
	}
	
	public static class elevator 
	{
		static Solenoid eD = new Solenoid(6);
		static Solenoid eU = new Solenoid(5);
		
		static void reset()
		{
			raise();
		}
		
		static void execute()
		{
			if (Control.xButt.getRawAxis(3) == 1)
			{
				lower();
			}
			if (Control.xButt.getRawAxis(3) == -1)
			{
				raise();
			}
		}
		
		static void raise()
		{
			eU.set(true);
			eD.set(false);
		}
		
		static void lower()
		{
			eU.set(false);
			eD.set(true);
		}
		
		static void destroy()
		{			
			eD.free();
			eU.free();
		}
	}

	public static class shooter 
	{
		static Solenoid sD = new Solenoid(3);
		static Solenoid sU = new Solenoid(4);
		static Solenoid feedIn = new Solenoid(1);
		static Solenoid feedOut = new Solenoid(2);
		
		static void reset()
		{
			lower();
			pIn();
		}
		
		static void autoShoot()
		{
			// More pretty lights
	    	Control.lights.purple();
	    	Timer.delay(0.05);
	    	Control.lights.reset();
	    	Timer.delay(0.05);
	    	Control.lights.purple();
			pIn();
			Timer.delay(2);
			pOut();
			Timer.delay(0.2);
			pIn();
		}
		
		static void execute()
		{
			if (Control.xButt.getRawButton(1))
			{
				lower();
			}
			if (Control.xButt.getRawButton(3))
			{
				raise();
			}
			if (Control.xButt.getRawButton(4))
			{
				pOut();
			}
			else if (!Control.xButt.getRawButton(4))
			{
				pIn();
			}
		}
		
		static void raise()
		{
			sD.set(true);
			sU.set(false);
		}
		
		static void lower()
		{
			sD.set(false);
			sU.set(true);
		}
		
		static void pOut()
		{
			feedIn.set(true);
			feedOut.set(false);
		}
		static void pIn()
		{
			feedIn.set(false);
			feedOut.set(true);			
		}
		
		static void destroy()
		{
			sD.free();
			sU.free();
			feedIn.free();
			feedOut.free();
		}
	}
}
