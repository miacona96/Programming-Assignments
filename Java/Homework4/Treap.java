import java.util.ArrayList;
import java.util.Stack;
import java.util.Collections;
import java.util.Random;

public class Treap<E extends Comparable<E>> {

    private static class Node<E> {

        public Node<E> right;
        public Node<E> left;
        public int priority;
        public E data;

        // data is the data
        // priority is the heap priority

        public Node(E data,int priority){
            this.right = null;
            this.left = null;
            this.priority = priority;
            this.data = data;
        }

        // Makes node rotate to the right
        void rotateRight(){
            if(left==null) return;
            Node<E> leftNode = left;
            this.left = leftNode.right;
            leftNode.right = this;
        }

        // makes node rotate to the left
        void rotateLeft(){
            if(right == null) return;
            Node<E> rightNode = right;
            this.right = rightNode.left;
            rightNode.left = this;
        }

        // Returns true if the node is a leaf

        boolean isLeaf(){

            return right == null && left == null;
        }

        public String toString(){
            return "(key=" + data + ", priority=" + priority + ")";
        }
    }
    private Node<E> root;
    private Random priorityGenerator;

    public Treap(){
        priorityGenerator = new Random();
    }
    /**@param seed to generate the random priority
     */
    public Treap(long seed) {
        priorityGenerator = new Random(seed);
    }
    /**Helper heaps the treap after the node is added
     * @param path from theadded node to the root
     */
    private void reheap(ArrayList<Node<E>> path) {
        Collections.reverse(path);
        for(int i=0; i < path.size() - 1; i++) {
            Node<E> current = path.get(i);
            Node<E> next = path.get(i+1);

            if(current.priority > next.priority) {
                if(next.left != null && next.left.data.compareTo(current.data) == 0){
                    if(next.data.compareTo(root.data) == 0 ) root = current;

                    next.rotateRight();
                    path.set(i, next);
                    path.set(i + 1, current);

                } else if( next.right != null && next.right.data.compareTo( current.data ) == 0 ) {
                    if( next.data.compareTo(root.data) == 0) root = current;
                    next.rotateLeft();
                    path.set(i, next);
                    path.set(i + 1, current);
                }
                if(i < path.size() - 2){
                    Node<E> parent = path.get(i + 2);

                    if(parent.left != null && parent.left.data.compareTo( next.data ) == 0)
                        parent.left = current;
                    else if(parent.right != null && parent.right.data.compareTo( next.data ) == 0)
                        parent.right = current;
                }}}}

    boolean add(E key){
        return add(key, priorityGenerator.nextInt());
    }

    /**Adds key to the treap
     * @param key key to add
     * @return returns true if successful, else false
     */
    boolean add(E key, int priority) {
        if(key == null) return false;

        Node<E> currentNode = root;
        Node<E> newNode = new Node<>(key, priority);
        ArrayList<Node<E>> path = new ArrayList<>();

        while(true){
            if(currentNode == null) {
                root = newNode;
                break;
            } else if( currentNode.data.compareTo( newNode.data) == 0)
                return false;
            else if(currentNode.data.compareTo(newNode.data) > 0) {
                path.add( currentNode );
                if( currentNode.left == null) {
                    currentNode.left = newNode;
                    path.add( newNode );
                    break;
                } else {
                    currentNode = currentNode.left;
                }
            } else if(currentNode.data.compareTo(newNode.data) < 0) {
                path.add(currentNode);

                if(currentNode.right == null) {
                    currentNode.right = newNode;
                    path.add( newNode );

                    break;
                } else {
                    currentNode = currentNode.right;
                }}}
        reheap(path);
        return true;
    }

    /**Deletes a key from the treap
     * @param key to delete
     * @return true if the key is deleted, if the key is not found: false
     */
    boolean delete(E key) {
        Node<E> NodeDelete = root;
        Node<E> NodeParent = null;

        while(NodeDelete == null || NodeDelete.data.compareTo(key) != 0){
            NodeParent = NodeDelete;
            if(NodeDelete == null ) return false;
            else if(NodeDelete.data.compareTo(key) > 0)
                NodeDelete = NodeDelete.left;
            else if(NodeDelete.data.compareTo(key) < 0)
                NodeDelete = NodeDelete.right;
        }

        while(!NodeDelete.isLeaf()) {
            if(NodeDelete.left ==null || (NodeDelete.right != null && NodeDelete.left.priority < NodeDelete.right.priority)) {
                if(NodeParent==null) root = NodeDelete.right;
                else{
                    if(NodeParent.right != null && NodeParent.right.data.compareTo(NodeDelete.data) == 0) NodeParent.right = NodeDelete.right;
                    else if(NodeParent.left != null && NodeParent.left.data.compareTo(NodeDelete.data) == 0) NodeParent.left = NodeDelete.right;
                }
                NodeParent = NodeDelete.right;
                NodeDelete.rotateLeft();
            } else if(NodeDelete.right == null || (NodeDelete.left != null && NodeDelete.left.priority > NodeDelete.right.priority)) {
                if(NodeParent == null) root = NodeDelete.left;
                else {
                    if(NodeParent.right != null && NodeParent.right.data.compareTo(NodeDelete.data ) == 0) NodeParent.right = NodeDelete.left;
                    else if(NodeParent.left != null && NodeParent.left.data.compareTo(NodeDelete.data ) == 0) NodeParent.left = NodeDelete.left;
                }
                NodeParent = NodeDelete.left;
                NodeDelete.rotateRight();
            }
        }
        if(NodeParent.right != null && NodeParent.right.data.compareTo(NodeDelete.data) == 0)
            NodeParent.right = null;
        else if(NodeParent.left != null && NodeParent.left.data.compareTo(NodeDelete.data) == 0)
            NodeParent.left = null;
        return true;
    }

    /** Returns the node for the given key
     * @param the key to search for
     * @return return the node node, otherwise return null
     */
    private E find(Node<E> root, E key) {
        Node<E> NodeCurrent = root;

        if(NodeCurrent == null) return null;
        else if(NodeCurrent.data.compareTo(key )==0) return NodeCurrent.data;
        else if(NodeCurrent.data.compareTo(key) < 0) return find(NodeCurrent.right,key);
        else return find(NodeCurrent.left,key);
    }
    E find(E key){
        return find(root,key);
    }

    /**Helper for the toString function*/
    private void preOrderTraversal(Node<E> node, int depth, StringBuilder builder) {
        for (int i = 1;i < depth; i++) {
            buulder.append(" ");
        }
        if (node==null) {
            builder.append("null\n");
        } else {
            preOrderTraverse(node.right, depth + 1, builder);
            preOrderTraverse(node.left, depth + 1, builder);
            builder.append(node.toString());
            builder.append("\n");
        }
    }
    /**Returns the string of the treap
     * @return returns the string
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        preOrderTraverse(root, 1, builder);
        return builder.toString();
    }
}

