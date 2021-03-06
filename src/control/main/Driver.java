package control.main;

public class Driver {

	private static boolean TRACING = true;
	
	public static void main(String[] args) {
		Driver.trace("Hello Light Control 2.0!");

		new ProgramControlChooser();
		
		
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				
//				//new ProgramControlChooser();
//			}
//		});
		
	}
	
	public static void trace(String msg) {
		if (TRACING) {
			System.out.println("[" + Thread.currentThread().getStackTrace()[2].getClassName().substring(
					Thread.currentThread().getStackTrace()[2].getClassName().lastIndexOf('.')+1)+"."
					+ "" + Thread.currentThread().getStackTrace()[2].getMethodName() + "()]: " + msg);
		}
	}

}
