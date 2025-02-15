package org.firstinspires.ftc.teamcode;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.opmode.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous
public class Blue1plus1 extends LinearOpMode {
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
                    intake.Autonom(1);
                    lift.Autonom(0);
                })
                //.addTemporalMarker(2,()->{
                  //  perekid.Sub();
                //})
                .back(24)
                .strafeLeft(24 * 2.35-6)
                .addTemporalMarker(1.5, () -> {
                    //i.Autonom(1);
                })
                .addTemporalMarker(6.7, () -> {
                    perekid.Take();
                    intake.Autonom(1);
                })
                .forward(25)
                .addTemporalMarker(8.5, () -> {
                    perekid.Score();
                    intake.Autonom(0);

                })

                .turn(Math.toRadians(135))
                .forward(13)
                .strafeLeft(24 * 1.26)
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
                .waitSeconds(4)
                .lineToLinearHeading(new Pose2d(22, 21+8, Math.toRadians(180)))
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