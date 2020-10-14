#include "common_defs.hpp"
#include <iostream>
#include <vector>
#include <math.h>
using namespace std;
class MVector
{

    vector<double> _components;

public:
    MVector(int d = 0, double x = 0.0);
    unsigned int size();
    double &operator[](int i);
    friend ostream &operator<<(ostream &os, MVector v);
    bool operator==(MVector v);
    MVector operator+(MVector v);
    MVector operator-();
    MVector operator-(MVector v);
    MVector operator+=(MVector v);
    MVector operator-=(MVector v);
    double operator*(MVector v);
    double norm();
};
