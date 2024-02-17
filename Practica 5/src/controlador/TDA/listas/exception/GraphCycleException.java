package controlador.TDA.listas.exception;

public class GraphCycleException extends Exception{
    public GraphCycleException(){
        super("Graph has a negative cycle!");
    }

    public GraphCycleException(String msg){
        super(msg);
    }

}
