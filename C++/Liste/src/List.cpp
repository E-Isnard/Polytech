//=======================================================================
// Basic C++: a simple (and uncomplete) class List of int's
//      Implementation
//-----------------------------------------------------------------------
// Jean-Paul Rigault --- Copyright 2003
// $Id: List.cpp,v 1.2 2004/11/01 15:19:04 jpr Exp $
//=======================================================================

#include "List.hpp"

//-----------------------------------------------------------------------
// Adding elements to the list
//-----------------------------------------------------------------------

// add element to the end
void List::append(int v)
{
    Cell *p = new Cell(v);
    if (is_empty())
        head = tail = p;
    else
    {
        tail->next = p;
        tail = p;
    }
}

void List::prepend(int v)
{
    Cell *p = new Cell(v, head);
    if (is_empty())
        head = tail = p;
    else
    {
        head = p;
    }
}

int List::get_first()
{
    int v = head->val;
    Cell *p = head;
    head = head->next;
    delete p;
    return v;
}

void List::insert(int v)
{
    if (is_empty())
        append(v);
    else
    {
        Cell *p = head;
        Cell *r = NULL;
        while (p->next != NULL && p->val < v)
        {
            r = p;
            p = p->next;
        }
        if (p->val > v)
        {
            if (r == NULL)
                prepend(v);
            else
            {
                Cell *q = new Cell(v, p);
                r->next = q;
            }
        }
        else
        {
            Cell *q = new Cell(v, p->next);
            p->next = q;
        }
    }
}

List List::mirror()
{
    List l;
    Cell *p = head;
    while (p != NULL)
    {
        l.prepend(p->val);
        p = p->next;
    }
    *this = l;
    return l;
}

ostream &operator<<(ostream &os, List l)
{
    List::Cell *p = l.head;
    while (p != NULL)
    {
        os << p->val << " ";
        p = p->next;
    }
    return os;
}
