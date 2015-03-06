/* 
 *
 *
 * Copyright (C) 1999-2013 Universal Electronics Inc.
 *
 * 
 */
package com.uei.driver;

public class BlasterStatusCode {
	 /** The Constant BLASTER_BLASTER_STATUS_SUCCESS. */
	public static final byte BLASTER_BLASTER_STATUS_SUCCESS = 0;			//Completion without error
    
    /** The Constant BLASTER_BLASTER_STATUS_INVALIDDEVICECODE. */
    public static final byte BLASTER_BLASTER_STATUS_INVALIDDEVICECODE = 1;	//Invalid device code
    
    /** The Constant BLASTER_BLASTER_STATUS_INVALIDDEVICETYPE. */
    public static final byte BLASTER_BLASTER_STATUS_INVALIDDEVICETYPE = 2;	//Invalid device type
    
    /** The Constant BLASTER_BLASTER_STATUS_INVALIDKEYCODE. */
    public static final byte BLASTER_BLASTER_STATUS_INVALIDKEYCODE = 3;		//Invalid key code
    
    /** The Constant BLASTER_BLASTER_STATUS_BADFDRA. */
    public static final byte BLASTER_BLASTER_STATUS_BADFDRA = 4;			//Bad FDRA
    
    /** The Constant BLASTER_BLASTER_STATUS_OUTOFMEMORY. */
    public static final byte BLASTER_BLASTER_STATUS_OUTOFMEMORY = 5;		//Out of Memory
    
    /** The Constant BLASTER_BLASTER_STATUS_LEARNINGERRORTIMEOUT. */
    public static final byte BLASTER_BLASTER_STATUS_LEARNINGERRORTIMEOUT = 6;	//Learning error timeout
    
    /** The Constant BLASTER_BLASTER_STATUS_DATAPACKETFORMATERROR. */
    public static final byte BLASTER_BLASTER_STATUS_DATAPACKETFORMATERROR = 7;	//Invalid data package format
    
    /** The Constant BLASTER_BLASTER_STATUS_DOWNLOADCODEALREADYEXIST. */
    public static final byte BLASTER_BLASTER_STATUS_DOWNLOADCODEALREADYEXIST = 8;	// Download code already exist
    
    /** The Constant BLASTER_BLASTER_STATUS_LOWVOLTAGE. */
    public static final byte BLASTER_BLASTER_STATUS_LOWVOLTAGE = 9;				// Low voltage
    
    /** The Constant BLASTER_BLASTER_STATUS_INVALIDLEARNCODEID. */
    public static final byte BLASTER_BLASTER_STATUS_INVALIDLEARNCODEID = 10;	// Invalid learn code Id
      
}
