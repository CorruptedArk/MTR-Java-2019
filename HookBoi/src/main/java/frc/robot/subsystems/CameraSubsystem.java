/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.cameraserver.CameraServer;
import frc.robot.RobotMap;



/**
 * Add your docs here.
 */
public class CameraSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private static CameraServer cameraServer = CameraServer.getInstance();
  private static UsbCamera camera1;
  private static UsbCamera camera2;
  private static VideoSink videoController;

  public void initCameras() {
    camera1 = cameraServer.startAutomaticCapture(RobotMap.CAMERA_1_ID);
    camera2 = cameraServer.startAutomaticCapture(RobotMap.CAMERA_2_ID);
    videoController = cameraServer.getServer();
  }

  public void switchToCamera1() {
    videoController.setSource(camera1);
  }

  public void switchToCamera2() {
    videoController.setSource(camera2);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    
  }
}
