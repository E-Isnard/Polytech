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
    Rat q(-1, 3);
    cout << q.inv() << endl;
    Rat r(1, 2);
    cout << q / r << endl;
    // cout << q.inv() << endl;

    return 0;
}
