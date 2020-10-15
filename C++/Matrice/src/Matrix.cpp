#include "Matrix.hpp"

using namespace std;
Matrix::Matrix(int n, int p, double x) : _lines(n, MVector(p, x))
{
}

const int *Matrix::size() const
{
    int *size = new int[2];

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

    const int *size = m.size();
    os << "[" << endl;
    if (size[0] != 0)
    {
        for (int i = 0; i < size[0]; i++)
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
