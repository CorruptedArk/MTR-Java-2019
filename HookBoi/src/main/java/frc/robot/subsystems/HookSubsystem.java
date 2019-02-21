/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Talon;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Counter;


/**
 * Add your docs here.
 */
public class HookSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private static final Talon verticalTalon = new Talon(RobotMap.VERTICAL_MOTOR_ID);
  private static final Talon armSlideTalon = new Talon(RobotMap.HORIZONTAL_MOTOR_ID);
  private static final DigitalInput bottomSwitch = new DigitalInput(RobotMap.BOTTOM_LIMIT_SWITCH_ID);
  private static final DigitalInput middleSwitch = new DigitalInput(RobotMap.MIDDLE_LIMIT_SWITCH_ID);
  private static final DigitalInput topSwitch = new DigitalInput(RobotMap.TOP_LIMIT_SWITCH_ID);
  private static final Counter bottomCounter = new Counter(bottomSwitch);
  private static final Counter middleCounter = new Counter(middleSwitch);
  private static final Counter topCounter = new Counter(topSwitch);

  public static final boolean UP = true;
  public static final boolean DOWN = false;
  
  private static int lastSwitchClicked = RobotMap.BOTTOM_LIMIT_SWITCH_ID;
  private static boolean lastDirection = DOWN;
  
  
  private static double scale = 0.5;
  private static final double HOOK_SCALE_STEP_SIZE = 0.1; 

  public void controlArmSlide(double armSlideSpeed) {
    armSlideTalon.set(armSlideSpeed);
  }

  public void controlArmLift(double armLiftSpeed) {
    verticalTalon.set(armLiftSpeed);
  }

  public void liftUp() {
    verticalTalon.set(scale);
  }

  public void liftDown() {
    verticalTalon.set(-scale);
  }

  public void stopTheLift() {
    verticalTalon.set(0.0);
  }

  public boolean checkTopSwitch() {
    return topSwitch.get() || topCounter.get() > 0;
  }

  public boolean checkMiddleSwitch() {
    return middleSwitch.get() || middleCounter.get() > 0;
  }

  public boolean checkBottomSwitch() {
    return bottomSwitch.get() || bottomCounter.get() > 0;
  }

  public boolean getLastDirection() {
    return lastDirection;
  }

  public void setLastDirection(boolean direction) {
    lastDirection = direction;
  } 

  public int getLastSwitchClicked(){
    return lastSwitchClicked;
  }

  public void setLastSwitchClicked(int switchID) {
    lastSwitchClicked = switchID;
  }

  public void resetTopCounter() {
    topCounter.reset();
  }

  public void resetMiddleCounter() {
    middleCounter.reset();
  }

  public void resetBottomCounter() {
    bottomCounter.reset();
  }

  public void resetAllCounters() {
    resetBottomCounter();
    resetMiddleCounter();
    resetTopCounter();
  }

  public static void stepUpScale() {
    if(scale < 1.0)
    {
      scale += HOOK_SCALE_STEP_SIZE;
    }
  }

  public static void stepDownScale()
  {
    if(scale > HOOK_SCALE_STEP_SIZE)
    {
      scale -= HOOK_SCALE_STEP_SIZE;
    }
  }

  public static double getScale() {
    return scale;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
