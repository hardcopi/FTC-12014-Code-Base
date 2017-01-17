package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.HardwareFireWiresBot;

@Disabled
@Autonomous(name = "Drive, Shoot Twice, Push Ball & Park", group = "FireBot")
public class Drive_Shoot_Twice_Push_Ball_Park extends LinearOpMode {
    HardwareFireWiresBot robot = new HardwareFireWiresBot();
    long start_time;

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        robot.shootServo.setPosition(1);
        /* Drive a little over 1/2 way there */
        robot.leftMotor.setPower(-.3);
        robot.rightMotor.setPower(-.3);
        sleep(1500);
        /* Fire! */
        robot.drive(0, 0);
        robot.leftShooter.setPower(.3);
        robot.rightShooter.setPower(.3);
        sleep(1500);
        robot.shootServo.setPosition(-1);
        sleep(1500);
        robot.leftShooter.setPower(0);
        robot.rightShooter.setPower(0);
        sleep(1000);
        robot.shootServo.setPosition(1);
        sleep(2000);
        robot.liftServo.setPosition(1);
        sleep(1000);

        /* Let's get greedy and try another shot */
        /* Reset the servo */
        robot.shootServo.setPosition(1);
        /* Wait 1 second */
        sleep(1000);
        robot.liftServo.setPosition(1);
        sleep(1000);
        /* Put the ball in place to fire again */
        /* Reverse the shooter motors to settle the ball */
        robot.shootServo.setPosition(-1);
        robot.leftShooter.setPower(-.2);
        robot.rightShooter.setPower(-.2);
        sleep(1000);
        robot.shootServo.setPosition(1);
        sleep(1000);
        /* Fire! */
        robot.leftShooter.setPower(.3);
        robot.rightShooter.setPower(.3);
        sleep(1500);
        robot.shootServo.setPosition(-1);
        sleep(3000);

        /* Drive the rest of the distance */
        robot.leftMotor.setPower(-.3);
        robot.rightMotor.setPower(-.3);
        sleep(1000);
        robot.drive(0, 0);
        robot.shootServo.setPosition(1);
        sleep(2000);
    }
}
