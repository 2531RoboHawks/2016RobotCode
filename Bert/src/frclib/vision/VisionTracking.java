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

	public VisionTracking(Camera cam) {
		this.cam = cam;
		frame = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 0);
		filteredframe = NIVision.imaqCreateImage(ImageType.IMAGE_U8, 0);
		displayframe = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
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
		cam.getImage(frame);
		ArrayList<NIVision.Rect> blobs = getBlobs();
		for (NIVision.Rect blob : blobs) {
			NIVision.imaqCopyRect(displayframe, frame, blob, new NIVision.Point(blob.left, blob.top));
		}
		CameraServer.getInstance().setImage(displayframe);
	}

	public ArrayList<NIVision.Rect> getBlobs() {
		cam.getImage(frame);
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
	}
}
