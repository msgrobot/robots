package XboxRemote;

import Data.M.M;
import ch.aplu.xboxcontroller.XboxController;

public class Remote {
	
	Server s;
	XboxController xc;
	Xa xa; //Adapter für XboxController
	long st = 20; //sleep time
	Frame f;
	
	public Remote(){
		s = new Server(1997);
		xc = new XboxController();
		xa = new Xa();
		xc.addXboxControllerListener(xa); //verarbeitet Kommandos vom Controller
		f = new Frame();
		f.setVisible(true);
	}
	
	public void start(){
		while(! xa.back){
			
			if(xa.ldb || xa.ltb || xa.rtb){
				double speed = xa.lt - xa.rt;
				float rot = (float) (Math.sin(Math.toRadians(xa.ld)) * 20*xa.r  * speed * xa.lm); //Rotation

				float ls = rot;
				float rs = -rot;

				ls += speed*30 * xa.g;
				rs += speed*30 * xa.g;

				s.sendTcp(new M(ls, rs));
			}
			
			if(xa.beep){
				s.sendTcp(new M(1337,1337));
			}if(xa.volb){
				f.setVol(xa.vol);
				s.sendTcp(new M(1337, xa.vol));
			}
			
			xa.reset();
			
			try {
				Thread.sleep(st);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			f.setGang(xa.g);
			f.setWR(xa.r);
		}
		s.close();
	}
	
}
