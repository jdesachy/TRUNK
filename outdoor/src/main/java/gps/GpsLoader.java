package gps;

import gps.description.GpxFile;

public class GpsLoader {

	private final GpxFile file;

	public GpsLoader(GpxFile file) {
		super();
		this.file = file;
	}

	public GpxFile getFile() {
		return file;
	}

}
