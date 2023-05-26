package com.pack.randomm;

public class Carte {
	int[] serialNumber = new int[500];

	void remplir(int[] serial) {
		for (int i = 1; i < serial.length; i++) {
			System.out.println("i:= " + i);
			if (i % 2 == 0) {
				System.out.println("1 cas"+" "+(i%2) );
				serial[i] = i * 222;
				System.out.println(serial[i]);
			} else {
				System.out.println("2 cas"+" "+(i%2) );
				serial[i] = i * 333*serial.length;
				System.out.println(serial[i]);

			}
		}
	}

	public static void main(String[] args) {
		Carte c = new Carte();
		c.remplir(c.serialNumber);
		for (int k : c.serialNumber) {
			System.out.println(k);
		}
	}
}
