package healthsignz.com.myownarraylist;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.AbstractList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

/**
 * Created by devuser on 11-03-2016.
 */
public class DataList {

    Object[] arraystart = null ;             // Null Array
    Object[] array = new Object[0];          //Array of object of size zero

    DataList(){
        arraystart = new Object[10];
    }

    //-------------------------------Extra Methods
    //-----------------------------LastIndex
    private int getLastIndex(){
        int i=0,emptyIndex=0;
        for(i=0; i<arraystart.length ; i++)
            if(arraystart[i]==null)
            {  emptyIndex=i;
                break;}
            else {
                emptyIndex = arraystart.length;
            }
        return emptyIndex;
    }

    //------------------------------IndexIsValid


    private boolean isValid(int index){
        if (index < 0 || index >= getLastIndex())
            throw new IndexOutOfBoundsException("Invalid index " + index );
        else
            return true;
    }

    //-------------------------------add()
    void add(Object object){
        int i;
        int emptyIndex = getLastIndex();


        if(emptyIndex==arraystart.length-1) {
            Object[] tempArray = new Object[arraystart.length + 1];
            for(i=0;i<arraystart.length ;i++)
                tempArray[i]=arraystart[i];
                tempArray[arraystart.length] = object ;
                arraystart = new Object[tempArray.length];
                arraystart = tempArray ;
        }else {
                arraystart[emptyIndex]=object;
        }
    }

    //-------------------------------size()
    int size(){
        return getLastIndex();
    }



    //-------------------------------isEmpty()
    boolean isEmpty(){
        if(arraystart[0]==null)
            return true;
        else
            return false;
    }

    //-------------------------------contains(Object o)
    public boolean contains(Object object){
        if(isEmpty())
            return false;
        else
            for (int i=0 ; i< getLastIndex(); i++)
            {
                if(arraystart[i]==object)
                    return true;
            }

        return false;
    }

    //-------------------------------indexOf(Object o)
    public int indexOf(Object object){
        if(isEmpty())
            return -1;
        else
            for (int i=0 ; i< getLastIndex(); i++)
            {
                if(arraystart[i]==object)
                    return i;
            }

        return -1;
    }


    //-----------------------------lastIndexOf
    public int lastIndexOf(Object object){
        if(isEmpty())
            return -1;
        else
            for (int i=getLastIndex() ; i> -1; i--)
            {
                if(arraystart[i]==object)
                    return i;
            }

        return -1;
    }



    //-------------------------get(int index)
    public Object get(int index){
        if(!isValid(index))
            return null;
        else
            return arraystart[index];
    }



    //---------------------set(int index,E element)
    public void set(int index, Object object){
        if(!isValid(index))
            return ;
        else
            arraystart[index]=object;
    }


    //------------------------remove(int index)
    public void remove(int index){
        if(!isValid(index))
            return ;
        else {
            for (int i = index; i < getLastIndex(); i++) {

                if(i!=getLastIndex()-1)
                arraystart[i] = arraystart[i + 1];
                else
                arraystart[i] = null;

            }

        }
    }

    //-------------------------show()
    public String show(){
        String arrayElements = "{";
        for(int i=0;i<getLastIndex();i++) {
            if (i != getLastIndex() - 1)
                arrayElements = arrayElements + arraystart[i] + ",";
            else
                arrayElements = arrayElements + arraystart[i] + "}";
        }
        return arrayElements;
    }


}
