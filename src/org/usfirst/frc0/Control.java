package org.usfirst.frc0;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.Relay.*;
import edu.wpi.first.wpilibj.camera.*;

public class Control extends Iterative2013
{	
	// Controllers
	static Joystick leftStick = new Joystick(1);
	static Joystick rightStick = new Joystick(2);
	static Joystick xButt = new Joystick(3);
	
	static class camera
	{
		// Camera statically assigned to 10.3.34.11
		static AxisCamera donCam = AxisCamera.getInstance("10.3.34.11");
		static Servo camSrv = new Servo(5);
		static Relay camLit = new Relay(2);
		
		static void execute(int brite)
		{
			donCam.writeResolution(AxisCamera.ResolutionT.k320x240);
			donCam.writeBrightness(brite);
			DriverStationLCD.getInstance().updateLCD();
			
			if(xButt.getRawAxis(6) == 1)
			{
				camSrv.set(camSrv.get() + 0.01);
			}
			if(xButt.getRawAxis(6) == -1)
			{
				camSrv.set(camSrv.get() - 0.01);
			}
			if(xButt.getRawButton(7))
			{
				camLit.set(Value.kOn);
				camLit.setDirection(Direction.kForward);
			}
			if(xButt.getRawButton(8))
			{
				camLit.set(Value.kOff);
			}
		}
	}
	
	static class lights
	{
	    static DigitalInput light = new DigitalInput(8);
	    
		static Relay red = new Relay(3, Relay.Direction.kForward);
	    static Relay blue = new Relay(4, Relay.Direction.kForward);
	    
	    static void reset()
	    {	
	    	red.set(Value.kOff);
	    	blue.set(Value.kOff);
	    }
	    
	    static void teleopShow()
	    {	
	    	// Strobe effect during wheel spool
			if (Control.xButt.getRawButton(5) || Control.xButt.getRawButton(6) || Control.xButt.getRawButton(2))
			{
				if (Control.xButt.getRawButton(5))
				{
					reset();
					Timer.delay(0.05);
					if (light.get())
					{
						blue();
					}
					else
					{
						red();
					}
					
				}
				if (Control.xButt.getRawButton(6))
				{
					reset();
					Timer.delay(0.08);
					if (light.get())
					{
						blue();
					}
					else
					{
						red();
					}
				}
				if (Control.xButt.getRawButton(2))
				{
					reset();
					Timer.delay(0.1);
					if (light.get())
					{
						blue();
					}
					else
					{
						red();
					}
				}
			}
			else
			{
				// Stay lit depending on hardware switch
				if (light.get())
				{
					blue();
				}
				else
				{
					red();
				}
			}
	    }
	    
	    static void red()
	    {
	    	red.set(Value.kOn);
	    	blue.set(Value.kOff);
	    }
	    
	    static void blue()
	    {
	    	blue.set(Value.kOn);
	    	red.set(Value.kOff);
	    }
	    
	    static void purple()
	    {
	    	red.set(Value.kOn);
	    	blue.set(Value.kOn);
	    }
	    
	    static void destroy()
	    {
	    	light.free();
	    	red.free();
	    	blue.free();
	    }
	    
	}
	
}
