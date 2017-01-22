package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HardwareFireWiresBot;

@Autonomous(name = "Red Shoot Sensors", group = "FireBot")
public class Red_Shoot_Sensors extends LinearOpMode {
    HardwareFireWiresBot robot = new HardwareFireWiresBot();
    long start_time;

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
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
