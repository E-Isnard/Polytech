#include "Matrix.hpp"
#define ui unsigned int
using namespace std;
Matrix::Matrix(int n, int p, double x) : _lines(n, MVector(p, x))
{
}

const ui *Matrix::size() const
{
    ui *size = new ui[2];

    size[0] = _lines.size();
    if (size[0] == 0)
    {
        size[1] = 0;
    }
    else
    {
        size[1] = _lines.at(0).size();
    }

    return size;
}

ostream &operator<<(ostream &os, const Matrix &m)
{

    const ui *size = m.size();
    os << "[" << endl;
    if (size[0] != 0)
    {
        for (ui i = 0; i < size[0]; i++)
        {
            cout << m._lines.at(i);
            if (i != size[0] - 1)
            {
                cout << endl;
            }
        }
        os << endl
           << "]";
    }
    else
    {
        os << "]";
    }

    return os;
}

bool Matrix::operator==(const Matrix &m)
{
    const ui *size1 = (*this).size();
    const ui *size2 = m.size();
    if (size1[0] != size2[0] || size1[1] != size2[1])
    {
        return false;
    }
    for (ui i = 0; i < size1[0]; i++)
    {
        if (!(_lines[i] == m._lines[i]))
        {
            return false;
        }
    }
    return true;
}

Matrix Matrix::operator+(const Matrix &m)
{
    const ui *size1 = (*this).size();
    const ui *size2 = m.size();
    if (size1[0] != size2[0] || size1[1] != size2[1])
    {
        throw Bad_Dimensions();
    }
    Matrix s(size1[0], size1[1]);
    for (ui i = 0; i < size1[0]; i++)
    {
        s._lines[i] = (*this)._lines[i] + m._lines[i];
    }

    return s;
}

Matrix Matrix::operator-(const Matrix &m)
{
    const ui *size1 = (*this).size();
    const ui *size2 = m.size();
    if (size1[0] != size2[0] || size1[1] != size2[1])
    {
        throw Bad_Dimensions();
    }
    Matrix d(size1[0], size1[1]);
    for (ui i = 0; i < size1[0]; i++)
    {
        d._lines[i] = (*this)._lines[i] - m._lines[i];
    }

    return d;
}

MVector &Matrix::operator[](ui i)
{
    const ui *size = (*this).size();
    if (size[0] == 0 || i > size[0] - 1)
    {
        throw OutOfBound();
    }
    return _lines.at(i);
}

MVector Matrix::operator[](ui i) const
{

    const ui *size = (*this).size();
    if (size[0] == 0 || i > size[0] - 1)
    {
        throw OutOfBound();
    }
    return _lines.at(i);
}

Matrix Matrix::operator*(const Matrix &m)
{
    const ui *size1 = (*this).size();
    const ui *size2 = m.size();
    if (size1[1] != size2[0])
    {
        throw Bad_Dimensions();
    }
    Matrix p(size1[0], size2[1]);
    for (ui i = 0; i < size1[0]; i++)
    {
        for (ui j = 0; j < size2[1]; j++)
        {
            for (ui k = 0; k < size1[1]; k++)
            {
                p[i][j] += (*this)[i][k] * m[k][j];
            }
        }
    }
    return p;
}

Matrix Matrix::operator+=(const Matrix &m)
{
    *this = *this + m;
    return *this;
}
Matrix Matrix::operator-=(const Matrix &m)
{
    *this = *this - m;
    return *this;
}

Matrix Matrix::operator*=(const Matrix &m)
{
    *this = *this * m;
    return *this;
}

Matrix Matrix::transpose()
{
    const ui *size = (*this).size();
    Matrix T(size[1], size[0]);
    for (ui i = 0; i < size[1]; i++)
    {
        for (ui j = 0; j < size[0]; j++)
        {
            T[i][j] = (*this)[j][i];
        }
    }

    *this = T;
    return T;
}
