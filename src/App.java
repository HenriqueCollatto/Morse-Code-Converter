
public class App {
    public static void main(String[] args) throws Exception {
        MorseTree morse = new MorseTree();
        //System.out.println("Árvore: ");
        //morse.GeraDOT();

        String encoded = morse.encode("exercicio bonus de algoritmos e estruturas de dados i");
        System.out.println("\nTexto em morse: \n" + encoded );
        System.out.println("\nTexto descodificado: ");
        System.out.println(morse.decode(encoded));
        System.out.println();


    }
}
