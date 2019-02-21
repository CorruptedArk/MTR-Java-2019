/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.Talon;

/**
 * Add your docs here.
 */
public class DriveSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  private static final Talon rightTalon = new Talon(RobotMap.RIGHT_MOTOR_ID);
  private static final Talon leftTalon = new Talon(RobotMap.LEFT_MOTOR_ID);
  private static final DifferentialDrive chassis = new DifferentialDrive(leftTalon, rightTalon);

  public void drive(double leftSpeed, double rightSpeed) {
    chassis.tankDrive(leftSpeed, rightSpeed);

  }
  
  public void setSafetyEnabled(boolean enabled)
  {
    chassis.setSafetyEnabled(enabled);

  }


  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());

  }
}

  