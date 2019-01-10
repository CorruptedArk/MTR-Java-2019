/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.InvertDriveCommand;
import frc.robot.commands.StepDownDriveScaleCommand;
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

  private static volatile double driveScale = 0.5; 
  private static volatile boolean driveInverted = false;

  private static final double SCALE_STEP_SIZE = 0.1;


  public static final double ZERO_MARGIN = 0.18;
  public static final Joystick driver = new Joystick(RobotMap.DRIVER);
  public static final Joystick shotgun = new Joystick(RobotMap.SHOTGUN);

  public static final JoystickButton stepUpDriveButton = new JoystickButton(driver, RobotMap.LEFT_BUMPER);
  public static final JoystickButton stepDownDriveButton = new JoystickButton(driver, RobotMap.RIGHT_BUMPER);
  public static final JoystickButton invertDriveButton = new JoystickButton(driver, RobotMap.B_BUTTON);

  public OI()
  {
    // Bind buttons to commands here
    stepDownDriveButton.whenPressed(new StepDownDriveScaleCommand());
    stepUpDriveButton.whenPressed(new StepUpDriveScaleCommand());
    invertDriveButton.whenPressed(new InvertDriveCommand());

  }

  public static boolean isDriveInverted()
  {
    return driveInverted;
  }

  public static void invertDrive()
  {
    driveInverted = !driveInverted;
  }

  public static double getDriveScale()
  {
    return driveScale;
  }

  public static void stepUpDriveScale()
  {
    if(driveScale < 1.0)
    {
      driveScale += SCALE_STEP_SIZE;
    }
  }

  public static void stepDownDriveScale()
  {
    if(driveScale > SCALE_STEP_SIZE)
    {
      driveScale -= SCALE_STEP_SIZE;
    }
  }

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

}
