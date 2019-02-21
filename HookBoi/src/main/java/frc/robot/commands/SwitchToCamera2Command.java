/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SwitchToCamera2Command extends Command {

  private boolean finished;

  public SwitchToCamera2Command() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    setInterruptible(false);
    requires(Robot.m_cameraSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    finished = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_cameraSubsystem.switchToCamera2();
    finished = true;

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return finished;
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
