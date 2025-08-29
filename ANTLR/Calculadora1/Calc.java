import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.nio.file.Paths;

public class Calc {
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.err.println("Usage: java Calc <input-file>");
            System.exit(1);
        }
        CharStream input = CharStreams.fromPath(Paths.get(args[0]));
        LabeledExprLexer lexer = new LabeledExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LabeledExprParser parser = new LabeledExprParser(tokens);
        ParseTree tree = parser.prog();        // parse
        EvalVisitor eval = new EvalVisitor();  // create visitor
        eval.visit(tree);                      // evaluate
    }
}
