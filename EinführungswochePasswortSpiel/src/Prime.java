package application;

public class Prime implements Runnable {

	public static int counter=0;
	
	public Prime() {
	
	}

	@Override
	public void run() {
		try {
			this.wait(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		counter=counter+1;
	}

}
