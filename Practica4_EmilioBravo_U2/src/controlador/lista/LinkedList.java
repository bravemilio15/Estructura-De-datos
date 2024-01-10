/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.lista;

import controlador.lista.Exception.IndexListException;
import controlador.lista.Exception.NonExistentElementException;
import controlador.lista.Exception.PositionException;
import controlador.lista.Exception.VacioException;
import java.lang.reflect.Field;
import java.util.Random;

/**
 *
 * @author emilio
 */
public class LinkedList<E> {

    private Nodo<E> head;
    private Nodo<E> last;
    private Integer size;
    private Field field;
    private String typeField;

    public LinkedList() {
        head = null;
        last = null;
        size = 0;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Boolean isEmpty() {
        return head == null || size == 0;
    }

    protected void addFirst(E data) {
        if (isEmpty()) {
            Nodo<E> aux = new Nodo<>(data, null);
            head = aux;
            last = aux;
        } else {
            Nodo<E> headOld = head;
            Nodo<E> aux = new Nodo<>(data, headOld);
            head = aux;
        }
        size++;
    }

    protected void addLast(E data) {
        if (isEmpty()) {
            addFirst(data);
        } else {
            Nodo<E> aux = new Nodo<>(data, null);
            last.setNext(aux);
            last = aux;
            size++;
        }

    }

    public void add(E data) {
        addLast(data);
    }

    public void add(E data, Integer pos) throws VacioException {
        if (pos == 0) {
            addFirst(data);
        } else if (pos.intValue() == size.intValue()) {
            addLast(data);
        } else {
            Nodo<E> search_preview = getNode(pos - 1);
            Nodo<E> search = getNode(pos);
            Nodo<E> aux = new Nodo<>(data, search);
            search_preview.setNext(aux);
            size++;

        }
    }

    public E getFirst() throws VacioException {
        if (isEmpty()) {
            throw new VacioException("Lista Vacia");
        } else {
            return head.getData();
        }
    }

    public E getLast() throws VacioException {
        if (isEmpty()) {
            throw new VacioException("Lista Vacia");
        } else {
            return last.getData();
        }
    }

    public E get(Integer index) throws VacioException {
        if (isEmpty()) {
            throw new VacioException("Lista Vacia");
        } else if (index.intValue() < 0 || index.intValue() >= size) {
            throw new IndexOutOfBoundsException("Fuera de Rango");
        } else if (index.intValue() == 0) {
            return getFirst();
        } else if (index.intValue() == (size - 1)) {
            return getLast();
        } else {
            Nodo<E> search = getNode(index);
            return search.getData();
        }
    }

    public Nodo<E> getNode(Integer pos) throws VacioException {
        if (isEmpty()) {
            throw new VacioException("Lista Esta Vacia ");
        } else if (pos < 0 || pos > size) {
            throw new IndexOutOfBoundsException("Error esta fuera de los limites de la lista");
        } else if (pos == 0) {
            return head;
        } else if (pos == (size - 1)) {
            return last;
        } else {
            Nodo<E> search = head;
            Integer cont = 0;
            while (cont < pos) {
                cont++;
                search = search.getNext();
            }
            return search;
        }
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        if (isEmpty()) {
            sb.append("Lista Vacia");
        } else {
            Nodo<E> aux = head;
            while (aux != null) {
                sb.append(aux.getData().toString()).append("\n");
                aux = aux.getNext();
            }
        }
        return sb.toString();
    }

    public void update(E dato, Integer pos) throws VacioException, PositionException {
        if (isEmpty()) {
            throw new VacioException("Lista Vacia");
        } else {

            if (pos >= 0 && pos < getSize()) {
                if (pos == 0) {
                    head.setData(dato);
                } else {
                    Nodo<E> aux = head;
                    for (int i = 0; i < pos; i++) {
                        aux = aux.getNext();
                    }
                    aux.setData(dato);
                }

            } else {
                throw new PositionException("Posición fuera de rango");
            }
        }
    }

    public void Update2(E data, Integer pos) throws VacioException {
        if (isEmpty()) {
            throw new VacioException("Lista Esta Vacia ");
        } else if (pos < 0 || pos > size) {
            throw new IndexOutOfBoundsException("Error esta fuera de los limites de la lista");
        } else if (pos == 0) {
            head.setData(data);
        } else if (pos == (size - 1)) {
            last.setData(data);
        } else {
            Nodo<E> search = head;
            Integer cont = 0;
            while (cont < pos) {
                cont++;
                search = search.getNext();
            }
            search.setData(data);
        }
    }

    public void clear() {
        head = null;
        size = 0;
        last = null;
    }

    //Hacer Eliminar
    public E deleteFirst() throws VacioException {
        if (isEmpty()) {
            throw new VacioException("List Empty");
        } else {

            E element = head.getData();
            Nodo<E> aux = head.getNext();
            head = aux;
            if (size.intValue() == 1) {
                last = null;
            }
            size--;
            return element;
        }
    }

    public E deleteLast() throws VacioException {
        if (isEmpty()) {
            throw new VacioException("List Empty");
        } else {

            E element = last.getData();
            Nodo<E> aux = getNode(size - 2);
            if (aux == null) {
                last = null;
                if (size == 2) {
                    last = head;
                } else {
                    head = null;
                }
            } else {
                last = null;
                last = aux;
                last.setNext(null);
            }

            size--;
            return element;
        }
    }

    public E delete(Integer pos) throws VacioException {
        if (isEmpty()) {
            throw new VacioException("Lista Esta Vacia ");
        } else if (pos < 0 || pos > size) {
            throw new IndexOutOfBoundsException("Error esta fuera de los limites de la lista");
        } else if (pos == 0) {
            return deleteFirst();
        } else if (pos == (size - 1)) {
            return deleteLast();
        } else {
            Nodo<E> preview = getNode(pos - 1);
            Nodo<E> actually = getNode(pos);
            E element = preview.getData();

            Nodo<E> next = actually.getNext();
            actually = null;
            preview.setNext(next);
            size--;
            return element;
        }

    }

    public E[] toArray() {
        Class clazz = null;
        E[] matriz = null;
        if (this.size > 0) {
            clazz = head.getData().getClass();
            matriz = (E[]) java.lang.reflect.Array.newInstance(clazz, size);
            Nodo<E> aux = head;
            for (int i = 0; i < size; i++) {
                matriz[i] = aux.getData();
                aux = aux.getNext();
            }
        }
        return matriz;
    }

    public LinkedList<E> toList(E[] m) {
        clear();
        for (int i = 0; i < m.length; i++) {
            this.add(m[i]);
        }

        return this;
    }

    private void getAttribute(String attribute) {

        Field[] fields = this.head.getData().getClass().getDeclaredFields();

        for (var fld : fields) {
            if (fld.getName().equals(attribute)) {
                this.field = fld;
            }
        }

        field.setAccessible(true);

        this.typeField = field.getType().getSimpleName();

    }

    private void resetAttribute() {
        field = null;
        typeField = null;
    }

    private Integer compare(E e1, E e2) throws IllegalAccessException {

        if (field == null) {
            System.out.println("Error en Compare Valor Nulo");
            return 0;
        }

        if (typeField.equals("Integer") || typeField.equals("Double")) {

            return Double.compare(((Number) field.get(e1)).doubleValue(), ((Number) field.get(e2)).doubleValue());

        } else {

            return Integer.compare(field.get(e1).toString().compareToIgnoreCase(field.get(e2).toString()), 0);

        }

    }

    private Integer compareS(E e1, Object e2) throws IllegalAccessException {
        if (field == null) {
            System.out.println("Error en CompareS Valor Nulo");
            return 0;
        }

        if (typeField.equals("Integer") || typeField.equals("Double")) {
            return Double.compare(((Number) field.get(e1)).doubleValue(), ((Number) e2).doubleValue());
        } else {
            return Integer.compare(field.get(e1).toString().compareToIgnoreCase(e2.toString()), 0);
        }
    }

    public void shuffle() throws VacioException {

        var tmpArr = this.toArray();

        Random random = new Random();

        for (Integer i = 0; i < tmpArr.length; i++) {

            Integer randomNum = random.nextInt(tmpArr.length - 1);

            var tmp = tmpArr[i];

            tmpArr[i] = tmpArr[randomNum];

            tmpArr[randomNum] = tmp;

        }

        this.toList(tmpArr);

    }

    private Integer partition(E[] arr, Integer low, Integer high, boolean typeSort) throws IllegalAccessException {

        var pivot = arr[high];

        Integer i = (low - 1);

        for (Integer j = low; j < high; j++) {

            if (typeSort ? compare(arr[j], pivot) <= 0 : compare(arr[j], pivot) >= 0) {

                i++;
                var tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;

            }

        }

        var tmp = arr[i + 1];

        arr[i + 1] = arr[high];

        arr[high] = tmp;

        return i + 1;

    }

    private void quick(E[] arr, Integer low, Integer high, boolean typeSort) throws IllegalAccessException {

        if (low < high) {

            Integer pi = partition(arr, low, high, typeSort);

            quick(arr, low, pi - 1, typeSort);

            quick(arr, pi + 1, high, typeSort);

        }

    }

    private void merge(E[] arr, Integer l, Integer m, Integer r, boolean typeSort) throws IllegalAccessException {

        Integer n1 = m - l + 1;
        Integer n2 = r - m;

        E[] L = (E[]) java.lang.reflect.Array.newInstance(this.head.getData().getClass(), n1);

        E[] R = (E[]) java.lang.reflect.Array.newInstance(this.head.getData().getClass(), n2);

        for (Integer i = 0; i < n1; ++i) {
            L[i] = arr[l + i];
        }

        for (Integer j = 0; j < n2; ++j) {
            R[j] = arr[m + 1 + j];
        }

        Integer i = 0, j = 0;

        Integer k = l;

        while (i < n1 && j < n2) {

            if (typeSort ? compare(L[i], R[j]) <= 0 : compare(L[i], R[j]) >= 0) {

                arr[k] = L[i];
                i++;

            } else {

                arr[k] = R[j];
                j++;

            }

            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }

    }

    private void sortMerge(E[] arr, Integer l, Integer r, boolean typeSort) throws IllegalAccessException {

        if (l < r) {

            Integer m = l + (r - l) / 2;

            sortMerge(arr, l, m, typeSort);

            sortMerge(arr, m + 1, r, typeSort);

            merge(arr, l, m, r, typeSort);

        }

    }

    public void mergeSort(String attribute, boolean typeSort) throws VacioException, IllegalAccessException {

        if (isEmpty()) {
            throw new VacioException();
        }

        if (getSize() == 1) {
            return;
        }

        getAttribute(attribute);

        var tmp = this.toArray();

        sortMerge(tmp, 0, (tmp.length - 1), typeSort);

        resetAttribute();

        this.toList(tmp);

    }

    public void quickSort(String attribute, boolean typeSort) throws VacioException, IllegalAccessException {

        if (isEmpty()) {
            throw new VacioException();
        }

        if (getSize() == 1) {
            return;
        }

        getAttribute(attribute);

        var tmp = this.toArray();

        quick(tmp, 0, (tmp.length - 1), typeSort);

        resetAttribute();

        this.toList(tmp);

    }

    private Integer binary(E[] arr, Object element, Integer low, Integer high) throws IllegalAccessException {

        if (high >= low) {

            Integer mid = low + (high - low) / 2;

            //check if mid-element is searched element
            if (compareS(arr[mid], element) == 0) {
                return mid;
            }

            //Search the left half of mid
            if (compareS(arr[mid], element) > 0) {
                return binary(arr, element, low, mid - 1);
            }

            //Search the right half of mid
            return binary(arr, element, mid + 1, high);

        }

        return -1;
    }

    public E binarySearch(String attribute, Object info) throws VacioException, IllegalAccessException, NonExistentElementException, IndexListException {

        if (isEmpty()) {
            throw new VacioException();
        }

        var tmpArray = this.toArray();

        getAttribute(attribute);

        Integer idx = binary(tmpArray, info, 0, tmpArray.length - 1);

        resetAttribute();

        if (idx == -1) {
            System.out.println("Elemento no encontrado en la búsqueda binaria");
            throw new NonExistentElementException();
        }

        return this.get(idx);
    }

    public LinkedList<E> linearSearch(String attribute, Object info) throws VacioException, IllegalAccessException, NonExistentElementException, IndexListException {

        LinkedList<E> result = new LinkedList<>();

        var tmpArray = toArray();

        getAttribute(attribute);

        Integer idx = binary(tmpArray, info, 0, tmpArray.length - 1);

        if (idx == -1) {
            throw new NonExistentElementException();
        }

        Integer tmpIdx = 0;

        for (Integer i = idx; i >= 0; i--) {
            if (compareS(this.get(i), info) != 0) {
                break;
            }
            tmpIdx = i;
        }

        for (Integer i = tmpIdx; i < this.getSize(); i++) {
            if (compareS(this.get(i), info) == 0) {
                result.add(this.get(i));
            } else {
                break;
            }
        }

        resetAttribute();

        return result;
    }

    public LinkedList<E> linearBinarySearch(String attribute, Object element) throws IllegalAccessException {

        if (isEmpty()) {
            throw new NullPointerException();
        }

        getAttribute(attribute);

        var result = new LinkedList<E>();

        var tmp = this.toArray();

        for (int i = 0; i < tmp.length; i++) {

            if (compareS(tmp[i], element) == 0) {
                result.add(tmp[i]);
            }

        }

        return result;

    }

    private Integer indexOf(E element) throws VacioException, IndexListException, IllegalAccessException, NonExistentElementException {
        for (Integer i = 0; i < getSize(); i++) {
            if (compareS(get(i), element) == 0) {
                return i;
            }
        }
        throw new NonExistentElementException(); // Cambiado a NonExistentElementException
    }

}
