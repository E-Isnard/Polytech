#include "common_defs.hpp"
#include <iostream>
#include <vector>
#include <math.h>
using namespace std;
class MVector
{

    vector<double> _components;

public:

    // Exceptions
    class BadDimension
    {
    };
    class OutOfBound
    {
    };
    MVector(int d = 0, double x = 0.0);
    MVector(vector<double> v);
    unsigned int size() const;
    double &operator[](unsigned int i);
    double operator[](unsigned int i) const;
    friend ostream &operator<<(ostream &os, MVector v);
    bool operator==(MVector v);
    friend MVector operator+(MVector v,MVector w);
    MVector operator-();
    friend MVector operator-(MVector v,MVector w);
    MVector operator+=(MVector v);
    MVector operator-=(MVector v);
    double operator*(MVector v);
    double norm();
};
