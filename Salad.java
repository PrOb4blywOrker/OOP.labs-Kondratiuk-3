import java.util.*;

public  class Salad implements List<Vegetable> {
    private Node<Vegetable> head;
    private Node<Vegetable> curr;
    private int size = 0;

    public Salad() {
        this.head = null;
        this.curr = null;
    }

    public Salad(Vegetable vegetable) {
        this.add(vegetable);
    }

    public Salad(Vegetable... _ingredients){
       this.addAll(Arrays.asList(_ingredients));
    }

    public Node<Vegetable> getHead() {
        return this.head;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(Object o) {
        Node<Vegetable> it = head;
        Vegetable v = (Vegetable)o;
        while(it != null) {
            if (it.val.equals(v))
                return true;
            it = it.next;
        }
        return false;
    }

    @Override
    public Iterator<Vegetable> iterator() {
        return new VegetableIterator(this);
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[this.size];
        for (int i=0; i < this.size; i++)
            arr[i] = get(i);
        return arr;
    }

    @Override
    public <Vegetable> Vegetable[] toArray(Vegetable[] ts) {
        for (int i=0; i < this.size; i++)
            ts[i] = (Vegetable) this.get(i);
        return ts;
    }

    @Override
    public boolean add(Vegetable vegetable) {
        if (this.head == null) {
            head = new Node<Vegetable>();
            head.val = vegetable;
            curr = head;
        } else {
            Node<Vegetable> v = new Node<>();
            curr.next = v;
            v.prev = curr;
            v.val = vegetable;
            curr = v;
        }
        this.size ++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node<Vegetable> it = head;
        Vegetable v = (Vegetable)o;
        while(it != null) {
            if (it.val.equals(v)) {
                Vegetable vegetable;
                if (this.size == 1) {
                    vegetable = this.head.val;
                    this.head = null;
                    this.curr = null;
                    this.size--;

                    return true;
                }

                if (it.equals(this.curr)) {
                    vegetable = this.curr.val;
                    curr = curr.prev;
                    curr.next = null;
                    this.size--;

                    return true;
                }

                if (it.equals(this.head)) {
                    vegetable = this.head.val;
                    head = head.next;
                    head.prev = null;
                    this.size--;

                    return true;
                }

                it.next.prev = it.prev;
                it.prev.next = it.next;
                this.size--;

                return true;
            }
            it = it.next;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object v:collection) {
            if (!contains(v)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Vegetable> collection) {
        for(Vegetable v: collection) {
            this.add(v);
        }
        return true;
    }

    @Override
    public boolean addAll(int i, Collection<? extends Vegetable> collection) {
        int it = i;
        if (i < 0 || i > this.size)
            throw new IndexOutOfBoundsException();
        if (i == this.size) {
            this.addAll(collection);
            return true;
        }

        for(Vegetable v : collection) {
            this.add(it,v);
            it++;
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        for (Object v:collection) {
            this.remove(v);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        Node<Vegetable> it = head;
        while(it != null) {
            if (!collection.contains(it.val)) {
                remove(it.val);
            }
            it = it.next;
        }

        return true;
    }

    @Override
    public void clear() {
        this.head = null;
        this.curr = null;
        this.size = 0;
    }

    @Override
    public Vegetable get(int i) {
        if (i < 0 || i >= this.size)
            throw new IndexOutOfBoundsException();
        Node<Vegetable> el = this.head;
        for (int it = 0; it < i; it++) {
            el = el.next;
        }
        return el.val;
    }

    @Override
    public Vegetable set(int i, Vegetable vegetable) {
        if (i < 0 || i > this.size)
            throw new IndexOutOfBoundsException();
        Node<Vegetable> el = head;
        for (int it = 0; it < i ; it++) {
            el = el.next;
        }
        el.val = vegetable;
        return vegetable;
    }

    @Override
    public void add(int i, Vegetable vegetable) {
        if (i < 0 || i > this.size)
            throw new IndexOutOfBoundsException();
        if (i == this.size)
            this.add(vegetable);
        if (i == 0) {
            Node<Vegetable> vegetableNode = new Node<>();
            vegetableNode.prev = null;
            vegetableNode.val = vegetable;
            vegetableNode.next = head;
            head = vegetableNode;
        }
        Node<Vegetable> el = head;
        for (int it = 0; it < i ; it++) {
            el = el.next;
        }
        Node<Vegetable> vegetableNode = new Node<>();
        vegetableNode.prev = el.prev;
        vegetableNode.val = vegetable;
        vegetableNode.next = el;
        this.size++;
    }

    @Override
    public Vegetable remove(int i) {
        if (i < 0 || i > this.size)
            throw new IndexOutOfBoundsException();
        Vegetable vegetable;
        if (this.size == 1) {
            vegetable = this.head.val;
            this.head = null;
            this.curr = null;
            this.size--;

            return vegetable;
        }

        if (i == this.size) {
            vegetable = this.curr.val;
            curr = curr.prev;
            curr.next = null;
            this.size--;

            return vegetable;
        }

        if (i == 0) {
            vegetable = this.head.val;
            head = head.next;
            head.prev = null;
            this.size--;

            return vegetable;
        }
        Node<Vegetable> el = head;
        for (int it = 0; it < i ; it++) {
            el = el.next;
        }
        vegetable = el.val;
        el.next.prev = el.prev;
        el.prev.next = el.next;
        this.size--;

        return vegetable;
    }

    @Override
    public int indexOf(Object o) {
        Node<Vegetable> el = head;
        Vegetable v = (Vegetable) o;
        for (int i = 0; i < this.size ; i++) {
            if (v.equals(el.val))
                return i;
            el = el.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        Node<Vegetable> el = curr;
        Vegetable v = (Vegetable) o;
        for (int i = this.size -1; i >= 0 ; i--) {
            if (v.equals(el.val))
                return i;
            el = el.prev;
        }
        return -1;
    }

    @Override
    public ListIterator<Vegetable> listIterator() {
        return new VegetableIterator(this);
    }

    @Override
    public ListIterator<Vegetable> listIterator(int i) {
        return null;
    }

    @Override
    public List<Vegetable> subList(int i, int i1) {
        if (i < 0 || i > this.size)
            throw new IndexOutOfBoundsException();
        if (i1 < 0 || i1 > this.size)
            throw new IndexOutOfBoundsException();

        List<Vegetable> list = new ArrayList<>();

        for (int it = i; it < i1; it++) {
            list.add(this.get(it));
        }

        return list;
    }
}

class VegetableIterator implements Iterator<Vegetable>, ListIterator<Vegetable> {
    private Node<Vegetable> current;
    private int index = 0;
    private Salad list;

    // initialize pointer to head of the list for iteration
    public VegetableIterator(Salad list)
    {
        current = (Node<Vegetable>) list.getHead();
        this.list = list;
    }

    public VegetableIterator(Salad list, int i) {
        Node<Vegetable> el = list.getHead();
        for(int it = 0; it < i; it++) {
            el = el.next;
        }
        this.list = list;
        current = el;
    }

    // returns false if next element does not exist
    public boolean hasNext()
    {
        return current != null;
    }

    // return current data and update pointer
    public Vegetable next()
    {
        Vegetable data = current.getData();
        current = current.getNext();
        index++;
        return data;
    }

    @Override
    public boolean hasPrevious() {
        return current.prev != null;
    }

    @Override
    public Vegetable previous() {
        if (current != null) {
            index --;
            current = current.prev;
            return current.val;
        }
        else
            return null;
    }

    @Override
    public int nextIndex() {
        return index+1;
    }

    @Override
    public int previousIndex() {
        return index-1;
    }

    // implement if needed
    public void remove()
    {
        list.remove(index);
    }

    @Override
    public void set(Vegetable vegetable) {

        list.set(index,vegetable);
    }

    @Override
    public void add(Vegetable vegetable) {
        list.add(index,vegetable);
    }


}

class Node<T> {
    T val;
    Node<T> next;
    Node<T> prev;

    Node() {
        val = null;
        next = null;
        prev = null;
    }

    Node(T item) {
        val = item;
    }

    public T getData() {
        return val;
    }

    public Node<T> getNext() {
        return next;
    }
}