/* 
 *
 *
 * Copyright (C) 1999-2013 Universal Electronics Inc.
 *
 * 
 */
package com.uei.driver;

public class IRBlasterConstants {

	/** Acer serial port name */
//	public static final String SERIALPORT = "/dev/ttyHSL1";   //zte
	// public static final String SERIALPORT = "/dev/ttyS1";
	public static final String SERIALPORT = "/dev/ttyMT2";
	// public static final String SERIALPORT = "/dev/ttyHS10";

	/** Blaster serial baud rate */
	public static final int BAUDRATE = 115200;   //zte
	// public static final int BAUDRATE = 230400;
	// public static final int BAUDRATE = 19200;

	/** MAXQ616 flag */
	public static final boolean ISMAXQ616 = false;// true;

	/** The Blaster wakeup command. */
	public static byte[] NEVO_CMD_WAKEUP = new byte[] { 0x00 };

	/** The Constant NEVO_BLASTER_WAKEUP_DELAY. */
	public static final int BLASTER_WAKEUP_DELAY = 100; // Minimum delay 20ms

	/** The Constant StopIRDelay. */
	public static final int StopIRDelay = 100;

	/** The Constant StopIRRetryForLocal. */
	public static final int StopIRRetryForLocal = 3;

	/** The Constant BLASTER_KEYFLAG_MACRO_ON. */
	public static final byte BLASTER_KEYFLAG_MACRO_ON = (byte) 0x80; // Macro on

	/** The Constant MASTERRESET_CLEARRAM. */
	public static final byte MASTERRESET_CLEARRAM = 0x01; // The MasterReset
															// command
															// parameter: 1
															// clear RAM

	/** The Constant MASTERRESET_CLEARLEARNCODES. */
	public static final byte MASTERRESET_CLEARLEARNCODES = 0x02; // The
																	// MasterReset
																	// command
																	// parameter:
																	// 2 clear
																	// learned
																	// codes

	/** The Constant MASTERRESET_CLEARLEARNANDUPGRADECODES. */
	public static final byte MASTERRESET_CLEARLEARNANDUPGRADECODES = 0x03; // The
																			// MasterReset
																			// command
																			// parameter:
																			// 3
																			// clear
																			// learned
																			// codes
																			// and
																			// upgrade
																			// codes

	/** The Constant BLASTER_CMD_SENDKEY. */
	public static final byte BLASTER_CMD_SENDKEY = 0x01; // The SendKey command
															// code

	/** The Constant BLASTER_CMD_GETKEYMAP. */
	public static final byte BLASTER_CMD_GETKEYMAP = 0x02; // The GetKeyMap
															// command code

	/** The Constant BLASTER_CMD_GETNEXTDEVICE. */
	public static final byte BLASTER_CMD_GETNEXTDEVICE = 0x03; // The
																// GetNextDevice
																// command code

	/** The Constant BLASTER_CMD_GETPREVDEVICE. */
	public static final byte BLASTER_CMD_GETPREVDEVICE = 0x04; // The
																// GetPreviousDevice
																// command code

	/** The Constant BLASTER_CMD_LEANRANDSAVE. */
	public static final byte BLASTER_CMD_LEANRANDSAVE = 0x05; // The
																// LearnAndSave
																// command code

	/** The Constant BLASTER_CMD_DELETELEARNEDCODE. */
	public static final byte BLASTER_CMD_DELETELEARNEDCODE = 0x06; // The
																	// DeleteLearnedCode
																	// command
																	// code

	/** The Constant BLASTER_CMD_MASTERRESET. */
	public static final byte BLASTER_CMD_MASTERRESET = 0x09; // The MasterReset
																// command code

	/** The Constant BLASTER_CMD_GETFIRSTDEVICE. */
	public static final byte BLASTER_CMD_GETFIRSTDEVICE = 0x0a; // The
																// GetFirstDevice
																// command code

	/** The Constant BLASTER_CMD_GETVERSION. */
	public static final byte BLASTER_CMD_GETVERSION = 0x0b; // The GetVersion
															// command code

	/** The Constant BLASTER_CMD_DELETEDOWNLOADCODE. */
	public static final byte BLASTER_CMD_DELETEDOWNLOADCODE = 0x0c;// The
																	// DeleteDownloadCode
																	// command
																	// code

	/** The Constant BLASTER_CMD_SENDIRPATTERN. */
	public static final byte BLASTER_CMD_SENDIRPATTERN = 0x10; // The
																// SendIrPattern
																// command code

	/** The Constant BLASTER_CMD_LISTALLUPGRADECODES. */
	public static final byte BLASTER_CMD_LISTALLUPGRADECODES = 0x11;// The
																	// ListAllUpgradeCodes
																	// command
																	// code

	/** The Constant BLASTER_CMD_LEARNANDUPLOAD. */
	public static final byte BLASTER_CMD_LEARNANDUPLOAD = 0x12; // The
																// LearnAndUpload
																// command code

	/** The Constant BLASTER_CMD_DOWNLOADDEVICETOFDRA. */
	public static final byte BLASTER_CMD_DOWNLOADDEVICETOFDRA = 0x22; // The
																		// DownloadDeviceToFDRA
																		// command
																		// code

}
