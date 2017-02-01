package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HardwareFireWiresBot;

@Autonomous(name = "Drive, Shoot, Push Ball & Park WAIT", group = "FireBot")
public class Drive_Shoot_Push_Ball_Park_Wait extends LinearOpMode {
    static final Robot_Methods amethods = new Robot_Methods();
    HardwareFireWiresBot robot = new HardwareFireWiresBot();
    long start_time;

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();

        robot.drive(-.3,-.3);
        amethods.waitSeconds(.5);
        robot.drive(0,0);

        amethods.waitSeconds(7);
        robot.shootServo.setPosition(1);
        /* Drive a little over 1/2 way there */
        robot.drive(-.3,-.3);
        amethods.waitSeconds(1);
        /* Fire! */
        robot.drive(0, 0);
        robot.leftShooter.setPower(.3);
        robot.rightShooter.setPower(.3);
        amethods.waitSeconds(1.5);
        robot.shootServo.setPosition(-1);
        amethods.waitSeconds(3);
        /* Drive the rest of the distance */
        robot.drive(-.3,-.3);
        amethods.waitSeconds(1);
        robot.drive(0, 0);
        robot.shootServo.setPosition(1);
        amethods.waitSeconds(2);
    }

}
