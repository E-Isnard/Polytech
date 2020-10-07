#include <iostream>
using namespace std;
int pgcd(int a, int b)
{
    int t = 0;
    while (b != 0)
    {
        t = b;
        b = a % b;
        a = t;
    }
    return a;
}

class Rat
{

public:
    int num;
    unsigned int denom;

    void simplify()
    {
        int k = pgcd(num, denom);

        while (k != 1)
        {
            if (k < 0)
            {
                k = -k;
            }
            num /= k;
            denom /= k;
            k = pgcd(num, denom);
        }
    }

    Rat()
    {
        num = 0;
        denom = 1;
    }
    Rat(int n)
    {
        num = n;
        denom = 1;
    }
    Rat(int n, unsigned int p)
    {
        if (p == 0)
        {
            throw "Error: Division by 0";
        }
        int k = pgcd(n, p);
        if (k < 0)
        {
            k = -k;
        }

        while (k != 1)
        {
            n /= k;
            p /= k;
            k = pgcd(n, p);
        }
        num = n;
        denom = p;
    }

    Rat operator*(Rat q)
    {
        q.num *= num;
        q.denom *= denom;
        q.simplify();
        return q;
    }

    Rat operator+(Rat r)
    {
        r.num = r.num * denom + num * r.denom;
        r.denom *= denom;
        r.simplify();
        return r;
    }

    Rat operator-()
    {
        Rat q;
        q.num = -num;
        q.denom = denom;
        return q;
    }

    Rat operator-(Rat r)
    {
        return *this + (-r);
    }

    Rat inv()
    {
        Rat q;
        if (num == 0)
        {
            throw "Error: Division by 0";
        }
        if (num < 0)
        {
            q.num = -denom;
            q.denom = -num;
        }
        else
        {
            q.num = denom;
            q.denom = num;
        }
        return q;
    }

    Rat operator/(Rat r)
    {
        Rat q = (*this * r.inv());
        return q;
    }

    bool operator==(Rat r)
    {
        Rat s = *this;
        s.simplify();
        r.simplify();
        return (r.num == s.num && r.denom == s.denom);
    }

    bool operator!=(Rat r)
    {
        return !((*this) == r);
    }

    bool operator<=(Rat r)
    {
        int n = num * r.denom;
        int p = r.num * denom;
        return n <= p;
    }

    bool operator>=(Rat r)
    {
        int n = num * r.denom;
        int p = r.num * denom;
        return n >= p;
    }

    bool operator<(Rat r)
    {

        return !(*this >= r);
    }

    bool operator>(Rat r)
    {
        return !(*this <= r);
    }

    Rat operator++()
    {
        *this = *this + 1;
        return *this;
    }

    Rat operator--()
    {
        *this = *this - 1;
        return *this;
    }
    Rat operator++(int)
    {
        *this = *this + 1;
        return *this - 1;
    }

    Rat operator--(int)
    {
        *this = *this - 1;
        return *this + 1;
    }
};

ostream &operator<<(ostream &os, Rat r)
{
    if (r.denom == 1)
    {
        os << r.num;
    }
    else
    {
        os << r.num << "/" << r.denom;
    }
    return os;
}

istream &operator>>(istream &is, Rat &r)
{
    is >> r.num;
    is >> r.denom;
    if(r.denom == 0){
        throw "Error: Division by 0";
    }
    r.simplify();
    return is;
}

// istream &operator>>(istream &is, Rat r)
// {
//     int n;
//     unsigned d;
//     is >> n;
//     cout << "n=" << n << endl;
//     r.setNum(n);
//     is >> d;
//     r.setDenom(d);
//     return is;
// }