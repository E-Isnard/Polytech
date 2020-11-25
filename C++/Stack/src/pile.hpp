#ifndef _STACK_H_
#define _STACK_H
#include "list-copy.hpp"

class Stack : List
{

public:
    using List::is_empty;
    int pop()
    {
        return get_first();
    }

    void push(int v)
    {
        append(v);
    }

    friend ostream &operator<<(ostream &os, Stack s);
};

ostream &operator<<(ostream &os, Stack s)
{
    List l = static_cast<List>(s);
    os << l;
    return os;
}

#endif