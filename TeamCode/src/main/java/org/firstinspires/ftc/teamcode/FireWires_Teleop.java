package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Teleop", group = "FireBot")
public class FireWires_Teleop extends OpMode {
    private static final float INTAKE_POWER = 1;
    private static final float INTAKE_POWER_REVERSE = -1;
    private static final float JOYSTICK_DEADBAND = .2f;
    private static final float JOYSTICK_OFFSET = 0;
    private static final float JOYSTICK_GAIN = .2f;
    private static final float SHOOTER_SERVO_UP = -1;
    private static final float SHOOTER_SERVO_DOWN = 1;
    private static final float DRIVE_SPEED = .6f;

    /* Declare OpMode members. */
    HardwareFireWiresBot robot = new HardwareFireWiresBot(); // use the class created to define a Pushbot's hardware
                                                         // could also use HardwarePushbotMatrix class.

    /*
     * Code to run ONCE when the driver hits INITF
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
        float left2;
        float right2;

        // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)
        left = gamepad1.left_stick_y;
        right = gamepad1.right_stick_y;
        left2 = gamepad2.left_stick_y;
        right2 = gamepad2.right_stick_y;


        /* Use the DPad for absolute control */
        if(gamepad1.dpad_up) {
            left = -DRIVE_SPEED;
            right = -DRIVE_SPEED;
            say("All Forward");
        }
        if(gamepad1.dpad_down){
            left = DRIVE_SPEED;
            right = DRIVE_SPEED;
            say("All Backward");
        }
        if(gamepad1.dpad_right){
            right = DRIVE_SPEED;
            left = 0;
            say("All Right");
        }
        if(gamepad1.dpad_left){
            left = DRIVE_SPEED;
            right = 0;
            say("All Left");
        }

        /**
         * Turn intake on at 100% to fix stuck balls
         */
        if (gamepad2.right_trigger == 1) {
            robot.intake(INTAKE_POWER_REVERSE);
        }

        /**
         * Turn intake on at 100% for normal intake
         */
        if (gamepad2.left_trigger == 1) {
            robot.intake(INTAKE_POWER);
        }

        /**
         * Turn intake off if bumpers not pressed
         */
        if (gamepad2.left_trigger == 0 && gamepad2.right_trigger == 0) {
            robot.intake(0);
        }

        if (!gamepad1.x & gamepad1.b) {
            robot.pusherServo.setPosition(.3);
        }

        if (gamepad1.x & !gamepad1.b) {
            robot.pusherServo.setPosition(.7);
        }

        if (!gamepad1.x & !gamepad1.b) {
            robot.pusherServo.setPosition(.5);
        }

        /**
         * Fire
         */
        if (gamepad2.a || gamepad2.x) {
            if (gamepad2.a) {
                robot.fire();
            } else {
                long setTime = System.currentTimeMillis();
                robot.fireFarther();

                /* Wait 1 second for the ball to settle */
                if (System.currentTimeMillis() - setTime > 1000) {
                    /* FIRE */
                    robot.shootServo.setPosition(SHOOTER_SERVO_UP);
                }
            }
        } else {
            say("Stopping Fire");
            robot.leftShooter.setPower(0);
            robot.rightShooter.setPower(0);
//            robot.move_shoot_servo(SHOOTER_SERVO_DOWN);
        }

        if (gamepad2.b && !gamepad2.a && left2 == 0) {
            robot.move_shoot_servo(SHOOTER_SERVO_UP);
        }
        if (!gamepad2.b && !gamepad2.a && left2 == 0) {
            robot.move_shoot_servo(SHOOTER_SERVO_DOWN);
        }

        if (right2 != 0) {
            robot.liftServo.setPosition(right2);
            say("Right2: " + right2);
        } else {
            robot.liftServo.setPosition(1);
        }

        if (left2 != 0) {
            robot.shootServo.setPosition(left2);
        }

        /* if left trigger not pressed run at 60% */
        if (gamepad1.left_trigger == 1) {
            left = left * .3f;
            right = right * .3f;
        }

        /* if left trigger not pressed run at 60% */
        if (gamepad1.right_trigger != 1) {
            left = left * .6f;
            right = right * .6f;
        }

        /**
         * Add the joystick conditioning
         * This should allow the driver to have better fine control over the robot
         * and make the robot generally less twitchy. It also keeps the motors from
         * being over taxed...
         */
//        left  = robot.joystick_conditioning(left, JOYSTICK_DEADBAND, JOYSTICK_OFFSET, JOYSTICK_GAIN);
//        right = robot.joystick_conditioning(right, JOYSTICK_DEADBAND, JOYSTICK_OFFSET, JOYSTICK_GAIN);

        robot.drive(left, right);

        telemetry.addData("Raw", robot.ods.getRawLightDetected());
        telemetry.addData("Normal", robot.ods.getLightDetected());

        robot.color.enableLed(true);

        telemetry.addData("Clear", robot.color.alpha());
        telemetry.addData("Red  ", robot.color.red());
        telemetry.addData("Green", robot.color.green());
        telemetry.addData("Blue ", robot.color.blue());

        telemetry.update();
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {

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
