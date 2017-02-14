package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HardwareFireWiresBot;
import org.firstinspires.ftc.teamcode.R;

@Autonomous(name = "Beacon Pusher Blue", group = "FireBot")
public class Beacon_Pusher_Blue extends LinearOpMode {
    HardwareFireWiresBot robot = new HardwareFireWiresBot();
    long start_time;

    static final double RUN_POWER = -0.13;
    static final double FLOOR_REFLECTANCE = 0.2;
    static final double LINE_REFLECTANCE = 0.04;
    static final double THRESHOLD_REFLECTANCE = (LINE_REFLECTANCE + FLOOR_REFLECTANCE) / 2;
    static final double COLOR_THRESHOLD = 2;
    double reflectance = 0.0;

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        // Set the LED in the beginning
        robot.color.enableLed(false);

        waitForStart();

        // turn on LED of light sensor.
        robot.ods.enableLed(true);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.addData("Light Level", robot.ods.getLightDetected());
        telemetry.addData("Light Level", robot.ods.getRawLightDetected());
        telemetry.update();

        while (opModeIsActive()) {
            robot.leftMotor.setPower(RUN_POWER);
            robot.rightMotor.setPower(RUN_POWER);
            sleep(800);
            robot.leftMotor.setPower((RUN_POWER * 2));
            robot.rightMotor.setPower(-RUN_POWER);
            sleep(650);

            boolean drive = true;
            robot.leftMotor.setPower(RUN_POWER);
            robot.rightMotor.setPower(RUN_POWER);
            while (drive) {
                // Display the light level while we are looking for the line
                reflectance = robot.ods.getLightDetected();
                telemetry.addData("Light Level", reflectance);
                telemetry.update();

                // If the sensor is on the line
                // only the right motor rotates to move it off the line
                if (reflectance > THRESHOLD_REFLECTANCE) {
                    robot.leftMotor.setPower(0);
                    robot.rightMotor.setPower(0);
                    drive = false;
                }
            }

            telemetry.addData("Light Level", "Found White Line!!!");
            telemetry.update();
            sleep(200);

            robot.leftMotor.setPower(-RUN_POWER);
            robot.rightMotor.setPower(-RUN_POWER);
            sleep(750);

            robot.leftMotor.setPower((RUN_POWER * 2));
            robot.rightMotor.setPower(-RUN_POWER);
            sleep(1100);
            robot.leftMotor.setPower(RUN_POWER);
            robot.rightMotor.setPower(RUN_POWER);
            sleep(650);

            robot.leftMotor.setPower(-RUN_POWER);
            robot.rightMotor.setPower(-RUN_POWER);
            sleep(500);

            telemetry.addData("Red", robot.color.red());
            telemetry.addData("Blue", robot.color.blue());
            telemetry.update();
            sleep(1000);

            while (robot.color.blue() < COLOR_THRESHOLD) {
                robot.leftMotor.setPower((RUN_POWER * 2));
                robot.rightMotor.setPower((RUN_POWER * 2));
                sleep(1000);
                telemetry.addData("Blue", robot.color.blue());
                telemetry.addData("Red", robot.color.red());
                telemetry.update();
                robot.leftMotor.setPower(-RUN_POWER);
                robot.rightMotor.setPower(-RUN_POWER);
                sleep(500);
                robot.leftMotor.setPower(0);
                robot.rightMotor.setPower(0);
                sleep(6000);
            }

            telemetry.addData("Say", "I GOT IT!!!!!!!!!!!");
            telemetry.update();
            robot.leftMotor.setPower(-RUN_POWER);
            robot.rightMotor.setPower(-RUN_POWER);
            sleep(4000);
        }
        // Stop all motors
        robot.leftMotor.setPower(0);
        robot.rightMotor.setPower(0);
        sleep(5000);
    }
}
