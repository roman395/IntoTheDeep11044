package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.AnalogSensor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorMROpticalDistance;
import org.firstinspires.ftc.robotcontroller.external.samples.SensorMRRangeSensor;

@Config
public class Claw
{
    Servo opener;
    Servo rotate;
    public static double requirement_number_for_sensor = 1024;
    public static double open = 1;
    public static double close = 0.8;
    public static double take_spec = 0.55;
    public static double take_spec_up = 0.65;
    public static double score_spec = 0.735;
    public static double score_sample = 0.2;
    public static double take_sample = 0.58;

    LinearOpMode linearOpMode;
    HardwareMap hardwareMap;
    Gamepad gamepad1;
    AnalogInput sensor;

    public Claw(LinearOpMode linearOpMode)
    {
        this.linearOpMode = linearOpMode;
        hardwareMap = linearOpMode.hardwareMap;
        gamepad1 = linearOpMode.gamepad1;
        opener = hardwareMap.get(Servo.class, "Claw");
        rotate = hardwareMap.get(Servo.class, "ClawAngle");
        sensor = hardwareMap.get(AnalogInput.class, "ClawSensor");

    }

    public void Open()
    {
        opener.setPosition(open);
    }

    public void Close()
    {
        opener.setPosition(close);
    }

    public double GetState()
    {
        return sensor.getVoltage();
    }

    public void TakeSpec()
    {
        rotate.setPosition(take_spec);
    }

    public void TakeSpecUp()
    {
        rotate.setPosition(take_spec_up);
    }

    public void ScoreSpec()
    {
        rotate.setPosition(score_spec);
    }

    public void ScoreSample()
    {
        rotate.setPosition(score_sample);
    }

    public void TakeSample()
    {
        rotate.setPosition(take_sample);
    }
}
