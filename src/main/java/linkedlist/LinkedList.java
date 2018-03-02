package linkedlist;

public class LinkedList<DataType>
{
    private LinkedNode<DataType>    front_element = null;
    private LinkedNode<DataType>    back_element = null;


    private int                     size = 0;

    public LinkedNode<DataType>  getElementAtIndex(int index)
    {
        int i = 0;
        for (LinkedNode<DataType> it = this.front_element; it != null; it = it.next)
        {
            if ( i == index)
            {
                return it;
            }
            ++i;
        }
        return null;
    }

    public LinkedNode<DataType>  getElementWithValue(DataType valueToSearch)
    {
        for (LinkedNode<DataType> it = this.front_element; it != null; it = it.next)
        {
            if ( it.data.equals(valueToSearch))
            {
                return it;
            }
        }
        return null;

    };
    public LinkedNode<DataType>  getFront(){ return this.front_element; };
    public LinkedNode<DataType>  getBack(){ return this.back_element; };

    private void        addElementToEmptyList(DataType data)
    {
        LinkedNode<DataType>    new_element = new LinkedNode<DataType>(data);

        front_element = new_element;
        back_element = new_element;
    }

    public void        pushFront(DataType data)
    {
        if (this.front_element != null)
        {
            LinkedNode<DataType>    new_element = new LinkedNode<DataType>(data);

            new_element.setNextElement(this.front_element);
            this.front_element.setPreviousElement(new_element);
            this.front_element = new_element;
        }
        else addElementToEmptyList(data);
    }

    public void        pushBack(DataType data)
    {
        if (this.front_element != null)
        {
            LinkedNode<DataType>    new_element = new LinkedNode<DataType>(data);

            new_element.setPreviousElement(this.back_element);
            this.back_element.setNextElement(new_element);
            this.back_element = new_element;
        }
        else addElementToEmptyList(data);
    }

    public void        insertElementAtIndex(DataType data, int index)
    {
        LinkedNode<DataType>    current = this.getElementAtIndex(index);
        LinkedNode<DataType>    new_element = new LinkedNode<DataType>(data);

        if (current != null)
        {
            LinkedNode<DataType>  current_previous = current.previous;
            if (current_previous != null)
                current_previous.linkAsPreviousElementOf(new_element);
            current.linkAsNextElementOf(new_element);
        }
    }

    private void        linkElementsTogether(LinkedNode<DataType> previous_node, LinkedNode<DataType> next_node)
    {
        if (previous_node != null)
            previous_node.linkAsPreviousElementOf(next_node);
        if (next_node != null)
            next_node.linkAsNextElementOf(previous_node);
    }
    public void        deleteElementAtIndex(int index)
    {
        LinkedNode<DataType>    target_element = this.getElementAtIndex(index);

        if (target_element != null)
        {
            LinkedNode<DataType> target_previous = target_element.previous;
            LinkedNode<DataType> target_next = target_element.next;
            linkElementsTogether(target_previous, target_next);
        }
    }

    private void       removeNode(LinkedNode<DataType> to_remove)
    {
        LinkedNode<DataType>        to_remove_previous = to_remove.previous;
        LinkedNode<DataType>        to_remove_next = to_remove.next;

        linkElementsTogether(to_remove_previous, to_remove_next);
    }

    public int        deleteElementsWithValue(DataType data)
    {
        LinkedNode<DataType> to_remove;
        int           nbOfElementsRemoved = 0;

        while ((to_remove = getElementWithValue(data)) != null)
        {
            removeNode(to_remove);
            ++nbOfElementsRemoved;
        }
        return nbOfElementsRemoved;
    }

    private void        swapNodeWithNext(LinkedNode<DataType> left, LinkedNode<DataType> right)
    {
        if (left == null || right == null)
            return;

        LinkedNode<DataType>    left_previous = left.previous;
        LinkedNode<DataType>    right_next = right.next;

        linkElementsTogether(left_previous, right);
        linkElementsTogether(left, right_next);
        linkElementsTogether(right, left);
    }

    public void        sortList()
    {
        for (LinkedNode<DataType> i = this.front_element; i != null; i = i.next)
        {
            LinkedNode<DataType> next_node = i.next;
            swapNodeWithNext(i, next_node);
        }
    }
    public int         getSize()
    {
        int     size = 0;
        for (LinkedNode<DataType> i = this.front_element; i != null; i = i.next)
        {
            ++size;
        }
        return size;
    }

    public void         printSelf()
    {
        int index = 0;
        for (LinkedNode<DataType> i = this.front_element; i != null; i = i.next)
        {
            System.out.println("Elem ["+index+"] = "+i.data);
            ++index;
        }
    }


    public LinkedList()
    {
    }
}
