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
        cout << "Error: the date is invalid";
        exit(EXIT_FAILURE);
    }
    _day = day;
    _month = month;
    _year = year;
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

int numberDayInMonth(int month, int year)
{
    if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
    {
        return 31;
    }
    else if (month == 2)
    {
        if ((year % 4 == 0 && year % 100 == 0) || year % 400 == 0)
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

    return d;
}

Date Date::operator-(int i)
{
    Date d(_year, _month, _day);
    d._day -= i;
    while (d._day < 0)
    {
        d._day -= numberDayInMonth(d._month, d._year);
        d._month++;
        if (d._month > 12)
        {
            d._month = 1;
            d._year++;
        }
    }

    return d;
}
