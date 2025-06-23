package pedroPathing;

import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.Path;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Linkage;

import pedroPathing.constants.FConstants;
import pedroPathing.constants.LConstants;

@Autonomous
public class testAutonom extends OpMode {
    private Follower follower;
    private Timer pathTimer;
    private int pathState;
    private final Pose startPose = new Pose(7,96,Math.toRadians(0));
    private final Pose endPose = new Pose(7+24,96,Math.toRadians(0));
    private Path path;
    private Linkage linkage;
    public void buildPaths(){
        path = new Path(new BezierLine(new Point(startPose), new Point(endPose)));
        path.setLinearHeadingInterpolation(startPose.getHeading(),endPose.getHeading());

    }
    public void autonomousPathUpdate(){
        switch (pathState){
            case 0:
                follower.followPath(path);
                pathState = 1;
            break;
        }
    }
    @Override
    public void loop(){
        follower.update();
        linkage.PIDController();
        autonomousPathUpdate();

        telemetry.addData("path state", pathState);
        telemetry.addData("x", follower.getPose().getX());
        telemetry.addData("y",follower.getPose().getY());
        telemetry.addData("heading",follower.getPose().getHeading());
        telemetry.update();
    }
    @Override
    public void init(){
        pathTimer=new Timer();
        linkage = new Linkage();
        follower = new Follower(hardwareMap, FConstants.class, LConstants.class);
        follower.setStartingPose(startPose);
        buildPaths();
    }
    @Override
    public void start(){
        pathState = 0;
    }
}
