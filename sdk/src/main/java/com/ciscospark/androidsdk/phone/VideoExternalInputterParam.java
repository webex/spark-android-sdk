package com.ciscospark.androidsdk.phone;

/**
 * The paramters for the external video inputter
 * @since 1.3.0-AR
 */

public class VideoExternalInputterParam {
    public int frameRate;
    public int width;
    public int height;
    public VideoExternalInputterParam(int frameRate, int width, int height){
        this.frameRate = frameRate;
        this.width = width;
        this.height = height;
    }
}
