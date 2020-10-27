#include <vector>
#include <iostream>
#include "MVector.hpp"
class Matrix
{

    vector<MVector> _lines;

public:
    class Bad_Dimensions
    {
    };
    class OutOfBound
    {
    };

    Matrix(int n = 0, int p = 0, double x = 0.0);
    const unsigned int *size() const;
    friend ostream &operator<<(ostream &os, const Matrix &m);
    bool operator==(const Matrix &m);
    Matrix operator+(const Matrix &m);
    Matrix operator-(const Matrix &m);
    Matrix operator*(const Matrix &m);
    MVector operator[](unsigned int) const;
    MVector &operator[](unsigned int i);
    Matrix transpose();
    Matrix operator+=(const Matrix &m);
    Matrix operator-=(const Matrix &m);
    Matrix operator*=(const Matrix &m);
};