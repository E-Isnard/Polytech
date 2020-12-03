#ifndef _VARIABLE_H_
#define _VARIABLE_H_
#include "expr-abstract.hpp"
#include <map>
using namespace std;

static map<string, int> variables;

class Variable_Ref : public Expr
{

public:
    string nom;
    Variable_Ref(string str) : nom(str)
    {
        variables.insert(pair<string, int>(str, 0));
    };
    int eval() const
    {
        return variables.at(nom);
    }
};

class Assignment : public Expr
{
public:
    Variable_Ref *vr;
    Expr *exp0;
    Assignment(Variable_Ref *var, Expr *exp) : vr(var), exp0(exp){};
    int eval() const
    {
        int n = exp0->eval();
        variables.at(vr->nom) = n;
        return n;
    }
};

#endif