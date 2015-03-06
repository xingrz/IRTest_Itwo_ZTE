/* 
 *
 *
 * Copyright (C) 1999-2013 Universal Electronics Inc.
 * 
 * 
 */
package com.uei.driver;


/**
 * The Class DeviceTypeHelper.
 */
public class DeviceTypeHelper 
{
	
	/**
	 * The Class BlasterDeviceType.
	 */
	public class BlasterDeviceType
	{
		
		/** The Constant DEVICE_TV. */
		public static final byte DEVICE_TV = 0x00;
		
		/** The Constant DEVICE_CABLE_IPTV. */
		public static final byte DEVICE_CABLE_IPTV = 0x01;
		
		/** The Constant DEVICE_VIDEOACCESSORY. */
		public static final byte DEVICE_VIDEOACCESSORY = 0x02;
		
		/** The Constant DEVICE_SAT_DSS. */
		public static final byte DEVICE_SAT_DSS = 0x03;
		
		/** The Constant DEVICE_VCR. */
		public static final byte DEVICE_VCR = 0x04;
		
		/** The Constant DEVICE_DVD. */
		public static final byte DEVICE_DVD = 0x06;
		
		/** The Constant DEVICE_RECEIVER_MISCAUDIO. */
		public static final byte DEVICE_RECEIVER_MISCAUDIO = 0x07;
		
		/** The Constant DEVICE_AMPLIFIER. */
		public static final byte DEVICE_AMPLIFIER = 0x08;
		
		/** The Constant DEVICE_CD. */
		public static final byte DEVICE_CD = 0x09;
		
		/** The Constant DEVICE_HOMECONTROL. */
		public static final byte DEVICE_HOMECONTROL = 0x0A;
		
		/** The Constant DEVICE_UNKNOWN. */
		public static final byte DEVICE_UNKNOWN = (byte)0xFF;
	}

	/** The Constant CODESETSTRINGLENGTH. */
	public static final int CODESETSTRINGLENGTH = 5; // "T0000"
	
	/** The Constant DEVICEMODE_TV. */
	public static final char DEVICEMODE_TV = 'T';
	
	/** The Constant DEVICEMODE_CABLE_IPTV. */
	public static final char DEVICEMODE_CABLE_IPTV = 'C';
	
	/** The Constant DEVICEMODE_VIDEOACCESSORY. */
	public static final char DEVICEMODE_VIDEOACCESSORY = 'N';
	
	/** The Constant DEVICEMODE_SAT_DSS. */
	public static final char DEVICEMODE_SAT_DSS = 'S';
	
	/** The Constant DEVICEMODE_VCR. */
	public static final char DEVICEMODE_VCR = 'V';
	
	/** The Constant DEVICEMODE_DVD. */
	public static final char DEVICEMODE_DVD = 'Y';
	
	/** The Constant DEVICEMODE_RECEIVER. */
	public static final char DEVICEMODE_RECEIVER = 'R';
	
	/** The Constant DEVICEMODE_MISCAUDIO. */
	public static final char DEVICEMODE_MISCAUDIO = 'M';
	
	/** The Constant DEVICEMODE_AMPLIFIER. */
	public static final char DEVICEMODE_AMPLIFIER = 'A';
	
	/** The Constant DEVICEMODE_CD. */
	public static final char DEVICEMODE_CD = 'D';
	
	/** The Constant DEVICEMODE_HOMECONTROL. */
	public static final char DEVICEMODE_HOMECONTROL = 'H';
	
	
	/**
	 * Gets the blaster device type by letter.
	 * 
	 * @param letter
	 *            the letter
	 * @return the blaster device type by letter
	 */
	public static byte getBlasterDeviceTypeByLetter(char letter)
	{
		byte deviceType = BlasterDeviceType.DEVICE_UNKNOWN;
		letter = Character.toUpperCase(letter);
		
		switch(letter)
		{
			case DEVICEMODE_TV: deviceType = BlasterDeviceType.DEVICE_TV; break;
			case DEVICEMODE_CABLE_IPTV: deviceType = BlasterDeviceType.DEVICE_CABLE_IPTV; break;
			case DEVICEMODE_VIDEOACCESSORY: deviceType = BlasterDeviceType.DEVICE_VIDEOACCESSORY; break;
			case DEVICEMODE_SAT_DSS: deviceType = BlasterDeviceType.DEVICE_SAT_DSS; break;
			case DEVICEMODE_VCR: deviceType = BlasterDeviceType.DEVICE_VCR; break;
			case DEVICEMODE_DVD: deviceType = BlasterDeviceType.DEVICE_DVD; break;
			case DEVICEMODE_MISCAUDIO:
			case DEVICEMODE_RECEIVER: deviceType = BlasterDeviceType.DEVICE_RECEIVER_MISCAUDIO; break;
			case DEVICEMODE_AMPLIFIER: deviceType = BlasterDeviceType.DEVICE_AMPLIFIER; break;
			case DEVICEMODE_CD: deviceType = BlasterDeviceType.DEVICE_CD; break;
			case DEVICEMODE_HOMECONTROL: deviceType = BlasterDeviceType.DEVICE_HOMECONTROL; break;			
		}
		
		return deviceType;		
	}
			
	/**
	 * Convert codeset to bytes.
	 * 
	 * @param codeset
	 *            the codeset
	 * @return the byte[]
	 */
	public static byte[] convertCodesetToBytes(String codeset)
	{
		short idNumber = Short.parseShort(codeset.substring(1));
		byte[] codesetBytes = new byte[3];
		byte deviceType = getBlasterDeviceTypeByLetter(codeset.charAt(0));
	
		codesetBytes[0] = (byte)(deviceType & 0x00ff);
		codesetBytes[1] = (byte)((idNumber & 0x00ff00) >> 8);
		codesetBytes[2] = (byte)((idNumber & 0x0000ff));
		return codesetBytes;
	}	
}
