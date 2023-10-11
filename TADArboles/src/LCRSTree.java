import material.Position;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * An implementation of the NAryTree interface using left-child, right-sibling representation.
 *
 * @param <E> the type of elements stored in the tree
 */
public class LCRSTree<E> extends DrawableTree<E> {

    private LCRSnode<E> root;
    private int size;

    private class LCRSnode<T> implements Position<T> {
        private T element;
        private LCRSnode parent, leftChild, rightChild;

        public LCRSnode(T element, LCRSnode parent, LCRSnode leftChild, LCRSnode rightChild) {
            this.element = element;
            this.parent = parent;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        public LCRSnode(T element) {
            this(element, null, null, null);
        }

        public LCRSnode(T element, LCRSnode<T> parent) {
            this(element, parent, null, null);
        }

        @Override
        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public LCRSnode getParent() {
            return parent;
        }

        public void setParent(LCRSnode parent) {
            this.parent = parent;
        }

        public LCRSnode getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(LCRSnode leftChild) {
            this.leftChild = leftChild;
        }

        public LCRSnode getRightChild() {
            return rightChild;
        }

        public void setRightChild(LCRSnode rightChild) {
            this.rightChild = rightChild;
        }

        public String toString() {
            return element.toString();
        }
    }

    @Override
    public Position<E> addRoot(E e) {
        if (!isEmpty())
            throw new RuntimeException("El árbol ya tiene raíz");
        this.root = new LCRSnode<>(e);
        this.size = 1;
        return this.root;
    }

    private LCRSnode<E> checkPosition(Position<E> p) {
        if (!(p instanceof LCRSnode))
            throw new RuntimeException("La posición es inválida");
        return (LCRSnode<E>) p;
    }

    @Override
    public Position<E> add(E element, Position<E> p) {
        LCRSnode<E> parent = checkPosition(p);
        LCRSnode<E> nNode = new LCRSnode<>(element, parent);
        if (parent.getLeftChild() == null)
            parent.setLeftChild(nNode);
        else {
            LCRSnode<E> leftChild = parent.getLeftChild();
            while (leftChild.getRightChild() != null)
                leftChild = leftChild.getRightChild();
            leftChild.setRightChild(nNode);
        }
        this.size++;
        return nNode;
    }

    @Override
    public Position<E> add(E element, Position<E> p, int n) {
        LCRSnode<E> parent = checkPosition(p);
        LCRSnode<E> nNode = new LCRSnode<>(element, parent);
        if (n < 0)
            throw new RuntimeException("La posición es inválida");
        else if (n == 0) {
            nNode.setRightChild(parent.getLeftChild());
            parent.setLeftChild(nNode);
        } else {
            LCRSnode<E> leftChild = parent.getLeftChild();
            int i = 1;
            while (i < n && leftChild.getRightChild() != null) {
                leftChild = leftChild.getRightChild();
                i++;
            }
            nNode.setRightChild(leftChild.getRightChild());
            leftChild.setRightChild(nNode);
        }
        this.size++;
        return nNode;
    }

    @Override
    public void swapElements(Position<E> p1, Position<E> p2) {
        var node1 = checkPosition(p1);
        var node2 = checkPosition(p2);
        E aux = node1.getElement();
        node1.setElement(node2.getElement());
        node2.setElement(aux);
    }

    @Override
    public E replace(Position<E> p, E e) {
        var node = checkPosition(p);
        E old = node.getElement();
        node.setElement(e);
        return old;
    }

    @Override
    public void remove(Position<E> p) {
        LCRSnode<E> node = checkPosition(p);
        if (node == root) {
            root = null;
            size = 0;
        } else {
            LCRSnode<E> parent = node.getParent();
            if (parent.getLeftChild() == node)
                parent.setLeftChild(node.getRightChild());
            else {
                LCRSnode<E> leftChild = parent.getLeftChild();
                while (leftChild.getRightChild() != node)
                    leftChild = leftChild.getRightChild();
                leftChild.setRightChild(node.getRightChild());
            }
            size -= computeSize(node);
        }
    }

    private int computeSize(LCRSnode<E> node) {
        if (node == null)
            return 0;
        else {
            int size = 1;
            LCRSnode<E> child = node.getLeftChild();
            while (child != null) {
                size += computeSize(child);
                child = child.getRightChild();
            }
            return size;
        }
    }

    @Override
    public NAryTree<E> subTree(Position<E> v) {
        var node = checkPosition(v);
        var tree = new LCRSTree<E>();
        tree.root = node;
        tree.size = computeSize(node);
        return tree;
    }

    @Override
    public void attach(Position<E> p, NAryTree<E> t) {
        var node = checkPosition(p);
        var tree = (LCRSTree<E>) t;
        var leftChild = node.getLeftChild();
        if (leftChild == null)
            node.setLeftChild(tree.root);
        else {
            while (leftChild.getRightChild() != null)
                leftChild = leftChild.getRightChild();
            leftChild.setRightChild(tree.root);
        }
        this.size += tree.size;
    }

    @Override
    public boolean isEmpty() {
        return this.root == null;
    }

    @Override
    public Position<E> root() {
        return this.root;
    }

    @Override
    public Position<E> parent(Position<E> v) {
        var node = checkPosition(v);
        return node.getParent();
    }

    @Override
    public Iterable<? extends Position<E>> children(Position<E> v) {
        var node = checkPosition(v);
        var list = new ArrayList<Position<E>>();
        var child = node.getLeftChild();
        while (child != null) {
            list.add(child);
            child = child.getRightChild();
        }
        return list;
    }

    @Override
    public boolean isInternal(Position<E> v) {
        var node = checkPosition(v);
        return node.getLeftChild() != null;
    }

    @Override
    public boolean isLeaf(Position<E> v) {
        var node = checkPosition(v);
        return node.getLeftChild() == null;
    }

    @Override
    public boolean isRoot(Position<E> v) {
        var node = checkPosition(v);
        return node == this.root;
    }

    @Override
    public Iterator<Position<E>> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int size() {
        return this.size;
    }

}