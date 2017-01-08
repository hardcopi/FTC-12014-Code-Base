package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="FireWires: Teleop Tank", group="Pushbot")
public class FireWires_Teleop extends OpMode {
    private static final double SHOOTER_WAIT_TIME = 1000;
    private static final double SHOOTER_SHOOT_STRENGTH = .43;
    private static final double SHOOTER_REVERSE_STRENGTH = -.2;
    private static final double SHOOTER_SERVO_UP = -1;
    private static final double SHOOTER_SERVO_DOWN = 1;
    private static final double INTAKE_POWER = 1;
    private static final double INTAKE_POWER_REVERSE = 1;
    private static final float JOYSTICK_DEADBAND = .2f;
    private static final float JOYSTICK_OFFSET = 0;
    private static final float JOYSTICK_GAIN = .2f;


    /* Declare OpMode members. */
    HardwareFireWiresBot robot = new HardwareFireWiresBot(); // use the class created to define a Pushbot's hardware
                                                         // could also use HardwarePushbotMatrix class.

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

    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        float left;
        float right;
        float x;

        // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)
        left = gamepad1.left_stick_y;
        right = gamepad1.right_stick_y;

        if(gamepad1.dpad_up) {
            left = 1;
            right = 1;
            say("All Forward");
        }
        if(gamepad1.dpad_down){
            left = -1;
            right = -1;
            say("All Backward");
        }
        if(gamepad1.dpad_right){
            left = 1;
            right = 0;
            say("All Right");
        }
        if(gamepad1.dpad_left){
            right = 1;
            left = 0;
            say("All Left");
        }

        /**
         * Turn on and off joystick conditioning
         */
        if (!gamepad1.right_bumper) {
            say("Joystick Unconditioned...");
        } else {
            say("Joystick Conditioned...");
            left = JoystickConditioning(left, JOYSTICK_DEADBAND, JOYSTICK_OFFSET, JOYSTICK_GAIN);
            right = JoystickConditioning(right, JOYSTICK_DEADBAND, JOYSTICK_OFFSET, JOYSTICK_GAIN);
        }

        /**
         * Turn intake on at 100% to fix stuck balls
         */
        if (gamepad2.right_trigger == 1) {
            robot.intakeMotor.setPower(INTAKE_POWER_REVERSE);
        }

        /**
         * Turn intake on at 100% for normal intake
         */
        if (gamepad2.left_trigger == 1) {
            robot.intakeMotor.setPower(INTAKE_POWER);
        }

        /**
         * Turn intake off if bumpers not pressed
         */
        if (gamepad2.left_trigger == 0 && gamepad2.right_trigger == 0) {
            robot.intakeMotor.setPower(-1);
        }

        /**
         * Fire
         */
        if (gamepad2.a) {
            long setTime = System.currentTimeMillis();
            /* Reverse the shooter motors to settle the ball */
            robot.leftShooter.setPower(SHOOTER_REVERSE_STRENGTH);
            robot.rightShooter.setPower(SHOOTER_REVERSE_STRENGTH);
            /* Use the servo to put the ball into place */
            robot.shootServo.setPosition(-1);
            /* Wait 1 second for the ball to settle */
            if (System.currentTimeMillis() - setTime > SHOOTER_WAIT_TIME) {
                /* Motors to speed */
                robot.leftShooter.setPower(SHOOTER_SHOOT_STRENGTH);
                robot.rightShooter.setPower(SHOOTER_SHOOT_STRENGTH);
                /* FIRE */
                robot.shootServo.setPosition(SHOOTER_SERVO_UP);
            }
        } else {
            robot.leftShooter.setPower(0);
            robot.rightShooter.setPower(0);
        }

        if (gamepad2.b) {
            robot.shootServo.setPosition(SHOOTER_SERVO_DOWN);
        } else {
            robot.shootServo.setPosition(SHOOTER_SERVO_UP);
        }

        say("Right: " + right);
        say("Left: " + left);
        robot.leftMotor.setPower(left);
        robot.rightMotor.setPower(right);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {

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
