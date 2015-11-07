package control.IO.audioIn;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Port;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

import control.main.Driver;


public class SoundCaptureThread implements Runnable {
	
	TargetDataLine tLine;

	//Thread thread;

	String errStr;

	AudioInputStream audioInputStream;

	ByteArrayOutputStream out;
//	ByteArrayOutputStream out2;
	
	byte[] data;
	
	int numBytesRead;
	int frameSizeInBytes;
	int bufferLengthInFrames;
	int bufferLengthInBytes;

	boolean running = true;
	
	public static int length = (int) Math.pow(2, 13);
	

	public SoundCaptureThread() {
		//duration = 0;
		audioInputStream = null;

		// define the required attributes for our line,
		// and make sure a compatible line is supported.

		AudioFormat.Encoding encoding = AudioFormat.Encoding.PCM_SIGNED;
		float rate = 44100.0f;
		int channels = 2;
		//int frameSize = 4;
		int sampleSize = 16;
		boolean bigEndian = true;

		AudioFormat format = new AudioFormat(encoding, rate, sampleSize, channels, (sampleSize / 8) * channels, rate, bigEndian);

//		Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();
//		for (Mixer.Info mInfo: mixerInfos){
//			Mixer m = AudioSystem.getMixer(mInfo);
//			
//				System.out.println("\t--Mixer: "+m.toString());
//			Line.Info[] lineInfos = m.getSourceLineInfo();
////			for (Line.Info lineInfo:lineInfos){
////				System.out.println (info.getName()+"---"+lineInfo);
////				Line line = null;
////				try {
////					line = m.getLine(lineInfo);
////				} catch (LineUnavailableException e) {
////					// TODO Auto-generated catch block
////					e.printStackTrace();
////				} catch (NullPointerException e) {
////					e.printStackTrace();
////				}
////				System.out.println("\t-----"+line);
////			}
//			lineInfos = m.getTargetLineInfo();
//			for (Line.Info lineInfo:lineInfos){
//				System.out.println (m+"---"+lineInfo);
//				Line line = null;
//				try {
//					line = m.getLine(lineInfo);
//				} catch (LineUnavailableException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (NullPointerException e) {
//					e.printStackTrace();
//				}
//				System.out.println("\t-----"+line);
//
//			}
//			
//		}
		
	
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

		if (!AudioSystem.isLineSupported(info)) {
			shutDown("Line matching " + info + " not supported.");
			return;
		}

		// get and open the target data line for capture.

		try {
			tLine = (TargetDataLine) AudioSystem.getLine(info);
			tLine.open(format, tLine.getBufferSize());
		} catch (LineUnavailableException ex) {
			shutDown("Unable to open the line: " + ex);
			return;
		} catch (SecurityException ex) {
			shutDown(ex.toString());
			//JavaSound.showInfoDialog();
			return;
		} catch (Exception ex) {
			shutDown(ex.toString());
			return;
		}

		// capture data to array/s
		out = new ByteArrayOutputStream();
//		out2 = new ByteArrayOutputStream();
		frameSizeInBytes = format.getFrameSize();
		bufferLengthInFrames = length;
		bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
		//data = new byte[bufferLengthInBytes];
		data = new byte[(int) (rate*channels)];
		
		tLine.start();
		
		Driver.trace("Successfully initialised.");
	}

	private void shutDown(String message) {
		//System.out.println("Broken out");

		// we reached the end of the stream.
		// stop and close the line.
		try {
			
			tLine.stop();
			tLine.close();
			
		} catch(NullPointerException e) {
			return;
		}
		tLine = null;
		
		// stop and close the output stream
		try {
			out.flush();
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		if ((errStr = message) != null) {
			//thread = null;
			//playB.setEnabled(true);
			//captB.setText("Record");
			running = false;
			System.err.println(errStr);
		}
	}

	public void run() {
		
		while (running && tLine != null) {
			int numReady = tLine.available();
			//Driver.trace("" + numReady);
			if (numReady > 0) {
				numBytesRead = tLine.read(data, 0, numReady);
				
				out.write(data, 0, numBytesRead);
			}
			
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public ByteArrayOutputStream getOut() {
		return out;
	}

	public void resetOut() {
		out.reset();
	}

	public byte[] getOutAsByteArray() {
		return out.toByteArray();
	}
	
	public double[] getOutAsDouble() {
		return floatMe(shortMe(out.toByteArray()));
	}
	
	public double[] getSound() {
		return getOutAsDouble();
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public static double[] floatMe(short[] pcms) {
		double[] floaters = new double[pcms.length];
		//System.out.println("Creating double array of length " + floaters.length);
		for (int i = 0; i < pcms.length; i++) {
			floaters[i] = pcms[i];
		}
		return floaters;
	}

	public static short[] shortMe(byte[] bytes) {
		short[] out = new short[bytes.length / 2]; // will drop last byte if odd number
		//System.out.println("Creating short array of length " + out.length);
		ByteBuffer bb = ByteBuffer.wrap(bytes);
		for (int i = 0; i < out.length; i++) {
			out[i] = bb.getShort();
		}
		return out;
	}
	
}