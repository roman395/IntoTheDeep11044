package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;


@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends LinearOpMode {
    enum State {
        NONE,
        UP,
        SCORE,
        TAKE,
        Move
    }

    Gamepad g;
    Mecanum mecanum;
    Intake i;
    Lift l;
    Perekid p;
    ElapsedTime e;

    @Override
    public void runOpMode() throws InterruptedException {
        State s = State.NONE;
        mecanum = new Mecanum(this);
        i = new Intake(this);
        l = new Lift(this);
        e = new ElapsedTime();
        p = new Perekid(this);
        g = gamepad1;
        waitForStart();
        while (opModeIsActive()) {
            if (g.a) s = State.UP;
            else if (g.b) s = State.Move;
            else if (g.x) s = State.TAKE;
            Lift.currentRot = l.motorL.getCurrentPosition();
            switch (s) {
                case NONE:
                    l.Down();
                    if (!l.motorL.isMotorEnabled()) p.Sub();
                    i.Off();
                    break;
                case UP:
                    p.Score();

                    l.Up();
                    if (!l.motorL.isMotorEnabled()) {
                        s = State.SCORE;
                        e.reset();
                    }
                    break;
                case SCORE:
                    if (e.seconds() > 1) {
                        i.Output();
                        if (i.fish.getState()) {
                            s = State.NONE;
                        }
                    }
                    break;
                case Move:
                    p.Sub();
                    i.Off();

                case TAKE:
                    p.Take();
                    i.Input();
                    if (!i.fish.getState()) s = State.Move;
                    break;

            }
            telemetry.addData("PositionL", l.motorL.getCurrentPosition());
            telemetry.addData("PositionR", l.motorR.getCurrentPosition());
            telemetry.addData("Mode", mecanum.operation);
            telemetry.update();
        }

    }
}



