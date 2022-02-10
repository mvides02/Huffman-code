/**
 * @author Miguel Angel Vides Urzúa  A01632891
 * Huffman
 * Recibe un MinHeapCreator y crea un árbol de frecuencias a partir de ello, transformando los NodeL en NodoH y procede a imprimir el árbol dando los caracteres, sus frecuencias y código Huffman de un archivo .txt para su compresión
 *
 * Creado: 26 de julio del 2020
 * Se implementa el funcionamiento correcto del código para obtener el código Huffman de un min heap
 * 
 * Actualización 1: 27 de julio del 2020
 * Se agregan comentarios para dar una mejor comprensión del código de los usuarios
 */
public class Huffman {
	private NodoH[] arbol;
	private MinHeapCreator minHeap;
	
	public Huffman(MinHeapCreator minHeap) {
		this.minHeap = minHeap;
		this.arbol = new NodoH[this.minHeap.heap.length];
		for(int i = 0; i < this.arbol.length; i++) {
			arbol[i] = new NodoH(this.minHeap.heap[i].getLetter(),this.minHeap.heap[i].getFreq());
		}
	}
	
/**
 * Crea un "nodo de frecuencias" con el valor de frecuencias equivalente a la suma de las frecuencias de los hijos 
 * @param a	hijo izquierdo (el de menor frecuencia)
 * @param b	hijo derecho (el de mayor frecuencia)
 * @return	un NodoH con la suma de frecuencias de los hijos (y sin valor importante en caracter)
 */
	public NodoH freqNodo(NodoH a, NodoH b) {
		NodoH parent = new NodoH(a.getFreq()+b.getFreq());
		parent.setLeft(a);
		parent.setRight(b);
		return parent;
	}
	
/**
 * Arma el árbol de frecuencias para poder generar el código Huffman
 */
	public void tree() {
		while(this.arbol.length > 1) {
			NodoH nuevo = freqNodo(this.arbol[0],this.arbol[1]);
			NodoH[] tmp = this.arbol;
			for(int i = 0; i < this.arbol.length-2; i++) {
				tmp[i] = tmp[i+2];
			}
			this.arbol = new NodoH[tmp.length-1];
			for(int i = 0; i < this.arbol.length-1; i++) {
				this.arbol[i] = tmp[i];
			}
			this.arbol[this.arbol.length-1] = nuevo;
			this.sort();
		}
	}
	
/**
 * Ordena el arreglo del árbol, de menor a mayor dependiendo de las frecuencias
 */
	public void sort() {
		int times = this.arbol.length;
		while(times > -1) {
			for(int i = 0; i < times-1; i++) {
				if(this.arbol[i].getFreq() > this.arbol[i+1].getFreq()) {
					NodoH tmp = this.arbol[i];
					this.arbol[i] = this.arbol[i+1];
					this.arbol[i+1] = tmp;
				}
			}
			times--;
		}
	}

/**
 * Imprime las hojas del árbol, dando a conocer el caracter, la frecuencia del caracter, y el código Huffman generado para ese caracter
 * @param nodo	nodo a partir del cual se imprimirán las hojas (finalmente se usará siempre el root)
 * @param str	String que va creando el código Huffman para cada caracter impreso
 */
	public void hojas(NodoH nodo,String str) {
		String camino = str;
		if(nodo == null){
			return;
		}
		if(nodo.getLeft() == null && nodo.getRight() == null) {
			if(nodo.getLetter() == '\n') {
				System.out.println("NxtLine\t  "+nodo.getFreq()+" \t \t "+camino);
			}
			else if(nodo.getLetter() == ' ') {
				System.out.println("SPACE \t  "+nodo.getFreq()+" \t \t "+camino);
			}
			else if(nodo.getLetter() == 'Ã') {
				System.out.println("ñ \t  "+nodo.getFreq()+" \t \t "+camino);
			}
			else{
				System.out.println(nodo.getLetter()+" \t  "+nodo.getFreq()+" \t \t "+camino);
			}
		}
		hojas(nodo.getLeft(),camino+"0");
		hojas(nodo.getRight(),camino+"1");
	}
	
	public static void main(String[] args) {
		LetterLinkedList letters = new LetterLinkedList();
		MinHeapCreator mHeap = new MinHeapCreator(letters);
		Huffman hf = new Huffman(mHeap);
		hf.tree();
		System.out.println("Char\t Frecuencia\t Código");
		hf.hojas(hf.arbol[0],"");
	}
}

class NodoH {
	private char letter;
	private int freq;
	private NodoH left;
	private NodoH right;
	
	public NodoH(char letter, int freq) {
		this.letter = letter;
		this.freq = freq;
		this.left = null;
		this.right = null;
	}
	
	public NodoH(int freq) {
		this.letter = '/';
		this.freq = freq;
		this.left = null;
		this.right = null;
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

	public NodoH getLeft() {
		return left;
	}

	public void setLeft(NodoH left) {
		this.left = left;
	}

	public NodoH getRight() {
		return right;
	}

	public void setRight(NodoH right) {
		this.right = right;
	}
}
