package utils;

import java.awt.image.BufferedImage;

public class CadastroUtil {

	public static boolean enviarApi(String endpoint) {

		return send(endpoint) == (200);

	}

	public static boolean enviarApi(String endpoint, BufferedImage documento) {

		return send(endpoint) == (200);

	}


	private static int send(String endpoint) {
		return 200;
	}

}
