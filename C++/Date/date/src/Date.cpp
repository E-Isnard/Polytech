#include "Date.h"
#include <string>
using namespace std;
Date::Date()
{
    _day = 1;
    _month = 1;
    _year = 1970;
}

Date::Date(int year, int month, int day)
{
    if (month < 1 || month > 12 || day < 0 || day > numberDayInMonth(month, year))
    {
        cerr << "Error: the date is invalid" << endl;
        exit(EXIT_FAILURE);
    }
    _day = day;
    _month = month;
    _year = year;
}

Date::Date(int s)
{
    int nbSecondInOneDay = 86400;
    int y = 1970;
    int m = 1;
    int d = 0;
    while (s>366*nbSecondInOneDay)
    {
        if (isBissextile(y))
        {
            s -= nbSecondInOneDay * 366;
        }
        else
        {
            s -= nbSecondInOneDay * 365;
        }
        y++;
    }
    while (s>31*nbSecondInOneDay)
    {
        m++;
        s -= nbSecondInOneDay * numberDayInMonth(m,y);
    }
    d = s / nbSecondInOneDay;

    _year = y;
    _month = m;
    _day = d;
}

ostream &operator<<(ostream &os, Date d)
{
    string monthName = d.monthName();
    os << monthName << " " << d._day << ", " << d._year;
    return os;
}

string Date::monthName()
{
    string name;
    switch (_month)
    {
    case 1:
        name = "January";
        break;
    case 2:
        name = "February";
        break;
    case 3:
        name = "March";
        break;
    case 4:
        name = "April";
        break;
    case 5:
        name = "May";
        break;
    case 6:
        name = "June";
        break;
    case 7:
        name = "July";
        break;
    case 8:
        name = "August";
        break;
    case 9:
        name = "September";
        break;
    case 10:
        name = "October";
        break;
    case 11:
        name = "November";
        break;
    case 12:
        name = "December";
        break;

    default:
        break;
    }

    return name;
}

bool isBissextile(int year)
{

    return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
}

int numberDayInMonth(int month, int year)
{
    if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
    {
        return 31;
    }
    else if (month == 2)
    {
        if (isBissextile(year))
        {
            return 29;
        }
        else
        {
            return 28;
        }
    }
    else
    {
        return 30;
    }
}

Date Date::operator+(int i)
{
    Date d(_year, _month, _day);
    d._day += i;
    if (i >= 0)
    {
        while (d._day > numberDayInMonth(d._month, d._year))
        {
            d._day -= numberDayInMonth(d._month, d._year);
            d._month++;
            if (d._month > 12)
            {
                d._month = 1;
                d._year++;
            }
        }
    }
    else
    {
        while (d._day < 1)
        {
            d._month--;
            if (d._month < 1)
            {
                d._month = 12;
                d._year--;
            }
            d._day += numberDayInMonth(d._month, d._year);
        }
    }

    return d;
}

Date Date::operator-(int i)
{
    return *this + (-i);
}

/*
Compute the number from  1st January 1970
*/
int Date::toDays()
{
    int nbDays = 0;
    nbDays += _day - 1;
    for (int m = 1; m < _month; m++)
    {
        nbDays += numberDayInMonth(m, _year);
        /* code */
    }

    if (_year >= 1970)
    {
        for (int y = 1970; y < _year; y++)
        {
            if (isBissextile(y))
            {
                nbDays += 366;
            }
            else
            {
                nbDays += 365;
            }
        }
    }
    else
    {
        for (int y = _year; y < 1970; y++)
        {
            if (isBissextile(y))
            {
                nbDays -= 366;
            }
            else
            {
                nbDays -= 365;
            }
        }
    }
    return nbDays;
}
int Date::operator-(Date d)
{
    return (*this).toDays() - d.toDays();
}

bool Date::operator<=(Date d)
{
    return ((*this) - d) <= 0;
}
bool Date::operator<(Date d)
{
    return ((*this) - d) < 0;
}

istream &operator>>(istream &is, Date &d)
{
    int year;
    int month;
    int day;

    is >> year;
    is >> month;
    is >> day;

    Date d2(year, month, day);
    d = d2;

    return is;
}
