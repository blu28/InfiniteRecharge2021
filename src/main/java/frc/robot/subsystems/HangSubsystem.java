/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class HangSubsystem extends SubsystemBase {
  private WPI_TalonFX hanger;
  private SupplyCurrentLimitConfiguration up,down;
  //private TalonFXConfiguration up;
  public HangSubsystem() {
    hanger=new WPI_TalonFX(Constants.HANGER);
    up=new SupplyCurrentLimitConfiguration(true, 3, 1, 1);
    down=new SupplyCurrentLimitConfiguration(true, 3, 3, 0);
    hanger.configGetSupplyCurrentLimit(up);
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  public void reach(){
    hanger.configGetSupplyCurrentLimit(up);
    hanger.set(0.5);
  }
  public void stop(){
    hanger.set(0);
  }
  public void pull(){
    hanger.configGetSupplyCurrentLimit(down);
    hanger.set(-.5);
  }
  
}
