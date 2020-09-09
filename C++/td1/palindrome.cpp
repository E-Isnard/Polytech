#include <iostream>
#include <string>
using namespace std;
int main(int argc, char const *argv[])
{
    string word = "";
    cout << "Veuillez rentrer un mot" << endl;
    cin >> word;
    
    int start = 0;
    int end = word.length()-1;

    while (start < end)
    {
        if(word[start] != word[end]){
            cout << "Le mot n'est pas un palindrome" << endl; ;
            break;
        }
        start++;
        end--;
    }
    
    if(start >= end){
        cout << "Le mot est un palindrome" << endl;;
    }
    return 0;
}
