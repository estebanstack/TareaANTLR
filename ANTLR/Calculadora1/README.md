# 📘 Calculadora ANTLR4 (Capítulo 4 – LabeledExpr)

Este proyecto es una implementación de una **calculadora** siguiendo el ejemplo del **Capítulo 4 del libro *The Definitive ANTLR4 Reference***.  
Se utiliza **ANTLR4** para generar el lexer y parser a partir de la gramática `LabeledExpr.g4`, y un **Visitor (EvalVisitor.java)** para evaluar expresiones aritméticas con soporte de variables.

---

## 📂 Archivos principales

- **LabeledExpr.g4** → gramática que define la calculadora.  
- **EvalVisitor.java** → clase que evalúa las expresiones con la técnica *Visitor*.  
- **Calc.java** → clase principal que lee un archivo de entrada y ejecuta la calculadora.  
- **t.expr** → archivo de prueba con expresiones.

---

## 🛠️ Funcionalidades

- Operaciones básicas:  
  ➕ Suma, ➖ Resta, ✖️ Multiplicación, ➗ División.  
- Uso de variables (ejemplo: `a = 5`).  
- Uso de paréntesis para modificar precedencia.  
- Evaluación de múltiples expresiones en un mismo archivo.  

---

## ▶️ Ejemplo de uso

Archivo `t.expr`:

```
193
a = 5
b = 6
a+b*2
(1+2)*3
```

### Resultado esperado:

```
193
5
6
17
9
```

---

## ⚡ Compilación y ejecución

### 1. Generar lexer y parser
```bash
antlr4 -visitor -no-listener LabeledExpr.g4
```

### 2. Compilar
```bash
javac -cp .:/ruta/a/antlr-4.13.1-complete.jar *.java
```

*(En Windows usar `;` en lugar de `:`)*

### 3. Ejecutar
```bash
java -cp .:/ruta/a/antlr-4.13.1-complete.jar Calc t.expr
```

---

## 📝 Notas

- El `EvalVisitor.java` implementa un **Map<String, Integer> memory** que guarda los valores de las variables asignadas.  
- Si se intenta usar una variable no definida, su valor es `0` por defecto.  
- Este proyecto sirve como **base** para extender la calculadora con nuevas funciones (ejemplo: trigonometría, factorial, logaritmos, etc.).  
