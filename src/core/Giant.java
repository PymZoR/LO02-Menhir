package core;


/**
 * Singleton
 * @author pyms
 *
 */
public class Giant {
	static private Giant inst;

	static public Giant getInst() {
		if (Giant.inst == null) {
			Giant.inst = new Giant();
			return  inst;
		}
		
		return inst;
	}

	private void Giant() {
		
	}
}
