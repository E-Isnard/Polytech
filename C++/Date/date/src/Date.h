#ifndef _DATE_H_
#define _DATE_H_
#include <iostream>
using namespace std;
class Date
{
    int _day;
    int _month;
    int _year;

public:
    Date();
    Date(int day, int month, int year);
    Date(int s);
    friend ostream &operator<<(ostream &os, Date d);
    friend istream &operator>>(istream &is, Date &d);
    Date operator+(int i);
    Date operator-(int i);
    int toDays();
    int operator-(Date d);
    bool operator<=(Date d);
    bool operator<(Date d);
    string monthName();
};

int numberDayInMonth(int month,int year);
bool isBissextile(int year);
#endif