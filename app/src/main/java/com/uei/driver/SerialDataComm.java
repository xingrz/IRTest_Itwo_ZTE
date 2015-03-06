/* 
 *
 *
 * Copyright (C) 1999-2013 Universal Electronics Inc.
 *
 * 
 */

package com.uei.driver;

import android.util.Log;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The Class SerialDataComm.
 */
public class SerialDataComm
{	
	/** The Constant BUFFERSIZE. */
	private static final int BUFFERSIZE = 1024;
	
	// JNI
	static
	{
		System.loadLibrary("serial_port");
	}	
	
	/**
	 * Open.
	 * 
	 * @param path
	 *            the path
	 * @param baudrate
	 *            the baudrate
	 * @return the file descriptor
	 */
	private native static FileDescriptor open(String path, int baudrate);
	
	/**
	 * Close.
	 */
	public native void close();
	
	/*
	 * Do not remove or rename the field mFd: it is used by native method close();
	 */
	/** The _port name. */
	private String _portName = "";
	
	/** The m fd. */
	private FileDescriptor mFD;
	
	/** The _ file input stream. */
	private FileInputStream _FileInputStream;
	
	/** The _ file output stream. */
	private FileOutputStream _FileOutputStream;
	
	/** The _read thread. */
	private ReadThread _readThread;
	
	/** The _data available listener. */
	private IDataAvailableListener _dataAvailableListener = null;
	
	/** The _read buffer. */
	private byte[] _readBuffer = null;
	
	/** The _is connected. */
	private boolean _isConnected = false;
	
	/** The _rec queue. */
	private	ArrayList<byte[]> _recQueue = new ArrayList<byte[]>();
	
	/** The _rec queue lock. */
	final private Lock _recQueueLock = new ReentrantLock();		    
    
    /** The _lock data write. */
    final Lock _lockDataWrite = new ReentrantLock();
 
	/**
	 * Instantiates a new serial data comm.
	 * 
	 * @param portName
	 *            the port name
	 * @param baudrate
	 *            the baudrate
	 * @throws SecurityException
	 *             the security exception
	 * @throws java.io.IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public SerialDataComm(String portName, int baudrate) throws SecurityException, IOException {
		
		File serialPortFile = null;
		this._portName = portName;
		
		Log.d("IRBlaster", "open serial port: " + portName +  " - " + baudrate);
		try
		{
			serialPortFile = new File(portName);
		}catch(Exception ex){
			ex.printStackTrace();
			Log.e("IRBlaster", ex.toString());
		}
		
			
		if(serialPortFile != null && serialPortFile.canRead() && serialPortFile.canWrite()){
			try	{
				
				this.mFD = open(serialPortFile.getAbsolutePath(), baudrate);
				if (this.mFD == null) {
					Log.e("IRBlaster","native open serial port returns null");				
					throw new IOException("native open serial port returns null");
				}
				this._FileInputStream = new FileInputStream(this.mFD);
				this._FileOutputStream = new FileOutputStream(this.mFD);
				this._readThread = new ReadThread();
				this._readThread.start();
				this._isConnected = true;
//				IRBlasterTestActivity.Singleton.output("Serial port opened successful");
			}catch(Exception ex){
				ex.printStackTrace();
				Log.e("IRBlaster", ex.toString());				
			}
		}else{
			Log.e("IRBlaster", "no permission on read/write serial port!");
			throw new IOException("no permission on read/write serial port!");
		}
	}
	
	public boolean isConnected() 
	{
		return (this._isConnected && this.mFD != null);
	}

	public void setDataAvailableListener(IDataAvailableListener l) 
	{
		this._dataAvailableListener = l;		
	}

	public void removeDataAvailableListener(IDataAvailableListener l) {
		this._dataAvailableListener = null;		
	}

	public byte[] readBytes() 
	{
		byte[] data = null;
	
		this._recQueueLock.lock();
		try{
			boolean invalidData = true;
			while(this._recQueue.size() > 0 && invalidData)	{
				data = this._recQueue.get(0);
				this._recQueue.remove(0);
				invalidData = (data == null || data.length == 0);
			}
			if(invalidData)	{
				data = null;
			}				
		}catch(Exception ex){
			ex.printStackTrace();
			Log.e("IRBlaster", ex.toString());			
		}
		finally
		{
			this._recQueueLock.unlock();
		}		
			
		return data;
	}

	public boolean writeBytes(byte[] buffer) {
		
		boolean success = false;
		this.clearReadBuffer();
		if(this._lockDataWrite.tryLock()){	
			try	{
				if(this._FileOutputStream != null){
					this._FileOutputStream.write(buffer);
					success = true;
				}
			}catch(Exception ex){
				ex.printStackTrace();
				Log.e("IRBlaster", ex.toString());				
			}finally{
				this._lockDataWrite.unlock();
			}
		}
			
		return success;
	}
	
	public void shutdown() 
	{		
		try	{
			this._isConnected = false;
			if (this._readThread != null && this._readThread.isAlive())	{
//				IRBlasterTestActivity.Singleton.output("Serial port shutdown");
				this._readThread.interrupt();
			}
			this._FileInputStream.close();
			this._FileOutputStream.close();
			this.close();			
		}catch(Exception ex){
			ex.printStackTrace();
			Log.e("IRBlaster", ex.toString());			
		}	
	}
	
	/**
	 * Clear read buffer.
	 */
	private void clearReadBuffer()
	{
		this._recQueueLock.lock();
		try	{
			if(this._recQueue != null){
				this._recQueue.clear();
			}
		}catch(Exception ex){
			ex.printStackTrace();
			Log.e("IRBlaster", ex.toString());		
		}finally{
			this._recQueueLock.unlock();
		}
	}
	
	/**
	 * On data received.
	 * 
	 * @param buffer
	 *            the buffer
	 */
	private void onDataReceived(byte[] buffer)
	{		
		if(this._recQueueLock.tryLock()){	
			try	{
				this._recQueue.add(buffer);
				if(this._dataAvailableListener != null)	{
					this._dataAvailableListener.dataAvailable(this);
				}
			}catch(Exception ex){
				ex.printStackTrace();
				Log.e("IRBlaster", ex.toString());				
			}finally{
				this._recQueueLock.unlock();
			}
		}		
	}
	
	/**
	 * The Class ReadThread.
	 */
	private class ReadThread extends Thread {

		/* (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			super.run();
			
			try	{
				byte[] buffer = new byte[BUFFERSIZE];
				while(!isInterrupted()) {
					int size;
					try {										
						if (SerialDataComm.this._FileInputStream == null)
						{
							return;
						}
						size = SerialDataComm.this._FileInputStream.available();
											
						long interval = System.currentTimeMillis();
						
						if(size > 0){
							
							//IRBlasterTestActivity.Singleton.output("Got data " + size);
							size = SerialDataComm.this._FileInputStream.read(buffer, 0, buffer.length);
							int payloadSize = ((int)((buffer[0]&0xFF) << 8) & 0x00FF00)|((int)(buffer[1]&0x0ff) & 0x00FF);
							
							int totalPacketSize = payloadSize + 2; // whole packet length
							if(size > 0 && payloadSize > 0)
							{
								int readCount = size;
								if(totalPacketSize > readCount) {
									// reading remaining data from blaster									
									while(readCount < totalPacketSize && readCount < BUFFERSIZE) {										
										size = SerialDataComm.this._FileInputStream.available();										
										if(size > 0) {
											size = SerialDataComm.this._FileInputStream.read(buffer, readCount, buffer.length-readCount);
											readCount += size;											
										} else {
											try	{
												Thread.sleep(10);
											} catch(Exception ex){}
										}
										// timeout after one second
										if((System.currentTimeMillis()-interval) > 1000) {
											// timeout
											break;
										}
									}
								}
							}
							
							if (payloadSize > 0){
								byte[] recvData = new byte[payloadSize+2];
								System.arraycopy(buffer, 0, recvData, 0,recvData.length);
								// IRBlasterTestActivity.Singleton.output("Got data reading. Fire event= " + recvData.length);
								onDataReceived(recvData);
							}
						}	
						else
						{
							Thread.sleep(10);
						}
						
					} catch (Exception e) {
						e.printStackTrace();						
						Log.e("IRBlaster", e.toString());	
						return;
					}
				}
			}catch(Exception ex){
				ex.printStackTrace();
				Log.e("IRBlaster", ex.toString());					
			}
			Log.d("IRBlaster", "serial port read closed.");
		}
	}
}
