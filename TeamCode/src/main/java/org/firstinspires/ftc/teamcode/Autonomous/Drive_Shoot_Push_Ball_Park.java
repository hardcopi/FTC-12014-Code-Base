package org.firstinspires.ftc.teamcode.Autonomous;

        import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

        import org.firstinspires.ftc.teamcode.HardwareFireWiresBot;

@Autonomous(name = "Drive, Shoot, Push Ball & Park", group = "FireBot")
public class Drive_Shoot_Push_Ball_Park extends LinearOpMode {
    HardwareFireWiresBot robot = new HardwareFireWiresBot();
    long start_time;

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        /* Drive a little over 1/2 way there */
        robot.DriveDistance(1,3);
        sleep(3000);
        /* Fire! */
        robot.fire();
        /* Drive the rest of the distance */
        robot.DriveDistance(1,2);
    }
}
