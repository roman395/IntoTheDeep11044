package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw {
    public Servo s;
    public static double open;
    public static double close;

    LinearOpMode linearOpMode;
    HardwareMap hardwareMap;
    Gamepad gamepad1;

    public Claw(LinearOpMode linearOpMode){

    }
}
