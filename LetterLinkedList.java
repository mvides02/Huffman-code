/**
 * @author Miguel Angel Vides Urzúa  A01632891
 * LetterLinkedList
 * Recibe un archivo .txt y genera una lista ligada de nodos que contiene los caracteres presentes en el archivo y la frecuencia con que aparecen
 * 
 * Creado: 26 de julio del 2020
 * Se implementa la clase de forma una poco más brusca y solo realiza lectura de un archivo específico
 * 
 * Actualización 1: 27 de julio del 2020
 * Se optimiza la eficiencia en la generación de la lista y se logra obtener un archivo como input del usuario
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LetterLinkedList {
	public NodeL first;
	public NodeL last;
	public int size;
	private String text;
	
/**
 * Constructor default que aparte genera la lista de nodos
 */
	public LetterLinkedList() {
		this.text = "";
		this.leerTexto();
		this.generateList(this.text);
	}

/**
 * Genera la lista ligada, agregando nodos para cada caracter nuevo y aumentando la frecuencia de los caracteres que ya tenian un nodo
 * @param text	contenido en forma de String del archivo .txt
 */
	public void generateList(String text) {
		if(text.length() < 1) {
			return;
		}
		else {
			if(this.contains(text.charAt(0))){
				this.addFreq(text.charAt(0));
			}
			else {
				this.addLast(text.charAt(0));
			}
		}
		this.generateList(text.substring(1));
	}
	
/**
 * Busca en la lista a ver si ya existe un nodo con el caracter designado
 * @param letter	caracter a buscar dentro de los nodos
 * @return			true si hay un nodo con ese caracter
 */
	public boolean contains(char letter) {
		NodeL tmp = this.first;
		while(tmp != null) {
			if(tmp.getLetter() == letter) {
				return true;
			}
			else {
				tmp = tmp.getNext();
			}
		}
		return false;
	}
	
/**
 * Aumenta por 1 la frecuencia del nodo de un caracter designado
 * @param letter	caracter del nodo a aumentar en frecuencia
 */
	public void addFreq(char letter) {
		NodeL tmp = this.first;
		while(tmp != null) {
			if(tmp.getLetter() == letter) {
				tmp.setFreq(tmp.getFreq()+1);
				break;
			}
			else {
				tmp = tmp.getNext();
			}
		}
	}
	
/**
 * Agrega un nodo a la lista en la última posición
 * @param letter	caracter del nuevo nodo
 */
	public void addLast(char letter) {
		if(this.size == 0) {
			this.addFirst(letter);
		}
		else {
			NodeL nuevo = new NodeL(letter);
			this.last.setNext(nuevo);
			this.last = nuevo;
			this.size++;
		}
	}
	
/**
 * Agrega un nodo a la lista en la primera posición
 * @param letter	caracter del nuevo nodo
 */
	public void addFirst(char letter) {
		this.first = new NodeL(letter);
		if(this.size == 0) {
			this.last = this.first;
		}
		this.size++;
	}
	
/**
 * Recibe el path y el nombre del archivo del cual realizaremos la lectura y guarda el contenido del archivo como un String en el atributo text
 */
	public void leerTexto() {
		//Obtener el input del path y el nombre del archivo del cual realizaremos el código Huffman
		Scanner inScanner = new Scanner(System.in);
		inScanner.useDelimiter("\r\n");
		System.out.print("Enter input file path and name:");
		String inFile = inScanner.next();
		System.out.println("You entered: " + inFile);           
		try {
			FileInputStream fs = new FileInputStream(inFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Leemos el archivo y guardamos su contenido en el atributo text
		String nombreArchivo = inFile;
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(nombreArchivo);
			bufferedReader = new BufferedReader(fileReader);
			StringBuilder stringBuilder = new StringBuilder("");
			String texto="";
			while ((texto = bufferedReader.readLine()) != null) {	
				stringBuilder.append(texto + "\n");
			}
			this.text = stringBuilder.toString();
		}
		catch (IOException ex) {
				System.out.println("error al leer archivo " + ex.getMessage());
		} 
	}

/**
 * Imprime la lista de nodos dando el caracter y la frecuencia (implementado para debuggear)
 */
	public void imprime() {
		NodeL tmp = this.first;
		while(tmp != null) {
			System.out.println(tmp.getLetter()+": "+tmp.getFreq());
			tmp = tmp.getNext();
		}
	}
	
	public static void main(String[] args) {
		LetterLinkedList letras = new LetterLinkedList();
		//System.out.println(letras.M.getFreq());
		//letras.leerTexto();
		System.out.println(letras.text);
		letras.imprime();
		System.out.println(letras.first.getLetter());
		System.out.println(letras.last.getLetter());
		System.out.println(letras.size);
	}
}

class NodeL {
	private char letter;
	private int freq;
	private NodeL next;
	
	public NodeL(char letter) {
		this.letter = letter;
		this.freq = 1;
		this.next = null;
	}

	public char getLetter() {
		return letter;
	}

	public void setLetter(char letter) {
		this.letter = letter;
	}

	public int getFreq() {
		return freq;
	}

	public void setFreq(int freq) {
		this.freq = freq;
	}

	public NodeL getNext() {
		return next;
	}

	public void setNext(NodeL next) {
		this.next = next;
	}
}