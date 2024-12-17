package org.firstinspires.ftc.teamcode;


import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
@Autonomous
@Config
public class AutonomeBlue1 extends LinearOpMode {
    Mecanum mec;
    ElapsedTime t;

    @Override
    public void runOpMode() throws InterruptedException {
        mec=new Mecanum(this);
t=new ElapsedTime();





        waitForStart();
        t.reset();
        while(t.seconds()<1){
            mec.BL.setPower(0.5);
            mec.FL.setPower(0.5);
            mec.FR.setPower(0.5);
            mec.BR.setPower(0.5);
        }
        mec.BL.setPower(0);
        mec.FL.setPower(0);
        mec.FR.setPower(0);
        mec.BR.setPower(0);





    }
}
