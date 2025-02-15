package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        // Declare a MeepMeep instance
        // With a field size of 800 pixels
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Required: Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(65.5, 30, Math.toRadians(180), Math.toRadians(180), 10.15)
                // Option: Set theme. Default = ColorSchemeRedDark()
                .setColorScheme(new ColorSchemeRedDark())
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(12, 60, Math.toRadians(-90)))
                                .forward(15)
                                .addTemporalMarker(1, () -> {
                                })
                                .waitSeconds(0.5)
                                .addTemporalMarker(1.5, () -> {
                                })
                                .back(24)
                                .strafeLeft(24*2.55)
                                .addTemporalMarker(2.2, () -> {
                                    //i.Autonom(-1);
                                })
                                .addTemporalMarker(7, () -> {

                                })
                                .forward(25)
                                .addTemporalMarker(9,()->{

                                })

                                .turn(Math.toRadians(135))
                                .forward(6)
                                .strafeLeft(24*1.4)
                                .addTemporalMarker(11,()->{

                                })
                                .addTemporalMarker(15,()->{
                                })
                                .addTemporalMarker(16.5,()->{

                                })
                                .waitSeconds(4)
                                .addTemporalMarker(20,()->{

                                })
                                .turn(Math.toRadians(-135))
                                .lineTo(new Vector2d(58,24))
                                .lineToLinearHeading(new Pose2d(55,60+24*1.5,Math.toRadians(45)))
                                //.lineToLinearHeading(new Pose2d(24,12,Math.toRadians(180)))
                                .build()
                );

        // Set field image
        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                // Background opacity from 0-1
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}