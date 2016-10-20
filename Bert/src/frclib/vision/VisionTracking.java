package frclib.vision;

import java.util.ArrayList;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;

import edu.wpi.first.wpilibj.CameraServer;

public class VisionTracking {

	Camera cam;
	Image frame;
	Image filteredframe;
	Image displayframe;
	NIVision.Range red = new NIVision.Range();
	NIVision.Range green = new NIVision.Range();
	NIVision.Range blue = new NIVision.Range();

	boolean tracking = false;

	boolean captureing = false;

	int session;

	public boolean isStarted = false;

	public VisionTracking(Camera cam) {
		this.cam = cam;
		frame = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 0);
		filteredframe = NIVision.imaqCreateImage(ImageType.IMAGE_U8, 0);
		displayframe = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		session = NIVision.IMAQdxOpenCamera(cam.getName(),
				NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		NIVision.IMAQdxConfigureGrab(session);
	}

	public void startCam() {
		tracking = true;
		captureing = true;
		NIVision.IMAQdxStartAcquisition(session);
		isStarted = true;
	}

	public void stopCam() {
		NIVision.IMAQdxStopAcquisition(session);
		isStarted = false;
		tracking = false;
		captureing = false;
	}

	public void displayRawImage() {
		if (captureing) {
			cam.getImage(frame, session);
			CameraServer.getInstance().setImage(frame);

		}
	}

	public void setColor(int rmax, int rmin, int gmax, int gmin, int bmax, int bmin) {
		red.minValue = rmin;
		red.maxValue = rmax;
		green.minValue = gmin;
		green.maxValue = gmax;
		blue.minValue = bmin;
		blue.maxValue = bmax;
	}

	public void displayBlobs() {
		if (tracking) {
			cam.getImage(frame, session);
			ArrayList<NIVision.Rect> blobs = getBlobs();
			for (NIVision.Rect blob : blobs) {
				NIVision.imaqCopyRect(displayframe, frame, blob, new NIVision.Point(blob.left, blob.top));
			}
			CameraServer.getInstance().setImage(displayframe);
		}
	}

	public ArrayList<NIVision.Rect> getBlobs() {
		if (tracking) {
			cam.getImage(frame, session);
			ArrayList<NIVision.Rect> blobs = new ArrayList<NIVision.Rect>();
			NIVision.imaqColorThreshold(filteredframe, frame, 255, NIVision.ColorMode.RGB, red, green, blue);
			int numblobs = NIVision.imaqCountParticles(filteredframe, 1);
			for (int i = 0; i < numblobs; i++) {
				int top = (int) NIVision.imaqMeasureParticle(filteredframe, i, 0,
						NIVision.MeasurementType.MT_BOUNDING_RECT_TOP);
				int left = (int) NIVision.imaqMeasureParticle(filteredframe, i, 0,
						NIVision.MeasurementType.MT_BOUNDING_RECT_LEFT);
				int width = (int) NIVision.imaqMeasureParticle(filteredframe, i, 0,
						NIVision.MeasurementType.MT_BOUNDING_RECT_WIDTH);
				int height = (int) NIVision.imaqMeasureParticle(filteredframe, i, 0,
						NIVision.MeasurementType.MT_BOUNDING_RECT_HEIGHT);
				blobs.add(new NIVision.Rect(top, left, height, width));
			}
			return blobs;
		} else {
			return null;
		}
	}
}
