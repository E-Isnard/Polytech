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
        if(k<0){
            k = -k;
        }
        while (k != 1)
        {
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
        int k = pgcd(n, p);
        if(k<0){
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

    Rat inv(){
        Rat q;
        if(num<0){
            q.num = -denom;
            q.denom = -num;
        }else{
            q.num = denom;
            q.denom = num;
        }
        return q;
    }

    Rat operator/(Rat r){
        Rat q = (*this * r.inv());
        return q;
    }

    // void setNum(int n)
    // {
    //     num = n;
    // }

    // void setDenom(int unsigned p)
    // {
    //     denom = p;
    // }
};

ostream &operator<<(ostream &os, Rat r)
{
    os << r.num << "/" << r.denom;
    return os;
}

istream &operator>>(istream &is, Rat &r)
{
    is >> r.num;
    is >> r.denom;
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