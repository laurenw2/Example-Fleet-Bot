// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Talon;

/*this robot will have four talons on its drivebase, two operating the left side and two operating the right
 * the "driver" controller will control these motors via arcadeDrive
 * another "operator" controller will control a simple spinning motor spinning forward with one button and backward with another
 * the buttons in this case are arbitrary -- you should always check in Driver Station which buttons should be used where
*/
public class Robot extends TimedRobot {

  /*this is where you will initialize the basic components of the robot
  */
    //MOTORS
      //motor initialization (the parameter for a Talon is whatever PWM port it is plugged into on the roboRIO)
      Talon l1 = new Talon(0);
      Talon l2 = new Talon(1);
    
      Talon r1 = new Talon(8);
      Talon r2 = new Talon(9);
    
      Talon spinningMotor = new Talon(5);

    //MOTOR CONTROLLER GROUPS
      //MotorControllerGroups group together each side, allowing them to drive as sides as opposed to individual motors
      MotorControllerGroup left = new MotorControllerGroup(l1, l2);
      MotorControllerGroup right = new MotorControllerGroup(r1, r2);
    
    //DIFFERENTIAL DRIVE  
      //Differential Drives distinguish the left and right sides as distinct sides, allowing for driving capability
      DifferentialDrive dT = new DifferentialDrive(left, right);
    
    //CONTROLLERS
      /*controller initiallization (the position of the name of the controller in the list of controllers in Driver Station will dictate it's parameter.
      to keep things consistent, driver should always be at location 0, i.e. the top of the list, and operator should always be at loc 1, i.e. second from top)
      */
      //"Joystick" controls the janky logitech controllers; if you wish to use Xbox Controllers, use "XboxController" (keep in mind button/axis values are different)
      Joystick driver = new Joystick(0);
      Joystick operator = new Joystick(1);

  @Override
  public void robotInit() {
    //will run as soon as the robot connects, even without being enabled
  }

  @Override
  public void robotPeriodic() {
    //will run periodically while robot is connected
  }

  @Override
  public void autonomousInit() {
    //will run at the beginning of autonomous period
  }

  @Override
  public void autonomousPeriodic() {
    //will run periodically throughout autonomous period
  }

  @Override
  public void teleopInit() {
    //will run at the beginning of teleop period
  }

  @Override
  public void teleopPeriodic() {
    //will run periodically throughout teleop period
    //where most of the code goes in Time Based programming

    //DRIVE CODE
        dT.arcadeDrive(driver.getRawAxis(0), driver.getRawAxis(1));
        //^an important line to memorize (again, axis values are arbitrary. figure them out yourself)
            /*note: sometimes speed and rotation flip, and the values need to be put in the opposite way. Also, play with polarity. Motors might not 
            always move the right direction*/

    
    
    //MOVING THE SPINNING MOTOR:
        if(operator.getRawButton(1)){
          spinningMotor.set(0.7);
          //spin forward at 70% when button 1 is pressed
          //never use 100% for anything; as your code gets more complex, safety measures like ramping are crucial on each motor to prevent burnout 
        }
        if(operator.getRawButton(2)){
          spinningMotor.set(-0.7);
          //spin backward at 70% when button 2 is pressed
        }
        if(!operator.getRawButton(1)&&!operator.getRawButton(2)){
          spinningMotor.set(0);
          //it is very important to set your motor back to 0 when neither button is pressed
          
          //also, it seems intuitive to use elses here. Yes, but they have never worked for me. Something about the scheduler doesn't play nice with them
          //I highly recommend sticking to lengthy if! lines
        }

  }

  @Override
  public void disabledInit() {
    //you get it by now
  }

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}
}
