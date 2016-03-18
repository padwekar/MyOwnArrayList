package healthsignz.com.myownarraylist;

/**
 * Created by devuser on 14-03-2016.
 */
public class Node {

    int data ;
    Node prev , next ;


    public Node(){
        prev = null ;
        next = null  ;
        data = 0 ;
    }


    public Node(int data , Node prev , Node next){
        this.data = data ;
        this.prev = prev ;
        this.next = next  ;
    }


    public void setNext(Node node){
        next = node ;
    }

    public Node getNext(){
        return next;
    }


    public void setPrev(Node node){
        prev = node ;
    }

    public Node getPrev(){
        return prev;
    }

    public void setData(int data){
        this.data = data ;
    }

    public int getData(){
        return data ;
    }
}
