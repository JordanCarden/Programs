def newtons_division(a: float, b: float, x0: float, num_iter: int = 10) -> float:
    """
    Calculates a/b using the Newton Raphson Method
    :param a: the value of a
    :param b: the value of b
    :param x0: initial guess of root (1/b)
    :param num_iter: number of iterations to run
    :return: approximation of a/b
    """
    for _ in range(num_iter):
        x0 = x0 - (x0 * b - a) / b
    return x0

resultA = newtons_division(3, 8, 0.24, 6)
resultB = newtons_division(1, 2, 0.8, 6)
resultC = newtons_division(2, 3, 0.5, 6)
print(f"Approximation using Newton's method: {resultA}")
print(f"Approximation using Newton's method: {resultB}")
print(f"Approximation using Newton's method: {resultC}")