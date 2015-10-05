package Karl;

import java.util.ArrayList;
import java.util.List;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.NXTSoundSensor;
//import lejos.utility.Delay;

public class ListenerThread implements Runnable{
	
	Thread t;
	float trigger;
	public List<float[]> stack = new ArrayList<float[]>();

	public ListenerThread(float trigger){
		this.trigger = trigger;
		t = new Thread(this, "SoundListener");
	}
	
	public void start(){
		t.start();
	}
	
	@Override
	public void run() {

		NXTSoundSensor ss1 = new NXTSoundSensor(SensorPort.S1);
		NXTSoundSensor ss2 = new NXTSoundSensor(SensorPort.S2);
		NXTSoundSensor ss3 = new NXTSoundSensor(SensorPort.S3);
	
		
		while(!stopped){

			float v1[] = new float[1];
			ss1.fetchSample(v1, 0);
			
			float v2[] = new float[1];
			ss2.fetchSample(v2, 0);

			float v3[] = new float[1];
			ss3.fetchSample(v3, 0);

			boolean b1 = false;
			boolean b2 = false;
			boolean b3 = false;
			if(v1[0] >= trigger){
				b1 = true;
			}
			if(v2[0] >= trigger){
				b2 = true;
			}
			if(v3[0] >= trigger){
				b3 = true;
			}
			
			if( b1 && b2 && b3){
				stack.add(new float[]{v1[0],v2[0],v3[0]});
			}
//			Delay.nsDelay(Karl.maxD12/2);
		}
		
		ss1.close();
		ss2.close();
		ss3.close();
		
	}
	
	boolean stopped = false;
	public void stop_(){
		stopped = true;
	}
}
