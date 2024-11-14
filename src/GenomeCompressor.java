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
        while (!BinaryStdIn.isEmpty()){
            char nucleotide = BinaryStdIn.readChar();
            switch (nucleotide){
                // Writes A as 00
                case 'A':
                    BinaryStdOut.write(false);
                    BinaryStdOut.write(false);
                    break;
                // Writes C as 01
                case 'C':
                    BinaryStdOut.write(false);
                    BinaryStdOut.write(true);
                    break;
                // Writes G as 10
                case 'G':
                    BinaryStdOut.write(true);
                    BinaryStdOut.write(false);
                    break;
                // Writes T as 11
                case 'T':
                    BinaryStdOut.write(true);
                    BinaryStdOut.write(true);
                    break;
            }
        }
        BinaryStdOut.close();
    }

    /**
     * Reads a binary sequence from standard input; expands and writes the results to standard output.
     */
    public static void expand() {

        // TODO: complete the expand() method

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