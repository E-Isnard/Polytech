#include <iostream>
#include "MVector.hpp"

using namespace std;

MVector::MVector(int d, double x) : _components(d, x)
{
}

MVector::MVector(vector<double> v) : _components(v) {}

unsigned int MVector::size() const
{
    return _components.size();
}

double &MVector::operator[](unsigned int i)
{
    if ((*this).size() == 0 || i > (*this).size() - 1)
    {
        throw OutOfBound();
    }
    return _components.at(i);
}

double MVector::operator[](unsigned int i) const
{

    if (_components.size() == 0 || i > _components.size() - 1)
    {
        throw OutOfBound();
    }
    return _components.at(i);
}

ostream &operator<<(ostream &os, MVector v)
{
    os << "[";
    if (v.size() != 0)
    {
        for (unsigned int i = 0; i < v.size(); i++)
        {
            os << v[i]; // faire un opérateur pour ça
            if (i != v.size() - 1)
            {
                os << ",";
            }
        }
    }

    os << "]";
    return os;
}

bool MVector::operator==(MVector v)
{
    if (_components.size() != v.size())
    {
        return false;
    }
    for (unsigned int i = 0; i < v.size(); i++)
    {
        if (_components[i] != v[i])
        {
            return false;
        }
    }
    return true;
}

MVector MVector::operator+=(MVector v)
{
    *this = *this + v;
    return *this;
}

MVector MVector::operator-=(MVector v)
{
    *this = *this - v;
    return *this;
}

MVector MVector::operator-()
{
    unsigned int n = _components.size();
    MVector v2(n);
    for (unsigned int i = 0; i < n; i++)
    {
        v2[i] = -_components[i];
    }

    return v2;
}

MVector operator+(MVector v, MVector w)
{
    unsigned int n = v.size();
    if (n != w.size())
    {
        throw MVector::BadDimension();
    }
    MVector v2(n);
    for (unsigned int i = 0; i < n; i++)
    {
        v2[i] = v[i] + w[i];
    }
    return v2;
}

// On n'utilise pas l'opérateur - unaire pour des raisons d'efficacité
MVector operator-(MVector v, MVector w)
{
    unsigned int n = v.size();
    if (n != w.size())
    {
        throw MVector::BadDimension();
    }
    MVector v2(n);
    for (unsigned int i = 0; i < n; i++)
    {
        v2[i] = v[i] - w[i];
    }
    return v2;
}

double MVector::operator*(MVector v)
{
    unsigned int n = _components.size();
    if (n != v.size())
    {
        throw BadDimension();
    }
    double p = 0;
    for (unsigned int i = 0; i < n; i++)
    {
        p += _components[i] * v[i];
    }
    return p;
}

double MVector::norm()
{
    return sqrt((*this) * (*this));
}