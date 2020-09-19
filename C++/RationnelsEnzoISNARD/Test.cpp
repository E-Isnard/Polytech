#include <iostream>
#include "Rats.h"
using namespace std;
int main(int argc, char const *argv[])
{

    try
    {
        Rat a = 3;
        cout << a << endl;
        Rat b(4, 6);
        cout << b << endl;
        Rat c = a * b;
        cout << c << endl;
        Rat d;
        cin >> d;
        cout << d << endl;
        Rat e(2, 3);
        Rat f(1, 2);
        cout << e + f << endl;
        Rat g(1, 2);
        cout << -g << endl;
        cout << g - g << endl;
        Rat h(-4, 2);
        h--;
        cout << h << endl;
        Rat i = 0;
        i.inv();
    }
    catch (const char *err)
    {
        cout << err << endl;
    }
    return 0;
}
