package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HardwareFireWiresBot;

/**
 * Created by 17rodrigues on 2/1/17.
 */

public class Robot_Methods {
    public static void waitSeconds(double seconds) {
        long milliseconds = (long) (seconds * 1000);
        try {
            Thread.sleep(milliseconds);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @param robot   The robot to act on
     * @param power   The power for both motors
     * @param seconds The number of seconds to drive
     */
    public static void driveForSecondsAtPower(HardwareFireWiresBot robot, double power, double seconds) {
        robot.drive(power, power);
        waitSeconds(seconds);
        robot.drive(0, 0);
    }

    /**
     * @param robot   The robot to act on
     * @param power   The power for both motors
     * @param seconds The number of seconds to drive
     */
    public static void turnForSecondsAtPower(HardwareFireWiresBot robot, double right, double left, double seconds) {
        robot.drive(right, left);
        waitSeconds(seconds);
        robot.drive(0, 0);
    }


    public static void shootOneBall(HardwareFireWiresBot robot, double shooterPower) {
        /* Fire! */
        robot.leftShooter.setPower(shooterPower);
        robot.rightShooter.setPower(shooterPower);
        Robot_Methods.waitSeconds(2);
        robot.shootServo.setPosition(-1);
        Robot_Methods.waitSeconds(.5);
        //Ball is shot
        robot.shootServo.setPosition(1);
        robot.leftShooter.setPower(0);
        robot.rightShooter.setPower(0);
        //Everything back to normal
    }
}
