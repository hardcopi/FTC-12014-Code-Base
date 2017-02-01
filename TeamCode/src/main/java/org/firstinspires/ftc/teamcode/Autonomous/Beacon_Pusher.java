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
    static double beginningLightLevel = 0.0;
    double reflectance = 0.0;
    static double lightlevel = 0.0;
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


        boolean drive = true;
        while (opModeIsActive()) {

            if (beginningLightLevel == 0.0) {
                beginningLightLevel = robot.ods.getLightDetected();
            }
            lightlevel = robot.ods.getLightDetected();
            telemetry.addData("Light Level", robot.ods.getLightDetected());
            telemetry.update();
            //Need to stop updating after stopping the app

            if (drive == true) {
                robot.drive((float) 0.35, (float) 0.35);
            } else {
                robot.drive((float) 0, (float) 0);
            }

            if (drive == true && (lightlevel > .02 && lightlevel < 1)) {

                drive = false;
            }

        }


    }
}
