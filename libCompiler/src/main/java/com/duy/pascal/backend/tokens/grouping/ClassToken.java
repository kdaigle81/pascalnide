package com.duy.pascal.backend.tokens.grouping;


import com.duy.pascal.backend.ast.FunctionDeclaration;
import com.duy.pascal.backend.ast.VariableDeclaration;
import com.duy.pascal.backend.ast.codeunit.classunit.ClassConstructor;
import com.duy.pascal.backend.ast.expressioncontext.ExpressionContext;
import com.duy.pascal.backend.linenumber.LineInfo;
import com.duy.pascal.backend.parse_exception.ParsingException;
import com.duy.pascal.backend.tokens.Token;
import com.duy.pascal.backend.tokens.WordToken;
import com.duy.pascal.backend.tokens.basic.ConstToken;
import com.duy.pascal.backend.tokens.basic.ConstructorToken;
import com.duy.pascal.backend.tokens.basic.DestructorToken;
import com.duy.pascal.backend.tokens.basic.FunctionToken;
import com.duy.pascal.backend.tokens.basic.ProcedureToken;
import com.duy.pascal.backend.tokens.basic.VarToken;
import com.duy.pascal.backend.tokens.visibility.BaseVisibilityToken;
import com.duy.pascal.backend.tokens.visibility.PrivateToken;
import com.duy.pascal.backend.tokens.visibility.ProtectedToken;
import com.duy.pascal.backend.tokens.visibility.PublicToken;
import com.duy.pascal.backend.types.PascalClassType;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ClassToken extends GrouperToken {
    public ClassToken(LineInfo line) {
        super(line);
    }

    @Override
    public String toCode() {
        StringBuilder result = new StringBuilder("class ");
        if (next != null) {
            result.append(next.toCode()).append(' ');
        }
        for (Token t : this.queue) {
            result.append(t.toCode()).append(' ');
        }
        result.append("end");
        return result.toString();
    }

    @Override
    public String toString() {
        return "class";
    }

    @Override
    protected String getClosingText() {
        return "end";
    }

    public void addDeclaresTo(PascalClassType classType, ExpressionContext context) throws ParsingException {
        while (hasNext()) {
            Token n = take();
            if (n instanceof PrivateToken) {
                while (hasNext() && !(next instanceof BaseVisibilityToken)) {
                    Token next = peek();
                    if (next instanceof FunctionToken || next instanceof ProcedureToken) {
                        take();
                        boolean isProcedure = next instanceof ProcedureToken;
                        FunctionDeclaration function = new FunctionDeclaration(context, this, isProcedure);
                        classType.addPrivateFunction(function);
                    } else if (next instanceof WordToken) {
                        ArrayList<VariableDeclaration> vars = getVariableDeclarations(context);
                        classType.addPrivateField(vars);
                    }
                }
            } else if (n instanceof PublicToken) {
                while (hasNext() && !(next instanceof BaseVisibilityToken)) {
                    Token next = peek();
                    if (next instanceof FunctionToken || next instanceof ProcedureToken) {
                        take();
                        boolean isProcedure = next instanceof ProcedureToken;
                        FunctionDeclaration function = new FunctionDeclaration(context, this, isProcedure);
                        classType.addPublicFunction(function);
                    } else if (next instanceof WordToken) {
                        ArrayList<VariableDeclaration> vars = getVariableDeclarations(context);
                        classType.addPublicFields(vars);
                    } else if (next instanceof ConstructorToken) {
                        take();
                        ClassConstructor constructor = new ClassConstructor(classType, context, this, true);
                        constructor.setModifier(Modifier.STATIC);
                        classType.addConstructor(constructor);

                    } else if (next instanceof DestructorToken) {
                        take();
                        FunctionDeclaration destructor = new FunctionDeclaration(context, this, true);
                        classType.getClassContext().setDestructor(destructor);
                    }
                }
            } else if (n instanceof ProtectedToken) {

                while (hasNext() && !(next instanceof BaseVisibilityToken)) {
                    Token next = peek();
                    if (next instanceof FunctionToken || next instanceof ProcedureToken) {
                        take();
                        boolean isProcedure = next instanceof ProcedureToken;
                        FunctionDeclaration function = new FunctionDeclaration(context, this, isProcedure);
                        classType.addPublicFunction(function);
                    } else if (next instanceof WordToken) {
                        take();
                        ArrayList<VariableDeclaration> vars = getVariableDeclarations(context);
                        classType.addPublicFields(vars);
                    }
                }
            } else if (n instanceof VarToken) {
                take();
                List<VariableDeclaration> d = getVariableDeclarations(context);
                classType.addPublicFields(d);
            } else if (n instanceof ConstToken) {
                take();
            }
        }
    }
}
