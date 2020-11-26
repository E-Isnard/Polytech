#ifndef _SIMPLE_MENU_ITEM_H_
#define _SIMPLE_MENU_ITEM_H_
#include "menu_item.hpp"
typedef void (*foncPtr)();

class Simple_Menu_Item : public Menu_Item
{
    foncPtr _action;

public:
    Simple_Menu_Item(string titre, foncPtr action) : Menu_Item(titre), _action(action)
    {
    }

    void execute()
    {
        _action();
    }
};

#endif