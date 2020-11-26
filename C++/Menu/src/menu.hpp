#ifndef _MENU_H_
#define _MENU_H
#include <vector>
#include "simple_menu_item.hpp"
class Menu
{
    string _titre;
    vector<Menu_Item *> _items;

public:
    Menu(string titre="",vector<Menu_Item *> items=vector<Menu_Item *>()) : _titre(titre),_items(items)
    {
    }

    void activate()
    {
        for (size_t i = 0; i < _items.size(); i++)
        {
            cout << i << "- " << *(_items.at(i)) << endl;
        }
        int choix;
        cin >> choix;
        _items.at(choix)->execute();
    }
};
#endif