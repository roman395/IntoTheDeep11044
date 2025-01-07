package org.firstinspires.ftc.teamcode;


import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.opmode.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous
@Config
public class AutonomeRed2 extends LinearOpMode {
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

        TrajectorySequence e = mec.trajectorySequenceBuilder(new Pose2d(-12, -58, Math.toRadians(180)))
                .turn(Math.toRadians(90))

                .addTemporalMarker(2,()->{
                   p.Autonom(true);
                })
                .waitSeconds(5)
                .build();


        waitForStart();
        mec.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mec.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mec.setPoseEstimate(new Pose2d(-12,-58,Math.toRadians(180)));
        mec.followTrajectorySequence(e);





    }
}
