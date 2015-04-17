package a6test;

import static org.junit.Assert.*;

import org.junit.Test;

import a6adept.*;

public class A6AdeptTest {

	private static final ColorPixel RED = new ColorPixel(1.0, 0.0, 0.0);
	private static final ColorPixel BLUE = new ColorPixel(0.0, 0.0, 1.0);
	private static final ColorPixel GREEN = new ColorPixel(0.0, 1.0, 0.0);
	
	@Test
	public void basicObserverTest() {
		ColorFrame cf = new ColorFrame(100, 100);
		AdeptFrameAreaCaptureObserver o = new AdeptFrameAreaCaptureObserver();
		
		cf.registerFrameObserver(o);
		cf.setPixel(10, 10, RED);
		
		assertEquals(cf, o.getReportingFrame());
		
		FrameArea changed_area = o.getReportedArea();
		assertEquals(10, changed_area.getMinX());
		assertEquals(10, changed_area.getMaxX());
		assertEquals(10, changed_area.getMinY());
		assertEquals(10, changed_area.getMaxY());
		
	}

	@Test
	public void indirectFrameObserverTest() {
		ColorFrame cf = new ColorFrame(100, 100);
		IndirectFrame i = new IndirectFrame(cf, 10, 10, 10, 10);
		
		AdeptFrameAreaCaptureObserver o = new AdeptFrameAreaCaptureObserver();
		
		i.registerFrameObserver(o);

		i.setPixel(0, 0, RED);
		assertEquals(i, o.getReportingFrame());
		
		FrameArea changed_area = o.getReportedArea();
		assertEquals(0, changed_area.getMinX());
		assertEquals(0, changed_area.getMaxX());
		assertEquals(0, changed_area.getMinY());
		assertEquals(0, changed_area.getMaxY());

		cf.setPixel(15, 15, GREEN);
		assertEquals(i, o.getReportingFrame());
		
		changed_area = o.getReportedArea();
		assertEquals(5, changed_area.getMinX());
		assertEquals(5, changed_area.getMaxX());
		assertEquals(5, changed_area.getMinY());
		assertEquals(5, changed_area.getMaxY());		
	}
	
	@Test
	public void overlappingIndirectFrameObserverTest() {
		ColorFrame cf = new ColorFrame(100, 100);
		IndirectFrame i1 = cf.crop(10, 10, 50, 50);
		IndirectFrame i2 = cf.crop(30, 30, 50, 50);

		AdeptFrameAreaCaptureObserver cfo = new AdeptFrameAreaCaptureObserver();
		AdeptFrameAreaCaptureObserver i1o = new AdeptFrameAreaCaptureObserver();
		AdeptFrameAreaCaptureObserver i2o = new AdeptFrameAreaCaptureObserver();

		cf.registerFrameObserver(cfo);
		i1.registerFrameObserver(i1o);
		i2.registerFrameObserver(i2o);
		
		cf.setPixel(35, 35, BLUE);
		
		FrameArea a;
		
		assertEquals(cf, cfo.getReportingFrame());
		a = cfo.getReportedArea();
		assertEquals(35, a.getMinX());
		assertEquals(35, a.getMinY());
		assertEquals(35, a.getMaxX());
		assertEquals(35, a.getMaxY());

		assertEquals(i1, i1o.getReportingFrame());
		a = i1o.getReportedArea();
		assertEquals(25, a.getMinX());
		assertEquals(25, a.getMinY());
		assertEquals(25, a.getMaxX());
		assertEquals(25, a.getMaxY());

		assertEquals(i2, i2o.getReportingFrame());
		a = i2o.getReportedArea();
		assertEquals(5, a.getMinX());
		assertEquals(5, a.getMinY());
		assertEquals(5, a.getMaxX());
		assertEquals(5, a.getMaxY());

		cfo.reset();
		i1o.reset();
		i2o.reset();
		cf.setPixel(11, 11, RED);

		assertEquals(cf, cfo.getReportingFrame());
		a = cfo.getReportedArea();
		assertEquals(11, a.getMinX());
		assertEquals(11, a.getMinY());
		assertEquals(11, a.getMaxX());
		assertEquals(11, a.getMaxY());

		assertEquals(i1, i1o.getReportingFrame());
		a = i1o.getReportedArea();
		assertEquals(1, a.getMinX());
		assertEquals(1, a.getMinY());
		assertEquals(1, a.getMaxX());
		assertEquals(1, a.getMaxY());

		assertEquals(null, i2o.getReportingFrame());
		assertEquals(null, i2o.getReportedArea());		
		
		cfo.reset();
		i1o.reset();
		i2o.reset();
		cf.setPixel(60, 60, GREEN);

		assertEquals(cf, cfo.getReportingFrame());
		a = cfo.getReportedArea();
		assertEquals(60, a.getMinX());
		assertEquals(60, a.getMinY());
		assertEquals(60, a.getMaxX());
		assertEquals(60, a.getMaxY());

		assertEquals(null, i1o.getReportingFrame());
		assertEquals(null, i1o.getReportedArea());		

		assertEquals(i2, i2o.getReportingFrame());
		a = i2o.getReportedArea();
		assertEquals(30, a.getMinX());
		assertEquals(30, a.getMinY());
		assertEquals(30, a.getMaxX());
		assertEquals(30, a.getMaxY());

		cfo.reset();
		i1o.reset();
		i2o.reset();
		cf.setPixel(5, 5, GREEN);

		assertEquals(cf, cfo.getReportingFrame());
		a = cfo.getReportedArea();
		assertEquals(5, a.getMinX());
		assertEquals(5, a.getMinY());
		assertEquals(5, a.getMaxX());
		assertEquals(5, a.getMaxY());

		assertEquals(null, i1o.getReportingFrame());
		assertEquals(null, i1o.getReportedArea());		

		assertEquals(null, i2o.getReportingFrame());
		assertEquals(null, i2o.getReportedArea());				
	}
	
	@Test
	public void doubleIndirectFrameTest() {
		ColorFrame cf = new ColorFrame(100, 100);
		IndirectFrame i1 = cf.crop(10, 10, 80, 80);
		IndirectFrame i2 = i1.crop(10, 10, 50, 50);
		
		AdeptFrameAreaCaptureObserver cfo = new AdeptFrameAreaCaptureObserver();
		AdeptFrameAreaCaptureObserver i1o = new AdeptFrameAreaCaptureObserver();
		AdeptFrameAreaCaptureObserver i2o = new AdeptFrameAreaCaptureObserver();

		cf.registerFrameObserver(cfo);
		i1.registerFrameObserver(i1o);
		i2.registerFrameObserver(i2o);

		FrameArea a;
		
		cf.setPixel(25, 25, RED);
		
		assertEquals(cf, cfo.getReportingFrame());
		a = cfo.getReportedArea();
		assertEquals(25, a.getMinX());
		assertEquals(25, a.getMinY());
		assertEquals(25, a.getMaxX());
		assertEquals(25, a.getMaxY());

		assertEquals(i1, i1o.getReportingFrame());
		a = i1o.getReportedArea();
		assertEquals(15, a.getMinX());
		assertEquals(15, a.getMinY());
		assertEquals(15, a.getMaxX());
		assertEquals(15, a.getMaxY());

		assertEquals(i2, i2o.getReportingFrame());
		a = i2o.getReportedArea();
		assertEquals(5, a.getMinX());
		assertEquals(5, a.getMinY());
		assertEquals(5, a.getMaxX());
		assertEquals(5, a.getMaxY());

		cfo.reset();
		i1o.reset();
		i2o.reset();

		i2.setPixel(6, 6, BLUE);
		assertEquals(cf, cfo.getReportingFrame());
		a = cfo.getReportedArea();
		assertEquals(26, a.getMinX());
		assertEquals(26, a.getMinY());
		assertEquals(26, a.getMaxX());
		assertEquals(26, a.getMaxY());

		assertEquals(i1, i1o.getReportingFrame());
		a = i1o.getReportedArea();
		assertEquals(16, a.getMinX());
		assertEquals(16, a.getMinY());
		assertEquals(16, a.getMaxX());
		assertEquals(16, a.getMaxY());

		assertEquals(i2, i2o.getReportingFrame());
		a = i2o.getReportedArea();
		assertEquals(6, a.getMinX());
		assertEquals(6, a.getMinY());
		assertEquals(6, a.getMaxX());
		assertEquals(6, a.getMaxY());

		cfo.reset();
		i1o.reset();
		i2o.reset();

		cf.setPixel(15, 15, RED);
		
		assertEquals(cf, cfo.getReportingFrame());
		a = cfo.getReportedArea();
		assertEquals(15, a.getMinX());
		assertEquals(15, a.getMinY());
		assertEquals(15, a.getMaxX());
		assertEquals(15, a.getMaxY());

		assertEquals(i1, i1o.getReportingFrame());
		a = i1o.getReportedArea();
		assertEquals(5, a.getMinX());
		assertEquals(5, a.getMinY());
		assertEquals(5, a.getMaxX());
		assertEquals(5, a.getMaxY());

		assertEquals(null, i2o.getReportingFrame());
		assertEquals(null, i2o.getReportedArea());
	}

}

class AdeptFrameAreaCaptureObserver implements FrameObserver {
	private Frame last_reporting_frame;
	private FrameArea last_area_reported;
	
	public AdeptFrameAreaCaptureObserver() {
		last_area_reported = null;
	}

	public Frame getReportingFrame() {
		return last_reporting_frame;
	}
	
	public FrameArea getReportedArea() {
		return last_area_reported;
	}

	public void reset() {
		last_reporting_frame = null;
		last_area_reported = null;
	}
	
	@Override
	public void update(Frame frame, FrameArea area) {
		last_reporting_frame = frame;
		last_area_reported = area;
	}
}