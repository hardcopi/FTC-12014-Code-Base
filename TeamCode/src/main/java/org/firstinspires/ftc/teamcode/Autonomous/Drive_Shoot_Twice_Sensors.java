package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HardwareFireWiresBot;

@Autonomous(name = "Drive, Shoot Twice, Beacons", group = "FireBot")
public class Drive_Shoot_Twice_Sensors extends LinearOpMode {
    HardwareFireWiresBot robot = new HardwareFireWiresBot();
    long start_time;

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        /* Drive a little over 1/2 way there */
        robot.leftMotor.setPower(-.25);
        robot.rightMotor.setPower(-.25);
        sleep(1500);

        /* Fire! */
        robot.drive(0, 0);
        robot.leftShooter.setPower(.22);
        robot.rightShooter.setPower(.22);
        sleep(1500);
        robot.shootServo.setPosition(-1);
        sleep(500);
        robot.shootServo.setPosition(1);
        robot.leftShooter.setPower(0);
        robot.rightShooter.setPower(0);
        sleep(1000);
        /* Fire again! */
        robot.shootServo.setPosition(1);
        sleep(2000);
        robot.leftShooter.setPower(.22);
        robot.rightShooter.setPower(.22);
        sleep(1000);
        robot.shootServo.setPosition(-1);
        sleep(1000);

        /* Drive the rest of the distance */
        robot.leftMotor.setPower(-.3);
        robot.rightMotor.setPower(-.3);
        sleep(1000);
        robot.leftShooter.setPower(0);
        robot.rightShooter.setPower(0);
        robot.drive(0, 0);

        /* Reset the servo for the match */
        robot.shootServo.setPosition(1);
        sleep(500);

        robot.leftMotor.setPower(-.3);
        robot.rightMotor.setPower(0);
        sleep(800);
        robot.leftMotor.setPower(-.3);
        robot.rightMotor.setPower(-.3);
        sleep(1300);
        robot.leftMotor.setPower(0);
        robot.rightMotor.setPower(0);
        sleep(1000);
        robot.leftMotor.setPower(-.3);
        robot.rightMotor.setPower(-.3);
        sleep(500);
    }
}
