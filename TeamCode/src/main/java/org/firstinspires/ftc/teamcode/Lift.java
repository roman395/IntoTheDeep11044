package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Config
public class Lift {
    public static double takePos;
    public static double scorePos;

    LinearOpMode linearOpMode;
    HardwareMap hardwareMap;
    Gamepad gamepad1;

    public Lift(LinearOpMode linearOpMode){

    }

}
