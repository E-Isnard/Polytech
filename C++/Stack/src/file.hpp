#ifndef _FILE_H_
#define _FILE_H

class Queue : protected List
{

public:
    using List::is_empty;
    void put(int v)
    {
        prepend(v);
    }

    int get()
    {
        return get_first();
    }

    friend ostream &operator<<(ostream &os, Queue q);
};

ostream &operator<<(ostream &os, Queue q)
{
    List l = static_cast<List>(q);
    os << l;
    return os;
}

#endif
