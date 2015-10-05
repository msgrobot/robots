package Karl;

import lejos.hardware.motor.Motor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

//import lejos.hardware.motor.Motor;
//import lejos.robotics.RegulatedMotor;
//import lejos.utility.Delay;

public class Karl11 {

	// 0
	// / 3 \
	// / \
	// / \
	// 0---------0
	// 1 2
	public final static long maxD12 = (long) (0.08D / 343.2D * 1000D * 1000D); // ~233
																				// ns;
	public final static long maxD13 = (long) (0.19D / 343.2D * 1000D * 1000D); // ~470
																				// ns
	public final static long maxD23 = (long) (0.19D / 343.2D * 1000D * 1000D); // ~470
																				// ns
	// 0.08m /(343.2 m/s) * 1000 * 1000
	// ms ns
	final public double ReifenAbstand = 12.5; // cm
	final public double RadDurchmesser = 5.8; // cm
	final long nanoTimeToSpinn = (long) (((double) ReifenAbstand) / ((double) RadDurchmesser)) * 1000 * 1000; // 360°/sec

	static final float trigger = (float) 0.11; // Auslösewert für LAUT
	float angle;

	public Karl11() {
		ListenerThread lt = new ListenerThread(trigger);
		lt.start();

		while (true) {
			float max[] = new float[] { -1, -1, -1 };

			int size = lt.stack.size();
			for (int i = 0; i < size; i++) {
				float[] fa = lt.stack.get(i);

				if (fa == null) {
					continue;
				}
				if (max == null) {
					max = new float[] { -1, -1, -1 };
				}
				if (fa[0] > max[0]) {
					max[0] = fa[0];
				}
				if (fa[1] > max[1]) {
					max[1] = fa[1];
				}
				if (fa[2] > max[2]) {
					max[2] = fa[2];
				}
			}
			lt.stack.clear();
			Delay.msDelay(400);
			if (max[0] != -1 && max[1] != -1 && max[2] != -1) {
				processData(max);
				lt.stack.clear();
			}
		}

	}

	void processData(float[] max) { 
		
		if(max == null || max.length != 3){
			return;
		}
		
//		int direction = -1;
		
		if(max[0]/max[1] > 0.85 && max[0]/max[1] < 1.15){
//			vorn oder hinten
			if(max[2] / max[0] >1.15){
//				vorn
//				direction =0;
				links(); links();
				vor();
				System.out.println("hinten");
			}else if(max[0] / max[2] > 1.15){
//				hinten
//				direction = 4;
				vor();
				System.out.println("vorn");
			}
			
		}else{
			if(max[0] >  max[1]){
				System.out.println("rechts");
				rechts();
				vor();
			}else{
				System.out.println("links");
				links();
				vor();
			}
		}
		
	}
	
	int spinndelay = 635;
	void links(){
		Motor.B.setSpeed(360);
		Motor.C.setSpeed(360);

		Motor.B.forward();
		Motor.C.backward();
		
		Delay.msDelay(spinndelay);
		
		Motor.B.startSynchronization();
		Motor.C.startSynchronization();
		Motor.B.synchronizeWith(new RegulatedMotor[]{Motor.C});
		Motor.C.synchronizeWith(new RegulatedMotor[]{Motor.B});
		
		Motor.C.stop();
		Motor.B.stop();

		Motor.B.endSynchronization();
		Motor.C.endSynchronization();
	}
	

	void rechts(){
		Motor.B.setSpeed(360);
		Motor.C.setSpeed(360);

		Motor.C.forward();
		Motor.B.backward();
		
		Delay.msDelay(spinndelay);
		
		Motor.B.startSynchronization();
		Motor.C.startSynchronization();
		Motor.B.synchronizeWith(new RegulatedMotor[]{Motor.C});
		Motor.C.synchronizeWith(new RegulatedMotor[]{Motor.B});
		
		Motor.C.stop();
		Motor.B.stop();

		Motor.B.endSynchronization();
		Motor.C.endSynchronization();
	}
	void vor(){
		Motor.B.setSpeed(360);
		Motor.C.setSpeed(360);

		
		Motor.B.startSynchronization();
		Motor.C.startSynchronization();
		Motor.B.synchronizeWith(new RegulatedMotor[]{Motor.C});
		Motor.C.synchronizeWith(new RegulatedMotor[]{Motor.B});
		
		Motor.B.forward();
		Motor.C.forward();
		
//		Delay.msDelay(1000);
		
		Motor.C.stop();
		Motor.B.stop();

		Motor.B.endSynchronization();
		Motor.C.endSynchronization();
	}

}
