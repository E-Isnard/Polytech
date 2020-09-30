#ifndef DATE_H
#define DATE_H
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
    friend ostream &operator<<(ostream &os, Date d);
    Date operator+(int i);
    Date operator-(int i);
    string monthName();
};

int numberDayInMonth(int month,int year);
#endif