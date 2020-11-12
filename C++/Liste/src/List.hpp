//=======================================================================
// Basic C++: a simple (and uncomplete) class List of int's
//      Specification
//-----------------------------------------------------------------------
// Jean-Paul Rigault --- Copyright 2003
// $Id: List.h,v 1.2 2004/11/01 15:19:04 jpr Exp $
//=======================================================================

#ifndef _LIST_H_
#define _LIST_H_

#include <iostream>
#include <cassert>
using namespace std;

#include "common_defs.hpp"

class List
{
private:
    // The list Cell type

    class Cell
    {
    public:
        int val;
        Cell *next;
        Cell(int v, Cell *n = 0) : val(v), next(n) {}
    };

    //
    Cell *head; // Pointer to first cell (element)
    Cell *tail; // Pointer to last cell (element)

public:
    // Construction

    List() : head(0), tail(0) {}

    // Accessor

    bool is_empty() const { return head == 0; }

    // Adding elements to the list

    void append(int); // at the end

    friend ostream &operator<<(ostream &os, List l);
    void prepend(int);
    void insert(int);
    int get_first();
    List mirror();
};

#endif
