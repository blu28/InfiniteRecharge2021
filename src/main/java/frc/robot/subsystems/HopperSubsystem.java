/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.hal.CANAPIJNI;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


/**
 * The hopper subsystem
 */
public class HopperSubsystem extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private final WPI_TalonSRX hopperTurn;
  private DigitalInput hopperSwitch;
  private DigitalInput hopperSwitch2;
  /**
   * The hopper subsystem constructor
   */
  public HopperSubsystem() {
    hopperTurn = new WPI_TalonSRX(Constants.HOPPER);
    hopperSwitch = new DigitalInput(Constants.HSWITCH);
    hopperSwitch2=new DigitalInput(Constants.HSWITCH2);
  }
  /**
   * turns the hopper at a set percent speed
   * @param speed input from hopper command
   */
  public void start(double speed) {
    hopperTurn.set(speed);
    SmartDashboard.putNumber("hopper speed", hopperTurn.get());
  }
  /**
   * Sets the hopper speed to 0.
   */
  public void stop(){
    hopperTurn.set(0);
    //SmartDashboard.putNumber("hopper speed", hopperTurn.get());
  }
  /**
   * gets digital input 0's state
   * @return returns digital input 0's state
   */
  public boolean getHopperSwitchState() {
    return hopperSwitch.get();
  }
  /**
   * gets digital input 3's state
   * @return returns digital input 3's state
   */
  public boolean getHopperSwitch2(){
    return hopperSwitch2.get();
  }
}
