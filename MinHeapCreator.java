/**
 * @author Miguel Angel Vides Urzúa  A01632891
 * MinHeapCreator
 * Recibe un LetterLinkedList y crea un min heap en forma de arreglo a partir de los nodos de la lista
 *
 * Creado: 26 de julio del 2020
 * Se implementa de forma un poco brusca para crear un min heap a partir de una lista ligada de nodos
 * 
 * Actualización 1: 27 de julio del 2020
 * Se replantea la implementación para ser más eficiente y acorde a los cambios en LetterLinkedList
 */

public class MinHeapCreator {
	public NodeL[] heap;
	private LetterLinkedList letters;
	
/**
 * Constructor del heap, recibe forzosamente un LetterLinkedList para poder generar un min heap en forma de arreglo
 * @param letters	LetterLinkedList que contiene los nodos con los caracteres y frecuencias de un archivo .txt leído
 */
	public MinHeapCreator(LetterLinkedList letters) {
		this.letters = letters;
		this.heap = new NodeL[this.letters.size];
		this.addHeap();
		this.sort();
	}
	
/**
 * Agrega elementos al arreglo del min heap al recorrer la lista ligada
 */
	public void addHeap() {
		NodeL tmp = this.letters.first;
		for(int i = 0; i < this.heap.length; i++) {
			this.heap[i] = tmp;
			tmp = tmp.getNext();
		}
	}
	
/**
 * Ordena los elementos del mi heap de menor a mayor
 */
	public void sort() {
		int times = this.heap.length;
		while(times > -1) {
			for(int i = 0; i < times-1; i++) {
				if(this.heap[i].getFreq() > this.heap[i+1].getFreq()) {
					NodeL tmp = this.heap[i];
					this.heap[i] = this.heap[i+1];
					this.heap[i+1] = tmp;
				}
			}
			times--;
		}
	}
	
/**
 * Imprime los elementos del min heap, dando su caracter y frecuencia (para propósitos de debuggeo)
 */
	public void imprime() {
		for(int i = 0; i < this.heap.length; i++) {
			System.out.println(this.heap[i].getLetter()+": "+this.heap[i].getFreq());
		}
	}
	
	public static void main(String[] args) {
		LetterLinkedList letters = new LetterLinkedList();
		MinHeapCreator minHeap = new MinHeapCreator(letters);
		minHeap.imprime();
	}
}