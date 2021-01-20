/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class IntakeSubsystem extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private WPI_TalonSRX intake;
  public WPI_TalonSRX deploy;
  private Encoder deployEncoder;
  private DigitalInput dio;
  private TalonSRXConfiguration intakeSettings;
  
  public IntakeSubsystem() {
    deploy=new WPI_TalonSRX(Constants.INTAKEDEPLOY);
    deployEncoder=new Encoder(Constants.INTAKE1, Constants.INTAKE2);
    deployEncoder.setDistancePerPulse(1.0/512.0);
    deployEncoder.reset();
    deployEncoder.setMaxPeriod(4);
    dio=new DigitalInput(Constants.ISWITCH);
    intake=new WPI_TalonSRX(Constants.INTAKE);
    intakeSettings=new TalonSRXConfiguration();
    intakeSettings.peakCurrentLimit= 5;
    intakeSettings.continuousCurrentLimit=5;
    intakeSettings.peakCurrentDuration=0;
    intake.configAllSettings(intakeSettings);
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  public boolean deployIntake(int direction){
    double distance=deployEncoder.getDistance();
    if(!dio.get()){
      resetEncoder();
    }
    if (direction==-1){
      if(distance>-2.45){
        deploy.set(direction*.5);
      }
      else{
        deploy.set(0);
      }//*/
      return (distance)<=-2.45;
    }
    else{
      if(distance<0){
        deploy.set(direction*.5);
      }
      else{
        deploy.set(0);
      }
      return distance>=0;
    }
  }
  public void periodic(){
    SmartDashboard.putNumber("deploy distance", deployEncoder.getDistance());
    SmartDashboard.putNumber("deploy count", deployEncoder.get());
    SmartDashboard.putBoolean("deployswitch", dio.get());
  }
  public void runIntake(double speed){
    intake.set(speed);
  }
  public void resetEncoder(){
    deployEncoder.reset();
  }
  public boolean getSwitch(){
    return dio.get();
  }
}
