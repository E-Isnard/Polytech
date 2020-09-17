#include <iostream>
#include "Rats.h"
using namespace std;
int main(int argc, char const *argv[])
{
    // Rat p = 3;
    // cout << p << endl;
    // Rat q(4, 6);
    // cout << q << endl;
    // Rat s = p * q;
    // cout << s << endl;
    // Rat t;
    // cin >> t;
    // cout << t << endl;
    // Rat a(2, 3);
    // Rat b(1, 2);
    // cout << a+b << endl;
    // Rat c(1, 2);
    // cout << -c << endl;
    // cout << c - c << endl;
    try
    {
        Rat q(-4, 2);
        q--;
        cout << q << endl;
        q.inv();
    }
    catch (const char *err)
    {
        cout << err << endl;
    }
    return 0;
}
