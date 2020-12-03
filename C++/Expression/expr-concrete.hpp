#ifndef _EXPR_CONCRETE_H_
#define _EXPR_CONCRETE_H_
#include "expr-abstract.hpp"

class Constant : public Expr
{
    int val;

public:
    Constant(int v) : val(v){};
    int eval() const
    {
        return val;
    }
};

class Unary_Minus : public Unary_Expr
{
public:
    using Unary_Expr::Unary_Expr;
    int eval() const
    {
        return -op->eval();
    }
};

class Binary_Mult : public Binary_Expr
{
    using Binary_Expr::Binary_Expr;
    int eval() const
    {
        return opl->eval() * opr->eval();
    }
};

class Binary_Minus : public Binary_Expr
{
    using Binary_Expr::Binary_Expr;
    int eval() const
    {
        return opl->eval() - opr->eval();
    }
};

class Binary_Plus : public Binary_Expr
{
    using Binary_Expr::Binary_Expr;
    int eval() const
    {
        return opl->eval() + opr->eval();
    }
};

class Binary_Div : public Binary_Expr
{
    using Binary_Expr::Binary_Expr;
    int eval() const
    {
        if (opr->eval() == 0)
        {
            throw ZeroDivide();
        }
        return opl->eval() / opr->eval();
    }
};

class Binary_Mod : public Binary_Expr
{
    using Binary_Expr::Binary_Expr;
    int eval() const
    {

        return opl->eval() % opr->eval();
    }
};

#endif