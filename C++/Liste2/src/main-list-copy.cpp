//=======================================================================
// Basic C++: a simple (and uncomplete) class List of int's
//      Utilization
//-----------------------------------------------------------------------
// Jean-Paul Rigault --- Copyright 2003
// $Id: main_List.cpp,v 1.4 2004/11/20 17:02:51 jpr Exp $
//=======================================================================

#include "list-copie.hpp"

int sum(List l)
{
	int s=0;

	while ( !l.is_empty() ) s += l.get_first();
	return s;
}

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
    OUT(l1); OUT(l2);

    l1.prepend(99);
    l2.append(99);
    OUT(l1); OUT(l2);

    OUT(l1.get_first()); OUT(l1);
    OUT(l2.get_first()); OUT(l2);

    while (!l1.is_empty()) OUT(l1.get_first());
    OUT(l1);

    MSG(List copy);

	l1.append(1); l1.append(2); l1.append(3);
	// 1 First uncomment these lines... it does NOT work ! Why ?
    l1 = l2;
    OUT(l1);
    OUT(l2);
    l2.append(73);
    l1.append(74);
    OUT(l1); OUT(l2);

    
	// 2 Then uncomment these lines... it does NOT work ! Why ?
    List l3;
    l3.append(1);
    l3.append(2);
    l3.append(3);
    OUT(l3);
    OUT(sum(l3));
    OUT(l3);
    OUT(sum(l3)); // should seg fault...
    OUT(l3);
    l3.append(22);
    OUT(l3);

	// TO DO
	
	// List Destructor

	OUT(l1); OUT( l1.get_middle_value() );
	OUT(l2); OUT( l2.get_middle_value() );
    OUT(l1);
    OUT(l2);

    cout << "End of program" << endl;

    return 0;
}
