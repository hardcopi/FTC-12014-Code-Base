package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.LightSensor;

import org.firstinspires.ftc.teamcode.HardwareFireWiresBot;

@Autonomous(name = "Beacon Pusher", group = "FireBot")
public class Beacon_Pusher extends LinearOpMode {
    HardwareFireWiresBot robot = new HardwareFireWiresBot();
    long start_time;

    static final double RUN_POWER = 0.2;
    static final double FLOOR_REFLECTANCE = 0.2;
    static final double LINE_REFLECTANCE = 0.55;
    static final double THRESHOLD_REFLECTANCE = (LINE_REFLECTANCE + FLOOR_REFLECTANCE)/2;

    double reflectance = 0.0;

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        waitForStart();

        // turn on LED of light sensor.
        robot.ods.enableLed(true);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        // Abort this loop is started or stopped.
        while (!(isStarted() || isStopRequested())) {
            // Display the light level while we are waiting to start
            telemetry.addData("Light Level", robot.ods.getLightDetected());
            telemetry.update();
            idle();
        }

        // run until the white line is seen OR the driver presses STOP;
        while (opModeIsActive()) {
            // Display the light level while we are looking for the line
            telemetry.addData("Light Level",  robot.ods.getLightDetected());
            telemetry.update();
        }

        while (opModeIsActive()) {
            // send the info back to driver station using telemetry function.
            // Write the reflectance detected to a variable
            reflectance = robot.ods.getLightDetected();

            // If the sensor is on the line
            // only the right motor rotates to move it off the line
            if (reflectance >= THRESHOLD_REFLECTANCE) {
                robot.rightMotor.setPower(RUN_POWER);
                robot.leftMotor.setPower(0);
            }
            // Otherwise (if the sensor is off the line)
            // only the left motor rotates to move it back toward the line
            else {
                robot.leftMotor.setPower(RUN_POWER);
                robot.rightMotor.setPower(0);
                telemetry.addData("Reflectance", "%7f4", reflectance);
                telemetry.update();
            }
        }

        // Stop all motors
        robot.leftMotor.setPower(0);
        robot.rightMotor.setPower(0);
    }
}
