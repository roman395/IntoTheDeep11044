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
public class Venom extends LinearOpMode {
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

        TrajectorySequence e = mec.trajectorySequenceBuilder(new Pose2d(12, 60, Math.toRadians(-90)))
                .forward(26)
                .addTemporalMarker(1.5,()->{
                    l.Autonom(l.minRot);
                })
                .waitSeconds(0.5)
                .back(20)
                .addTemporalMarker(2.2,()->{
                    i.Autonom(-1);
                })
                .addTemporalMarker(3,()->{
                    p.Autonom(false);
                    i.Autonom(0);
                })
                .lineTo(new Vector2d(-52,55))
                .build();





        waitForStart();
        mec.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mec.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mec.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mec.setPoseEstimate(new Pose2d(12,60,Math.toRadians(-90)));
        l.Autonom(l.specScoreRot);
        p.Autonom(false);

        mec.followTrajectorySequence(e);
        p.Autonom(true);
        i.Autonom(1);




    }
}
