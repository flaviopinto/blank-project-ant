package br.com.blank.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.text.ParseException;

import javax.swing.text.MaskFormatter;

public class Util {

	public static String formataCpf(String cpf) throws ParseException {
		MaskFormatter mf = new MaskFormatter("###.###.###-##");
		mf.setValueContainsLiteralCharacters(false);
		return mf.valueToString(cpf);
	}
	
	public static String formataCnpj(String cnpj) throws ParseException {
		MaskFormatter mf = new MaskFormatter("##.###.###/####-##");
		mf.setValueContainsLiteralCharacters(false);
		return mf.valueToString(cnpj);
	}
	
	public static String formataTelefone(String telefone) throws ParseException {
		MaskFormatter mf = new MaskFormatter("(##) ####-####");
		mf.setValueContainsLiteralCharacters(false);
		return mf.valueToString(telefone);
	}
	
	public static String formataCep(String cep) throws ParseException {
		MaskFormatter mf = new MaskFormatter("#####-###");
		mf.setValueContainsLiteralCharacters(false);
		return mf.valueToString(cep);
	}

	public static String normalize(String s) {
		s = Normalizer.normalize(s, Normalizer.Form.NFD);
		s = s.replaceAll("[^\\p{ASCII}]", "");

		return s;
	}
	
	public static String setMD5Password(String password) {
		String senhaCriptografada = "";
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			LogUtil.logError(e.getMessage());
		}

		BigInteger hash = new BigInteger(1, md.digest(password.getBytes()));
		senhaCriptografada = hash.toString(16);
		return senhaCriptografada;
	}
}
