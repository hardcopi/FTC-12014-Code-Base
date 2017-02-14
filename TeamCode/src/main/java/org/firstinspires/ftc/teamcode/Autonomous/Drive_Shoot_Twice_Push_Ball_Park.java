package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.HardwareFireWiresBot;

@Autonomous(name = "Drive, Shoot Twice, Push Ball & Park", group = "FireBot")
public class Drive_Shoot_Twice_Push_Ball_Park extends LinearOpMode {
    HardwareFireWiresBot robot = new HardwareFireWiresBot();
    static final double SHOOTER_POWER = .25;
    long start_time;

    @Override
    public void runOpMode() {

        robot.init(hardwareMap);
        waitForStart();
        /* Drive a little over 1/2 way there */
        robot.drive(-.25, -.25);

        sleep(2000);

        /* Fire! */
        robot.drive(0, 0);
        robot.leftShooter.setPower(SHOOTER_POWER);
        robot.rightShooter.setPower(SHOOTER_POWER);
        sleep(2000);
        robot.shootServo.setPosition(-1);
        sleep(500);
        robot.shootServo.setPosition(1);
        robot.leftShooter.setPower(0);
        robot.rightShooter.setPower(0);
        sleep(1000);
        /* Fire again! */
        robot.shootServo.setPosition(1);
        sleep(2000);
        robot.leftShooter.setPower(SHOOTER_POWER);
        robot.rightShooter.setPower(SHOOTER_POWER);
        sleep(1000);
        robot.shootServo.setPosition(-1);
        sleep(1000);
        Robot_Methods.driveForSecondsAtPower(robot, -.3, 1.5);

//        Robot_Methods.driveForSecondsAtPower(robot,-.25,1.5);
//        Robot_Methods.shootOneBall(robot,SHOOTER_POWER);
//        sleep(1000);
//        robot.shootServo.setPosition(-1);
//        sleep(1000);
//        Robot_Methods.shootOneBall(robot,SHOOTER_POWER);
//        sleep(1000);
//        /* Drive the rest of the distance */
//        Robot_Methods.driveForSecondsAtPower(robot,-.3,1.5);
//
//        /* Reset the servo for the match */
//        robot.shootServo.setPosition(1);
//        sleep(2000);
    }
}
