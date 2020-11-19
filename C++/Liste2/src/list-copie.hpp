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
    
    struct Cell
    {
        int val;
        Cell *next;
        Cell(int v, Cell *n = 0) : val(v), next(n) {}
    };

    // 
    Cell *head;         // Pointer to first cell
    Cell *tail;         // Pointer to last cell

public:
    // Exception

    class Empty {};

    // Construction

    List() : head(0), tail(0) {}

    List(List &l);

    // Accessor ?
    
    bool is_empty() const {return head == 0;}

    // Adding elements to the list
    
    void append(int);   // at the end
    void prepend(int);  // at the beginning
    void insert(int); // at the right place in a sorted list

    // Removing elements from the list
    
    int get_first();

    // Display
    
    friend ostream& operator<<(ostream&, const List&);

    // misc
    void mirror(); // reverse the elements of a list

    List &operator=(const List &);

    void operator~();

    int get_middle_value();

    unsigned int size() const;
};

#endif
