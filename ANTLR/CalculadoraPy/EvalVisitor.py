import math
from LabeledExprParser import LabeledExprParser
from LabeledExprVisitor import LabeledExprVisitor

class EvalVisitor(LabeledExprVisitor):
    def __init__(self):
        super().__init__()
        self.memory = {}
        # True = grados, False = radianes
        self.useDegrees = False

    # ID '=' expr
    def visitAssign(self, ctx:LabeledExprParser.AssignContext):
        id_ = ctx.ID().getText()
        value = self.visit(ctx.expr())
        self.memory[id_] = value
        return value

    # expr NEWLINE
    def visitPrintExpr(self, ctx:LabeledExprParser.PrintExprContext):
        value = self.visit(ctx.expr())
        print(value)
        return 0

    # INT
    def visitInt(self, ctx:LabeledExprParser.IntContext):
        return int(ctx.INT().getText())

    # ID
    def visitId(self, ctx:LabeledExprParser.IdContext):
        id_ = ctx.ID().getText()
        return self.memory.get(id_, 0)

    # expr op=('*'|'/') expr
    def visitMulDiv(self, ctx:LabeledExprParser.MulDivContext):
        left = self.visit(ctx.expr(0))
        right = self.visit(ctx.expr(1))
        if ctx.op.type == LabeledExprParser.MUL:
            return left * right
        return int(left / right)

    # expr op=('+'|'-') expr
    def visitAddSub(self, ctx:LabeledExprParser.AddSubContext):
        left = self.visit(ctx.expr(0))
        right = self.visit(ctx.expr(1))
        if ctx.op.type == LabeledExprParser.ADD:
            return left + right
        return left - right

    # '(' expr ')'
    def visitParens(self, ctx:LabeledExprParser.ParensContext):
        return self.visit(ctx.expr())

    # funciones matemáticas
    def visitFuncCall(self, ctx:LabeledExprParser.FuncCallContext):
        funcName = ctx.func().getText()
        arg = self.visit(ctx.expr())

        value = arg
        if self.useDegrees and funcName in ["Sin", "Cos", "Tan"]:
            value = math.radians(arg)

        if funcName == "Sin":
            return round(math.sin(value))
        elif funcName == "Cos":
            return round(math.cos(value))
        elif funcName == "Tan":
            return round(math.tan(value))
        elif funcName == "Sqrt":
            return round(math.sqrt(arg))
        elif funcName == "Ln":
            return round(math.log(arg))
        elif funcName == "Log":
            return round(math.log10(arg))
        return 0

    # factorial
    def visitFactorial(self, ctx:LabeledExprParser.FactorialContext):
        n = self.visit(ctx.expr())
        return self.factorial(n)

    def factorial(self, n):
        if n < 0:
            return 0
        res = 1
        for i in range(2, n+1):
            res *= i
        return res
