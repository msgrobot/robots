package Data.M;

import java.io.Serializable;

import Data.Data;

public class M extends Data implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public float speedl;
	public float speedr;
	
	public M(float speedl, float speedr){
		this.speedl = speedl;
		this.speedr = speedr;
	}
	
}
