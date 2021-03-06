package gps.description;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("gpx")
public class GpxFile {

	@XStreamAlias("xmlns")
	@XStreamAsAttribute
	private String namespace;

	@XStreamAlias("xmlns:xsi")
	@XStreamAsAttribute
	private String xmlSchema;

	@XStreamAsAttribute
	private final String creator = "http://www.polarpersonaltrainer.com";

	@XStreamAsAttribute
	private final String version = "1.1";

	@XStreamAlias("xsi:schemaLocation")
	@XStreamAsAttribute
	private String schemaLocation;

	private Infos metadata;

	@XStreamAlias("trk")
	private List<Datas> datas = new ArrayList<Datas>();

	public Infos getMetadata() {
		return metadata;
	}

	public void setMetadata(Infos metadata) {
		this.metadata = metadata;
	}

	public void addData(Datas d) {
		datas.add(d);
	}

	public List<Datas> getDatas() {
		return datas;
	}

	public void setDatas(List<Datas> datas) {
		this.datas = datas;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getXmlSchema() {
		return xmlSchema;
	}

	public void setXmlSchema(String xmlSchema) {
		this.xmlSchema = xmlSchema;
	}

	public String getSchemaLocation() {
		return schemaLocation;
	}

	public void setSchemaLocation(String schemaLocation) {
		this.schemaLocation = schemaLocation;
	}
}
