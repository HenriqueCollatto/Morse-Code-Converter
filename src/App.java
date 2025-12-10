
public class App {
    public static void main(String[] args) throws Exception {
        MorseTree morse = new MorseTree();
        System.out.println("Árvore: ");
        morse.GeraDOT();

        String msg = "exercicio bonus de algoritmos e estruturas de dados i";
        System.out.println("\nTexto que será codificado: \n" + msg );
        String encoded = morse.encode(msg);
        System.out.println("\nTexto em morse: \n" + encoded );
        System.out.println("\nTexto descodificado: ");
        String decoded = morse.decode(encoded);
        System.out.println(decoded);
        System.out.println();

    }
}
