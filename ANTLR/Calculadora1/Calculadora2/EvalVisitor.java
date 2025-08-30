import java.util.HashMap;
import java.util.Map;

public class EvalVisitor extends LabeledExprBaseVisitor<Integer> {
    Map<String, Integer> memory = new HashMap<String, Integer>();

    // true = grados, false = radianes
    boolean useDegrees = false;

    @Override
    public Integer visitAssign(LabeledExprParser.AssignContext ctx) {
        String id = ctx.ID().getText();
        int value = visit(ctx.expr());
        memory.put(id, value);
        return value;
    }

    @Override
    public Integer visitPrintExpr(LabeledExprParser.PrintExprContext ctx) {
        Integer value = visit(ctx.expr());
        System.out.println(value);
        return 0;
    }

    @Override
    public Integer visitInt(LabeledExprParser.IntContext ctx) {
        return Integer.valueOf(ctx.INT().getText());
    }

    @Override
    public Integer visitId(LabeledExprParser.IdContext ctx) {
        String id = ctx.ID().getText();
        if (memory.containsKey(id)) return memory.get(id);
        return 0;
    }

    @Override
    public Integer visitMulDiv(LabeledExprParser.MulDivContext ctx) {
        int left = visit(ctx.expr(0));
        int right = visit(ctx.expr(1));
        if (ctx.op.getType() == LabeledExprParser.MUL) return left * right;
        return left / right;
    }

    @Override
    public Integer visitAddSub(LabeledExprParser.AddSubContext ctx) {
        int left = visit(ctx.expr(0));
        int right = visit(ctx.expr(1));
        if (ctx.op.getType() == LabeledExprParser.ADD) return left + right;
        return left - right;
    }

    @Override
    public Integer visitParens(LabeledExprParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

    /** Funciones matemáticas */
    @Override
    public Integer visitFuncCall(LabeledExprParser.FuncCallContext ctx) {
        String funcName = ctx.func().getText();
        double arg = visit(ctx.expr());

        // Si estoy en grados, convierto a radianes
        double value = arg;
        if (useDegrees && (funcName.equals("Sin") || funcName.equals("Cos") || funcName.equals("Tan"))) {
            value = Math.toRadians(arg);
        }

        switch (funcName) {
            case "Sin": return (int)Math.round(Math.sin(value));
            case "Cos": return (int)Math.round(Math.cos(value));
            case "Tan": return (int)Math.round(Math.tan(value));
            case "Sqrt": return (int)Math.round(Math.sqrt(arg));
            case "Ln": return (int)Math.round(Math.log(arg));
            case "Log": return (int)Math.round(Math.log10(arg));
            default: return 0;
        }
    }

    /** Factorial: expr '!' */
    @Override
    public Integer visitFactorial(LabeledExprParser.FactorialContext ctx) {
        int n = visit(ctx.expr());
        return factorial(n);
    }

    private int factorial(int n) {
        if (n < 0) return 0; // no definido
        int res = 1;
        for (int i = 2; i <= n; i++) {
            res *= i;
        }
        return res;
    }
}
