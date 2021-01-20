/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import io.github.oblarg.oblog.annotations.*;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    @Log
    public static double PORT_HEIGHT=86.75;
    @Log
    public static final double ANGLE=14;
    @Log
    public static final double CAM_HEIGHT=9.25;
    //joystick ports
    public static final int A = 1;
    public static final int B = 2;
    public static final int X = 3;
    public static final int Y = 4;
    public static final int LEFT_BUMPER = 5;
    public static final int RIGHT_BUMPER = 6;
    public static final int BACK = 7;
    public static final int START = 8;
    public static final int LEFT_JOYSTICK_X = 0;
  	public static final int LEFT_JOYSTICK_Y = 1;
    public static final int RIGHT_JOYSTICK_X = 4;
    public static final int RIGHT_JOYSTICK_Y = 5;

  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

  // DIO
  public static final int HSWITCH=0;
  public static final int HSWITCH2 = 3;
  public static final int ISWITCH = 7;
  public static final int INTAKE1 = 8;
  public static final int INTAKE2 = 9;
  //PWM PORTS
  

  //CAN IDs
  //Sequence RIO 1 3 5 6 11 12 14 15 
  public static final int INTAKE = 1;
  public static final int INTAKEDEPLOY=2;
  public static final int HOPPER = 3;
  public static final int LAUNCHER1 = 5;
  public static final int LAUNCHER2 = 6;
  public static final int HANGER = 7;
  public static final int FRONT_RIGHT = 14;
  public static final int BACK_RIGHT = 15;
  public static final int FRONT_LEFT = 11;
  public static final int BACK_LEFT = 12;
  //Final Variables
  @Log
  public static double LAUNCHERKP=0.0003;
  @Log
  public static double LAUNCHERKI=0.00032;
  @Log
  public static double LAUNCHERKD=0.000000003;
  @Log
  public static double HOPPER_SPEED = 1;
  @Config
  public void setLAUNCHERKP(double value){
    LAUNCHERKP=value;
  }
  @Config
  public void setLAUNCHERKI(double value){
    LAUNCHERKI=value;
  }
  @Config
  public void setLAUNCHERKD(double value){
    LAUNCHERKD=value;
  }
  @Config
  public void setHOPPER_SPEED(double value){
    HOPPER_SPEED=value;
  }
}

