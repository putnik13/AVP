package com.atanor.hdconnect.client.json;

import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.json.client.JsonWriter;

import com.atanor.hdconnect.client.Snapshot;
import com.google.gwt.core.shared.GWT;

public class JsonConverters {

	public interface SnapshotReader extends JsonReader<Snapshot> {}
	public static final SnapshotReader SNAPSHOT_READER = GWT.create(SnapshotReader.class);

	public interface SnapshotWriter extends JsonWriter<Snapshot> {}
	public static final SnapshotWriter SNAPSHOT_WRITER = GWT.create(SnapshotWriter.class);
	
}
