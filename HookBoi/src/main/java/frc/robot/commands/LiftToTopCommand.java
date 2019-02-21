/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.HookSubsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class LiftToTopCommand extends Command {

  private boolean finished;
  private boolean direction;
  private static boolean running = false;

  public static boolean getRunning() {
    return running;
  }

  public LiftToTopCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_hookSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    finished = false;
    if(LiftToBottomCommand.getRunning() || LiftToMiddleCommand.getRunning() || LiftToTopCommand.getRunning()) {
      end();
    } 
    else {
      running = true;
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.m_hookSubsystem.checkTopSwitch()) {
      end();
    }
    else {
      Robot.m_hookSubsystem.liftUp();
      direction = HookSubsystem.UP;
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return finished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    if (running) {
      Robot.m_hookSubsystem.stopTheLift();
      Robot.m_hookSubsystem.setLastSwitchClicked(RobotMap.TOP_LIMIT_SWITCH_ID);
      Robot.m_hookSubsystem.resetAllCounters();
      Robot.m_hookSubsystem.setLastDirection(direction);
    }
    finished = true;
    running = false;
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.m_hookSubsystem.stopTheLift();
    Robot.m_hookSubsystem.setLastDirection(direction);
    running = false;
  }
}
