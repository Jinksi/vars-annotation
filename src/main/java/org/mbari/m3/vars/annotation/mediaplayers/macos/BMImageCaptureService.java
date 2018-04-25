package org.mbari.m3.vars.annotation.mediaplayers.macos;

import org.mbari.m3.blackmagic.BlackmagicImageCapture;
import org.mbari.m3.vars.annotation.Initializer;
import org.mbari.m3.vars.annotation.mediaplayers.MediaPlayer;
import org.mbari.m3.vars.annotation.model.Framegrab;
import org.mbari.m3.vars.annotation.services.ImageCaptureService;
import org.mbari.vcr4j.VideoError;
import org.mbari.vcr4j.VideoIndex;
import org.mbari.vcr4j.VideoState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author Brian Schlining
 * @since 2018-04-24T16:11:00
 */
public class BMImageCaptureService implements ImageCaptureService {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private BlackmagicImageCapture imageCapture;
    public static BMImageCaptureService imageCaptureService;
    private String currentDevice = "";

    protected BMImageCaptureService() {
        imageCapture = new BlackmagicImageCapture();
    }

    public Collection<String> listDevices() {
        return Arrays.asList(imageCapture.videoDevicesAsStrings());
    }

    public void setDevice(String device) {
        currentDevice = device;
        imageCapture.startSessionWithNamedDevice(currentDevice);
    }

    @Override
    public Framegrab capture(File file) {
        Framegrab framegrab = new Framegrab();
        //imageCapture.startSessionWithNamedDevice(currentDevice);
        Optional<Image> imageOpt = imageCapture.capture(file, Duration.ofSeconds(10));
        if (imageOpt.isPresent()) {
            framegrab.setImage(imageOpt.get());

            MediaPlayer<? extends VideoState, ? extends VideoError> mediaPlayer = Initializer.getToolBox().getMediaPlayer();
            if (mediaPlayer != null) {
                try {

                    // HACK - Use a 3 second timeout
                    mediaPlayer.requestVideoIndex()
                            .thenAccept(framegrab::setVideoIndex)
                            .get(3000, TimeUnit.MILLISECONDS);
                }
                catch (Exception e) {
                    log.warn("Problem with requesting videoIndex while capturing a framegrab", e);
                    framegrab.setVideoIndex(new VideoIndex(Instant.now()));
                }
            }

            // If, for some reason, getting the video index fails. Fall back to a timestamp
            if (!framegrab.getVideoIndex().isPresent()) {
                log.warn("Failed to get video index. Using current timestamp for video index");
                framegrab.setVideoIndex(new VideoIndex(Instant.now()));
            }


        }
        else {
            log.warn("Failed to capture image from device named '" +
                    currentDevice + "'");
        }
        //imageCapture.stopSession();
        return framegrab;
    }

    @Override
    public void dispose() {
        imageCapture.stopSession();
        // TODO need to add dispose method to vars-blackmagic
    }

    public static BMImageCaptureService getInstance() {
        if (imageCaptureService == null) {
            imageCaptureService = new BMImageCaptureService();
        }
        return imageCaptureService;
    }

    public BlackmagicImageCapture getImageCapture() {
        return imageCapture;
    }


}
