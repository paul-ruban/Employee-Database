//BINARY SEARCH TREE HELPS TO SORT RECORDS BY SIN NUMBER
public class BST{
    class Node {
        Data data;
        Node left, right;
        Node (){}
        Node(Data data){
            this.data = data;
            this.left = this.right = null;
        }
    }
    private Node root;
    public BST(){
        root = null;
    }
    //ADDS A NODE TO A TREE
    public Node add(Node node, Data data){
        if (node == null){
            node = new Node(data);
            return node;
        }
        if (data.getSIN() < node.data.getSIN())
            node.left = add(node.left, data);
        else
            node.right = add(node.right, data);
        return node;
    }
    //PRINTS TREE INORDER
    public void inOrder(Node node){
        if (node != null){
            inOrder(node.left);
            System.out.println(node.data.toString());
            inOrder(node.right);
        }
    }
    //RETURNS ROOT
    public Node root(){
        return root;
    }
    //SETS ROOT
    public void setRoot(Node node){
        root = node;
    }
    //SEARCHES A NODE BY SIN VALUE
    public Node search(Node root, int sin){
        if (root == null)
            return null;
        else if (sin > root.data.getSIN())
            return search(root.right, sin);
        else if (sin < root.data.getSIN())
            return search(root.left, sin);
        else
            return root;
    }
    //EXTRACTS DATA FROM A NODE
    public Data getData(Node node){
        return (node == null) ? null : node.data;
    }
    //DELETES NODE FROM BST
    public void deleteNode(BST tree, Data data)
    {
        tree.setRoot(delete(tree.root(), data));
    }
    //METHOD TO HELP DELETE
    public Node delete(Node node, Data data){

        if (node == null)
            return node;
        else if (data.getSIN() < node.data.getSIN())
            node.left = delete(node.left, data);
        else if (node.data.getSIN() < data.getSIN())
            node.right = delete(node.right, data);
        else{
            if (node.left == null)
                return node.right;
            else if (node.right == null)
                return node.left;
            node.data = min(node.right);
            node.right = delete(node.right, data);
        }
        return node;
    }
    //FINDS MINIMUM INORDER VALUE OF THE NODE
    public Data min(Node node) {
        return (node.left == null) ? node.data : min(node.left);
    }
}
