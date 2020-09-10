#include <iostream>
#include <string>
#include <fstream>
#include <vector>
using namespace std;

struct bookrecord
{
    string nom_client;
    int argent;
};

ostream &operator<<(ostream &os, bookrecord &record)
{
    os << "{" << record.nom_client << "," << record.argent << "}" << endl;
    return os;
}

istream &operator>>(istream &is, bookrecord &record)
{
    is >> record.nom_client;
    string tmp = "";
    is >> tmp;
    record.argent = stoi(tmp);
    return is;
}

vector<bookrecord> read_file()
{

    vector<bookrecord> records;
    fstream file;
    file.open("./bookrecord");

    bookrecord record;

    while (!file.eof())
    {
        file >> record;
        records.push_back(record);
    }
    return records;
}

void print_records(vector<bookrecord> records)
{
    for (int i = 0; i < records.size(); i++)
    {
        cout << records[i];
    }
}

void somme_client()
{
    string client;
    cout << "Nom du client:" << endl;
    cin >> client;
    vector<bookrecord> records = read_file();
    int sum = 0;
    for (int i = 0; i < records.size(); i++)
    {
        if (records[i].nom_client == client)
        {
            sum += records[i].argent;
        }
    }

    cout << "Argent total: " << sum << endl;
}

int main(int argc, char const *argv[])
{
    vector<bookrecord> records = read_file();
    print_records(records);
    somme_client();

    return 0;
}
