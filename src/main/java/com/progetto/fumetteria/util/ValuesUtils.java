/*

* ValuesUtils: classe con valori costanti
*

* version 1.0

*

* 01/08/2015

*

* ©iTrio

*/
package com.progetto.fumetteria.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ValuesUtils {

	public final static String CAMPO_SENHA_PADRAO = "123";

	public static final String MENSAGEM_ERRO_VALIDACAO_FORMULARIO = "Opção Inválida";

	public final static String ORDENACAO_ASCENDENTE = "ASC";
	public final static String ORDENACAO_DESCENDENTE = "DESC";

	public final static String PROJECT_FILES_URL = "/files";

	public final static String URL_UPLOAD_FILES = "C:\\Users\\Elisa Antolli\\Desktop\\fumetteria\\src\\main\\webapp\\files";

	public final static String encrypt(String password) {
		MessageDigest algorithm;
		try {
			algorithm = MessageDigest.getInstance("SHA-256");
			byte messageDigest[] = algorithm.digest(password.getBytes("UTF-8"));
			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
				hexString.append(String.format("%02X", 0xFF & b));
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

}