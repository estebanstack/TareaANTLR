# Calculadora ANTLR4 Extendida

Este proyecto es una extensión de la calculadora básica del Capítulo 4.  
Además de las operaciones aritméticas y variables, se añadieron **funciones matemáticas avanzadas** y el operador factorial `!`.

---

## Archivos principales

- **LabeledExpr.g4** → gramática extendida con nuevas reglas para funciones y factorial.  
- **EvalVisitor.java** → implementación del *Visitor* con soporte para las funciones nuevas.  
- **Calc.java** → clase principal (se mantiene igual a la versión básica).  
- **t.expr** → archivo de prueba con ejemplos.

---

## Funcionalidades nuevas

Además de suma, resta, multiplicación, división, variables y paréntesis, ahora soporta:

- **Funciones trigonométricas**:  
  - `Sin(x)`  
  - `Cos(x)`  
  - `Tan(x)`  
- **Raíz cuadrada**:  
  - `Sqrt(x)`  
- **Logaritmos**:  
  - `Ln(x)` → logaritmo natural (base *e*).  
  - `Log(x)` → logaritmo en base 10.  
- **Factorial**:  
  - `n!` (ejemplo: `5! = 120`).  

### Grados y radianes
La calculadora permite trabajar en **grados** o en **radianes** para las funciones trigonométricas.  
Esto se controla con la variable interna `useDegrees` en `EvalVisitor.java`.  
- Si está en `true`, se interpretan los ángulos en grados.  
- Si está en `false`, se usan radianes.  

---

## Ejemplo de uso

Archivo `t.expr`:

```
a = 5
a!
Sin(90)
Cos(0)
Tan(45)
Sqrt(16)
Ln(1)
Log(100)
```

### Resultado esperado (con `useDegrees = true`):

```
5
120
1
1
1
4
0
2
```

---

## Compilación y ejecución

### 1. Generar lexer y parser
```bash
antlr4 -visitor -no-listener LabeledExpr.g4
```

### 2. Compilar
```bash
javac Calc.java LabeledExpr*.java
```
### 3. Leer el archivo de prueba
```bash
cat t.expr
```

### 4. Ejecutar
```bash
java Calc t.expr
```

---

## 📝 Notas

- Para simplificar, las funciones matemáticas devuelven resultados **redondeados a enteros** (`Math.round`).  
- Las funciones trigonométricas convierten automáticamente los valores a radianes si `useDegrees = true`.  
- El `Calc.java` no cambió respecto a la versión básica.  
