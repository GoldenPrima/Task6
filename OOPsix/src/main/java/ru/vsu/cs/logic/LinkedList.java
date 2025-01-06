package ru.vsu.cs.logic;

//import java.util.Collection;
//import java.util.Iterator;
//import java.util.List;
//import java.util.ListIterator;

public class LinkedList<T> implements List<T> {
    private int size = 0;
    private Node<T> head;
    private Node<T> tail;
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        Node<T> cur = tail;
        while (cur != null)  {
            if (cur.item.equals(o)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    @Override
    public boolean containsAll(List<T> c) {
        for (int i = 0; i < c.size(); i++) {
            if (!contains(c.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object[] toArray() {
        Object[] list = new Object[size];
        Node<T> cur = tail;
        int count = 0;
        while (cur != null)  {
            list[count] = cur.item;
            cur = cur.next;
        }
        return list;
    }

//    @Override
//    public boolean add(T t) {
//        if (t == null) {
//            throw new RuntimeException("Ошибка вставки");
//        }
//        if (isEmpty()) {
//            tail = new Node<T>(null, t,  null);
//            head = tail;
//        } else {
//            head.next = new Node<T>(head, t,  null);
//            head = head.next;
//        }
//        size++;
//        return true;
//    }

    @Override
    public boolean remove(Object o) {
        remove(indexOf(o));
        return true;
    }

    @Override
    public T removeFirst() {
        return remove(0);
    }

    @Override
    public T removeLast() {
        return remove(size - 1);
    }

    @Override
    public List<T> reversed() {
        List<T> list = new LinkedList<>();
        Node<T> cur = head;
        while (cur != null){
            list.addLast(cur.item);
            cur = cur.prev;
        }
        return list;
    }

//    @Override
//    public boolean containsAll(Collection<?> c) {
//        for (Object el: c) {
//            if (!contains(el)) {
//                return false;
//            }
//        }
//        return true;
//    }

//    @Override
//    public boolean addAll(Collection<? extends T> c) {
//        for (Object el: c) {
//            head.next = new Node<>(head, (T) el, null);
//            head = head.next;
//        }
//        size+=c.size();
//        return true;
//    }
//
//    @Override
//    public boolean addAll(int index, Collection<? extends T> c) {
//        if (index >= size) {
//            throw new RuntimeException("Выход за индекс");
//        }
//        Node<T> cur = tail;
//        int count = 0;
//        while (count < index) {
//            cur = cur.next;
//        }
//        Node<T> step = cur.next;
//        for (Object el: c) {
//            cur.next = new Node<>(cur, (T) el, null);
//            cur = cur.next;
//        }
//        cur.next = step;
//        if (step != null) {
//            step.prev = cur;
//        }
//        size+=c.size();
//        return true;
//    }

    @Override
    public void clear() {
        size = 0;
        head = null;
        tail = null;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            throw new RuntimeException("Выход за индекс");
        }
        Node<T> cur = tail;
        int count = 0;
        while (count < index) {
            cur = cur.next;
            count++;
        }
        return cur.item;
    }

    @Override
    public T getFirst() {
        return tail.item;
    }

    @Override
    public T getLast() {
        return head.item;
    }

    @Override
    public void set(int index, T element) {
        if (index >= size) {
            throw new RuntimeException("Выход за индекс");
        }
        Node<T> cur = tail;
        int count = 0;
        while (count < index) {
            cur = cur.next;
            count++;
        }
        cur.item = element;
    }

    @Override
    public void add(int index, T element) {
        if (index > size) {
            throw new RuntimeException("Выход за индекс");
        }
        if (isEmpty()) {
            tail = new Node<T>(null, element, null);
            head = tail;
        } else if (index == size){
            addLast(element);
        } else {
            Node<T> cur = tail;
            int count = 0;
            while (count < index) {
                cur = cur.next;
                count++;
            }
            Node<T> prev = cur.prev;
            Node<T> next = cur;
            Node<T> newNode = new Node<>(prev, element, next);
            if (prev != null) {
                prev.next = newNode;
            } else {
                tail = newNode;
            }
            next.prev = newNode;
        }
        size++;
    }

    @Override
    public boolean addAll(int index, List<T> c) {
        if (index > size || index < 0) {
            throw new RuntimeException("Выход за индекс");
        }
        for (int i = c.size() - 1; i >= 0; i--) {
            add(index, c.get(i));
        }
        return true;
    }

    @Override
    public void addFirst(T e) {
        if (size == 0) {
            tail = new Node<>(null, e, null);
            head = tail;
        } else {
            Node<T> node = new Node<>(null, e, tail);
            tail.prev = node;
            tail = node;
        }
        size++;
    }

    @Override
    public void addLast(T e) {
        if (size == 0) {
            tail = new Node<>(null, e, null);
            head = tail;
        } else {
            Node<T> node = new Node<>(head, e, null);
            head.next = node;
            head = node;
        }
        size++;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new RuntimeException("Выход за индекс");
        }
        if (size == 1) {
            T el = tail.item;
            tail = null;
            head = null;
            size--;
            return el;
        }
        Node<T> cur = tail;
        int count = 0;
        while (count < index) {
            cur = cur.next;
            count++;
        }
        T el = cur.item;
        Node<T> prev = cur.prev;
        Node<T> next = cur.next;
        if (prev != null) {
            prev.next = next;
        } else {
            tail = next;
        }
        if (next != null) {
            next.prev = prev;
        } else {
            head = head.prev;
        }
        size--;
        return el;
    }

    @Override
    public int indexOf(Object o) {
        int count = 0;
        Node<T> cur = tail;
        while (cur != null)  {
            if (cur.item.equals(o)) {
                return count;
            }
            cur = cur.next;
            count++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int count = 0;
        Node<T> cur = head;
        while (cur != null)  {
            if (cur.item.equals(o)) {
                return count;
            }
            cur = cur.prev;
            count++;
        }
        return -1;
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Node<T> cur = tail;
        while (cur != null) {
            stringBuilder.append(cur.item);
            stringBuilder.append(" ");
            cur = cur.next;
        }
        return stringBuilder.toString();
    }
}
