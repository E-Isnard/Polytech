//=======================================================================
// Basic C++: a simple (and uncomplete) class List of int's
//      Utilization
//-----------------------------------------------------------------------
// Jean-Paul Rigault --- Copyright 2003
// $Id: main_List.cpp,v 1.4 2004/11/20 17:02:51 jpr Exp $
//=======================================================================

#include "List.hpp"

int main()
{
    int i;

    MSG(List operations);

    List l1, l2;

    OUT(l1);
    for (i = 0; i < 10; i++)
    {
        l1.append(i);
        l2.prepend(i);
    }
    OUT(l1);
    OUT(l2);

    l1.prepend(99);
    l2.append(99);
    OUT(l1);
    OUT(l2);

    OUT(l1.get_first()); // Suppress first element and return its value
    OUT(l1);
    OUT(l2.get_first());
    OUT(l2);

    while (!l1.is_empty())
        OUT(l1.get_first());
    
    try{

    l1.get_first();
    }
    catch(List::EmptyListException ele)
    {
        cout << ele.msg << endl;
    }
    OUT(l1);

    List l3; // insert in the right place in a sorted list
    l3.insert(25);
    l3.insert(3);
    l3.insert(30);
    l3.insert(4);
    l3.insert(26);
    l3.insert(15);
    l3.insert(12);
    l3.insert(100);
    l3.insert(20);
    OUT(l3);

    // mirror modifies l : reverse order of elements
    OUT(l2);
    l2.mirror();
    OUT(l2);
    return 0;
}
