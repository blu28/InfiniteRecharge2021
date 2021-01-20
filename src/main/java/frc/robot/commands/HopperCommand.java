/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.Timer;
import java.util.TimerTask;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.HopperSubsystem;
/**
 * Command to feed Launcher
 */
public class HopperCommand extends CommandBase {
  private boolean fin=false;
  private HopperSubsystem m_hopperSubsystem;
  private Joystick coDrive = new Joystick(9);
  private Timer time;
  private boolean prevState=true;
  private double speed;
  private boolean auto=false;
  /**
   * hopper command constructor
   * @param subsystem the hopper subsystem
   * @param speed the speed of the hopper(%)
   * @param coDrive the codrivers input
   */
  public HopperCommand(HopperSubsystem subsystem, double speed, Joystick coDrive) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.speed = speed;
    this.coDrive=coDrive;
    m_hopperSubsystem = subsystem;
    addRequirements(subsystem);
    time=new Timer();
    auto=false;
    
  }


  public HopperCommand(HopperSubsystem hopperSubsystem, double hopperSpeed) {
    speed=hopperSpeed;
    m_hopperSubsystem=hopperSubsystem;
    addRequirements(hopperSubsystem);
    time=new Timer();
    auto=true;
    coDrive=new Joystick(3);
}


// Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putBoolean("gothere", false);
  }

  /**
   * called when the command is scheduled
   */
  @Override
  public void execute() {
    if(coDrive.getRawButton(Constants.LEFT_BUMPER)){
      if(m_hopperSubsystem.getHopperSwitch2()){
        m_hopperSubsystem.stop();
        SmartDashboard.putBoolean("gothere", true);
      }
      else{
        m_hopperSubsystem.start(-speed);
        SmartDashboard.putBoolean("gothere", true);
      }
    }
    else if (!coDrive.getRawButton(Constants.LEFT_BUMPER)){
      //check for ready ball and command to launch
      if(auto||(coDrive.getRawButton(Constants.RIGHT_BUMPER)&&!m_hopperSubsystem.getHopperSwitch2())){
        if(prevState){
          prevState=false;
          m_hopperSubsystem.stop();
          time.schedule(new TimerTask(){
            @Override
            public void run() {
              m_hopperSubsystem.start(speed);
              time.schedule(new TimerTask(){
                @Override
                public void run() {
                  
                  prevState=true;
                }
              },250);
            }
          },2000);
        }
      }
      //check to see if the hopper should stop
      else if(!m_hopperSubsystem.getHopperSwitch2()){
        m_hopperSubsystem.stop();
        Robot.m_robotContainer.getStopIntakeCommand().schedule();
      }
      //check for intent to launch or if there is a ball to intake without forcing into the launcher
      else if((coDrive.getRawButton(Constants.RIGHT_BUMPER))||(!m_hopperSubsystem.getHopperSwitchState())){
        m_hopperSubsystem.start(speed);
      }
      else if(m_hopperSubsystem.getHopperSwitchState()) {
        m_hopperSubsystem.stop();
      }
      if(auto){
        time.schedule(new TimerTask(){

          @Override
          public void run() {
            fin=true;

          }
        }, 5000);
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_hopperSubsystem.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return fin;
  }
}
