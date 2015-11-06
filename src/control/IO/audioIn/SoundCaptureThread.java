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
	ByteArrayOutputStream out2;
	
	byte[] data;
	
	int numBytesRead;
	int frameSizeInBytes;
	int bufferLengthInFrames;
	int bufferLengthInBytes;

	boolean running = true;

	private static int bin = 0;
	
	public static int length = 1024;
	

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
		out2 = new ByteArrayOutputStream();
		frameSizeInBytes = format.getFrameSize();
		bufferLengthInFrames = length;
		bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
		data = new byte[bufferLengthInBytes];
		
		tLine.start();
		
		Driver.trace("finished init");
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
			//if (bin == 0) {
				if ((numBytesRead = tLine.read(data, 0, bufferLengthInBytes)) == -1)
					break;

				out.write(data, 0, numBytesRead);
				//tLine.flush();
//			}
//			else {
//				if ((numBytesRead = tLine.read(data, 0, bufferLengthInBytes)) == -1)
//					break;
//
//				
//				out2.write(data, 0, numBytesRead);
//				//tLine.flush();
//			}
		}
	}

	public ByteArrayOutputStream getOut() {
		return out;
	}

	public void setOut(ByteArrayOutputStream out) {
		this.out = out;
	}

	public ByteArrayOutputStream getOut2() {
		return out2;
	}

	public void setOut2(ByteArrayOutputStream out2) {
		this.out2 = out2;
	}

	public void resetOut() {
		out.reset();
	}

	public void resetOut2() {
		out2.reset();
	}

	public byte[] getOutAsByteArray() {
		return out.toByteArray();
	}

	public byte[] getOut2AsByteArray() {
		return out2.toByteArray();
	}
	
	public double[] getOutAsDouble() {
		return floatMe(shortMe(out.toByteArray()));
	}
	
	public double[] getOut2AsDouble() {
		return floatMe(shortMe(out2.toByteArray()));
	}
	
	public double[] getSound() {
		if (bin == 0) {
			return getOut2AsDouble();
		} else {
			return getOutAsDouble();
		}
	}

	public int getBin() {
		return bin;
	}

	public void setBin(int bin) {
		SoundCaptureThread.bin = bin;
	}

	public void flipBin() {
		if (bin == 0) bin = 1;
		else if (bin == 1) bin = 0;
		else throw new RuntimeException("SoundCaptureThread in illegal state");
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public static double[] floatMe(short[] pcms) {
		double[] floaters = new double[pcms.length];
		//System.out.println("Creating double array of lenght " + floaters.length);
		for (int i = 0; i < pcms.length; i++) {
			floaters[i] = pcms[i];
		}
		return floaters;
	}

	public static short[] shortMe(byte[] bytes) {
		short[] out = new short[bytes.length / 2]; // will drop last byte if odd number
		//System.out.println("Creating short array of lenght " + out.length);
		ByteBuffer bb = ByteBuffer.wrap(bytes);
		for (int i = 0; i < out.length; i++) {
			out[i] = bb.getShort();
		}
		return out;
	}
	
}