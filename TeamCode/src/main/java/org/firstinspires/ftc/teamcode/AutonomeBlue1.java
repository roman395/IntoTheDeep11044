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
public class AutonomeBlue1 extends LinearOpMode {
    SampleMecanumDrive mec;


    @Override
    public void runOpMode() throws InterruptedException {
        mec=new SampleMecanumDrive(hardwareMap);


        TrajectorySequence e = mec.trajectorySequenceBuilder(new Pose2d(-12, 58, Math.toRadians(0)))

                .lineToLinearHeading(new Pose2d(62,40,Math.toRadians(70)))
                .addDisplacementMarker(()->{

                })
                .waitSeconds(1)
                .turn(Math.toRadians(-160))
                .addDisplacementMarker(()->{

                })
                .waitSeconds(0.5)
                .turn(Math.toRadians(160))
                .addDisplacementMarker(()->{

                })
                .waitSeconds(1)
                .lineToLinearHeading(new Pose2d(0,36,Math.toRadians(180)))
                .splineToConstantHeading(new Vector2d(-36,-6),Math.toRadians(0))


                .build();


        waitForStart();
        mec.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mec.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mec.setPoseEstimate(new Pose2d(-12,58));
        mec.followTrajectorySequence(e);





    }
}
