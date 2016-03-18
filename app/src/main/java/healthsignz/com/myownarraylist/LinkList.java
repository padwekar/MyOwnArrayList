package healthsignz.com.myownarraylist;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.LinkedList;

/**
 * Created by devuser on 11-03-2016.
 */
public class LinkList extends LinkedList {
    protected static String resultentLinklist ;
    Node start ,end ;
    int size ;

    public LinkList(){
        start = null ;
        end  = null ;
        size = 0 ;
    }


    public boolean isEmpty(){
        return start==null;

    }

    public int getSize(){
        return size ;
    }

    public void insertAtFirst(int value){
        Node node = new Node(value,null,null);

        if(start==null){

            start=node ;
            end =start ;
        }else{

            start.setPrev(node);
            node.setNext(start);
            start = node ;
        }
        size++;
    }

    public void insertAtEnd(int value){

        Node node = new Node(value,null,null);

        if(start==null){
            start=node ;
            end =start ;
        }else{
            end.setNext(node);
            node.setPrev(end);
            end = node;
        }
        size++;
    }

    public void insertAtPos(int value, int pos){
        Node node = new Node(value,null,null);
        if(pos==0){
            insertAtFirst(value);
            return ;
        }


        Node ptr = start;

        for(int i=1 ;i <= size ; i++){
            if(i==pos){

                /*Node temp = ptr.getNext();
                ptr.setNext(node);
                node.setPrev(ptr);
                node.setNext(temp);
                temp.setPrev(node);*/

                Node temp =ptr.getNext();
                ptr.next = node ;
                node.prev =ptr;
                node.next = temp;
                temp.prev = node ;
            }
            ptr = ptr.getNext();
        }
        size++;
    }

    public void  deleteAtEnd(){
        if(size==1) {
            start = null;
            end = null;
            size = 0;
            return;
        }
        if(!isEmpty()){
            end = end.getPrev();
            end.next = null;
            size--;
        }
    }

    public void deleteAtFirst(){

            if(size==1) {
                start = null;
                end = null;
                size = 0;
                return;
            }
            start = start.getNext();
            start.prev = null;
             size--;
    }

    public boolean posExcedsSize(int pos){
        if(pos==0)
            return false;
        else
             return pos>=(size);
    }
    public void deleteAtPos(int pos){

        if(pos==0){
           deleteAtFirst();
            return;
        }

        if(pos==size-1){
            deleteAtEnd();
            return;
        }

        Node ptr = start;
        for(int i=0 ; i< size ; i++){
            if(i==pos){

                Node p = ptr.getPrev();
                Node n = ptr.getNext();

                p.next = n ;
                n.prev = p ;
                size--;
                return ;

            }
            ptr = ptr.getNext();
        }
    }

    public void display(){



        if(isEmpty()){
            resultentLinklist ="";
            return ;
        }


        if(start.next==null){
            resultentLinklist =start.getData()+"<-->";
            return;
        }


        Node ptr = start;
        resultentLinklist = start.getData() + "<-->";
         ptr = start.getNext();
        while(ptr.next!=null){
            resultentLinklist =resultentLinklist + ptr.getData()+"<-->" ;
            ptr = ptr.getNext();
        }
        resultentLinklist = resultentLinklist + ptr.getData();
    }


}
