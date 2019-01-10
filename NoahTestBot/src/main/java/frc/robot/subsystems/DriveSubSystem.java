/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.Talon;

/**
 * Add your docs here.
 */
public class DriveSubSystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.


  private Talon frontLeftMotor = new Talon(RobotMap.FRONT_LEFT_MOTOR_ID);
  private Talon rearLeftMotor = new Talon(RobotMap.REAR_LEFT_MOTOR_ID); 
  private Talon frontRightMotor = new Talon(RobotMap.FRONT_RIGHT_MOTOR_ID);
  private Talon rearRightMotor = new Talon(RobotMap.REAR_RIGHT_MOTOR_ID);

  private MecanumDrive driveBase = new MecanumDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);

  public void setSafetyEnabled(boolean enabled)
  {
    driveBase.setSafetyEnabled(enabled);
  }

  public void drive(double horizontal, double forward, double rotation) {
    driveBase.driveCartesian(horizontal, forward, rotation);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
