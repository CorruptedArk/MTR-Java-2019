/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;


public class TeleOpCommand extends Command {
  public TeleOpCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_driveSystem);
    setInterruptible(true);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_driveSystem.setSafetyEnabled(true);

    // double horizontal = OI.buffer(RobotMap.LEFT_X_AXIS, OI.driver, OI.isDriveInverted(), OI.ZERO_MARGIN, -OI.ZERO_MARGIN, OI.getDriveScale());
    // double forward = OI.buffer(RobotMap.LEFT_Y_AXIS, OI.driver, OI.isDriveInverted(), OI.ZERO_MARGIN, -OI.ZERO_MARGIN, OI.getDriveScale());
    // double rotation = OI.buffer(RobotMap.RIGHT_X_AXIS, OI.driver, false, OI.ZERO_MARGIN, -OI.ZERO_MARGIN, OI.getDriveScale());

    // Robot.m_driveSystem.drive(horizontal, forward, rotation);
    
    double left;
    double right; 


    if(OI.isDriveInverted())
    {
      left = OI.buffer(RobotMap.LEFT_Y_AXIS, OI.driver, OI.isDriveInverted(), OI.ZERO_MARGIN, -OI.ZERO_MARGIN, OI.getDriveScale());
      right = OI.buffer(RobotMap.RIGHT_Y_AXIS, OI.driver, OI.isDriveInverted(), OI.ZERO_MARGIN, -OI.ZERO_MARGIN, OI.getDriveScale());
    }
    else
    {
      left = OI.buffer(RobotMap.RIGHT_Y_AXIS, OI.driver, OI.isDriveInverted(), OI.ZERO_MARGIN, -OI.ZERO_MARGIN, OI.getDriveScale());
      right = OI.buffer(RobotMap.LEFT_Y_AXIS, OI.driver, OI.isDriveInverted(), OI.ZERO_MARGIN, -OI.ZERO_MARGIN, OI.getDriveScale());
    }
   
    Robot.m_driveSystem.drive(left, right);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_driveSystem.drive(0, 0);
    Robot.m_driveSystem.setSafetyEnabled(false);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
