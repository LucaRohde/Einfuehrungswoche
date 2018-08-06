package util;

import java.util.Date;

public class Data {

	// Diese Variablen dem PASSWORD zuweisen

	private static String gründungsJahrDpma = "1877";

	private static int raumnummerUndTelefonKurzwahl = 2121 + 3242;

	private static String meinGeburtstag = "16.04";

	private static String wortPaterNoster = "Paternoster";

	private static String kürzelMeinesAusbildungsberufes = "FISI";

	private static String anzahlDerStifte = "14";

	private static String preisMeinesEssens = "6,30";

	private static String zahlAusPapierschnipsel ="42";

	// hier wird das Passwort bestimmt
	public final static String PASSWORD = gründungsJahrDpma + raumnummerUndTelefonKurzwahl + meinGeburtstag
			+ wortPaterNoster + kürzelMeinesAusbildungsberufes + anzahlDerStifte + preisMeinesEssens+zahlAusPapierschnipsel;

}
