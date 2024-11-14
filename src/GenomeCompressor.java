/******************************************************************************
 *  Compilation:  javac GenomeCompressor.java
 *  Execution:    java GenomeCompressor - < input.txt   (compress)
 *  Execution:    java GenomeCompressor + < input.txt   (expand)
 *  Dependencies: BinaryIn.java BinaryOut.java
 *  Data files:   genomeTest.txt
 *                virus.txt
 *
 *  Compress or expand a genomic sequence using a 2-bit code.
 ******************************************************************************/


/**
 *  The {@code GenomeCompressor} class provides static methods for compressing
 *  and expanding a genomic sequence using a 2-bit code.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *  @author Zach Blick
 */
public class GenomeCompressor {

    /**
     * Reads a sequence of 8-bit extended ASCII characters over the alphabet
     * { A, C, T, G } from standard input; compresses and writes the results to standard output.
     */
    public static void compress() {

        String sequence = BinaryStdIn.readString();
        int length = sequence.length();
        BinaryStdOut.write(length);

        int[] map = new int[256];
        map['A'] = 0b00;
        map['C'] = 0b01;
        map['G'] = 0b10;
        map['T'] = 0b11;

        while (!BinaryStdIn.isEmpty()){
            char nucleotide = BinaryStdIn.readChar();
            System.out.println("Compressing nucleotide: " + nucleotide);
            BinaryStdOut.write(map[nucleotide], 2);


//            switch (nucleotide){
//                // Writes A as 00
//                case 'A':
//                    BinaryStdOut.write(false);
//                    BinaryStdOut.write(false);
//                    BinaryStdOut.write(0,2);
//                    break;
//                // Writes C as 01
//                case 'C':
//                    BinaryStdOut.write(false);
//                    BinaryStdOut.write(true);
//                    break;
//                // Writes G as 10
//                case 'G':
//                    BinaryStdOut.write(true);
//                    BinaryStdOut.write(false);
//                    break;
//                // Writes T as 11
//                case 'T':
//                    BinaryStdOut.write(true);
//                    BinaryStdOut.write(true);
//                    break;
//            }
        }
        BinaryStdOut.close();
    }

    /**
     * Reads a binary sequence from standard input; expands and writes the results to standard output.
     */
    public static void expand() {

        int[] map = new int[256];
        map[0b00] = 'A';
        map[0b01] = 'C';
        map[0b10] = 'G';
        map[0b11] = 'T';


        // Reads two bits at a time and decodes each into a nucleotide
        while(!BinaryStdIn.isEmpty()){
            boolean bit1 = BinaryStdIn.readInt(2);
            boolean bit2 = BinaryStdIn.readBoolean();

            if(!bit1 && !bit2){
                BinaryStdOut.write('A');
            }
            else if (!bit1 && bit2) {
                BinaryStdOut.write('C');
            }
            else if (bit1 && !bit2) {
                BinaryStdOut.write('G');
            }
            else if (bit1 && bit2){
                BinaryStdOut.write('T');
            }

            System.out.println("Decoded bits: " + bit1 + " " + bit2);
        }

        BinaryStdOut.close();
    }


    /**
     * Main, when invoked at the command line, calls {@code compress()} if the command-line
     * argument is "-" an {@code expand()} if it is "+".
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

        if      (args[0].equals("-")) compress();
        else if (args[0].equals("+")) expand();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}
