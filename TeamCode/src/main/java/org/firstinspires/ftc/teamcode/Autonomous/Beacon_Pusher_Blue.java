package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.HardwareFireWiresBot;
import org.firstinspires.ftc.teamcode.R;

@Autonomous(name = "Beacon Pusher Blue", group = "FireBot")
public class Beacon_Pusher_Blue extends LinearOpMode  {
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
        boolean drive = true;
        robot.leftMotor.setPower(.3);
        robot.rightMotor.setPower(.3);
        while (opModeIsActive()) {
            // send the info back to driver station using telemetry function.
            // Write the reflectance detected to a variable
            reflectance = robot.ods.getLightDetected();

            // If the sensor is on the line
            // only the right motor rotates to move it off the line
            if(reflectance > THRESHOLD_REFLECTANCE && drive ==true){
                sleep(200);
                robot.leftMotor.setPower(0);
                robot.rightMotor.setPower(0);
                drive = false;

            }
            if(!drive){
                //code to execute after finding the line
                robot.leftMotor.setPower(.3);
                robot.rightMotor.setPower(0);
                sleep(1000);
                robot.leftMotor.setPower(.3);
                robot.rightMotor.setPower(.3);
                sleep(1000);
                robot.leftMotor.setPower(0);
                robot.rightMotor.setPower(0);

            }

        }

        // Stop all motors
        robot.leftMotor.setPower(0);
        robot.rightMotor.setPower(0);
    }
}
