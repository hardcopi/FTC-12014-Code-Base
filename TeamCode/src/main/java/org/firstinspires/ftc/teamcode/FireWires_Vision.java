package org.firstinspires.ftc.teamcode;

import org.lasarobotics.vision.android.Cameras;
import org.lasarobotics.vision.ftc.resq.Beacon;
import org.lasarobotics.vision.opmode.VisionOpMode;
import org.lasarobotics.vision.opmode.extensions.CameraControlExtension;
import org.lasarobotics.vision.util.ScreenOrientation;
import org.opencv.core.Size;

/**
 * This is the basic Fire Wires Vision Module
 * Based on Code from FTC Vision @
 * https://github.com/lasarobotics/FTCVision
 */
public class FireWires_Vision extends VisionOpMode {
    @Override
    public void init() {
        super.init();

        /**
         * Set the camera used for detection
         * PRIMARY = Front-facing, larger camera
         * SECONDARY = Screen-facing, "selfie" camera :D
         **/
        this.setCamera(Cameras.SECONDARY);
        this.setFrameSize(new Size(900, 900));

        /**
         * Enable extensions
         */
        enableExtension(Extensions.BEACON);         //Beacon detection
        enableExtension(Extensions.ROTATION);       //Automatic screen rotation correction
        enableExtension(Extensions.CAMERA_CONTROL); //Manual camera control

        beacon.setAnalysisMethod(Beacon.AnalysisMethod.FAST);
        beacon.setColorToleranceRed(0);
        beacon.setColorToleranceBlue(0);

        rotation.setIsUsingSecondaryCamera(false);
        rotation.disableAutoRotate();
        rotation.setActivityOrientationFixed(ScreenOrientation.PORTRAIT);
        cameraControl.setColorTemperature(CameraControlExtension.ColorTemperature.AUTO);
        cameraControl.setAutoExposureCompensation();
    }

    @Override
    public void loop() {
        super.loop();

        telemetry.addData("Beacon Color", beacon.getAnalysis().getColorString());
        telemetry.addData("Beacon Center", beacon.getAnalysis().getLocationString());
        telemetry.addData("Beacon Confidence", beacon.getAnalysis().getConfidenceString());
        telemetry.addData("Beacon Buttons", beacon.getAnalysis().getButtonString());
        telemetry.addData("Screen Rotation", rotation.getScreenOrientationActual());
        telemetry.addData("Frame Rate", fps.getFPSString() + " FPS");
        telemetry.addData("Frame Size", "Width: " + width + " Height: " + height);
    }

    @Override
    public void stop() {
        super.stop();
    }
}
