//=======================================================================
// Basic C++: a simple (and uncomplete) class List of int's
//      Implementation
//-----------------------------------------------------------------------
// Jean-Paul Rigault --- Copyright 2003
// $Id: List.cpp,v 1.2 2004/11/01 15:19:04 jpr Exp $
//=======================================================================

#include "list-copie.hpp"

List::List(List &l) : head(0), tail(0)
{
    Cell *p = l.head;
    while (p != 0)
    {
        append(p->val);
        p = p->next;
    }
}

//-----------------------------------------------------------------------
// Adding elements to the list
//-----------------------------------------------------------------------

void List::append(int v)
{
    Cell *p = new Cell(v);
    if (head == 0)
        head = tail = p;
    else
    {
        tail->next = p;
        tail = p;
    }
}

// Au debut

void List::prepend(int v)
{
    Cell *p = new Cell(v);
    if (head == 0)
        head = tail = p;
    else
    {
        p->next = head;
        head = p;
    }
}

//-----------------------------------------------------------------------
// Removing elements from the list
//-----------------------------------------------------------------------

int List::get_first()
{
    if (head == 0)
        throw Empty();
    int v = head->val;
    if (tail == head)
        tail = 0;
    Cell *p = head;
    head = head->next;
    p->next = 0; // needed: otherwise the next line destroy the whole list!
    delete p;
    return v;
}

void List::insert(int e)
{
    Cell *p, *pprev;

    for (p = head, pprev = 0; p != 0 && p->val < e; pprev = p, p = p->next)
    {
    }

    if (pprev != 0) // inserting in the middle or at the end
    {
        Cell *ptemp = new Cell(e, pprev->next);
        pprev->next = ptemp;
        if (p == 0)
            tail = ptemp;
    }
    else
        prepend(e); // inserting in front
}

void List::mirror()
{
    Cell *pp;  // cellule précédente
    Cell *p;   // cellule courante
    Cell *tmp; // variable temporaire

    for (pp = 0, p = head; p != 0; pp = p, p = tmp)
    {
        tmp = p->next; // sauvegarde de l'ancienne valeur de p->next
        p->next = pp;
    }

    // il ne reste plus qu'à échanger les valeurs de head et tail :
    tmp = head;
    head = tail;
    tail = tmp;
}
//-----------------------------------------------------------------------
// Display
//-----------------------------------------------------------------------

ostream &operator<<(ostream &os, const List &l)
{
    List::Cell *p;
    for (p = l.head; p != 0; p = p->next)
        cout << p->val << ' ';
    return os;
}

void List::operator~()
{
    while (head != 0)
    {
        get_first();
    }
}

List &List::operator=(const List &l)
{
    Cell *p = l.head;
    ~(*this);
    while (p != 0)
    {
        append(p->val);
        p = p->next;
    }

    return *this;
}

unsigned int List::size() const
{
    int n = 0;
    Cell *p = head;
    while (p != 0)
    {
        n++;
        p = p->next;
    }
    return n;
}

int List::get_middle_value()
{
    if (head == 0)
        throw Empty();
    if (head == tail)
        tail = 0;
    Cell *p = head;
    Cell *q = head;
    Cell *r = 0;
    while (q!=0)
    {
        r = p;
        p = p->next;
        q = q->next;
        if (q != 0)
            q = q->next;
    }

    int v = p->val;
    r->next = p->next;
    delete p;
    return v;
}
