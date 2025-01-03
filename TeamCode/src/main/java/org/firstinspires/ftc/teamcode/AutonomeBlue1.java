package org.firstinspires.ftc.teamcode;


import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.opmode.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
@Autonomous
@Config
public class AutonomeBlue1 extends LinearOpMode {
    SampleMecanumDrive mec;
    ElapsedTime t;
    @Override
    public void runOpMode() throws InterruptedException {
        mec=new SampleMecanumDrive(hardwareMap);
        t=new ElapsedTime();
        waitForStart();
        t.reset();
        while (t.milliseconds()<1000){
            mec.leftFront.setPower(-1);
            mec.leftRear.setPower(-1);
            mec.rightRear.setPower(-1);
            mec.rightFront.setPower(-1);
        }
        mec.leftFront.setPower(0);
        mec.leftRear.setPower(0);
        mec.rightRear.setPower(0);
        mec.rightFront.setPower(0);
        t.reset();
        while (t.milliseconds()<3100){
            mec.leftFront.setPower(0.5);
            mec.leftRear.setPower(0.5);
            mec.rightRear.setPower(0.5);
            mec.rightFront.setPower(0.5);
        }
        mec.leftFront.setPower(0);
        mec.leftRear.setPower(0);
        mec.rightRear.setPower(0);
        mec.rightFront.setPower(0);

    }
}
