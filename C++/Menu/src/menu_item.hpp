#ifndef _MENU_ITEM_H_
#define _MENU_ITEM_H_
class Menu_Item
{
protected:
    string _nom;

public:
    virtual void execute(){};
    Menu_Item(string nom="") : _nom(nom){};
    friend ostream &operator<<(ostream &os, Menu_Item mi);
};

ostream &operator<<(ostream &os, Menu_Item mi)
{
    os << mi._nom;
    return os;
}

#endif