package XboxRemote;

import ch.aplu.xboxcontroller.XboxControllerAdapter;

public class Xa extends XboxControllerAdapter{
	boolean back = false;
	
	double ld = 0;
	boolean ldb = false;
	
	double lt;
	boolean ltb = false;
	double rt;
	boolean rtb = false;
	
	boolean beep = false;
	boolean volb = false;
	
	int g = 1; //Gang
	int r = 1; //Wenderadius
	int vol = 20;
	
	@Override
	public void back(boolean pressed) {
		if(pressed)
			back = true;
	}
	@Override
	public void leftThumbDirection(double direction) {
		ld = direction;
		ldb = true;
	}
	double lm = 0;
	@Override
	public void leftThumbMagnitude(double magnitude) {
		lm = magnitude>0.3 ? 1 : 0;
	}
	
	@Override
	public void leftTrigger(double value) {
		lt = value;
		ltb = true;
	}
	
	@Override
	public void rightTrigger(double value) {
		rt = value;
		rtb = true;
	}
	
	@Override
	public void dpad(int direction, boolean pressed) {
			//komisches Kreuzchen
		if (!pressed){
			return;
		}
		if(direction == 0){ //nach oben
			g++;
		}else if(direction == 4 && g > 1){ //nach unten
			g--;
		}
		
		if(direction == 2){ //nach links
			r++;
		}else if(direction == 6 && r > 1){ //nach rechts
			r--;
		}
	}
	
	@Override
	public void buttonA(boolean pressed) {
		if (pressed){
			beep = true;
		}
	}
	
	@Override
	public void leftShoulder(boolean pressed) {//über Trigger
		if(pressed){
			vol -= 10;
			if(vol < 10){
				vol = 10;
			}
			volb = true;
		}
	}
	@Override
	public void rightShoulder(boolean pressed) {
		if(pressed){
			vol += 10;
			if(vol > 100){
				vol = 100;
			}
			volb = true;
		}
	}
	
	public void reset(){
		back = false;
		ldb = false;
		ltb = false;
		rtb = false;
		beep = false;
		volb = false;
	}
}
