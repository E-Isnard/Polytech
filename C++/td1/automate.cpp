#include <iostream>
#include <vector>
#include <random>
using namespace std;

vector<int> initialize_cells()
{

    srand(time(NULL));
    vector<int> cells(10, 0);
    for (int i = 0; i < 10; i++)
    {
        cells[i] = rand() % 2;
    }

    return cells;
}

void print_cells(vector<int> cells)
{

    for (int i = 0; i < cells.size(); i++)
    {
        cout << cells[i] << " ";
    }
    cout << endl;
}

vector<int> evolve(vector<int> cells)
{

    vector<int> temp = cells;

    for (int i = 0; i < cells.size(); i++)
    {
        if (i == 0)
        {
            cells[0] = temp[0] && temp[1];
        }
        else
        {
            if (i == cells.size() - 1)
            {
                cells[i] = temp[i] && temp[i - 1];
            }
            else{
                cells[i] = temp[i-1] && temp[i] && temp[i+1];
            }
        }
    }

    return cells;
}


vector<int> evolve_xor(vector<int> cells)
{

    vector<int> temp = cells;

    for (int i = 0; i < cells.size(); i++)
    {
        if (i == 0)
        {
            cells[0] = temp[0] ^ temp[1];
        }
        else
        {
            if (i == cells.size() - 1)
            {
                cells[i] = temp[i] ^ temp[i - 1];
            }
            else{
                cells[i] = temp[i-1] ^ temp[i] ^ temp[i+1];
            }
        }
    }

    return cells;
}

int main(int argc, char const *argv[])
{

    vector<int> cells = initialize_cells();

    for (int i = 0; i < 20; i++)
    {
        print_cells(cells);
        cells = evolve_xor(cells);
    }
    

    return 0;
}
