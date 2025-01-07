package org.firstinspires.ftc.teamcode;


import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.opmode.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous
@Config
public class AutonomeRed1 extends LinearOpMode {
    SampleMecanumDrive mec;
    Perekid p;
    Lift l;
    Intake i;

    @Override
    public void runOpMode() throws InterruptedException {
        mec=new SampleMecanumDrive(hardwareMap);
        i=new Intake(this);
        l=new Lift(this);
        p=new Perekid(this);

        TrajectorySequence e = mec.trajectorySequenceBuilder(new Pose2d(12, 60, Math.toRadians(0)))
                .addDisplacementMarker(()->{
                    p.Autonom(false);
                    l.Autonom(l.maxRot);
                })
                .lineToLinearHeading(new Pose2d(24*2,55-12,Math.toRadians(45)))
                .waitSeconds(1)
                .addDisplacementMarker(()->{
                    i.Autonom(-1);
                })
                .waitSeconds(2)
                .lineToLinearHeading(new Pose2d(24,12,Math.toRadians(0)))
                .build();





        waitForStart();
        mec.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mec.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mec.setPoseEstimate(new Pose2d(12,60,Math.toRadians(0)));
        mec.followTrajectorySequence(e);





    }
}
