# Huffman-code
A project to generate the Huffman Tree to a .txt file. A Huffman Tree provides a way of lossless compression based on the frequency a character appears on a file

Begin with LetterLinkedList class, which receives the .txt file and transforms its contents into a linked list.

MinHeapCreator class receives the linked list and transforms it into a min-heap array.

Huffman finally receives the min-heap array and creates the Huffman Tree, outputting a table giving the characters in the .txt file, their frequency of appearance, and their corresponding Huffman code to generate the Huffman compressed file.
