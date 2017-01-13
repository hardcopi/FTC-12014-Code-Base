package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.HardwareFireWiresBot;

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
        robot.leftShooter.setPower(.43);
        robot.rightShooter.setPower(.43);
        sleep(1500);
        /* After Firing Reset the shooter for ball 2 */
        robot.shootServo.setPosition(-1);
        sleep(1000);
        robot.shootServo.setPosition(1);
        sleep(1000);
        robot.shootServo.setPosition(-1);
        sleep(2000);
        robot.leftShooter.setPower(.43);
        robot.rightShooter.setPower(.43);
        sleep(1000);
        robot.shootServo.setPosition(1);
        /* After 2nd shot then wait 2 seconds then drive the rest of the way and reset the servo */
        sleep(2000);
        /* Drive the rest of the distance */
        robot.leftMotor.setPower(-.3);
        robot.rightMotor.setPower(-.3);
        sleep(1000);
        robot.drive(0, 0);
        robot.shootServo.setPosition(1);
        sleep(2000);
    }
}
