package org.firstinspires.ftc.teamcode;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.opmode.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous
public class VenomBlue extends LinearOpMode {
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

        TrajectorySequence e = mec.trajectorySequenceBuilder(new Pose2d(12, 60, Math.toRadians(-90)))
                .forward(15)
                .addTemporalMarker(1, () -> {
                    l.Autonom(l.specScoreRot);
                })
                .waitSeconds(0.5)
                .addTemporalMarker(1.5, () -> {
                    l.Autonom(0);
                })
                .back(24)
                .strafeLeft(24*2.55)
                .addTemporalMarker(2.2, () -> {
                    //i.Autonom(-1);
                })
                .addTemporalMarker(7, () -> {
                    p.Take();
                    i.Autonom(1);
                })
                .forward(25)
                .addTemporalMarker(9,()->{
                    p.Score();
                    i.Autonom(0);

                })

                .turn(Math.toRadians(135))
                .forward(6)
                .strafeLeft(24*1.4)
                .addTemporalMarker(11,()->{
                    l.Autonom(l.maxRot);
                })
                .addTemporalMarker(15,()->{
                    i.Autonom(-1);
                })
                .addTemporalMarker(16.5,()->{
                    i.Autonom(0);
                    l.Autonom(0);
                })
                .waitSeconds(3)
                .lineToLinearHeading(new Pose2d(24,12,Math.toRadians(180)))
                .build();

        waitForStart();
        mec.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mec.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mec.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mec.setPoseEstimate(new Pose2d(12, 24 * 3, Math.toRadians(-90)));
        l.Autonom(l.specScoreRot);
        p.Score();

        mec.followTrajectorySequence(e);

    }
}