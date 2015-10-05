package Karl;

//import lejos.hardware.motor.Motor;
//import lejos.robotics.RegulatedMotor;
//import lejos.utility.Delay;

public class Karl10 {
	
	//      0
	//    / 3 \
	//   /     \
	//  /       \
	// 0---------0
	// 1         2
	public final static long maxD12 = (long) (0.08D/ 343.2D * 1000D * 1000D); //~233 ns;
	public final static long maxD13 = (long) (0.19D/ 343.2D * 1000D * 1000D); //~470 ns
	public final static long maxD23 = (long) (0.19D/ 343.2D * 1000D * 1000D); //~470 ns
										   // 0.08m /(343.2 m/s) * 1000 * 1000
						 				   //                       ms     ns
	final public double ReifenAbstand = 12.5; //cm
	final public double RadDurchmesser = 5.8; //cm
	final long nanoTimeToSpinn =(long) (((double)ReifenAbstand) / ((double)RadDurchmesser))*1000 *1000; // 360°/sec
	
	static final float trigger = (float) 0.15; //Auslösewert für LAUT
	
	float angle;
	
	public Karl10() {
		ListenerThread lt = new ListenerThread(trigger);
		lt.start();
		while(true){
	    	
//	    	if(ListenerThread.isReady()){//wenn jedes Mic. schon einmal LAUT war
//	    		angle = calcAngel(ListenerThread.t1-ListenerThread.t2, ListenerThread.t1-ListenerThread.t3, ListenerThread.t2-ListenerThread.t3);
//	    		
//	    		Motor.B.setSpeed(360);
//	    		Motor.C.setSpeed(360);
//	    		if (angle > 180 && angle < 358){
//		    		
//	    			Motor.B.forward();
//		    		Motor.C.backward();
//
//		    		Motor.B.startSynchronization();
//		    		Motor.C.startSynchronization();
//		    		Motor.B.synchronizeWith(new RegulatedMotor[]{Motor.C});
//		    		Motor.C.synchronizeWith(new RegulatedMotor[]{Motor.B});
//		    		
//		    		Delay.nsDelay((long) (((double)nanoTimeToSpinn/360D)*(double)(360D-angle)));
//
//		    		Motor.B.stop();
//		    		Motor.C.stop();
//		    		Motor.B.endSynchronization();
//		    		Motor.C.endSynchronization();
//	    		}else if(angle <= 180 && angle > 2){
//	    			
//		    		Motor.C.forward();
//		    		Motor.B.backward();	
//		    		
//		    		Motor.B.startSynchronization();
//		    		Motor.C.startSynchronization();
//		    		Motor.B.synchronizeWith(new RegulatedMotor[]{Motor.C});
//		    		Motor.C.synchronizeWith(new RegulatedMotor[]{Motor.B});
//		    		
//		    		Delay.nsDelay((long) (((double)nanoTimeToSpinn/360D)*(double)(angle)));
//
//
//		    		Motor.B.stop();
//		    		Motor.C.stop();
//		    		Motor.B.endSynchronization();
//		    		Motor.C.endSynchronization();
//	    		}
//
//	    		Motor.C.startSynchronization();
//	    		Motor.B.startSynchronization();	    		
//	    		Motor.C.synchronizeWith(new RegulatedMotor[]{Motor.B});
//	    		Motor.B.synchronizeWith(new RegulatedMotor[]{Motor.A});
//	    		Motor.B.forward();
//	    		Motor.C.forward();
//	    		
//	    		Delay.msDelay(1000);
//	    		
//	    		Motor.C.stop();
//	    		Motor.B.stop();
//
//	    		Motor.B.endSynchronization();
//	    		Motor.C.endSynchronization();
//	    	}
//
////	    	ListenerThread.reset();
//	    	Delay.msDelay(500);
	    }
	    
	}
	
//	private float calcAngel(long d12, long d13, long d23) {
//		double ratio = ((double)(d12)) / ((double)maxD12);
////		if(ratio > 1 || ratio < -1){
////			return -1;
////		}
//    	System.out.println("d12" + Math.round(d12*100)/100 + " - d13" +Math.round(d13*100)/100 + " - d23" + Math.round(d23*100)/100 + " - " + Math.round(ratio*100)/100 + "%");
//		return (float) (ratioToAngel(ratio));
//		
//	}

//	float ratioToAngel(double ratio){
//		return (float) Math.toDegrees(Math.acos(ratio));
//	}
}
