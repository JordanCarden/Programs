#Jordan Carden

import math
import numpy as np
from matplotlib import pyplot as plt

# Define the coefficients
coefficients = [1] + [1 / math.factorial(i) for i in range(1, 13)]

# Define a function to calculate nested polynomials
def p_nested(x, n, coeffs):
    # Initialize the polynomial with the coefficient at index n
    poly = coeffs[n]
    # Iterate over the coefficients in reverse order to calculate the nested polynomial
    for k in reversed(range(n)):
        poly = coeffs[k] + x * poly
    return poly

# Generate x values
x = np.linspace(-10, 10, 1000)

# Plot polynomials of degrees 1 through 12
for order in range(1, 13):
    pfunc = p_nested(x, order, coefficients)
    plt.plot(x, pfunc, label=f'Order {order}')

# Set limits for the x and y axes
plt.xlim((-2, 2))
plt.ylim((-1, 8))

# Set title
plt.title("Jordan Carden")

# Display the plot
plt.legend()
plt.savefig('jordan_carden.png')
plt.show()

