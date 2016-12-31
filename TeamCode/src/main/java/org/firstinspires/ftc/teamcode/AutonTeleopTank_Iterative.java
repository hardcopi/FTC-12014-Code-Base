package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="FireWires: Auto Tank", group="Pushbot")
public class AutonTeleopTank_Iterative extends OpMode{

    /* Declare OpMode members. */
    HardwarePushbot robot = new HardwarePushbot(); // use the class created to define a Pushbot's hardware
                                                         // could also use HardwarePushbotMatrix class.
    long start_time;
    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Systems Initialized...");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {

    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        //int dist = (int) robot.ConvertDistance( 1);
        say("Distance " + robot.distance);
        robot.setStart();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop(){
        robot.DriveDistance(1,5);
    }



    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        robot.Drive(0);
    }

    public static float JoystickConditioning(float x, float db, float off, float gain) {
        float output = 0;
        boolean sign = (x > 0);

        x = Math.abs(x);
        if (x > db) {
            output = (float) (off - ((off - 1) * Math.pow(((db - x) / (db - 1)), gain)));
            output *= sign ? 1 : -1;
        }
        return output;
    }

    public void print(String command, String string)
    {
        telemetry.addData(command, string);
    }
    private void say(String string)
    {
        telemetry.addData("Say", string);
    }
}
