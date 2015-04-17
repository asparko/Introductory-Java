package a6test;

import static org.junit.Assert.*;

import org.junit.Test;

import a6jedi.*;

public class A6JediTest {

	private static final ColorPixel RED = new ColorPixel(1.0, 0.0, 0.0);
	private static final ColorPixel BLUE = new ColorPixel(0.0, 0.0, 1.0);
	private static final ColorPixel GREEN = new ColorPixel(0.0, 1.0, 0.0);

	@Test
	public void suspendNotifyTest() {
		ColorFrame cf = new ColorFrame(100, 100);
		
		JediFrameAreaCaptureObserver o = new JediFrameAreaCaptureObserver();
		
		cf.registerFrameObserver(o);
		
		cf.suspendNotifications();
		cf.setPixel(0,0,RED);
		cf.setPixel(10,0,RED);
		cf.setPixel(10,10,RED);
		cf.setPixel(0,10,RED);
		cf.resumeNotifications();

		FrameArea a;

		assertEquals(cf, o.getReportingFrame());
		a = o.getReportedArea();
		assertEquals(0, a.getMinX());
		assertEquals(0, a.getMinY());
		assertEquals(10, a.getMaxX());
		assertEquals(10, a.getMaxY());
	}
	
	@Test
	public void areaOfInterestTest() {
		ColorFrame cf = new ColorFrame(100, 100);
		
		JediFrameAreaCaptureObserver o = new JediFrameAreaCaptureObserver();
		
		cf.registerFrameObserver(o, new FrameArea(10, 10, 20, 20));
		
		cf.setPixel(5, 5, RED);

		assertEquals(null, o.getReportingFrame());
		assertEquals(null, o.getReportedArea());
		
		cf.setPixel(15, 15, BLUE);

		FrameArea a;

		assertEquals(cf, o.getReportingFrame());
		a = o.getReportedArea();
		assertEquals(15, a.getMinX());
		assertEquals(15, a.getMinY());
		assertEquals(15, a.getMaxX());
		assertEquals(15, a.getMaxY());
		
		o.reset();
		cf.suspendNotifications();
		cf.setPixel(5, 5, GREEN);
		cf.setPixel(15, 15, GREEN);
		cf.resumeNotifications();
		
		assertEquals(cf, o.getReportingFrame());
		a = o.getReportedArea();
		assertEquals(5, a.getMinX());
		assertEquals(5, a.getMinY());
		assertEquals(15, a.getMaxX());
		assertEquals(15, a.getMaxY());
		
		
	}
	
	@Test
	public void indirectFrameSuspensionTest() {
		ColorFrame cf = new ColorFrame(100, 100);
		IndirectFrame i1 = cf.crop(10, 10, 50, 50);
		
		JediFrameAreaCaptureObserver cfo = new JediFrameAreaCaptureObserver();
		JediFrameAreaCaptureObserver i1o = new JediFrameAreaCaptureObserver();

		cf.registerFrameObserver(cfo);
		i1.registerFrameObserver(i1o);
		
		FrameArea a;
		
		i1.suspendNotifications();

		cf.setPixel(5, 5, RED);
		
		assertEquals(cf, cfo.getReportingFrame());
		a = cfo.getReportedArea();
		assertEquals(5, a.getMinX());
		assertEquals(5, a.getMinY());
		assertEquals(5, a.getMaxX());
		assertEquals(5, a.getMaxY());
		
		assertEquals(null, i1o.getReportingFrame());
		assertEquals(null, i1o.getReportedArea());
		
		cf.setPixel(15, 15, RED);
		
		assertEquals(cf, cfo.getReportingFrame());
		a = cfo.getReportedArea();
		assertEquals(15, a.getMinX());
		assertEquals(15, a.getMinY());
		assertEquals(15, a.getMaxX());
		assertEquals(15, a.getMaxY());
		
		assertEquals(null, i1o.getReportingFrame());
		assertEquals(null, i1o.getReportedArea());

		cf.setPixel(25, 25, RED);

		assertEquals(cf, cfo.getReportingFrame());
		a = cfo.getReportedArea();
		assertEquals(25, a.getMinX());
		assertEquals(25, a.getMinY());
		assertEquals(25, a.getMaxX());
		assertEquals(25, a.getMaxY());
		
		assertEquals(null, i1o.getReportingFrame());
		assertEquals(null, i1o.getReportedArea());

		cfo.reset();
		i1.resumeNotifications();
		
		assertEquals(null, cfo.getReportingFrame());
		assertEquals(null, cfo.getReportedArea());
		
		assertEquals(i1, i1o.getReportingFrame());
		a = i1o.getReportedArea();
		assertEquals(5, a.getMinX());
		assertEquals(5, a.getMinY());
		assertEquals(15, a.getMaxX());
		assertEquals(15, a.getMaxY());
		
	}

}

class JediFrameAreaCaptureObserver implements FrameObserver {
	private Frame last_reporting_frame;
	private FrameArea last_area_reported;
	
	public JediFrameAreaCaptureObserver() {
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
