#include <iostream>
#include <fstream>
using namespace std;

double average_file(){

    ifstream file;
    file.open("./notes");
    int n = 0;
    int i = 0;
    double sum = 0;
    while(file >> n){
        sum += n;
        i++;
    }

    return sum/i;

}

double average()
{
    int number = 0;
    int sum = 0;
    for (int i = 0; i < 10; i++)
    {
        cin >> number;
        sum += number;
    }
    return sum/10;
}

int main(int argc, char const *argv[])
{

    // double x = average();
    // cout << "Moyenne: " << x << endl;
    cout << "Moyenne: " << average_file() << endl;
    return 0;
}
