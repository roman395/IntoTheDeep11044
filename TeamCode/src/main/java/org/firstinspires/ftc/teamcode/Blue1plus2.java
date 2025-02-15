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
                    lift.Autonom(lift.specScoreRot);
                })
                .waitSeconds(0.5)
                .addTemporalMarker(1.5, () -> {
                    lift.Autonom(0);
                })
                .back(24)
                .strafeLeft(24 * 2.35)
                .addTemporalMarker(1.5, () -> {
                    //i.Autonom(1);
                })
                .addTemporalMarker(7, () -> {
                    perekid.Take();
                    intake.Autonom(1);
                })
                .forward(25)
                .addTemporalMarker(8.5, () -> {
                    perekid.Score();
                    intake.Autonom(0);

                })

                .turn(Math.toRadians(135))
                .forward(8)
                .strafeLeft(24 * 1.25)
                .addTemporalMarker(11, () -> {
                    lift.Autonom(lift.maxRot);
                })
                .addTemporalMarker(15, () -> {
                    intake.Autonom(-1);
                })
                .addTemporalMarker(16.5, () -> {
                    intake.Autonom(0);
                    lift.Autonom(0);
                })
                .waitSeconds(5)
                .addTemporalMarker(18.5, () -> {
                    intake.Autonom(1);
                    perekid.Take();
                })
                .turn(Math.toRadians(-135))
                .lineTo(new Vector2d(58, 34))
                .lineToLinearHeading(new Pose2d(59 - 6, 60 + 24 * 1.2 - 6, Math.toRadians(45)))
                .addTemporalMarker(22, () -> {
                    lift.Autonom(lift.maxRot);
                    perekid.Score();
                })
                .addTemporalMarker(25, () -> {
                    intake.Autonom(-1);
                })
                .addTemporalMarker(26, () -> {
                    lift.Autonom(0);
                    intake.Autonom(0);
                })
                .waitSeconds(6)
                .lineToLinearHeading(new Pose2d(26, 24, Math.toRadians(180)))
                .build();

        waitForStart();
        drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        drive.setPoseEstimate(new Pose2d(12, 24 * 3, Math.toRadians(-90)));
        lift.Autonom(lift.specScoreRot);
        perekid.Score();

        drive.followTrajectorySequence(e);

    }
}