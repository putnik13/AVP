package com.atanor.vserver.vsadmin.client;

public class RecordData {

	 private static SettingsRecord[] records;  
	  
	    public static SettingsRecord[] getRecords() {  
	        if (records == null) {  
	            records = getNewRecords();  
	        }  
	        return records;  
	    }  
	  
	    public static SettingsRecord[] getNewRecords() {  
	        return new SettingsRecord[]{  
	                new SettingsRecord(1, "Input Stream Params", ":sout=#transcode{vcodec=h264,acodec=mpga,ab=128,channels=2,samplerate=44100}:std{access=file,mux=ts,dst=%s}", "******", true),  
	                new SettingsRecord(2, "Output Params", "D://tmp", null, null),  
	                new SettingsRecord(3, "Path for Output", "C://Program Files//VideoLAN//VLC", null, null),
	                new SettingsRecord(4, "Palantir Url and Port", "192.168.1.80:5050 ", null, null),
	               
	        };  
	    }  
}
