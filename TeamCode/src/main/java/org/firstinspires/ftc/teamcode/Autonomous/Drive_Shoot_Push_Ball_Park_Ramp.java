package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HardwareFireWiresBot;

@Autonomous(name = "Drive, Shoot, Push Ball & Park Ramp WAIT", group = "FireBot")
public class Drive_Shoot_Push_Ball_Park_Ramp extends LinearOpMode {
    HardwareFireWiresBot robot = new HardwareFireWiresBot();
    long start_time;

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();
        robot.leftMotor.setPower(-.3);
        robot.rightMotor.setPower(-.3);
        sleep(500);
        robot.leftMotor.setPower(0);
        robot.rightMotor.setPower(0);
        sleep(7000);
        robot.shootServo.setPosition(1);
        /* Drive a little over 1/2 way there */
        robot.leftMotor.setPower(-.3);
        robot.rightMotor.setPower(-.3);
        sleep(1000);
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
        robot.leftMotor.setPower(-.3);
        sleep(500);
        robot.leftMotor.setPower(-.3);
        robot.rightMotor.setPower(-.3);
        sleep(2000);
        robot.drive(0, 0);
        robot.shootServo.setPosition(1);

        sleep(2000);
    }
}
