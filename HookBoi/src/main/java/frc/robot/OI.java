/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.InvertDriveBackwardsCommand;
import frc.robot.commands.RevertDriveForwardCommand;
import frc.robot.commands.StepDownArmScaleCommand;
import frc.robot.commands.StepDownDriveScaleCommand;
import frc.robot.commands.StepUpArmScaleCommand;
import frc.robot.commands.StepUpDriveScaleCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());

  public static final double ZERO_MARGIN = 0.18;
  private static volatile double driveScale = .5;
  public static final double DRIVE_SCALE_STEP_SIZE = 0.1;
  

  public static final int CAPTAIN_ID = 0;
  public static final int BUDDY_ID = 1;

  public static final Joystick captain = new Joystick(CAPTAIN_ID);
  public static final Joystick buddy = new Joystick(BUDDY_ID);
  
  private static volatile boolean driveInverted;

  public static final JoystickButton stepUpDriveButton = new JoystickButton(captain, RobotMap.LEFT_BUMPER);
  public static final JoystickButton stepDownDriveButton = new JoystickButton(captain, RobotMap.RIGHT_BUMPER);
  public static final JoystickButton invertDriveButton = new JoystickButton(captain, RobotMap.B_BUTTON);
  public static final JoystickButton revertDriveButton = new JoystickButton(captain, RobotMap.A_BUTTON);
  public static final JoystickButton stepUpArmScaleButton = new JoystickButton(buddy, RobotMap.LEFT_BUMPER);
  public static final JoystickButton stepDownArmScaleButton = new JoystickButton(buddy, RobotMap.RIGHT_BUMPER);
  public static final JoystickButton switchToCamera1Button = new JoystickButton(captain, RobotMap.LEFT_JOYSTICK_CLICK);
  public static final JoystickButton switchToCamera2Button = new JoystickButton(captain, RobotMap.RIGHT_JOYSTICK_CLICK);


   /**
     * This function buffers Joystick.getRawAxis() input.
     * If the raw axis output is between lowMargin and highMargin, 
     * buffer will automatically return 0
     * @param axisNum The ID for the axis of a Joystick.
     * @param joystick The Joystick that input is coming from. 
     * @param inverted If true, buffer will return the negative of the raw axis value
     * @param highMargin The high margin of the buffer.
     * @param lowMargin The low margin of the buffer.
     * @return moveOut - The buffered axis data from joystick.getRawAxis().
     **/

    public static double buffer(int axisNum, Joystick joystick, boolean inverted, double highMargin, double lowMargin) 
    {
        double moveIn = joystick.getRawAxis(axisNum);
        double moveOut;
        moveOut = 0.0;
        
        if(moveIn >= lowMargin && moveIn <= highMargin ) {
            moveOut = 0.0;
        }
        else{
            if(inverted){
                moveOut = -moveIn;
            }
            else if(!inverted){ 
                moveOut = moveIn;
            }    
        }
  
        return moveOut;
    }
   
    
    /**
     * This function buffers Joystick.getRawAxis() input.
     * If the raw axis output is between lowMargin and highMargin, 
     * buffer will automatically return 0
     * @param axisNum The ID for the axis of a Joystick.
     * @param joystick The Joystick that input is coming from. 
     * @param inverted If true, buffer will negate the raw axis value
     * @param highMargin The high margin of the buffer.
     * @param lowMargin The low margin of the buffer.
     * @param scale The magnitude that the raw axis value is multiplied by
     * @return moveOut - The buffered axis data from joystick.getRawAxis().
     **/
    public static double buffer(int axisNum, Joystick joystick, boolean inverted, double highMargin, double lowMargin, double scale) 
    {
        double moveIn = joystick.getRawAxis(axisNum);
        double moveOut;
        moveOut = 0.0;
        
        if(moveIn >= lowMargin && moveIn <= highMargin ) {
            moveOut = 0.0;
        }
        else{
            if(inverted){
                moveOut = -moveIn;
            }
            else if(!inverted){ 
                moveOut = moveIn;
            }    
        }
        
        scale = Math.abs(scale);
        
        if(scale >= 1){
            scale = 1;
        }
        
        moveOut = moveOut*scale;
        
        return moveOut;
    }

    public static boolean isDriveInverted()
    {
        return driveInverted;
    }

    public static void revertDriveForwards()
    {
        driveInverted = false;

    }

    public static void invertDriveBackwards()
    {
        driveInverted = true;
    }

    public static double getDriveScale()
    {
        return driveScale;
    }

    public static void invertDrive()
    {
        driveInverted = !driveInverted;
    }

    public static void stepUpDriveScale()
    {
        if(driveScale < 1.0)
        {
            driveScale += DRIVE_SCALE_STEP_SIZE;
        }
    }

    public static void stepDownDriveScale()
    {
        if(driveScale > DRIVE_SCALE_STEP_SIZE)
        {
            driveScale -= DRIVE_SCALE_STEP_SIZE;
        }
    }


    public OI()
    {
        //Dis is where da bind button commands go
        stepDownDriveButton.whenPressed(new StepDownDriveScaleCommand());
        stepUpDriveButton.whenPressed(new StepUpDriveScaleCommand());
        invertDriveButton.whenPressed(new InvertDriveBackwardsCommand());
        revertDriveButton.whenPressed(new RevertDriveForwardCommand());
        stepUpArmScaleButton.whenPressed(new StepUpArmScaleCommand());
        stepDownArmScaleButton.whenPressed(new StepDownArmScaleCommand());
    }
}

    



