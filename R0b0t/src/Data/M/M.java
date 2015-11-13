package Data.M;

import java.io.Serializable;

import Data.Data;

//Geschwingikeits und Lautstärkesteuerung
public class M extends Data implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public float speedl; //Geschwindigkeit links
	public float speedr; //Geschwindigkeit rechts
	
	public M(float speedl, float speedr){
		this.speedl = speedl;
		this.speedr = speedr;
	}
	
}
