package co.edu.unbosque.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGenerator {
	public static String generarHash(String input) {
		try {
			// Crear una instancia de MessageDigest con el algoritmo SHA-256
			MessageDigest digest = MessageDigest.getInstance("SHA-1");

			// Calcular el hash del input
			byte[] hashBytes = digest.digest(input.getBytes());

			// Convertir el array de bytes a formato hexadecimal
			StringBuilder hexString = new StringBuilder();
			for (byte b : hashBytes) {
				String hex = Integer.toHexString(0xff & b);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}

			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Error: Algoritmo de hash no disponible.", e);
		}

}
	public static boolean verificarHash(String claveIngresada,String hashAlmecenado) {
		return generarHash(claveIngresada).equals(hashAlmecenado);
	}
}