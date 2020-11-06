#ifndef _MATRIX_H_
#define _MATRIX_H_
#include <vector>
#include <iostream>
#include "MVector.hpp"
#define ui unsigned int

class MVector;

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
    Matrix(MVector v);
    const ui *size() const;
    friend ostream &operator<<(ostream &os, const Matrix &m);
    bool operator==(const Matrix &m);
    Matrix operator+(const Matrix &m);
    Matrix operator-(const Matrix &m);
    Matrix operator*(const Matrix &m);
    MVector operator[](ui i) const;
    MVector &operator[](ui i);
    Matrix transpose() const;
    Matrix operator+=(const Matrix &m);
    Matrix operator-=(const Matrix &m);
    Matrix operator*=(const Matrix &m);
    Matrix operator~();
    double operator()(ui i, ui j) const;
    double &operator()(ui i, ui j);
    MVector line(ui i) const;
    MVector column(ui j) const;
    double det() const;
};
#endif