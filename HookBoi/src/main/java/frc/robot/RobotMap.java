/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

  //The constants for the Xbox controller buttons
  public static final int A_BUTTON = 1;
  public static final int B_BUTTON = 2;
  public static final int X_BUTTON = 3;
  public static final int Y_BUTTON = 4;
  public static final int LEFT_BUMPER = 5;
  public static final int RIGHT_BUMPER = 6;
  public static final int BACK_BUTTON = 7;
  public static final int START_BUTTON = 8;
  public static final int LEFT_JOYSTICK_CLICK = 9;
  public static final int RIGHT_JOYSTICK_CLICK = 10;
  
  //Constants for Xbox Axes
  public static final int LEFT_X_AXIS = 0;
  public static final int LEFT_Y_AXIS = 1;
  public static final int LEFT_TRIGGER_AXIS = 2;
  public static final int RIGHT_TRIGGER_AXIS = 3;
  public static final int RIGHT_X_AXIS = 4;
  public static final int RIGHT_Y_AXIS = 5;
  public static final int D_PAD = 6; 

   //Constants for motor IDs
   public static final int RIGHT_MOTOR_ID = 0;
   public static final int LEFT_MOTOR_ID = 1;  

   //Constants for motor IDs for the hook
   public static final int HORIZONTAL_MOTOR_ID = 2 ;
   public static final int VERTICAL_MOTOR_ID = 3;

    //Constants for Servo IDs
    public static final int STOP_ARM_SERVO_ID = 4;

   //Constants for limit switches IDs
  //  public static final int BOTTOM_LIMIT_SWITCH_ID = 0;
  //  public static final int MIDDLE_LIMIT_SWITCH_ID = 1;
  //  public static final int TOP_LIMIT_SWITCH_ID = 2;

  public static final int FRONT_LIMIT_SWITCH_ID = 7;
  public static final int REAR_LIMIT_SWITCH_ID = 8;

 


   //Constants for Camera IDs
   public static final int CAMERA_1_ID = 0;
   public static final int CAMERA_2_ID = 1;
}

