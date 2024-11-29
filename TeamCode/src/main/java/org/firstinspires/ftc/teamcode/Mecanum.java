package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp
public class Mecanum {
    public DcMotor FL;
    public DcMotor FR;
    public DcMotor BL;
    public DcMotor BR;
    LinearOpMode linearOpMode;
    HardwareMap hardwareMap;
    Gamepad gamepad1;

public Mecanum(LinearOpMode linearOpMode){
   this.linearOpMode = linearOpMode;
   hardwareMap = linearOpMode.hardwareMap;
   gamepad1 = linearOpMode.gamepad1;
    FL=hardwareMap.get(DcMotor.class,"leftFront");
    FR=hardwareMap.get(DcMotor.class,"rightFront");
    BL=hardwareMap.get(DcMotor.class,"leftRear");
    BR=hardwareMap.get(DcMotor.class,"rightRear");
    FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    FL.setDirection(DcMotorSimple.Direction.REVERSE);
    BL.setDirection(DcMotorSimple.Direction.REVERSE);

}
    public void TeleOp() {




            double x = gamepad1.left_stick_x;
            double y = -gamepad1.left_stick_y;
            double rx = -(gamepad1.left_trigger-gamepad1.right_trigger)*0.8;
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            FL.setPower((y+x+rx)/denominator);
            BL.setPower((y-x+rx)/denominator);
            FR.setPower((y-x-rx)/denominator);
            BR.setPower((y+x-rx)/denominator);

    }
}
