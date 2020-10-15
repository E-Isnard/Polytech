#include <vector>
#include <iostream>
#include "MVector.hpp"
class Matrix
{

    vector<MVector> _lines;

public:
    Matrix(int n = 0, int p = 0, double x = 0.0);
    const int *size() const;
    friend ostream &operator<<(ostream &os, const Matrix &m);
};