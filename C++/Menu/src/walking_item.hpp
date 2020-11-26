#ifndef _WALKING_ITEM_H_
#define _WALKING_ITEM_H_
#include "menu_item.hpp"
#include <vector>
class Walking_Menu : public Menu_Item, public Menu
{

public:
    Walking_Menu(string nom, vector<Menu_Item *> actions) : Menu_Item(nom+" ->"),Menu("",actions)
    {
    }

    void execute()
    {
        activate();
    }
};
#endif