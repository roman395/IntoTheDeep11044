package org.firstinspires.ftc.teamcode;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.opmode.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous
public class Blue1plus2 extends LinearOpMode {
    SampleMecanumDrive drive;
    Perekid perekid;
    Lift lift;
    Intake intake;

    @Override
    public void runOpMode() throws InterruptedException {

        drive = new SampleMecanumDrive(hardwareMap);

        intake = new Intake(this);
        lift = new Lift(this);
        perekid = new Perekid(this);

        TrajectorySequence e = drive.trajectorySequenceBuilder(new Pose2d(12, 60, Math.toRadians(-90)))
                .forward(14)
                .addTemporalMarker(1, () -> {
                    telemetry.addData("Time","29");
                    telemetry.update();
                    lift.Autonom(lift.specScoreRot);
                })
                .waitSeconds(0.5)
                .addTemporalMarker(1.5, () -> {
                    telemetry.addData("Time","28.5");
                    telemetry.update();
                    lift.Autonom(0);
                    intake.Autonom(1);
                })
                //.addTemporalMarker(2,()->{
                //  perekid.Sub();
                //})
                .back(25)
                .strafeLeft(24 * 2.35-4)
                .addTemporalMarker(1.5, () -> {
                    //i.Autonom(1);
                })
                .addTemporalMarker(6.7, () -> {
                    telemetry.addData("Time","23.4");
                    telemetry.update();
                    perekid.Take();
                    intake.Autonom(1);
                })
                .forward(25)
                .addTemporalMarker(8.5, () -> {
                    telemetry.addData("Time","21.5");
                    telemetry.update();
                    perekid.Score();
                    intake.Autonom(0);

                })

                .turn(Math.toRadians(135))
                .forward(12)
                .strafeLeft(24 * 1.26)
                .addTemporalMarker(11, () -> {
                    telemetry.addData("Time","19");
                    telemetry.update();
                    lift.Autonom(lift.maxRot);
                })
                .addTemporalMarker(14, () -> {
                    telemetry.addData("Time","16");
                    telemetry.update();
                    intake.Autonom(-1);
                })
                .addTemporalMarker(15.5, () -> {
                    telemetry.addData("Time","14.5");
                    telemetry.update();
                    intake.Autonom(0);
                    lift.Autonom(0);
                })
                .waitSeconds(4)
                .turn(Math.toRadians(-135))
                .addTemporalMarker(18,()->{
                    telemetry.addData("Time","12");
                    telemetry.update();
                    perekid.Take();
                    intake.Autonom(1);
                })
                .waitSeconds(1)
                .addTemporalMarker(22,()->{
                    telemetry.addData("Time","8");
                    telemetry.update();
                    perekid.Score();
                    intake.Autonom(0);
                    lift.Autonom(lift.maxRot);
                })
                .addTemporalMarker(26,()->{
                    telemetry.addData("Time","4");
                    telemetry.update();
                    intake.Autonom(-1);
                })
                .addTemporalMarker(27,()->{
                    telemetry.addData("Time","3");
                    telemetry.update();
                    lift.Autonom(0);
                    intake.Autonom(0);
                })

                .turn(Math.toRadians(10))
                .lineToConstantHeading(new Vector2d(68,50))
                .waitSeconds(1)
                .lineToLinearHeading(new Pose2d(60-4 ,60+24*1.5-22,Math.toRadians(45)))
                .waitSeconds(4)
                .lineToLinearHeading(new Pose2d(24, 21+8, Math.toRadians(180)))
                .build();

        waitForStart();
        drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        drive.setPoseEstimate(new Pose2d(10, 24 * 3, Math.toRadians(-90)));
        lift.Autonom(lift.specScoreRot);
        perekid.Score();

        drive.followTrajectorySequence(e);

    }
}