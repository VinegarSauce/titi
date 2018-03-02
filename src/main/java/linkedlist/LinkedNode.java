package linkedlist;

public class LinkedNode<DataType> {

    DataType                  data;
    LinkedNode<DataType>      previous;
    LinkedNode<DataType>      next;

    public LinkedNode(DataType data)
    {
        this.data = data;
        this.previous = null;
        this.next = null;
    }

    void    linkAsNextElementOf(LinkedNode<DataType> previous_element)
    {
        this.previous = previous_element;
        if (previous_element != null)
            previous_element.setNextElement(this);
    }

    void    linkAsPreviousElementOf(LinkedNode<DataType> next_element)
    {
        this.next = next_element;
        if (next_element != null)
            next_element.setPreviousElement(this);
    }
    void    setNextElement(LinkedNode<DataType> next_element)
    {
        if (next_element != this)
            this.next = next_element;
    }

    void    setPreviousElement(LinkedNode<DataType> previous_element)
    {
        if (previous_element != this)
            this.previous = previous_element;
    }
}