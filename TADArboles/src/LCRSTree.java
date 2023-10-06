import material.Position;

import java.util.Iterator;


/**
 * An implementation of the NAryTree interface using left-child, right-sibling representation.
 *
 * @param <E> the type of elements stored in the tree
 */
public class LCRSTree<E> implements NAryTree<E> {

    private LCRSnode<E> root;
    private int size;

    private class LCRSnode<T> implements Position<T> {
        private T element;
        private LCRSnode<T> parent;
        private LCRSnode<T> leftChild;
        private LCRSnode<T> rightSibling;



        /**
         * A constructor that allows you to set all the attributes of the nodes.
         *
         * @param element      the element to store at this position
         * @param parent       the parent of the new node
         * @param leftChild    the left child of the new node
         * @param rightSibling the right sibling of the new node
         */

        public LCRSnode(T element, LCRSnode<T> parent, LCRSnode<T> leftChild, LCRSnode<T> rightSibling) {
            this.element = element;
            this.parent = parent;
            this.leftChild = leftChild;
            this.rightSibling = rightSibling;
        }

        public LCRSnode(T element){
            this(element, null, null, null);
        }

        public LCRSnode(T element, LCRSnode<T> parent){
            this(element, parent, null, null);
        }

        @Override
        public T getElement() {
            return element;
        }

        public LCRSnode<T> getParent() {
            return parent;
        }

        public LCRSnode<T> getLeftChild() {
            return leftChild;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public void setParent(LCRSnode<T> parent) {
            this.parent = parent;
        }

        public void setLeftChild(LCRSnode<T> leftChild) {
            this.leftChild = leftChild;
        }

        public void setRightSibling(LCRSnode<T> rightSibling) {
            this.rightSibling = rightSibling;
        }

        public LCRSnode<T> getRightSibling() {
            return rightSibling;
        }
    }

    @Override
    public Position<E> addRoot(E e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Position<E> add(E element, Position<E> p) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Position<E> add(E element, Position<E> p, int n) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void swapElements(Position<E> p1, Position<E> p2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public E replace(Position<E> p, E e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void remove(Position<E> p) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public NAryTree<E> subTree(Position<E> v) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void attach(Position<E> p, NAryTree<E> t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Position<E> root() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Position<E> parent(Position<E> v) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterable<? extends Position<E>> children(Position<E> v) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isInternal(Position<E> v) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isLeaf(Position<E> v) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isRoot(Position<E> v) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterator<Position<E>> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int size() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
