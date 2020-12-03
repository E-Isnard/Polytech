#ifndef _TERNARY_EXPR_H_
#define _TERNARY_EXPR_H_

#include "expr-abstract.hpp"
class Ternary_Expr : public Expr
{
protected:
    Expr *op1, *op2, *op3;

public:
    Ternary_Expr(Expr *exp1, Expr *exp2, Expr *exp3) : op1(exp1), op2(exp2), op3(exp3){};
};

class Ternary_Conditional : public Ternary_Expr
{
    using Ternary_Expr::Ternary_Expr;
    int eval() const
    {
        return op1->eval() ? op2->eval() : op3->eval();
    }
};

#endif