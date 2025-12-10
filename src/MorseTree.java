import java.util.Stack;

public class MorseTree {

    private static final class Node {

        public Node father;
        public Node left;
        public Node right;
        private char element;

        public Node(char element) {
            father = null;
            left = null;
            right = null;
            this.element = element;
        }
    }

    private Node root; //referência para o nodo raiz

    public MorseTree() {
        root = new Node(' ');
        this.insert('a', ".-");
        this.insert('b', "-...");
        this.insert('c', "-.-.");
        this.insert('d', "-..");
        this.insert('e', ".");
        this.insert('f', "..-.");
        this.insert('g', "--.");
        this.insert('h', "....");
        this.insert('i', "..");
        this.insert('j', ".---");
        this.insert('k', "-.-");
        this.insert('l', ".-..");
        this.insert('m', "--");
        this.insert('n', "-.");
        this.insert('o', "---");
        this.insert('p', ".--.");
        this.insert('q', "--.-");
        this.insert('r', ".-.");
        this.insert('s', "...");
        this.insert('t', "-");
        this.insert('u', "..-");
        this.insert('v', "...-");
        this.insert('w', ".--");
        this.insert('x', "-..-");
        this.insert('y', "-.--");
        this.insert('z', "--..");
    }

    /**
     * Crição da árvore de caracteres para codifição e 
     * descodificação do código morse. 
     * 
     * @param caratcter - caractere a ser codificado na árvore
     * @param caminho   - posição a ser inserido o caractere
     *  
     */ 
    private void insert(char caratcter, String caminho) {
        Node atual = root;
        for (int i = 0; i < caminho.length(); i++) {
            if (caminho.charAt(i) == '.') {
                if (atual.left == null) {
                    atual.left = new Node(' ');
                    atual.left.father = atual;
                }
                atual = atual.left;
            } else {
                if (atual.right == null) {
                    atual.right = new Node(' ');
                    atual.right.father = atual;
                }
                atual = atual.right;
            }
        }
        atual.element = caratcter;
    }

    /**
     * Codifica um texto em código morse. Como o código morse 
     * possui prefixos em comum deve ser colocado um espaço
     * entre cada codificação de caractere. 
     * Caracteres que não estão na árvore são desprezados
     * 
     * Caracteres da mensagem que não estão na árvore devem 
     * ser desprezados
     * 
     * @param texto texto a ser codificado
     * @return código morse correspondente ao texto 
     */
    public String encode(String texto) {
        if(texto == null) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            sb.append(encode(c));
            sb.append(" ");
        }
        String encoded = sb.toString();
        
        return encoded;
    }

    /**
     * Método auxiliar para o método encode
     * 
     * @param caracter - caractere a ser codificado
     * @return  representação em código morse do caractere 
     */
    private String encode(char caracter) {
        Node atual = searchNodeRef(root, caracter);
        if(atual == null) return "";
        Stack<Character> pilha = new Stack<>();

        while(atual.father != null){
            //System.out.print(atual.element);
            Node pai = atual.father;
            if (atual == pai.left) pilha.add('.');
            if (atual == pai.right) pilha.add('-');
            atual = atual.father;
        }
        StringBuilder sb = new StringBuilder();
        while(!pilha.isEmpty()){
            sb.append(pilha.pop());
        }

        String encoded = sb.toString();
        return encoded;
    }

    private Node searchNodeRef(Node n, char c) {
        if (n == null) {
            return null;
        } 
        if (n.element == c) {
                return n;
            } else {
                Node left = searchNodeRef(n.left, c);
                if (left != null) {
                    return left;
                } else {
                    return searchNodeRef(n.right, c);
                }
            }
    }

    /**
     * Codifica um texto em código morse. Como o código morse 
     * possui prefixos em comum deve ser colocado um espaço
     * entre cada codificação de caractere. 
     * 
     * @param encoded texto a ser descodificado
     * @return texto correspondente ao código ecebidco
     */
    public String decode(String encoded) {
        if(encoded == null) return "";
        StringBuilder sb = new StringBuilder();
        String[] letras =  encoded.split(" ");
        for (String letra : letras) {
            sb.append(decode(root, letra));
        }
        String decoded = sb.toString();
        
        return decoded;
    }

    private char decode(Node n, String code) {
        if (code == null) return '\0';
        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);
            if(c == ('.')) n = n.left;
            if(c == ('-')) n = n.right;
        }
        return n.element;
    }   

    // Gera uma saida no formato DOT
    // Esta saida pode ser visualizada no GraphViz
    // Versoes online do GraphViz pode ser encontradas em
    // http://www.webgraphviz.com/
    // http://viz-js.com/
    // https://dreampuf.github.io/GraphvizOnline 
    private void GeraConexoesDOT(Node nodo) {
        if (nodo == null) {
            return;
        }

        GeraConexoesDOT(nodo.left);
        //   "nodeA":esq -> "nodeB" [color="0.650 0.700 0.700"]
        if (nodo.left != null) {
            System.out.println("\"node" + nodo.element + "\":esq -> \"node" + nodo.left.element + "\" " + "\n");
        }

        GeraConexoesDOT(nodo.right);
        //   "nodeA":dir -> "nodeB";
        if (nodo.right != null) {
            System.out.println("\"node" + nodo.element + "\":dir -> \"node" + nodo.right.element + "\" ");
        }

    }

    private void GeraNodosDOT(Node nodo) {
        if (nodo == null) {
            return;
        }
        GeraNodosDOT(nodo.left);
        //node10[label = "<esq> | 10 | <dir> "];
        System.out.println("node" + nodo.element + "[label = \"<esq> | " + nodo.element + " | <dir> \"]");
        GeraNodosDOT(nodo.right);
    }

    public void GeraConexoesDOT() {
        GeraConexoesDOT(root);
    }

    public void GeraNodosDOT() {
        GeraNodosDOT(root);
    }

    public void GeraDOT() {
        System.out.println("digraph g { \nnode [shape = record,height=.1];\n" + "\n");

        GeraNodosDOT();
        System.out.println("");
        GeraConexoesDOT(root);
        System.out.println("}" + "\n");
    }

}
