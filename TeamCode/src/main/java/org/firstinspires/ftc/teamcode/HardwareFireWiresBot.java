package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.internal.TelemetryImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *,]]
 *
 * This class defines all hardware aspects of the robot
 *
 */
public class HardwareFireWiresBot
{
    private static final double SHOOTER_WAIT_TIME = 1000;
    private static final double SHOOTER_SHOOT_STRENGTH = .60;
    private static final double SHOOTER_REVERSE_STRENGTH = -.2;
    private static final float SHOOTER_SERVO_UP = -1;
    private static final float SHOOTER_SERVO_DOWN = 1;

    public DcMotor r = null;
    /* DC Motors */
    public DcMotor intakeMotor, leftShooter, rightShooter, leftMotor, rightMotor = null;
    public ArrayList<DcMotor> motorArray = new ArrayList<DcMotor>(Arrays.asList(
            leftMotor, rightMotor, intakeMotor, leftShooter, rightShooter));

    /* Servo Motors */
    public Servo shootServo = null;
    public Servo liftServo = null;

    public Map<DcMotor, String> m = new HashMap<DcMotor, String>();
    public long start_time;
    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();
    public int distance = 0;

    /* Constructor */
    public HardwareFireWiresBot() {

    }

    public void setStart(){
        start_time = System.currentTimeMillis();
    }
    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Servos
        if (hwMap.servo.get("shoot-servo") != null) {
            shootServo = hwMap.servo.get("shoot-servo");
        }

        // Define and Initialize Servos
        if (hwMap.servo.get("lift-servo") != null) {
            liftServo = hwMap.servo.get("lift-servo");
        }

        // Define and Initialize Motors
        if(hwMap.dcMotor.get("left_drive") != null){
            leftMotor   = hwMap.dcMotor.get("left_drive");
            leftMotor.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors

        }
        if(hwMap.dcMotor.get("right_drive")!=null){
           rightMotor  = hwMap.dcMotor.get("right_drive");
            rightMotor.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors

        }
        if (hwMap.dcMotor.get("intake_motor") != null) {
            intakeMotor = hwMap.dcMotor.get("intake_motor");
            intakeMotor.setDirection(DcMotor.Direction.FORWARD);

        }
        if (hwMap.dcMotor.get("left_shooter_motor") != null) {
            leftShooter = hwMap.dcMotor.get("left_shooter_motor");
            leftShooter.setDirection(DcMotor.Direction.REVERSE);

        }
        if (hwMap.dcMotor.get("right_shooter_motor") != null) {
            rightShooter = hwMap.dcMotor.get("right_shooter_motor");
            rightShooter.setDirection(DcMotor.Direction.FORWARD);
        }

        // Set all motors to zero power
        for(DcMotor motorVar: motorArray){
            if(motorVar !=null) {
                motorVar.setPower(0);
                motorVar.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            }
        }

    }

    /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs  Length of wait cycle in mSec.
     */
    public void waitForTick(long periodMs) {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0) {
            try {
                Thread.sleep(remaining);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Reset the cycle clock for the next pass.
        period.reset();
    }

    /**
     * Start Driving the robot
     *
     * @param power 	-1 to 1 range of power provided to robot
     */
    public void Drive(float power) {
        leftMotor.setPower(power);
        rightMotor.setPower(power);
    }

    /**
     * Turn the robot right
     *
     * @param power 	-1 to 1 range of power provided to robot
     */
    public void TurnRight(double power) {
        leftMotor.setPower(-power);
        rightMotor.setPower(power);
    }

    /**
     * Turn the robot left
     *
     * @param power 	-1 to 1 range of power provided to robot
     */
    public void TurnLeft(double power) {
        leftMotor.setPower(power);
        rightMotor.setPower(-power);
    }

    /**
     * Stop Moving
     *
     */
    public void Stop() {
        Drive(0);
    }

    /**
     * Convert Distance to Rotations
     *
     * @param distance 	Distance in inches
     */
    public double ConvertDistance(double distance) {
        int Andymark_Tickers_Per_Rev = 1120;
	/* Find number of rotations (12.5 is circumfrence of motor) */
        distance = distance / 12.5;
        distance = distance * Andymark_Tickers_Per_Rev;
        return(distance);
    }

    /**
     * Fire Command
     */
    public void fire() {
        long setTime = System.currentTimeMillis();
        leftShooter.setPower(SHOOTER_SHOOT_STRENGTH);
        rightShooter.setPower(SHOOTER_SHOOT_STRENGTH);
    }

    /**
     * Stop Firing
     */
    public void stop_firing() {
        leftShooter.setPower(0);
        rightShooter.setPower(0);
    }

    /**
     * Set intake power
     *
     * @param power
     */
    public void intake(float power) {
        intakeMotor.setPower(power);
    }

    /**
     * Lift Servo
     *
     * @param distance
     */
    public void move_shoot_servo(float distance) {
        long setTime = System.currentTimeMillis();
        if (distance == -1) {
            leftShooter.setPower(SHOOTER_REVERSE_STRENGTH);
            rightShooter.setPower(SHOOTER_REVERSE_STRENGTH);
            shootServo.setPosition(distance);
        } else {
            shootServo.setPosition(distance);
        }
    }

    public void drive(float left, float right) {
        leftMotor.setPower(left);
        rightMotor.setPower(right);
    }

    /**
     * Turn a certain Distance in inches
     *
     * @param power 	-1 to 1 range of power provided to robot
     * @param distance 	Distance in inches
     */
    public void TurnLeftDistance(float power, int distance) {
    /* Convert Distance to Revolutions */

	/* Reset Encoders */
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

	/* Set Target Position */
        leftMotor.setTargetPosition(-distance);
        rightMotor.setTargetPosition(distance);

	/* Set to RUN_TO_POSITION mode */
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

	/* Set Drive Power */
        Drive(power);

        while (leftMotor.isBusy() && rightMotor.isBusy()) {
		/* Wait until target position is reached */
        }

	/* Stop Driving */
        Stop();
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    /**
     * Condition the joystick
     *
     * @param x
     * @param db   - Deadband
     * @param off  - Offset
     * @param gain - Gain
     * @return
     */
    public float joystick_conditioning(float x, float db, float off, float gain) {
        float output = 0;
        boolean sign = (x > 0);

        x = Math.abs(x);
        if (x > db) {
            output = (float) (off - ((off - 1) * Math.pow(((db - x) / (db - 1)), gain)));
            output *= sign ? 1 : -1;
        }
        return output;
    }

    /**
     * Turn a certain Distance in inches
     *
     * @param power 	-1 to 1 range of power provided to robot
     * @param distance 	Distance in inches
     */
    public void TurnRightDistance(float power, int distance) {
    /* Convert Distance to Revolutions */
//        distance = ConvertDistance(distance);

	/* Reset Encoders */
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

	/* Set Target Position */
        leftMotor.setTargetPosition(distance);
        rightMotor.setTargetPosition(distance);

	/* Set to RUN_TO_POSITION mode */
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

	/* Set Drive Power */
        Drive(power);

        while (leftMotor.isBusy() && rightMotor.isBusy()) {
		/* Wait until target position is reached */
        }

	/* Stop Driving */
        Stop();
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

}

