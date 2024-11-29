package org.firstinspires.ftc.teamcode;


import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous
@Config
public class AutonomeBlue2 extends LinearOpMode {
    SampleMecanumDrive mec;


    @Override
    public void runOpMode() throws InterruptedException {
        mec=new SampleMecanumDrive(hardwareMap);


        TrajectorySequence e = mec.trajectorySequenceBuilder(new Pose2d(12, 58, Math.toRadians(0)))
                .lineToLinearHeading(new Pose2d(48,48,Math.toRadians(45)))

                .waitSeconds(1)

                .splineToLinearHeading(new Pose2d(39,12,Math.toRadians(0)),Math.toRadians(-45))

                .waitSeconds(0.5)
                .splineTo(new Vector2d(48,40),Math.toRadians(45))

                .waitSeconds(1)
                .lineToLinearHeading(new Pose2d(42,-12,Math.toRadians(0)))

                .build();


        waitForStart();
        mec.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mec.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mec.setPoseEstimate(new Pose2d(12,58));
        mec.followTrajectorySequence(e);





    }
}
