package com.tentinet.healthy.exception;

public class BLEException extends Exception {
	
	/**
	 * 
	 * @author wutianlin
	 * @version 1.0
	 * @date 2014-4-16
	 */
	private static final long serialVersionUID = 9119543544891044501L;

	public BLEException(){};
	
	public BLEException(String exception){
		super(exception);
	} 
	
}
