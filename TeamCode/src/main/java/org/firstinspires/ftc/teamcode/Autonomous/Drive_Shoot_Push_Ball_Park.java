package org.firstinspires.ftc.teamcode.Autonomous;

        import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

        import org.firstinspires.ftc.teamcode.HardwareFireWiresBot;

@Autonomous(name = "Drive, Shoot, Push Ball & Park", group = "FireBot")
public class Drive_Shoot_Push_Ball_Park extends LinearOpMode {
    static final Robot_Methods amethods = new Robot_Methods();
    static final double  SHOOTER_POWER = .25;
    HardwareFireWiresBot robot = new HardwareFireWiresBot();
    long start_time;

    @Override
    public void runOpMode() {

        robot.init(hardwareMap);
        waitForStart();
        /* Drive a little over 1/2 way there */
        Robot_Methods.driveForSecondsAtPower(robot,-.25,1.5);
        Robot_Methods.shootOneBall(robot,SHOOTER_POWER);
        Robot_Methods.waitSeconds(1);
    }
}
