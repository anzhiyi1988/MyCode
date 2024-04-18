import pandas as pd

data = pd.DataFrame({
    'cl1': ['G1', 'G1', 'G1', 'G2', 'G2', 'G2', 'G3', 'G3', 'G3'],
    'cl2': ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'],
    'cl3': [1, 2, 3, 4, 5, 6, 7, 8, 9]
})

data1 = pd.DataFrame({
    'cl2': ['Aa', 'Bb', 'Cc', 'Dd', 'Ew'],
    'cl3': [1, 2, 3, 4, 5]
})

print(data)
print(data1)

print((data['cl1'].isin(['G1', 'G2'])))
