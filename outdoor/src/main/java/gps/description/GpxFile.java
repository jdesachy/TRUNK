package gps.description;

public class GpxFile {

	private Infos metadata;
	private Datas trk;

	public Infos getMetadata() {
		return metadata;
	}

	public void setMetadata(Infos metadata) {
		this.metadata = metadata;
	}

	public Datas getTrk() {
		return trk;
	}

	public void setTrk(Datas trk) {
		this.trk = trk;
	}
}
