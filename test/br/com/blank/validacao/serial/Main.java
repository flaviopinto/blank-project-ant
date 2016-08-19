package br.com.blank.validacao.serial;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<Serial> seriais = new ArrayList<Serial>();
		Serial serial = new Serial();
		serial.setNome("novo nome");
		seriais.add(serial);
		
		serial = new Serial();
		serial.setNome("outro nome");
		seriais.add(serial);
		
		serial = new Serial();
		serial.setNome("mais um nome");
		seriais.add(serial);
		
		try {
			FileOutputStream fo = new FileOutputStream("seriais1.ser");
			ObjectOutputStream oo = new ObjectOutputStream(fo);
			oo.writeObject(seriais); // serializo objeto cat
			oo.close();
			System.out.println("Class Cat – object serializado com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// desserializo o objeto
		try {
			FileInputStream fi = new FileInputStream("seriais1.ser");
			ObjectInputStream oi = new ObjectInputStream(fi);
			seriais = (List<Serial>) oi.readObject();
			oi.close();
			System.out.println("agora ele foi des-serializado com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
