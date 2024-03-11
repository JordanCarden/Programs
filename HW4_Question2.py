import numpy as np
import math
from typing import Callable


def bisection_root_solver(f: Callable[[float], float],
                          a: float,
                          b: float,
                          eps: float,
                          max_iter: int = 1000) -> float:
    """
    Determines the root of a function f(x) between a and b
    :param f: function to evaluate
    :param a: left side of the interval
    :param b: right side of the interval
    :param eps: maximum allowable error
    :param max_iter: maximum number of iterations to run before forcing stop
    :return:
    """
    # We need to first handle some edge cases
    # Let's handle the case that there are no roots in the specified interval
    if np.sign(f(a)) * np.sign(f(b)) > 0:
        print("Warning: This function has no roots on the specified interval!")
        return None
    # Handle the case where f(a) is equal to f(b). Could be two roots or a flat function
    if f(a) == f(b):
        print("Warning: roots could not be determined! f(a)=f(b)")
        return None
    # Now let's handle the case where the specified value for "a" is a root
    if f(a) == 0:
        return a
    # Now let's handle the case where the specified value for "b" is a root
    if f(b) == 0:
        return b

    # this will keep track of how many iterations we have done so far
    num_iter = 0
    # force the loop to stop when we have reached the max number of iterations
    while num_iter < max_iter:
        num_iter += 1
        c = (a + b) / 2
        # check if we have reached error
        if np.abs(b - c) <= eps:
            print("Found bisection root in {0} iterations".format(num_iter))
            return c
        if np.sign(f(b)) * np.sign(f(c)) <= 0:
            a = c
        else:
            b = c
    print("Warning: Root solver has stopped at max_iter={0} iterations".format(max_iter))
    return c

def newtons_root_solver(f: Callable[[float], float],
                        df: Callable[[float], float],
                        x0: float,
                        eps: float,
                        max_iter: int = 1000) -> float:
    """
    Determines the root of a function f(x) using Newton's method
    :param f: function to evaluate
    :param f: derivative of the function to evaluate
    :param x0: initial guess of root
    :param eps: maximum allowable error
    :param max_iter: maximum number of iterations to run before forcing stop
    :return:
    """
    # Let's handle the case where the initial guess is a root
    if f(x0) == 0:
        return x0

    # this will keep track of how many iterations we have done so far
    num_iter = 0
    # this will contain the root guess at each iteration
    xn = x0
    # force the loop to stop when we have reached the max number of iterations
    while num_iter < max_iter:
        num_iter += 1
        # determine the root of the 1st order Taylor series centered at the previous root
        xn1 = xn - f(xn) / df(xn)
        # check if we have reached error
        if np.abs(xn1 - xn) <= eps:
            print("Found newtons root in {0} iterations".format(num_iter))
            return xn1
        xn = xn1
    print("Warning: Root solver has stopped at max_iter={0} iterations".format(max_iter))
    return xn

def secant_root_solver(f: Callable[[float], float],
                       x0: float,
                       x1: float,
                       eps: float,
                       max_iter: int = 1000) -> float:
    """
    Determines the root of a function f(x) using the Secant method
    :param f: function to evaluate
    :param x0: first guess of root
    :param x1: second guess of root
    :param eps: maximum allowable error
    :param max_iter: maximum number of iterations to run before forcing stop
    :return:
    """
    # Let's handle the case where one of the initial guesses is a root
    if f(x0) == 0:
        return x0
    if f(x1) == 0:
        return x1

    # this will keep track of how many iterations we have done so far
    num_iter = 0
    # this will contain the root guess of the previous iteration (x_(n-1))
    x_nm1 = x0
    # this will contain the root guess of the current iteration (x_n)
    x_n = x1
    # force the loop to stop when we have reached the max number of iterations
    while num_iter < max_iter:
        num_iter += 1
        # check stopping condition
        if np.abs(x_n - x_nm1) <= eps:
            print("Found secant root in {0} iterations".format(num_iter))
            return x_n

        # determine the root of the secant line
        x_np1 = x_nm1 - f(x_nm1) * (x_n - x_nm1) / (f(x_n) - f(x_nm1))

        # reassign values for next iteration
        x_nm1 = x_n
        x_n = x_np1
        pass

    print("Warning: Root solver has stopped at max_iter={0} iterations".format(max_iter))
    return x_n


def my_function(x):
    """
    Define a function that evaluates x^2 - x
    :param x: input value
    :return: function evaluation
    """
    return math.exp(x) - pow(x, 3) + pow(x,2) - x

def my_derivative(x):
    return math.exp(x) - 3*pow(x, 2) + 2*x - 1


broot = bisection_root_solver(my_function, 2.25, 3, pow(10,-15))
nroot = newtons_root_solver(my_function, my_derivative, 2.25, pow(10,-15))
sroot = secant_root_solver(my_function, 2.1, 2.9, pow(10,-15))
print("bisection root =", broot)
print("newtons root =", nroot)
print("secant root =", sroot)
