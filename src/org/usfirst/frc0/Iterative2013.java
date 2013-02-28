/*
 * FRC Team 334 2013
 * Brooklyn Technical High School
 * 
 * Program for 'Donatella', the Ultimate Ascent bot.
 * (C) 2013
 * 
 * WPILibJ is bound by the BSD license included in the source directory. By using this software you agree
 * to the terms stated within its respective agreement.
 * 
 * This software is bound by the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * You are free to share and modify this code as long as you release under the same license.
 * No commercial use is allowed.
 * 
 * http://creativecommons.org/licenses/by-nc-sa/3.0/
 */

package org.usfirst.frc0;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Iterative2013 extends IterativeRobot {	
    public void robotInit() 
    {
    	
    }
    public void autonomousInit()
    {
    	Air.reset();
    	Air.pressurize();
    	Drive.shooter.rightRound.set(0.76);
    }
    
    public void autonomousPeriodic() 
    {
    	// This automatically loops due to the way autonomousPeriodic is called
    	Air.shooter.autoShoot();
    }
    
    public void autonomousDisabled()
    {
    	Air.shooter.reset();
    }
    
    public void teleopInit()
    {
    	Air.reset();
    	Drive.shooter.init();
    	Drive.wheels.init(0.05);
    }

    public void teleopPeriodic() 
    { 	
    	Control.camera.execute(50);
    	Air.pressurize();
    	Drive.shooter.execute();
        Drive.wheels.execute();
        Air.elevator.execute();
        Air.shooter.execute();
    	Control.lights.teleopShow();
    }
    
    public void disabledInit()
    {
    	Air.reset();
    }
    
}
