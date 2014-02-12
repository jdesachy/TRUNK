package front.ski;

import java.util.ArrayList;
import java.util.List;

public enum Massif {

	ARAVIS("Bornes - Aravis"), CHABLAIS("Chablais - Faucigny"), AIGUILLES_ROUGES(
			"Haut Giffre - Aiguilles Rouges"), MONT_BLANC("Mont Blanc"), APLES_G_S(
			"Alpes Grées S"), ALPES_G_N("Alpes Grées N"), VANOISE("Vanoise"), LAUZIERE(
			"Lauzière - Cheval Noir"), BAUGES("Bauges"), BEAUFORTAIN(
			"Beaufortain"), TAILLEFER("Taillefer - Matheysine"), VERCORS(
			"Vercors"), CHARTREUSE("Chartreuse"), BELLEDONNE("Belledonne"), ARVES(
			"Grandes Rousses - Arves"), QUEYRAS("Queyras - Alpes Cozie N"), ECRINS(
			"Ecrins"), DEVOLUY("Devoluy"), THABOR(
			"Cerces - Thabor - Mont Cenis");

	private String name;

	private Massif(String name) {
		this.name = name;
	}

	public String getMassifName() {
		return this.name;
	}

	public static List<String> getList() {
		List<String> list = new ArrayList<String>();
		for (Massif massif : Massif.values()) {
			list.add(massif.getMassifName());
		}

		return list;
	}
}
