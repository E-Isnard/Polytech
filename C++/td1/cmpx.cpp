#include <iostream>
#include <vector>

using namespace std;

void cmpx(int &a, int &b)
{
    int temp = 0;
    if (a > b)
    {
        temp = a;
        a = b;
        b = temp;
    }
}

void insert(int n, vector<int> &vec)
{
    vec.push_back(n);
    if (vec.size() > 1)
    {
        int i = vec.size() - 2;
        while (vec[i + 1] < vec[i])
        {
            cmpx(vec[i], vec[i + 1]);
            i--;
        }
    }
}

void print_vect(vector<int> vec)
{
    cout << "(";
    for (int i = 0; i < vec.size(); i++)
    {
        cout << vec.at(i);
        if (i != vec.size() - 1)
        {
            cout << ",";
        }
    }
    cout << ")" << endl;
}

void sort_list()
{

    vector<int> vector;
    int n = 0;
    for (int i = 0; i < 4; i++)
    {
        cin >> n;
        insert(n,vector);
    }

    print_vect(vector);
}

int main(int argc, char const *argv[])
{

    int b = 2;
    int a = 3;
    cmpx(a, b);
    cout << "a = " << a << endl;
    cout << "b = " << b << endl;

    vector<int> vec = {1, 2, 10};

    print_vect(vec);

    insert(8, vec);

    print_vect(vec);

    sort_list();

    return 0;
}
