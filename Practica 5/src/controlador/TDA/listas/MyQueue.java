package controlador.TDA.listas;



public class MyQueue<E> {
    private Nodo<E> head;
    private int length;

    public MyQueue(){
        this.head = null;
    }

    public void offer(E data){

        length++;

        if(isEmpty()){
            head = new Nodo<>(data);
            return;
        }

        Nodo<E> tmp = this.head;

        while (tmp.getNext() != null) tmp = tmp.getNext();

        tmp.setNext(new Nodo<>(data));

    }

    public E peek(){
        return head.getData();
    }

    public E poll(){

        length--;

        if(isEmpty()) return null;

        var tmp = head;

        head = head.getNext();

        return tmp.getData();

    }
    
    public void print(){
        
        var tmp = head;
        System.out.println("Queue");
        while(tmp != null){
            System.out.println(tmp.getData());
            tmp = tmp.getNext();
        }
        
    }

    public int size(){
        return length;
    }

    public boolean isEmpty(){
        return head == null;
    }
}