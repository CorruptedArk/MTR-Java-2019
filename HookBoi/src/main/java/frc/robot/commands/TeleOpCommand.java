/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.subsystems.HookSubsystem;

public class TeleOpCommand extends Command {
  public TeleOpCommand() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_driveSubsystem);
    requires(Robot.m_hookSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_driveSubsystem.setSafetyEnabled(true); 
  
    double leftSpeed;
    double rightSpeed;
    double armLiftSpeed;
    double armSlideSpeed;
 
    if(OI.isDriveInverted())
    {
      leftSpeed = OI.buffer(RobotMap.LEFT_Y_AXIS, OI.captain, OI.isDriveInverted(), OI.ZERO_MARGIN, -OI.ZERO_MARGIN, OI.getDriveScale());
      rightSpeed = OI.buffer(RobotMap.RIGHT_Y_AXIS, OI.captain, OI.isDriveInverted(), OI.ZERO_MARGIN, -OI.ZERO_MARGIN, OI.getDriveScale());
    } 
    else
    {
      leftSpeed = OI.buffer(RobotMap.RIGHT_Y_AXIS, OI.captain, OI.isDriveInverted(), OI.ZERO_MARGIN, -OI.ZERO_MARGIN, OI.getDriveScale());
      rightSpeed = OI.buffer(RobotMap.LEFT_Y_AXIS, OI.captain, OI.isDriveInverted(), OI.ZERO_MARGIN, -OI.ZERO_MARGIN, OI.getDriveScale());
    }

    armLiftSpeed = OI.buffer(RobotMap.RIGHT_Y_AXIS, OI.buddy, false, OI.ZERO_MARGIN, -OI.ZERO_MARGIN, HookSubsystem.getScale());
    armSlideSpeed = OI.buffer(RobotMap.LEFT_Y_AXIS, OI.buddy, false, OI.ZERO_MARGIN, -OI.ZERO_MARGIN, 0.5 * HookSubsystem.getScale());

    Robot.m_driveSubsystem.drive(leftSpeed, rightSpeed);
    Robot.m_hookSubsystem.controlArmLift(armLiftSpeed);
    Robot.m_hookSubsystem.controlArmSlide(armSlideSpeed);
  }   

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
