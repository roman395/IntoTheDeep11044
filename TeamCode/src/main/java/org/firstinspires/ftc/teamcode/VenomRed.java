package org.firstinspires.ftc.teamcode;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.opmode.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous
public class VenomRed extends LinearOpMode {
    SampleMecanumDrive mec;
    Perekid p;
    Lift l;
    Intake i;

    @Override
    public void runOpMode() throws InterruptedException {

        mec = new SampleMecanumDrive(hardwareMap);

        i = new Intake(this);
        l = new Lift(this);
        p = new Perekid(this);

        TrajectorySequence e = mec.trajectorySequenceBuilder(new Pose2d(-12, -60, Math.toRadians(90)))
                .forward(16)
                .addTemporalMarker(1, () -> {
                    l.Autonom(l.specScoreRot);
                })
                .waitSeconds(0.5)
                .addTemporalMarker(1.5, () -> {
                    l.Autonom(0);
                })
                .back(20)
                .addTemporalMarker(2.2, () -> {
                    //i.Autonom(-1);
                })
                .addTemporalMarker(3, () -> {
                    p.Take();
                    i.Autonom(0);
                })
                .lineTo(new Vector2d(52, -72))
                .build();

        waitForStart();
        mec.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mec.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mec.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mec.setPoseEstimate(new Pose2d(-12, -24 * 3, Math.toRadians(90)));
        l.Autonom(l.specScoreRot);
        p.Score();

        mec.followTrajectorySequence(e);

    }
}