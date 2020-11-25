#ifndef _FILE_PRIOR_H_
#define _FILE_PRIOR_H_

class Priority_Queue : public Queue
{

public:
    void put(int v)
    {
        insert(v);
    }
};

#endif