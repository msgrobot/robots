package Heinz;

import lejos.hardware.Sound;
import lejos.hardware.motor.Motor;
import lejos.robotics.RegulatedMotor;
import Data.Data;
import Data.M.M;

public class Heinz {
	Client client; //Netzwerk
	String ip;
	
	boolean inv; //Motorrichtung
	
	public Heinz(String ip, boolean inverted) {
		this.ip = ip;
		client = new Client(ip, 1997, this); //Internetverbindung
		inv = inverted; //Motor Richtung
	}

	public void processData(Data d) { //Daten Verarbeitung
		if (d instanceof M) {//wenn Daten vom richtigen Protokoll sind, dann
			//Links
			M m = (M) d; 
			int s1 = (int) (m.speedl * (inv ? -1 : +1)); //Motorrichtung
			Motor.A.setSpeed(s1); //Motor ansteuern

			//Rechts
			int s2 = (int) (m.speedr * (inv ? -1 : +1)); //Motorrichtung
			Motor.B.setSpeed(s2); //Motor ansteuern
			
			if (m.speedl == m.speedr){ //Synchronisierung
				if(m.speedl == 1337){
					Sound.playTone(500, 100); //Ton
				}else{
					Motor.A.startSynchronization(); //Motoren Synchronisierung - gleichzeitiges Starten und Stoppen
					Motor.B.startSynchronization();
					Motor.A.synchronizeWith(new RegulatedMotor[]{Motor.B});
					Motor.B.synchronizeWith(new RegulatedMotor[]{Motor.A});
					
					if (m.speedl > 0) {
						Motor.A.forward();
						Motor.B.forward();

					} else if (m.speedl < 0) {
						Motor.A.backward();	
						Motor.B.backward();
					} else {
						Motor.A.stop();
						Motor.B.stop();
					}
					Motor.A.endSynchronization();
					Motor.B.endSynchronization();
				}
			}else{
				if(m.speedl == 1337){
					Sound.setVolume((int) m.speedr);
				}else{
					if (s2 > 0) {
						Motor.B.forward();
					} else if (s2 < 0) {
						Motor.B.backward();
					} else {
						Motor.B.stop();
					}
		
					if (s1 > 0) {
						Motor.A.forward();
					} else if (s1 < 0) {
						Motor.A.backward();
					} else {
						Motor.A.stop();
					}
				}
			}
		} 
	}
}
