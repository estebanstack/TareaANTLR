# Calculadora ANTLR4 en Python
Este proyecto implementa una **calculadora en Python** usando **ANTLR4** como generador de parser.  
La calculadora soporta operaciones aritméticas, variables, funciones matemáticas (trigonometría, raíz cuadrada, logaritmos) y factorial.  

---

##  Archivos principales

- **LabeledExpr.g4** → gramática de la calculadora (igual que en Java).  
- **EvalVisitor.py** → implementación del *Visitor* en Python.  
- **calc.py** → programa principal para ejecutar la calculadora.  
- **t.expr** → archivo de prueba con expresiones.  

---

## Funcionalidades

- Operaciones básicas: `+`, `-`, `*`, `/`  
- Variables y asignaciones (`a = 5`)  
- Paréntesis para precedencia  
- **Funciones nuevas**:  
  - `Sin(x)` – seno  
  - `Cos(x)` – coseno  
  - `Tan(x)` – tangente  
  - `Sqrt(x)` – raíz cuadrada  
  - `Ln(x)` – logaritmo natural (base *e*)  
  - `Log(x)` – logaritmo en base 10  
  - `n!` – factorial de un número entero  

Las funciones trigonométricas se pueden usar en **grados o radianes**, controlado con la variable `useDegrees` en `EvalVisitor.py`.

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

### Resultado esperado (con `useDegrees = True`):

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

## Generación de archivos con ANTLR4

1. Generar lexer, parser y visitor en Python:
```bash
antlr4 -Dlanguage=Python3 -visitor LabeledExpr.g4
```

Esto crea `LabeledExprLexer.py`, `LabeledExprParser.py`, `LabeledExprVisitor.py`, etc.

---

## Uso con `venv` (fue necesario para poder instalar las respectivas librerias de ANTLR en python)

Algunos sistemas basados en linux no permiten instalar paquetes Python globalmente con `pip`, por lo que es necesario usar un **entorno virtual**.

### 1. Crear un entorno virtual
```bash
python3 -m venv venv
```

### 2. Activar el entorno
```bash
source venv/bin/activate
```

### 3. Instalar runtime de ANTLR en el venv

```bash
pip install antlr4-python3-runtime==4.13.1
```

### 4. Ejecutar la calculadora
```bash
python calc.py t.expr
```

### 5. Salir del venv
```bash
deactivate
```

---

## Notas

- El venv guarda las librerías dentro de la carpeta `venv/` y no toca el Python global.  
- Si se intenta ejecutar `calc.py` sin activar el venv, dará error de `ModuleNotFoundError: No module named 'antlr4'`.  
- Para cada nueva sesión, toca volver a activar el venv con:
  ```bash
  source venv/bin/activate
  ```
