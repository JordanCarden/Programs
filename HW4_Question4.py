# HW4_Question4.py

import numpy as np
import matplotlib.pyplot as plt
from typing import List, Union

def lagrange_interp(x: np.ndarray,
                    x_nodes: Union[List, np.ndarray],
                    y_nodes: Union[List, np.ndarray]) -> np.ndarray:
    """
    Lagrange interpolation
    :param x: values on which to evaluate the polynomial
    :param x_nodes: x coordinates of interpolation points
    :param y_nodes: y coordinates of interpolation points
    :return: evaluated polynomial
    """
    n = len(x_nodes)
    result = np.zeros_like(x, dtype=float)

    for i in range(n):
        term = y_nodes[i]
        for j in range(n):
            if i != j:
                term = term * (x - x_nodes[j]) / (x_nodes[i] - x_nodes[j])
        result += term

    return result

# Given points for square root function
x_nodes = [0, 1, 2, 4, 6]
y_nodes = [0, 1, 1.4142135624, 2, 2.449489742783]

# Generate 1000 values between 0 and 6 for plotting
x_values = np.linspace(0, 6, 1000)

# Calculate Lagrange interpolation for the given points
y_interp = lagrange_interp(x_values, x_nodes, y_nodes)

# Plot the Lagrange interpolation and the actual square root function
plt.plot(x_values, y_interp, label='Lagrange Interpolation')
plt.plot(x_values, np.sqrt(x_values), label='Actual √x')
plt.scatter(x_nodes, y_nodes, color='red', label='Interpolation Points')
plt.title('Lagrange Interpolation for √x')
plt.xlabel('x')
plt.ylabel('y')
plt.legend()
plt.grid(True)
plt.savefig('HW4_Question4a_plot.png')
plt.show()

# Calculate the absolute error between the Lagrange interpolation and actual square root function
error = np.abs(y_interp - np.sqrt(x_values))

# Plot the error
plt.plot(x_values, error, label='Absolute Error')
plt.title('Error in Lagrange Interpolation for √x')
plt.xlabel('x')
plt.ylabel('Absolute Error')
plt.legend()
plt.grid(True)
plt.savefig('HW4_Question4b_plot.png')
plt.show()