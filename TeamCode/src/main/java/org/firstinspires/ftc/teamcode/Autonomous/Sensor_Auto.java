package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.robot.Robot;

import org.firstinspires.ftc.teamcode.HardwareFireWiresBot;

@Autonomous(name = "Sensor Auto", group = "FireBot")
public class Sensor_Auto extends LinearOpMode {
    HardwareFireWiresBot robot = new HardwareFireWiresBot();
    static final double SHOOTER_POWER = .25;
    long start_time;

    @Override
    public void runOpMode() {

        robot.init(hardwareMap);
        waitForStart();

        Robot_Methods.driveForSecondsAtPower(robot, -.25, 1);
        Robot_Methods.shootOneBall(robot, SHOOTER_POWER);
        Robot_Methods.waitSeconds(1);
        Robot_Methods.shootOneBall(robot, SHOOTER_POWER);
        Robot_Methods.waitSeconds(1);
        Robot_Methods.turnForSecondsAtPower(robot, .25, -.25, 1);
        Robot_Methods.driveForSecondsAtPower(robot, -.25, .5);

        /* Reset the servo for the match */
        robot.shootServo.setPosition(1);
        sleep(2000);
    }
}
